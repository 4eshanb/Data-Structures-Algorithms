import java.io.*;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SearchAndSort {

	// Utility function: split a string into words,
	// making them all lowercase and ignoring all non-text characters
	public static String[] splitIntoWords(String str) {
		// Handle apostrophes: "lula's", "'top o' the mornin''"
		// Ignore any non-alphabetical characters ("1942", "1920s")
		str = str.toLowerCase();
		str = str.replaceAll("'", "").replaceAll("\\s+", " ").replaceAll("[^a-zA-Z ]", " ");

		// Split on any amount of spaces
		String[] words = str.split("\\s+");
		return words;
	}

	// Load all of the words in this filename
	public static String[] createWordList(String filename) {
		try {
			String text = new String(Files.readAllBytes(Paths.get(filename)));
			return splitIntoWords(text);

		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return new String[0];
	}

	/*
	 * ========================================================================
	 * START TODO #1: "countWordsInUnsorted"
	 */
	public static int countWordsInUnsorted(String[] word, String query) {
		int count = 0;
		for (int i = 0; i < word.length; i++) {
			if (word[i].equals(query))
				count++;
		}
		return count;
	}
	/*
	 * END TODO #1: "countWordsInUnsorted"
	 * ========================================================================
	 */

	/*
	 * ========================================================================
	 * START TODO #2: "mergeSort"
	 */
	public static void merge(String[] arrayToSort, String[] tempArray, int first, int last, int mid) {
		int leftIndex = first;
		int rightIndex = mid + 1;
		int index = first;

		while (leftIndex <= mid && rightIndex <= last) {
			if (arrayToSort[leftIndex].compareTo(arrayToSort[rightIndex]) < 0) {
				tempArray[index] = arrayToSort[leftIndex];
				leftIndex++;
			} else {
				tempArray[index] = arrayToSort[rightIndex];
				rightIndex++;
			}
			index++;
		}
		while (leftIndex <= mid) {
			tempArray[index] = arrayToSort[leftIndex];
			leftIndex++;
			index++;
		}
		while (rightIndex <= last) {
			tempArray[index] = arrayToSort[rightIndex];
			rightIndex++;
			index++;
		}
		for (int i = 0; i <= last; i++) {
			arrayToSort[i] = tempArray[i];
		}
	}

	// public static void mergeSort(String[] arrayToSort, String[] tempArray, int
	// first, int last, String indent) {
	public static void mergeSort(String[] arrayToSort, String[] tempArray, int first, int last) {
		int mid = (first + last) / 2;
		if (first < last) {
			// System.out.printf("%s D: start=%d, mid = %d, end=%d\n", indent, first, mid,
			// last);
			mergeSort(arrayToSort, tempArray, first, mid);
			mergeSort(arrayToSort, tempArray, mid + 1, last);
			merge(arrayToSort, tempArray, first, last, mid);
		}

	}

	/*
	 * END TODO #2: "mergeSort"
	 * ========================================================================
	 */

	/*
	 * ========================================================================
	 * START TODO #3: binary search
	 */
	public static int binarySearch(String[] sortedWords, String query, int startIndex, int endIndex) {
		// if (startIndex > endIndex) {
		// return -1;
		// }
		int mid = (startIndex + endIndex) / 2;
		if (sortedWords[mid].compareTo(query) == 0) {
			return mid;
		} else if (sortedWords[mid].compareTo(query) < 0) {
			binarySearch(sortedWords, query, startIndex, mid - 1);
		} else
			binarySearch(sortedWords, query, mid + 1, endIndex);

		for (int i = 0; i < sortedWords.length; i++) {
			if (sortedWords[i] == query) {
				return i;
			}
		}
		return -1;

	}

	public static int getSmallestIndex(String[] words, String query, int startIndex, int endIndex) {
		if (binarySearch(words, query, startIndex, endIndex) == -1) {
			return -1;
		}

		// if (binarySearch(words, query, startIndex, endIndex)) {
		// }
		return endIndex;

	}

	/*
	 * END TODO #3: binary search
	 * ========================================================================
	 */

	public static void main(String[] args) {

		// Create a word list from Frankenstein
		String[] allWords = createWordList("frankenstein.txt");

		// Save the arguments

		String[] queryWords = { "doctor", "frankenstein", "the", "monster", "igor", "student", "college", "lightning",
				"electricity", "blood", "soul" };
		int timingCount = 100;

		if (args.length > 0) {
			// There is an argument, so some different words to search for and count were
			// passed in.
			queryWords = args[0].split(",");
		}

		System.out.println("\nSEARCH AND SORT");
		System.out.println("\nSearching and counting the words " + String.join(",", queryWords));

		System.out.println("\nNAIVE SEARCH:");

		// Record the current time
		long t0 = (new Date()).getTime();

		// Time how long it takes to run timingCount loops
		// for countWordsInUnsorted
		for (int j = 0; j < timingCount; j++) {
			// Search for and count the words timingCount times in order to get an average
			// time

			for (int i = 0; i < queryWords.length; i++) {
				//

				/*
				 * ========================================================================
				 * START: TODO #1
				 */

				int count = countWordsInUnsorted(allWords, queryWords[i]); // Replace the 0 in this
																			// line of code with
				// the call to countWordsInUnsorted once
				// you've written it
				/*
				 * END: TODO #1
				 * ========================================================================
				 */

				// For the first time the words are counted, print out the value
				if (j == 0)
					System.out.println(queryWords[i] + ":" + count);

			}
		}

		// Record the current time
		long t1 = (new Date()).getTime();

		long timeToSeachNaive = t1 - t0;
		int searchCount = timingCount * queryWords.length;

		// Output how long the searches took, for how many searches
		// (remember: searches = timingcount * the number of words searched)
		System.out.printf("%d ms for %d searches, %f ms per search\n", timeToSeachNaive, searchCount,
				timeToSeachNaive * 1.0f / searchCount);

		// Sort the list of words
		System.out.println("\nSORTING: ");

		/*
		 * ========================================================================
		 * START: TODO #2
		 */
		// Put your call to mergeSort here to sort allWords.
		String[] temp = new String[allWords.length];
		mergeSort(allWords, temp, 0, allWords.length - 1);

		/*
		 * END: TODO #2
		 * ========================================================================
		 */

		// Record the current time
		long t2 = (new Date()).getTime();

		// Output how long the sorting took
		long timeToSort = t2 - t1;
		System.out.printf("%d ms to sort %d words\n", timeToSort, allWords.length);

		// Output every 1000th word of your sorted wordlist
		int step = (int) (allWords.length * .00663 + 1);
		System.out.print("\nSORTED (every " + step + " word): ");
		for (int i = 0; i < allWords.length; i++) {
			if (i % step == 0)
				System.out.print(allWords[i] + " ");
		}
		System.out.println("\n");

		System.out.println("BINARY SEARCH:");

		// Run timingCount loops for countWordsInSorted
		// for the first loop, output the count for each word

		for (int j = 0; j < timingCount; j++) {
			for (int i = 0; i < queryWords.length; i++) {

				/*
				 * ========================================================================
				 * START: TODO #3
				 */

				/*
				 * Replace the line of code below with the code to: 1. call getSmallestIndex to
				 * find the smallest index at which a word occurs. 2. call getLargestIndex to
				 * find the largest index at which a word occurs. 3. use these two indices to
				 * figure out how many times the word occurred.
				 */
				int count = 0;

				/*
				 * END: TODO #3
				 * ========================================================================
				 */

				// For the first one, print out the value
				if (j == 0)
					System.out.println(queryWords[i] + ":" + count);
			}
		}

		// Output how long the searches took, for how many searches
		// (remember: searchCount = timingcount * the number of words searched. This is
		// computed above.)

		// Record the current time
		long t3 = (new Date()).getTime();

		long timeToSeachBinary = t3 - t2;
		System.out.printf("%d ms for %d searches, %f ms per search\n", timeToSeachBinary, searchCount,
				timeToSeachBinary * 1.0f / searchCount);
	}

}