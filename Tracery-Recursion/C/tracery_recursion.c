#include <stdio.h>
#include <stdlib.h>

char* make_string_from(char* from, int count) {
	/* TODO 2 */

	char* new_array = calloc(count + 1, sizeof(int));

	for(int i = 0; i < count; i++){
		new_array[i] = from[i];
	}

	return new_array;

	/* TODO 2 */
}

int* make_array(){

	int* int_array = calloc(3, sizeof(int));
	int_array[0] = 1;
	int_array[1] = 10;
	int_array[2] = 100;

	return int_array;
}

int main(int argc, char** argv) {
	/* TODO 1 */

 	for(int i = 0; i < argc; i++){
 		printf("%s\n", argv[i]);
 	}

 	/* TODO 1 */
 	char* st = "mundungus";
 
	int* made_array = make_array();
	char* st_array = make_string_from(st,9);
	printf("%d\n", made_array[1]);
	printf("%s\n", st_array);
	
	
	free(made_array);
  
 	/* TODO 3 */

	char* char_buffer = calloc(1000, sizeof(int));
	int buffer_index = 0;
	char* rule;

	for(int i = getchar(); i != EOF; i++){
		if(i == ':'){
			rule = make_string_from(char_buffer, buffer_index);
			buffer_index = 0;

		}
		else if (i == ',' || i == '\n'){
			char* expansion = make_string_from(char_buffer, buffer_index);
			buffer_index = 0;
			printf("%s, %s, %s, %s\n", "A potential expansion of rule ", rule, "is", expansion);
			free(expansion);
			free(rule);
		}

		else {
			char_buffer[buffer_index] = i;
			buffer_index ++;
		}
	}

	free(char_buffer);


 	/* TODO 3 */
}


