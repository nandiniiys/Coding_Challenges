/* Notes:
	-- Tips to remember;

	-- Questions to ask interviewer:
		- Are we to assume that all the characters being added are alphabetical or are all ASCII i.e a-z or A-Z
		- Are there any assumptions we can make about the cases of the strings i.e upper or lower?
		- Does a difference in case also contribute to a string transformation? i.e Abc -- abc = 1 transformation of replace?

	-- Difference in the attempts: 

*/

import java.util.*;

class OneAWay {
// ---------------------------------------------------------------------------------------------------------------------
												// ATTEMPT ONE
//	---------------------------------------------------------------------------------------------------------------------
	// attempt one: brute force attempt using iteration assuming all alphabetical chars and no case sensitivity - no data structures
	// time complexity: O(n^2) + O(n^2) + O(n) = O(n^2)
	public static boolean attempt1(String orig, String modi) {
		return (insertedOne(orig, modi) || removedOne(orig, modi) || replacedOne(orig, modi));
	}

	// time complexity: O(n * 26 * n) = O(n^2)
	public static boolean insertedOne(String orig, String modi) {
		// check if one char inserted
		String check = "";
		if ((modi.length() - orig.length()) > 1) {
			return false;
		}

		for (int i = 0; i <= orig.length(); i++) {
			for (int c = 97; c < 123; c++) {
				check = orig.toLowerCase().substring(0, i) + (char) c + orig.toLowerCase().substring(i, orig.length());
				if (check.equals(modi.toLowerCase())) {
					return true;
				}
			}
		}
		return false;
	}

	// time complexity: O(n^2)
	public static boolean removedOne(String orig, String modi) {
		// check if one char removed
		if ((orig.length() - modi.length()) > 1) {
			return false;
		}
		String check = orig.toLowerCase().substring(1, orig.length());
		for (int i = 0; i < orig.length(); i++) {
			check = orig.toLowerCase().substring(0, i) + orig.toLowerCase().substring(i + 1, orig.length());
			if (check.equals(modi.toLowerCase())) {
				return true;
			}
			check = "";
		}
		return false;
	}

	// time complexity: O(n)
	public static boolean replacedOne(String orig, String modi) {
		// check if one char replaced
		int edits = 0;
		if (orig.length() != (modi.length())) {
			return false;
		}
		for (int i = 0; i < orig.length(); i++) {
			if (orig.toLowerCase().charAt(i) != modi.toLowerCase().charAt(i)) {
				edits++;
				if (edits > 1) {
					return false;
				}
			}
		}
		return true;
	}

// ---------------------------------------------------------------------------------------------------------------------
												// ATTEMPT TWO
//	---------------------------------------------------------------------------------------------------------------------
	// attempt two: improving time complexity - trying textbook solution implementation
	// assumes all input is in one case and that no character checks are needed, all chars in string are valid input.
	// time complexity:
	public static boolean attempt2(String orig, String modi) {
		if (orig.equals(modi)) {
			return true;
		}
		return (insertedOne2(orig, modi) || removedOne2(orig, modi) || replacedOne2(orig, modi));
	}

	// time complexity: O(n)
	public static boolean insertedOne2(String orig, String modi) {
		// check if one char inserted
		String check = "";
		if ((modi.length() - orig.length()) > 1) {
			return false;
		}

		// once you encounter a diff between the 2 strings, ignoring that char, the remaining chars are equal.
		int i1 = 0;
		int i2 = 0;
		boolean diffFound = false;

		while (i1 < orig.length() && i2 < modi.length()) {
			if (orig.charAt(i1) != modi.charAt(i2)) {
				if (diffFound) {
					break;
				}
				diffFound = true;
				i2++;
			}
			else {
				i1++;
				i2++;
			}
		}

		if (i2 == modi.length() - 1) {return true;}
		return (orig.substring(i1, orig.length())).equals(modi.substring(i2, modi.length()));

	}

	// time complexity: O(n)
	public static boolean removedOne2(String orig, String modi) {
		// opposite of insert
		return insertedOne2(modi, orig);
	}

	// time complexity: O(n)
	// using attempt one impl and changing edits to a boolean
	//  - because if edits is anything but 1, return false.
	public static boolean replacedOne2(String orig, String modi) {
		// check if one char replaced
		boolean edits = false;
		if (orig.length() != (modi.length())) {
			return false;
		}
		for (int i = 0; i < orig.length(); i++) {
			if (orig.charAt(i) != modi.charAt(i)) {
				if (edits) {
					return false;
				}
				edits = true;
			}
		}
		return true;
	}

// ---------------------------------------------------------------------------------------------------------------------
									// TEST CASES
//	---------------------------------------------------------------------------------------------------------------------

	public static void main(String[] args) {

		System.out.println("Testing insertedOne");
		assert insertedOne("pale", "pales"): " Should be true, returns false";
		assert insertedOne("pale", "spale"): " Should be true, returns false";
		assert insertedOne("pale", "pasle"): " Should be true, returns false";
		assert !insertedOne("pale", "spales"): " Should be false, returns true";
		assert !insertedOne("pale", "ale"): " Should be false, returns true";

		System.out.println("Testing removedOne");
		assert removedOne("pale", "ale"): " Should be true, returns false";
		assert removedOne("pale", "ple"): " Should be true, returns false";
		assert removedOne("pale", "pae"): " Should be true, returns false";
		assert removedOne("pale", "pal"): " Should be true, returns false";
		assert !removedOne("pale", "pl"): " Should be false, returns true";
		assert !removedOne("pale", "pals"): " Should be false, returns true";

		System.out.println("Testing replacedOne");
		assert replacedOne("pale", "bale"): " Should be true, returns false";
		assert replacedOne("pale", "ptle"): " Should be true, returns false";
		assert replacedOne("pale", "paze"): " Should be true, returns false";
		assert replacedOne("pale", "pald"): " Should be true, returns false";
		assert !replacedOne("pale", "bate"): " Should be false, returns true";


		System.out.println("Attempt 1: Using no data structures");
		System.out.println(attempt1("pale", "ple"));
		System.out.println(attempt1("pale", "pales"));
		System.out.println(attempt1("pale", "bale"));
		System.out.println(attempt1("pale", "bake"));
		System.out.println();


		System.out.println("Testing insertedOne2");
		assert insertedOne2("pale", "pales"): " Should be true, returns false";
		assert insertedOne2("pale", "spale"): " Should be true, returns false";
		assert insertedOne2("pale", "pasle"): " Should be true, returns false";
		assert !insertedOne2("pale", "spales"): " Should be false, returns true";
		assert !insertedOne2("pale", "ale"): " Should be false, returns true";

		System.out.println("Testing removedOne2");
		assert removedOne2("pale", "ale"): " Should be true, returns false";
		assert removedOne2("pale", "ple"): " Should be true, returns false";
		assert removedOne2("pale", "pae"): " Should be true, returns false";
		assert removedOne2("pale", "pal"): " Should be true, returns false";
		assert !removedOne2("pale", "pl"): " Should be false, returns true";
		assert !removedOne2("pale", "pals"): " Should be false, returns true";

		System.out.println("Testing replacedOne2");
		assert replacedOne2("pale", "bale"): " Should be true, returns false";
		assert replacedOne2("pale", "ptle"): " Should be true, returns false";
		assert replacedOne2("pale", "paze"): " Should be true, returns false";
		assert replacedOne2("pale", "pald"): " Should be true, returns false";
		assert !replacedOne2("pale", "bate"): " Should be false, returns true";

		System.out.println("Attempt 2: Improving time complexity");
		System.out.println(attempt2("pale", "ple"));
		System.out.println(attempt2("pale", "pales"));
		System.out.println(attempt2("pale", "bale"));
		System.out.println(attempt2("pale", "bake"));
		System.out.println();


	}
}