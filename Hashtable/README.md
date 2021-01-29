# Hashtable

Implemented a hashtable class using the DictionaryInterface.
Created a ​MyHashTable​ with size 20,000.
The ​keys​ used in this hashtable are rhyming groups.
The ​values​ used in this hashtable are ​sorted linked lists​.
Each sorted linked list​ will store individual words sharing a rhyme group.
The CMU Pronunciation dictionary is a free data source of how each word in
English is pronounced, useful for text-to-speech or rhyming applications.
The program writes poems by picking two rhyming groups at random from an array of keys.
The sorted linked list of words for each group.
Two random indices are picked for each list (based on the length of the list), then
these two are used get four words, two words from each list.
The words are used to make a poems.
Unrhymable words were removed from the dictionary. Some words are in a
rhyme group by themselves. That means that nothing rhymes with them. 

This assignment was done in my undergraduate studies, specifically Professor Michael Mateas's cmps12b.