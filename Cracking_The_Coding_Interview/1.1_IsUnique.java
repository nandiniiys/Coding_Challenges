/* Notes:
	-- Questions to ask interviewer:
		1. Can we safely assume that the string is an ASCII string.
			- since a UNICODE string would need more space allocation.
		2. Are we allowed to modify the string?
			- this allows attempt 3.
*/

import java.util.HashSet;
import java.util.Arrays;

class IsUnique {
	// attempt one: with additional data structures.
	// time complexity: O(n) where n is the length of the string.
	public static boolean withDS(String s) {
		HashSet<Character> uniqueS = new HashSet<Character>();
		char[] sArray = s.toCharArray();	// time complexity O(n)
		for (char c : sArray) {				// time complexity O(n)
			uniqueS.add(c);					// time complexity O(1)
		}
		if (s.length() > uniqueS.size()) {	// time complexity O(1)
			return false;
		}
		return true;
	}

	// attempt two: without additional data structures.
	// time complexity: O(n^2) where n is the length of the string.
	public static boolean withoutDS(String s) {
		for (int i = 0; i < s.length(); i++) {	// time complexity O(n)
			char curr = s.charAt(i);			// time complexity O(1)
			if (s.lastIndexOf(curr) != i) {		// time complexity O(n)
				return false;
			}
		}
		return true;
	}

	// attempt three: without additional data structures version 2.
	// implementing book solution.
	// time complexity: O(n log n) where n is the length of the string.
	public static boolean withoutDSV2(String s) {
		char[] schars = s.toCharArray();			// time complexity O(n)
		Arrays.sort(schars);						// time complexity O(n log n)
	    for (int i = 0; i < schars.length-1; i++) {	// time complexity O(n)
	    	if (schars[i] == schars[i + 1]) {
	    		return false;
	    	}
	    }
	    return true;
	}

	public static void main(String[] args) {
		long start;
		long end;

		String unique1 = "abcde";
		String unique2 = "12345";
		String unique3 = "!@#$%";
		String notUnique1 = "abcbss";
		String notUnique2 = "1232351";
		String notUnique3 = "!@#$$#@!";

		start = System.nanoTime();
		System.out.println("Attempt 1: should return true");
		System.out.println(withDS(unique1));
		System.out.println(withDS(unique2));
		System.out.println(withDS(unique3));
		System.out.println("Attempt 1: should return false");
		System.out.println(withDS(notUnique1));
		System.out.println(withDS(notUnique2));
		System.out.println(withDS(notUnique3));
		end = System.nanoTime();
		System.out.println("Time taken for execution: " + (end - start));

		System.out.println();

		start = System.nanoTime();
		System.out.println("Attempt 2: should return true");
		System.out.println(withoutDS(unique1));
		System.out.println(withoutDS(unique2));
		System.out.println(withoutDS(unique3));
		System.out.println("Attempt 2: should return false");
		System.out.println(withoutDS(notUnique1));
		System.out.println(withoutDS(notUnique2));
		System.out.println(withoutDS(notUnique3));
		end = System.nanoTime();
		System.out.println("Time taken for execution: " + (end - start));


		System.out.println();

		start = System.nanoTime();
		System.out.println("Attempt 3: should return true");
		System.out.println(withoutDSV2(unique1));
		System.out.println(withoutDSV2(unique2));
		System.out.println(withoutDSV2(unique3));
		System.out.println("Attempt 3: should return false");
		System.out.println(withoutDSV2(notUnique1));
		System.out.println(withoutDSV2(notUnique2));
		System.out.println(withoutDSV2(notUnique3));
		end = System.nanoTime();
		System.out.println("Time taken for execution: " + (end - start));
	}
}
