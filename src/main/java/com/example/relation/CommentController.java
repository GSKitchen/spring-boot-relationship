package com.example.relation;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.relation.exception.ResourceNotFoundException;
import com.example.relation.model.Comment;
import com.example.relation.repository.CommentRepository;
import com.example.relation.repository.PostRepository;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class CommentController {
	
	@Autowired
	CommentRepository commentRepository;

	@Autowired
	PostRepository postRepository;
	
	@GetMapping("/posts/{postId}/comments")
	public Page<Comment> getAllComments(@PathVariable(value="postId") Long postId, Pageable pageable){
		return commentRepository.findByPostId(postId, pageable);
	}
	
	@PostMapping("/posts/{postId}/comments")
	public Comment createComment(@PathVariable Long postId, @Valid @RequestBody Comment comment) {
		return postRepository.findById(postId).map(post ->{
			comment.setPost(post);
			return commentRepository.save(comment);
		}).orElseThrow(() -> new ResourceNotFoundException("Comment for post id = " + postId));
	}
	
	public ResponseEntity<?> deleteComment(@PathVariable Long postId, @PathVariable Long commentId){
		return commentRepository.findByIdAndPostId(commentId, postId).map(comment -> {
			commentRepository.delete(comment);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException(String.format("Comment for {0} id, and post id {1} has not found", commentId, postId)));
	}
	//test git
}
