/* Notes:
	-- Tips to remember;
		1. With Strings, think sorting.
		2. Popular solution is to use alphabet array of 128 chars for ASCII as flag.
	-- Questions to ask interviewer:
		2. Can we modify the string?
			- To allow deleting and sorting.
		3. Is the comparison case sensitive?
		4. Is whitespace to be ignored or considered?
		5. ASCII characters?
*/

import java.lang.StringBuilder;
import java.util.Arrays;

class Permutations {
	// attempt one: using a string builder.
	// time complexity: O(n^2) where n is the length of the second parameter string.
	// assume case sensitive comparison and regard for whitespace
	public static boolean IsPermutation01(String s1, String s2) {
		if (s1.length() != s2.length()) {
			return false;
		}
		String s3 = s1;
		for (char c : s2.toCharArray()) {				// time complexity O(n)
			StringBuilder sb = new StringBuilder(s3);	
			if (s3.contains("" + c)) {					// time complexity O(n)
				sb.deleteCharAt(sb.indexOf("" + c));	// time complexity O(1 * n)
				s3 = sb.toString();						// time complexity O(n)
			}
		}
		if (s3.length() != 0) {
			return false;
		}
		return true;
	}

	// attempt two: using sorting.
	// time complexity: O(n log n) where n is the length of the strings
	// assume case sensitive comparison and regard for whitespace
	public static boolean IsPermutation02(String s1, String s2) {
		if (s1.length() != s2.length()) {
			return false;
		}
		char[] s1chars = s1.toCharArray();			// time complexity O(n)
		Arrays.sort(s1chars);						// time complexity O(n log n)
		char[] s2chars = s2.toCharArray();			// time complexity O(n)
		Arrays.sort(s2chars);						// time complexity O(n log n)
		for (int i = 0; i < s1chars.length; i++) {	// time complexity O(n)
			if (s1chars[i] != (s2chars[i])) {
				return false;
			}
		}
		return true;
	}

	// attempt three: attempting textbook solution.
	// time complexity: O(n) where n is the length of the longer? string.
	// assume case sensitive comparison and regard for whitespace
	public static boolean IsPermutation03(String s1, String s2) {
		if (s1.length() != s2.length()) {
			return false;
		}
		int[] alphabet = new int[128];
		for (int i = 0; i < s1.length(); i++) {		// time complexity O(n)
			alphabet[s1.charAt(i)]++;				// time complexity O(1)
		}
		for (int j = 0; j < s2.length() ; j++) {	// time complexity O(n)
			alphabet[s2.charAt(j)]--;				// time complexity O(1)
			if (alphabet[s2.charAt(j)] < 0) {		// time complexity O(1)
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		System.out.println("Attempt 1: Using a String builder");
		System.out.println(IsPermutation01("abcd", "dcba"));
		System.out.println(IsPermutation01("1234", "4321"));
		System.out.println(IsPermutation01("abcd", "zcba"));
		System.out.println(IsPermutation01("abd", "dcba"));
		System.out.println(IsPermutation01("!@#$", "@#$%"));
		System.out.println(IsPermutation01("God    ", "dog"));
		System.out.println(IsPermutation01("god", "dog"));
		System.out.println();

		System.out.println("Attempt 2: Using a Sorting");
		System.out.println(IsPermutation02("abcd", "dcba"));
		System.out.println(IsPermutation02("1234", "4321"));
		System.out.println(IsPermutation02("abcd", "zcba"));
		System.out.println(IsPermutation02("abd", "dcba"));
		System.out.println(IsPermutation02("!@#$", "@#$%"));
		System.out.println(IsPermutation02("God    ", "dog"));
		System.out.println(IsPermutation02("god", "dog"));
		System.out.println();

		System.out.println("Attempt 3: Attempting textbook solution");
		System.out.println(IsPermutation03("abcd", "dcba"));
		System.out.println(IsPermutation03("1234", "4321"));
		System.out.println(IsPermutation03("abcd", "zcba"));
		System.out.println(IsPermutation03("abd", "dcba"));
		System.out.println(IsPermutation03("!@#$", "@#$%"));
		System.out.println(IsPermutation03("God    ", "dog"));
		System.out.println(IsPermutation03("god", "dog"));
		System.out.println();

	}
}