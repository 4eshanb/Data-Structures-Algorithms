
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "list.h"
#include "rule.h"
#include "helpers.h"

Rule* make_rule(char* key){
  /*
    TODO 3
  */
  Rule* rule = calloc(1, sizeof(Rule));
  rule->key = key;
  rule->expansions = NULL;

  return rule; // replace this
  /*
    TODO 3
  */
}

char * make_string_from(char * from, int count) {
   
    char * string_array = calloc(count + 1, sizeof(char));
    for (int i = 0; i < count; i++) {
      string_array[i] = from[i];
    }
    string_array[count] = '\0';
    return string_array;

}

void free_rule(Rule* rule){
  /*
    TODO 3
  */
    if(rule->key != NULL){
    printf("free_rule(): Over here\n");
    free(rule->key);
  }
	free_list(rule->expansions);
  /*
    TODO 3
  */
	
}


List* read_grammar(char* filename){
   
  /*
   * TODO 4A
   */ 
  //Construct a new List* called grammar that we will fill up in the following code

  List* grammar = make_list();

  /* 
   * TODO 4A
   */
  FILE* input_file = fopen(filename,"r");
  char buffer[1000];
  
  int number_of_expansions = 0;
  int buffer_index = 0;

  int number_of_rules = 0;
  for (char current = fgetc(input_file); current != EOF; current = fgetc(input_file)){
    if (current == ':'){
      
	  
      char* key = calloc(buffer_index+1,sizeof(char));
      memcpy(key,buffer,buffer_index);
      /*
       * TODO 4B
       */ 

      //Construct a new Rule* and add it to grammar 
     
       // printf("The key is %s\n",key);
       Rule* rule = make_rule(key);
       // printf("New rule, size is %d\n",grammar->size);
       add(grammar,grammar->size,rule);
       
       // for( int i = 0 ; i < grammar->size ; i ++ ) {
       //   Rule * tmp = get(grammar,i);
       //   printf("i=%d tmp->key=%s\n", i,tmp->key );
       // }

      /*
       * TODO 4B
       */ 
      buffer_index = 0;
    }
    else if (current == ',' || current == '\n'){
      
      char* expansion = calloc(buffer_index+1,sizeof(char));      
      memcpy(expansion,buffer,buffer_index);
     
      /*
       * TODO 4C
       */ 
      //Get the last Rule* inserted into grammar and add expansion to it 
       // printf("Grammer->size %d\n",grammar->size);

      Rule * rule ;
      for( int i = 0 ; i < grammar->size ; i ++ ) {
         rule = get(grammar,i);
         
       }

       
      
       if( rule->expansions == NULL) {
         rule->expansions = make_list();
       }
    
       add(rule->expansions, rule->expansions->size,expansion);
    


      /*
       * TODO 4C
       */ 
      buffer_index = 0;
		 
    }
    else {
      buffer[buffer_index] = current;
      buffer_index++;
    }
  }
  fclose(input_file);

  
  /*
   * TODO 4D
   */ 
  return grammar; // replace this to return the grammar we just filled up
  /*
   * TODO 4D
   */ 
}



char* expand(char* text, List* grammar){

  /*
   * BONUS TODO
   */
   List* sections = split(text, "#");
   
	
  /*
   * BONUS TODO
   */
}

//Iterates through a grammar list and prints out all of the rules
void print_grammar(List* grammar){
  
  for (int ii = 0; ii < grammar->size; ii++){
    Rule* rule = get(grammar,ii);
    for (int jj = 0; jj < rule->expansions->size; jj++){
      printf("A potential expansion of rule '%s' is '%s'\n",rule->key, (char*) get(rule->expansions,jj));
    }
  }
  
}
