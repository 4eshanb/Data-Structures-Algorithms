class Entry {
	String key;
	Object value;

	Entry(String key, Object value) {
		this.key = key;
		this.value = value;
	}
}

class MyHashtable implements DictionaryInterface {

	protected int size;
	protected int tableSize;
	protected MyLinkedList[] table;

	// Returns the size of the biggest bucket (most collisions) in the hashtable.
	public int biggestBucket() {
		int biggestBucket = 0;

		for (int i = 0; i < table.length; i++) {
			// Loop through the table looking for non-null locations.
			if (table[i] != null) {
				// If you find a non-null location, compare the bucket size against the largest
				// bucket size found so far. If the current bucket size is bigger, set
				// biggestBucket
				// to this new size.
				MyLinkedList bucket = table[i];
				if (biggestBucket < bucket.size())
					biggestBucket = bucket.size();
			}
		}
		return biggestBucket; // Return the size of the biggest bucket found.
	}

	// Returns the average bucket length. Gives a sense of how many collisions are
	// happening overall.
	public float averageBucket() {
		float bucketCount = 0; // Number of buckets (non-null table locations)
		float bucketSizeSum = 0; // Sum of the size of all buckets
		for (int i = 0; i < table.length; i++) {
			// Loop through the table
			if (table[i] != null) {
				// For a non-null location, increment the bucketCount and add to the
				// bucketSizeSum
				MyLinkedList bucket = table[i];
				bucketSizeSum += bucket.size();
				bucketCount++;
			}
		}

		// Divide bucketSizeSum by the number of buckets to get an average bucket
		// length.
		return bucketSizeSum / bucketCount;
	}

	public String toString() {
		String s = "";
		for (int tableIndex = 0; tableIndex < tableSize; tableIndex++) {
			if (table[tableIndex] != null) {
				MyLinkedList bucket = table[tableIndex];
				for (int listIndex = 0; listIndex < bucket.size(); listIndex++) {
					Entry e = (Entry) bucket.get(listIndex);
					s = s + "key: " + e.key + ", value: " + e.value + "\n";
				}
			}
		}
		return s;
	}

	//Computes an array index given the key 
	public int hash(String key) {

		int hashCode = key.hashCode();
		int arrayIndex = Math.abs(hashCode) % tableSize;
		return arrayIndex;
	}

	// returns true if the number of key/value entries stored in the hashtable is 0
	@Override
	public boolean isEmpty() {
		if (size == 0) {
			return true;
		}
		return false;
	}

	// returns number of key/value entries stored in the hashtable
	@Override
	public int size() {
		return size;
	}

	@Override
	public Object put(String key, Object value) {
		int arrayIndex = hash(key);

		if (table[arrayIndex] == null) {
			MyLinkedList bucket = new MyLinkedList();
			int bucketSize = bucket.size();
			bucket.add(bucketSize, new Entry(key, value));
			table[arrayIndex] = bucket;
			size++;
			return null;
		} else if (table[arrayIndex] != null) {
			MyLinkedList bucket = table[arrayIndex];
			for (int i = 0; i < bucket.size; i++) {
				Entry tmp = (Entry)bucket.get(i);
				if (key.equals(tmp.key)) {
					 //e = new Entry(key, value);
					tmp.value = value;
					return key;
				} else {
					bucket.add(0, tmp);
					size++;
					return null;
				}
			}
		}
		return null;
	}

	@Override
	public Object get(String key) {
		int arrayIndex = hash(key);
		
		if (table[arrayIndex] == null) {
			return null;
		} else if (table[arrayIndex] != null) {
			MyLinkedList bucket = table[arrayIndex];
			for (int x = 0; x < bucket.size; x++) {
				Entry tmp = (Entry)bucket.get(x);
				if (key.equals(tmp.key)) {
					return tmp.value;
				} 
			}
		}
		return null;
	}

	@Override
	public void remove(String key) {
		int arrayIndex = hash(key);
			// If the location in the table has a bucket, we need to linearly search it to
		// see if it contains
		// an Entry with the key. If you find an Entry in the bucket (linked list) with
		// the key:
		// a. Remove this Entry from the bucket.
		// b. Decrement size (the number of unique keys stored in the hashtable)

		if (table[arrayIndex] != null) {
			MyLinkedList bucket = table[arrayIndex];
			for (int i = 0; i < bucket.size; i++) {
				Entry tmp = (Entry)bucket.get(i);
				if (tmp.key.equals(key)) {
					bucket.remove(i);
					size--;
				}
			}
		}

	}

	@Override
	public void clear() {
		table = new MyLinkedList[size];
		size = 0;
		tableSize = 0 ; 
	}

	@Override
	public String[] getKeys() {
		String[] s = new String[size];
		int counter = 0;
		// MyLinkedList bucket = new MyLinkedList();
		for (int i = 0; i < table.length; i++) {
			MyLinkedList bucket = table[i];
			// Iterate through the bucket (linked list), getting the key out of each Entry
			// and
			// storing it in the array of strings you created in step 1. You'll need some
			// kind of
			// counter to keep track of where in the array of Strings you're adding the key
			if (table[i] != null) {
				for (int x = 0; x < bucket.size; x++) {
					Entry tmp = (Entry) bucket.get(x);
					s[counter] = tmp.key;
					counter++;
				}
			}
		}
		return s;
	}

	public MyHashtable(int tableSize) {
		this.tableSize = tableSize;
		table = new MyLinkedList[tableSize];
		size = 0;

	}

	public static void main(String[] args) {
		MyHashtable h = new MyHashtable(5);
		System.out.println(h.isEmpty());
		System.out.println(h.size());
		h.put("f", "food");
		h.put("f", "mundungus");
		h.put("e", "butt");
		h.put("x", "dingus");
		h.put("v", "stupid");
		//h.remove("e");
		System.out.println("Get(v) " + h.get("v"));
		System.out.println("Get(f) " + h.get("f"));
		 //h.remove("v");
		for ( String key : h.getKeys())
			System.out.println("The key is " + key);
		// h.put("m", "lift");
		
		System.out.println(h.isEmpty());
		System.out.println(h.size());
		
		System.out.println(h);
		h.clear();
		System.out.println("After clear");
		System.out.println(h);
		
	}
}
