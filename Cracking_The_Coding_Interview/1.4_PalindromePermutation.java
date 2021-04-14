/* Notes:
    -- Tips:
    	- Ignore casing and non-letter characters.
	-- Questions to ask interviewer:
*/

import java.util.HashMap;
import java.util.Map;

class PP {
	// attempt one: using a hashmap
	// time complexity:
	// Hint: Think of the characteristics of palindrome strings.
	//		- even length string, all chars even count.
	//		- odd length string, all chars except one even count.
	public static boolean PalindromePerm(String s) {
		int slen = s.length();			// used to disregard whitespaces.
		String str = s.toLowerCase();	// to ignore casing.

		// update hashmap with chars in the string and their counts
		HashMap<Character, Integer> dict = new HashMap<Character, Integer>();
		for(int i = 0; i < s.length(); i++) {
			char key = str.charAt(i);
			if (key != ' ') {
				int value = dict.containsKey(key) ? dict.get(key) + 1 : 1;
				dict.put(key, value);
			}
			else {
				slen--;
			}
		}

		boolean pal = true;
		if (slen % 2 == 0) {
			for(Map.Entry<Character, Integer> entry : dict.entrySet()) {
				if (entry.getValue() % 2 != 0) {
					return false;
				}
			}
		}
		else {
			for(Map.Entry<Character, Integer> entry : dict.entrySet()) {
				if (entry.getValue() % 2 != 0) {
					if (!pal) {
						return false;
					}
					pal = false;
				}
			}
		}
		return true;
	}

	public static void main(String[] args) {
		System.out.println("Attempt 1: Using a hashmap");
		System.out.println("Should be True: " + PalindromePerm("tacocat"));
		System.out.println("Should be True: " + PalindromePerm("taco cat"));
		System.out.println("Should be True: " + PalindromePerm("Taco cAt"));
		System.out.println("Should be True: " + PalindromePerm("Tact Coa"));
		System.out.println("Should be True: " + PalindromePerm("tacto ac"));
		System.out.println("Should be False: " + PalindromePerm("abc sfe"));
	}

}
