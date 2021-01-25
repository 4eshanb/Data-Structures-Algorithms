import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;

public class Palindrome {

	static WordDictionary dictionary = new WordDictionary();

	// Get all words that can be formed starting at this index
	public static String[] getWords(String text, int index) {
		ArrayList<String> words = new ArrayList<String>();
		for (int i = 0; i <= text.length() - index; i++) {
			String maybeWord = text.substring(index, index + i);
			if (dictionary.isWord(text.substring(index, index + i))) {
				words.add(maybeWord);
			}
		}

		return words.toArray(new String[0]);
	}

	public static String stackToReverseString(MyStack stack) {
		/*
		 * TODO 3
		 */
		String reverse = "";
		MyStack tempStack = new MyStack();
		while (!stack.isEmpty()) {
			tempStack.push(stack.pop());
		}

		while (!tempStack.isEmpty()) {
			Object item = tempStack.pop();
			reverse += " " + item.toString();
			stack.push(item);
		}
		return reverse;
		/*
		 * TODO 3
		 */
	}

	public static void testStackToReverseString() {
		MyStack testStack = new MyStack();
		testStack.push("able");
		testStack.push("was");
		testStack.push("I");
		System.out.println(testStack);
		String reverseString = stackToReverseString(testStack);
		System.out.println("ReverseString" + reverseString);
		if (reverseString.equals(" able was I")) {
			System.out.println("It worked");
		} else {
			System.out.println("It failed");
			System.exit(-1);
		}

	}

	public static String reverseStringAndRemoveNonAlpha(String text) {
		/*
		 * TODO 4
		 */
		String reverse = "";
		MyStack stack = new MyStack();
		for (int i = 0; i < text.length(); i++) {
			char stringChar = text.charAt(i);
			if (Character.isAlphabetic(stringChar)) {
				stack.push(new Character(stringChar));
			}
		}
		while (!stack.isEmpty()) {
			Character ch = (Character) stack.pop();
			reverse += ch.toString();
		}

		return reverse;
		/*
		 * TODO 4
		 */
	}

	// Returns true if the text is a palindrome, false if not.
	public static boolean isPalindrome(String text) {
		/*
		 * TODO 5: Implement "explorePalindrome"
		 */
		String str = text.toLowerCase();
		MyStack stack = new MyStack();
		MyQueue queue = new MyQueue();
		// String reverse = "";
		// String reverseStack = reverseStringAndRemoveNonAlpha(str);
		for (int i = 0; i < str.length(); i++) {
			char stringChar = str.charAt(i);
			if (Character.isAlphabetic(stringChar)) {
				Character ch = new Character(stringChar);
				queue.enqueue(ch);
				stack.push(ch);
			}
		}
		boolean isPalin = false;
		while (!queue.isEmpty() || !stack.isEmpty()) {
			Character ch = (Character) queue.dequeue();
			Character ch1 = (Character) stack.pop();
			if (ch1.equals(ch)) {
				isPalin = true;
			} else
				isPalin = false;
		}
		return isPalin;
		/*
		 * TODO 5: Implement "explorePalindrome"
		 */
	}

	public static void decomposeText(String originalText, String textToDecompose, 
			int index, MyStack decomposition, String inden) {
		System.out.printf("Entered:%s %s %s %d \n", inden, originalText,textToDecompose, index);
		if(index == textToDecompose.length()) {
			System.out.println(originalText);
			System.out.println(stackToReverseString(decomposition));
		}
		else {
			String[] words = getWords(textToDecompose,index);
			for(String word : words) {
				System.out.printf("BB %s %s\n", inden, word);
				decomposition.push(word);
				decomposeText(originalText, textToDecompose, word.length()-1, decomposition, inden.concat("  "));
				decomposition.pop();
			}
		}
		
	}
	public static void explorePalindrome(String text) {

		/*
		 * TODO 6: Implement "explorePalindrome" & helper function
		 */
		String str = text.toLowerCase();
		String revString = reverseStringAndRemoveNonAlpha(str);
		MyStack stack = new MyStack();
		decomposeText(str,revString,0,stack," ");
		
		/*
		 * TODO 6
		 */

	}

	// This function looks at the arguments that are passed
	// and decides whether to test palindromes or expand them
	public static void main(String[] args) throws IOException {

		// testStackToReverseString();
		// System.out.println(reverseStringAndRemoveNonAlpha("ta, co hell"));
//		System.out.println(isPalindrome("ABBA"));
		//System.out.println(isPalindrome("I'm alas, a salami"));
		//System.out.println(isPalindrome("i'm alas, a salami"));
	explorePalindrome("stole no desserts");
//		explorePalindrome("an era live");

		if (args.length == 0) {
			System.out.println("ERROR: Remember to set the mode with an argument: 'test' or 'expand'");
		} else {
			String mode = args[0];

			// Default palindromes to use if none are provided
			String[] testPalindromes = { "A", "ABBA", "oh no an oboe", "salami?", "I'm alas, a salami" };
			if (args.length > 1)
				testPalindromes = Arrays.copyOfRange(args, 1, args.length);

			// Test whether the provided strings are palindromes
			if (mode.equals("test")) {

				for (int i = 0; i < testPalindromes.length; i++) {
					String text = testPalindromes[i];
					boolean result = isPalindrome(text);
					System.out.println("'" + text + "': " + result);
				}

			} else if (mode.equals("expand")) {
				for (int i = 0; i < testPalindromes.length; i++) {

					explorePalindrome(testPalindromes[i]);
				}
			} else {
				System.out.println("unknown mode: " + mode);
			}
		}
	}
}