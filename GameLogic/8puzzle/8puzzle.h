#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <time.h>


// init array
void init_array(int *array) {
    // init with default values
    for (int i = 0; i < 9; i++) array[i] = 9;

    // init with random values
    srand(time(NULL));
    int i = 0;
    while (i < 9) {
        int r = rand() % 9;
        if (array[r] == 9) {
            array[r] = i++;
        }
    }
}


// game won check
bool game_end(int *array) {
    int check = 0;
    for (int i = 0; i < 9; i++) {
        if(array[i] == i) 
            check++;
    }
    return check == 9 ? true : false;
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


// move space up
void move_up(int * array) {
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


// cmd listner
void game_loop(int *array) {
    init_array(array);
    printf("\n\n\'up\'    | \'w\'   GO UP\n\'down\'  | \'s\'   GO DOWN\n\'right\' | \'d\'   GO RIGHT\n\'left\'  | \'a\'   GO LEFT\n");
    char cmd[4];

    do {
        print_array(array);

        printf("\n~! ");
        scanf("%s", cmd);

        if (!strcmp(cmd, "up") || !strcmp(cmd, "w")) {
            move_up(array);
            system("clear");
        }
        else if (!strcmp(cmd, "down") || !strcmp(cmd, "s")) {
            move_down(array);
            system("clear");
        }
        
        else if (!strcmp(cmd, "right") || !strcmp(cmd, "d")) {
            move_right(array);
            system("clear");
        }
        
        else if (!strcmp(cmd, "left") || !strcmp(cmd, "a")) {
            move_left(array);
            system("clear");
        }

        else if (!strcmp(cmd, "exit") || !strcmp(cmd, "e")) {
            printf("GAME END!\n");
            exit(EXIT_SUCCESS);
        }

        else {
            system("clear");
            printf("Error %s is not a valid commend\n", cmd);
        }

    } while(!game_end(array));
}