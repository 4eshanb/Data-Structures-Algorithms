#include <stdlib.h>
#include "list.h"
#include <stdio.h>
#include <assert.h>

Node* make_node(void* data, Node* next){
	
	/* 
	* TODO 2
	*/ 
	 Node* node = calloc(1, sizeof(Node));
	 node->data = data;
	 node->next = next;
	
	return node;
	/* 
	* TODO 2
	*/ 
}

List* make_list(){
	
	/* 
	* TODO 2
	*/ 
	List* list = calloc(1, sizeof(List));
	list->size = 0; 
	list->head = NULL;

	
	return list;
	/* 
	* TODO 2
	*/ 
}

void free_node(Node* node){
	
	/* 
	* TODO 2
	*/ 
	if(node->data != NULL){
	    free(node->data);
	}
	free(node);

	
	/* 
	* TODO 2
	*/ 
}


void* getNodeAt(List* list, int index){
	
	/* 
	* TODO 2
	*/ 
	assert( !(index > list->size || index < 0) && "Index was out of bounds");
	Node* current = list->head;
	if(index > 0 && index < list->size){
		int i = 0 ; 
		
		while(i < index){
			current = current->next;
			i++;
		}

	}

	return current; 
	/* 
	* TODO 2
	*/ 
}


void free_list(List* list) {
	
	/* 
	* TODO 2
	*/ 

	Node* head  = list->head;
	while ( head != NULL) {

		Node* n = head; 
		head = head->next;
		// printf("Freeing %s\n", n->data);
		free_node(n);
	}
	
	
	free(list);
	/* 
	* TODO 2
	*/ 
}

void add(List* list, int index, void* data) {
	
	/* 
	* TODO 2
	*/ 
	assert( !(index > list->size || index < 0) && "Index was out of bounds");

	
	if(index == 0){
	
		Node* newNode = calloc(1, sizeof(Node));
		newNode->data =  data;
		

		if( list->size == 0 ) {
		  newNode->next = NULL;
		
		}
		else {
			Node * firstNode = getNodeAt(list,0);
			newNode->next = firstNode;

		}
		list->head = newNode;
		
		
	} else if(index == list->size){
		Node* prev = getNodeAt(list, index-1);
		Node* newNode = make_node(data, NULL);
		prev->next = newNode;
	} else {
		int i = 0;
		// Node* current = list->head;
		// while(i<index-1){
		// 	current = current->next;
		// 	i++;
		// }

		Node* current = getNodeAt(list,index-1);
		Node* newNode = make_node(data, list->head);
		newNode->next = current->next; 
		current->next = newNode; 
	}
	list->size++;

	
	/* 
	* TODO 2
	*/ 
}

void printList(List* list) {
	Node* head  = list->head;
	
	while ( head != NULL) {
		Node* n = head; 
		printf(" data = %s\n",n->data);
		head = head->next;
	
	}
}
void createTestList(){
	List* list = make_list();
	add(list, 0, "hello");
	add(list,1,"eshan");
	// add(list, 1,"asmita");
	set(list, 1,"asmita");


	printList(list);

	free_list(list);

}

void* get(List* list, int index){
	
	/* 
	* TODO 2
	*/ 
	assert( !(index > list->size || index < 0) && "Index was out of bounds");
	Node* current = list->head;
	if(index > 0 && index < list->size){
		int i = 0 ; 
		
		while(i < index){
			current = current->next;
			i++;
		}

	}

	return current->data; 
	/* 
	* TODO 2
	*/ 
}

void set(List* list, int index, void* data) {
	
	/* 
	* TODO 2
	*/ 


	Node * current = getNodeAt(list,index);
	current->data = data;
	current = current ->next;

	// int i = 0;
	// Node* current = list->head;

	// while(i < list->size){
	// 	if(i == index ){
	// 		//data = list->head->data;
	// 		//current->data = data;
	// 	}
	// 	i++;
	// 	current = current->next;
	// }
	

	/* 
	* TODO 2
	*/ 
}

// int main(int argc, char** argv){
	
// 	createTestList();
// }
