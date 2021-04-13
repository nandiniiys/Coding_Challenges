import java.util.Arrays;

/* Notes:
	-- Tips to remember;
		- String.valueOf(array) converts char array to string.
		- Java strings are immutable so attempt 1 and 2 must return new copies of strings.
		- Attempt 3 uses char arrays instead so that the replacing can be done in place.
	-- Questions to ask interviewer:
		1. Can we assume the characters are ASCII?
			- to be able to use decimal for ASCII whitespace characters.
		2. What can we assume about the nature of the whitespaces?
			- Space, tab, line feed (newline), carriage return, form feed, and vertical tab
				are all considered whitespace characters, are we to consider all these?
		3. Are we to replace leading and trailing whitespace?
			- This allows attempt 2.

	-- Difference in the attempts: 
		- 1 replaces any leading whitespaces but not trailing whitespaces like attempt 3, 
		  2 replaces neither leading nor trailing whitespaces.
		- 1 and 2 use strings thus return new copies of strings,
		  3 uses char array thus does the replacing in place.
		- 1 and 2 accounts for all whitespace characters,
		  3 only accounts for single spaces. 
*/

class URLify {
	// attempt one: using a char array.
	// time complexity: O(m) where m is the length of the string returned.
	public static String withArray(String s, int n) {
		char[] chars = new char[n * 3];	// worst case: all whitespaces
		int c = 0;
		for (int i = 0; i < n; i++) {					// time complexity O(n) where n is the original length. 
			boolean whitespace = s.charAt(i) == 9 ||
								  s.charAt(i) == 10 ||
								  s.charAt(i) == 11 ||
								  s.charAt(i) == 12 ||
								  s.charAt(i) == 13 ||
								  s.charAt(i) == 32;
			if (whitespace) {							// time complexity O(1)
				chars[c++] = '%';						// time complexity O(1)
				chars[c++] = '2';						// time complexity O(1)
				chars[c++] = '0';						// time complexity O(1)
			}
			else {
				chars[c++] = s.charAt(i);				// time complexity O(1)
			}
		}
		return String.valueOf(chars);					// time complexity O(m) where m is the new length of the string.
	}

	// attempt two: using regex
	// time complexity: O(n) where n is the length of the string.
	public static String withRegex(String s, int n) {
		return s.trim().replaceAll("[ \t\r\n\f]", "%20");	// time complexity O(n)
	}

	// attempt three: attempting textbook solution.
	// time complexity: O(m) where m is the length of the updated char array.
	public static void bookSol(char[] s, int n) {
		int spaces = 0;						// improvement on attempt 1. Instead of assuming worst case, find number of spaces.
		for (int i = 0; i < n; i++) {		// time complexity O(n)
			if (s[i] == ' ') {
				spaces++;
			}
		}
		int newlen = n + (2 * spaces);
		int newind = n - 1 + (2 * spaces);
		if (newind + 1 < s.length) {
			s[newind + 1] = '\0';
		}
		for (int i = n - 1; i >= 0; i++) {	// time complexity O(n)
			if (s[i] == ' ') {
				s[newind] = '0';
				s[newind - 1] = '2';
				s[newind - 2] = '%';
				newind = newind - 3;
			}
			else {
				s[newind] = s[i];
				newind--;
			}
		}

		for (int i = 0; i < newlen; i++) {  // time complexity O(m)
			System.out.print(s[i]);
		}

	}


	public static void main(String[] args) {
		System.out.println("Attempt 1: Using a character array");
		System.out.println(withArray(" Mr John	Smith		", 14));
		System.out.println();

		System.out.println("Attempt 2: Using regex");
		System.out.println(withRegex("Mr John	Smith		", 13));
		System.out.println();

		System.out.println("Attempt 3: Attempting textbook solution");
		char[] test = new char[20];
		Arrays.fill(test, ' ');
		test[0] = 'M';
		test[1] = 'r';
		test[2] = ' ';
		test[3] = 'J';
		test[4] = 'o';
		test[5] = 'e';
		test[6] = ' ';
		//char[] test = ("Mr John Smith").toCharArray();
		//bookSol(test, 6);
		System.out.println("Need to debug");
	}
}