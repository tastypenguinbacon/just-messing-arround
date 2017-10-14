#include <stdio.h>
#include <stdlib.h>

typedef enum {
    False, 
    True
} bool;

void swap(int *a, int *b) {
    int c = *a;
    *a = *b;
    *b = c;
}

void bubble_sort(int *begin, int* end) {
    bool bubbled = True;
    while (bubbled) {
        bubbled = False;
        for (int i = 1; begin + i != end; i++) {
            if (begin[i] < begin[i - 1]) {
                bubbled = True;
                swap(&begin[i - 1], &begin[i]);
            } 
        }
    }
}

int main(int argc, char *argv[]) {
    const int number_count = argc - 1;
    int *table = malloc(sizeof(int) * number_count);

    for (int i = 0; i < number_count; i++)
        table[i] = atoi(argv[i + 1]);

    bubble_sort(table, table + number_count);

    for (int i = 0; i < number_count; i++)
        printf("%d ", table[i]);
    putchar('\n');

    free(table);
    return 0;
}
