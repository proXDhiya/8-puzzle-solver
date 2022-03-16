#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <time.h>

#define MAX_NODE_LIST_SIZE 40000

// structs
typedef struct {
    int node_gen;
    char *action;
    int array[9];
} Node;


// init array
void init_array(int *array) {
    // init with default values
    // for (int i = 0; i < 9; i++) array[i] = 9;

    // // init with random values
    // srand(time(NULL));
    // int i = 0;
    // while (i < 9) {
    //     int r = rand() % 9;
    //     if (array[r] == 9) {
    //         array[r] = i++;
    //     }
    // }

    // init with specific values
    array[0] = 7;
    array[1] = 2;
    array[2] = 4;
    array[3] = 5;
    array[4] = 0;
    array[5] = 6;
    array[6] = 8;
    array[7] = 3;
    array[8] = 1;
}


// game won check
bool game_end(int *array) {
    for (int i = 0; i < 9; i++)
        if(array[i] != i)
            return false;
    return true;
}


// print array
void print_array(int *array) {
    for (int i = 0; i < 9; i++) {
        if (i == 0 || i == 3 || i == 6) printf("\n-------------\n");
        if (array[i] == 0) printf("|   ");
        else printf("| %d ", array[i]);
        if (i == 2 || i == 5 || i == 8) printf("|");
    }
    printf("\n-------------\n");
}


// compare two arrays
bool compare_arrays(int *array1, int *array2) {
    for (int i = 0; i < 9; i++) {
        if (array1[i] != array2[i]) return false;
    }
    return true;
}


// copy array to another
void copy_array(int *array, int *array_copy) {
    for (int i = 0; i < 9; i++) {
        array_copy[i] = array[i];
    }
}


// move space up
void move_up(int *array) {
    for (int i = 0; i < 9; i++) {
        if (array[i] == 0 && i > 2) {
            array[i] = array[i-3];
            array[i-3] = 0;
            break;
        }
    }
}


// move space down
void move_down(int *array) {
    for (int i = 0; i < 9; i++) {
        if (array[i] == 0 && i < 6) {
            array[i] = array[i+3];
            array[i+3] = 0;
            break;
        }
    }
}


// move space right
void move_right(int *array) {
    for (int i = 0; i < 9; i++) {
        if (array[i] == 0 && i != 2 && i != 5 && i != 8) {
            array[i] = array[i+1];
            array[i+1] = 0;
            break;
        }
    }
}


// move space left
void move_left(int *array) {
    for (int i = 0; i < 9; i++) {
        if (array[i] == 0 && i != 0 && i != 3 && i != 6) {
            array[i] = array[i-1];
            array[i-1] = 0;
            break;
        }
    }
}


// insert new node to node list
void insert_node(Node *node_list, int index, int node_gen, char *action, int *array) {
    node_list[index].node_gen = node_gen;
    node_list[index].action = action;
    copy_array(array, node_list[index].array);
}


// check if node counter is MAX_NODE_LIST_SIZE
bool check_node_counter(int node_counter) {
    if (node_counter == MAX_NODE_LIST_SIZE) return true;
    return false;
}


// cmd listner
void game_loop(int *array, Node *node_list) {
    init_array(array);

    // init first node
    insert_node(node_list, 0, 0, "init", array);

    // create node counter
    int node_counter = 1;

    // create node corrent index
    int node_current_index = 0;

    // game state check
    bool game_state = false;

    // game loop
    do {
        // up
        if (!game_state) {
            int array_up[9];
            copy_array(node_list[node_current_index].array, array_up);
            move_up(array_up);
            
            if (!compare_arrays(array_up, node_list[node_current_index].array)) {
                insert_node(node_list, node_counter, node_current_index, "up", array_up);
                node_counter++;
                if (game_end(array_up)) {
                    print_array(array_up);
                    break;
                }
                if (check_node_counter(node_counter)) {
                    printf("\nNo Solution Found!\n");
                    break;
                }
            }
        }


        // down
        if (!game_state) {
            int array_down[9];
            copy_array(node_list[node_current_index].array, array_down);
            move_down(array_down);

            if (!compare_arrays(array_down, node_list[node_current_index].array)) {
                insert_node(node_list, node_counter, node_current_index, "down", array_down);
                node_counter++;
                if (game_end(array_down)) {
                    print_array(array_down);
                    break;
                }
                if (check_node_counter(node_counter)) {
                    printf("\nNo Solution Found!\n");
                    break;
                }
            }
        }
        

        // right
        if (!game_state) {
            int array_right[9];
            copy_array(node_list[node_current_index].array, array_right);
            move_right(array_right);
            if (!compare_arrays(array_right, node_list[node_current_index].array)) {
                insert_node(node_list, node_counter, node_current_index, "right", array_right);
                node_counter++;
                if (game_end(array_right)) {
                    print_array(array_right);
                    break;
                }
                if (check_node_counter(node_counter)) {
                    printf("\nNo Solution Found!\n");
                    break;
                }
            }
        }
        

        // left
        if (!game_state) {
            int array_left[9];
            copy_array(node_list[node_current_index].array, array_left);
            move_left(array_left);
            if (!compare_arrays(array_left, node_list[node_current_index].array)) {
                insert_node(node_list, node_counter, node_current_index, "left", array_left);
                node_counter++;
                if (game_end(array_left)) {
                    print_array(array_left);
                    break;
                }
                if (check_node_counter(node_counter)) {
                    printf("\nNo Solution Found!\n");
                    break;
                }
            }
        }
        
        // next node
        node_current_index++;
    } while(true);
}


void print_node_list(Node *node_list) {
    for (int i = 0; i < MAX_NODE_LIST_SIZE; i++) {
        print_array(node_list[i].array);
        printf("\nNode %d: From Node %d\n", i, node_list[i].node_gen);
        printf("Action: %s\n", node_list[i].action);
    }
}
