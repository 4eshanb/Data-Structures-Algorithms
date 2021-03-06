import java.io.*;

import java.util.*;
import java.util.Arrays;

import java.nio.file.Files;
import java.nio.file.Paths;

public class RhymingDict {

	// Given a pronunciation, get the rhyme group
	// get the more *heavily emphasized vowel* and follwing syllables
	// For "tomato", this is "-ato", and not "-omato", or "-o"
	// Tomato shares a rhyming group with "potato", but not "grow"
	private static String getRhymeGroup(String line) {

		int firstSpace = line.indexOf(" ");

		String pronunciation = line.substring(firstSpace + 1, line.length());

		int stress0 = pronunciation.indexOf("0");
		int stress1 = pronunciation.indexOf("1");
		int stress2 = pronunciation.indexOf("2");

		if (stress2 >= 0)
			return pronunciation.substring(stress2 - 2, pronunciation.length());
		if (stress1 >= 0)
			return pronunciation.substring(stress1 - 2, pronunciation.length());
		if (stress0 >= 0)
			return pronunciation.substring(stress0 - 2, pronunciation.length());

		// No vowels at all? ("hmmm", "mmm", "shh")
		return pronunciation;
	}

	private static String getWord(String line) {
		int firstSpace = line.indexOf(" ");

		String word = line.substring(0, firstSpace);

		return word;
	}

	// Load the dictionary
	private static String[] loadDictionary() {
		// Load the file and read it

		String[] lines = null; // Array we'll return holding all the lines of the dictionary

		try {
			String path = "cmudict/cmudict-short.dict";
			// Creating an array of strings, one for each line in the file
			lines = new String(Files.readAllBytes(Paths.get(path))).split("\\r?\\n");

		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return lines;
	}

	public static void main(String[] args) {

		String[] dictionaryLines = loadDictionary();

		/*
		 * This code is in here to help you test MyLinkedList without having to mess
		 * around with the dictionary. Feel free to change this test code as you're
		 * testing your linked list. But be sure to comment this code out when you
		 * submit it. MyLinkedList testList = new MyLinkedList(); testList.add(0,
		 * "hello"); testList.add(1, "world"); testList.add(2, "!");
		 * System.out.println(testList); System.out.println("index 2 = " +
		 * testList.get(2)); System.out.println("world at index " +
		 * testList.find("world")); System.out.println("hello at index " +
		 * testList.find("hello")); System.out.println("! at index " +
		 * testList.find("!")); System.out.println("wow at index " +
		 * testList.find("wow")); testList.remove(2); System.out.println(testList);
		 * testList.remove(0); System.out.println(testList); testList.remove(0);
		 * System.out.println(testList); System.out.println("hello at index " +
		 * testList.find("hello"));
		 */

		// System.out.println(testList);
		// System.out.println("index 2 = " + testList.get(2));
		// System.out.println("world at index " + testList.find("world"));
		// System.out.println("hello at index " + testList.find("hello"));
		// System.out.println("! at index " + testList.find("!"));
		// System.out.println("wow at index " + testList.find("wow"));
		// testList.remove(2);
		// System.out.println(testList);
		// testList.remove(0);
		// System.out.println(testList);
		// testList.remove(0);
		// System.out.println(testList);
		// System.out.println("hello at index " + testList.find("hello"));

		// List of rhyme groups. The items in this linked list will be RhymeGroupWords.
		ListInterface rhymeGroups = new MyLinkedList();

		/*
		 * TODO: Add in your code to load the dictionary into your linked lists.
		 * Remember that rhymeGroups is a list of RhymeGroupWords. Inside each of this
		 * objects is another linked list which is a list of words within the same rhyme
		 * group. I would recommend first getting this working with MyLinkedList for
		 * both lists (rhyme groups and word lists) then get it working using
		 * MySortedLinkedList for the word groups.
		 */

		for (int i = 0; i < dictionaryLines.length; i++) {
			String dictionaryLine = dictionaryLines[i];
			String rg = getRhymeGroup(dictionaryLine);
			int index = rhymeGroups.find(rg);
			if (index >= 0) {
				RhymeGroupWords x = (RhymeGroupWords) rhymeGroups.get(index);
				MySortedLinkedList wl = (MySortedLinkedList) x.getWordList();
				MyWord neword = new MyWord(getWord(dictionaryLine));
				wl.add(neword);

			} else {
				MySortedLinkedList l = new MySortedLinkedList();
				MyWord newWord = new MyWord(getWord(dictionaryLine));
				l.add(newWord);
				RhymeGroupWords newRhymeGroup = new RhymeGroupWords(rg, l);
				rhymeGroups.add(rhymeGroups.size(), newRhymeGroup);

			}
		}

		/* End TODO for adding dictionary in rhymeGroups. */

		// This code prints out the rhyme groups that have been loaded above.
		for (int i = 0; i < rhymeGroups.size(); i++) {
			RhymeGroupWords rg = (RhymeGroupWords) rhymeGroups.get(i);
			System.out.print(rg.getRhymeGroup() + ": ");
			System.out.println(rg.getWordList());
		}

		/*
		 * TODO: Add the code here to iterate through pairs of arguments, testing to see
		 * if they are in the same rhyme group or not.
		 */
		String s1 = "";
		String s2 = "";
		for (int i = 0; i < args.length; i++) {
			if (i % 2 == 0) {
				s1 = args[i];
			} else {
				s2 = args[i];
				checkRhyme(s1, s2, rhymeGroups);
				s1 = "";
				s2 = "";
			}
		}

	}

	private static void checkRhyme(String s1, String s2, ListInterface rhymeGroups) {
		boolean didRhyme = false;
		boolean isWord1InDict = false;
		boolean isWord2InDict = false;
		for (int i = 0; i < rhymeGroups.size(); i++) {
			RhymeGroupWords rg = (RhymeGroupWords) rhymeGroups.get(i);
			ListInterface wordList = rg.getWordList();
			int index = wordList.find(s1);
			if (index >= 0) {
				isWord1InDict = true;
			}
			int index2 = wordList.find(s2);
			if (index2 >= 0) {
				isWord2InDict = true;
			}
			if (index >= 0 && index2 >= 0) {
				System.out.println(s1 + " and " + s2 + " rhyme");
				didRhyme = true;
				break;
			}
		}
		if (isWord1InDict == false) {
			System.out.println(s1 + " is not in the dictionary");
		}
		if (isWord2InDict == false) {
			System.out.println(s2 + " is not in the dictionary");
		}
		if (didRhyme == false && isWord1InDict == true && isWord2InDict) {
			System.out.println(s1 + " and " + s2 + " don't rhyme");
		}

	}
}