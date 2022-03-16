#include "8puzzle/8puzzle.h"

int main() {
    // create array
    int array[9];

    // create node list
    Node node_list[MAX_NODE_LIST_SIZE];

    // game loop
    game_loop(array, node_list);

    // print node list
    print_node_list(node_list);

    return 0;
}
