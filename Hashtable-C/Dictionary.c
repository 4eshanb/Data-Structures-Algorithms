#include <stdlib.h>
#include <stdio.h>
#include "Dictionary.h"
#include "list.h"
#include <string.h>

typedef struct DictionaryObj {
  int tableSize;
  int size;
  List** table;

} DictionaryObj;

typedef struct EntryObj {
  char* key;
  char* value;

} EntryObj;

// allows EntryObj* to be called Entry in this file
typedef struct EntryObj* Entry;
int hash(Dictionary D, char* key);

Entry newEntry(char* key, char* value){
  Entry Entry = malloc(sizeof(EntryObj));
  Entry->key = key;
  Entry->value = value;
  return Entry;
}
void freeEntry(EntryObj* pE){

}

Dictionary newDictionary(int tableSize) {
  Dictionary Dict = malloc(sizeof(DictionaryObj));
  Dict->tableSize = tableSize;
  Dict->size = 0;
  Dict->table = calloc(tableSize, sizeof(List*));
  for(int i = 0; i < tableSize; i++){
    Dict->table[i] = make_list();
  }
  return Dict;
}

void freeDictionary(Dictionary* pD){

}

int isEmpty(Dictionary D){
  if(D->tableSize == 0){
    return 1;
  }
  return 0;
}

int size(Dictionary D){
  return D->size;
}

void insert(Dictionary D, char* key, char* value){
  int arrayIndex = hash(D,key);
  List* bucket = D->table[arrayIndex];
  if(bucket == NULL){
    List* list = malloc(sizeof(List));
    //Entry entry =  newEntry(key, value);
    add(list, list->size, newEntry(key, value));
    bucket = list;
    D->size++;
  }
  else if(bucket!= NULL){
    for(int i = 0; i < bucket->size; i++){
      Entry e = (Entry) get(bucket,i);
      char* oldValue = calloc(1, sizeof(char));
      if(strcmp(key, e->key) == 0){
        oldValue = e->value;
        e->value = value;

      }
    }
    add(bucket, 0, newEntry(key, value));
    D->size++;

  }
}

char* lookup(Dictionary D, char* key){
  int arrayIndex = hash(D, key);
  List* bucket = D->table[arrayIndex];
  if(D->table[arrayIndex] == NULL){
    return NULL;
  }
  else if (D->table[arrayIndex] != NULL){
    for(int i = 0; i < bucket->size; i++){
      Entry e = (Entry) get(bucket,i);
      if (strcmp(e->key, key) == 0){
        return e->value;  
      }  
    }
    return NULL;
  }
  return NULL;
}

void delete(Dictionary D, char* key){
  int arrayIndex = hash(D,key);
  if(D->table[arrayIndex] != NULL){
    List* bucket = D->table[arrayIndex];
    for(int i = 0; i < bucket->size; i++){
      Entry e = (Entry) get(bucket, i);
      if(strcmp(e->key, key) == 0){
        remove(key);
        D->size --;
        //free(e->key);
      }
    }
  }
}

void makeEmpty(Dictionary D){
  //freeDictionary(D);
  D->table = calloc(D->tableSize, sizeof(List*));
  D->size = 0; 
}

void printDictionary(FILE* out, Dictionary D){
  char* s = "";
  for(int tableIndex = 0; tableIndex < D->tableSize; tableIndex++){
    if(D->table[tableIndex] != NULL){
      List* bucket = D->table[tableIndex];
      for( int listIndex = 0; listIndex < bucket->size; listIndex ++){
        Entry e = (Entry)get(bucket, listIndex);
        fprintf(out,"%s %s\n", e->key, e->value );
      }
    }
  }
}

// rotate_left()
// rotate the bits in an unsigned int
unsigned int rotate_left(unsigned int value, int shift) {
   int sizeInBits = 8*sizeof(unsigned int);
   shift = shift & (sizeInBits - 1);
   if ( shift == 0 ) {
      return value;
   }
   return (value << shift) | (value >> (sizeInBits - shift));
}

// pre_hash()
// turn a string into an unsigned int
unsigned int pre_hash(char* input) {
   unsigned int result = 0xBAE86554;
   while (*input) {
      result ^= *input++;
      result = rotate_left(result, 5);
   }
   return result;
}

// hash()
// turns a string into an int in the range 0 to tableSize-1
int hash(Dictionary D, char* key){
   return pre_hash(key) % D->tableSize;
}

// int main (int argc, char** argv){
//   Dictionary d = newDictionary(10);
//   printf("%d\n", d->tableSize);
//   printf("%d\n", d->size);
//   //printf("%d\n", isEmpty(d));
//   //printf("%d\n", size(d));
//   insert(d,"a", "ahso");
//   insert(d,"a", "argus");
//   insert(d,"b", "bubba");
//   delete(d,"b");
//   printf("%s\n", lookup(d, "b"));
//   printDictionary(stdout, d);
//   return 0;
// }