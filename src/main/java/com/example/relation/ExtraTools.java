package com.example.relation;

public class ExtraTools {
	
	public static String toSlug(String input) {
		String slugged = input.replaceAll(" ", "-").toLowerCase();
		System.out.println(slugged);
		return slugged;
	}

}
