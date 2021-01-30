# Hashtable in C

Implemented basic hashtable functionality in C. This hashtable can be viewed as an array of lists.
An array of lists is used instead of just using an array because it is much more efficient with storage space since we don’t need to make space in an array for every possible hash value. Additionally we don’t have to worry about running out of space in the array to deal with ​collisions​, two keys being turned into the same array index. A string is hashed into an "array value", which is the key and a list is used as a value associated 
with the keys.