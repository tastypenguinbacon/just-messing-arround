#include <stdio.h>
#include <stdlib.h>

void insertion_sort(int *begin, int *end) {
    for (int i = 1; i < end - begin; i++) {
        int key = begin[i], j;

        for (j = i - 1; j >= 0 && begin[j] > key ; j--) 
            begin[j + 1] = begin[j];
        begin[j + 1] = key;
    }
}

int main(int argc, char *argv[]) {
    const int number_count = argc - 1;
    int *table = malloc(sizeof(int) * number_count);

    for (int i = 0; i < number_count; i++) {
        table[i] = atoi(argv[i + 1]);
    }

    insertion_sort(table, table + number_count);

    for (int i = 0; i < number_count; i++) {
        printf("%d ", table[i]);
    }
    putchar('\n');

    free(table);
    return 0;
}
