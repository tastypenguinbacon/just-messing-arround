#include <stdio.h>
#include <stdlib.h>
#include <limits.h>
#include <string.h>

int max(int *begin, int *end) {
    int max_value = INT_MIN;
    for (int i = 0; i < end - begin; i++) {
        max_value = max_value > begin[i] ? max_value : begin[i];
    }
    return max_value;
}

void counting_sort(int *begin, int *end) {
    int count_array_size = max(begin, end) + 1;
    int *count_array = malloc(sizeof(int) * count_array_size);
    memset(count_array, 0, sizeof(int) * count_array_size);

    for (int i = 0; i < end - begin; i++)
        count_array[begin[i]]++;

    int array_cursor = 0;
    for (int i = 0; i < count_array_size; i++) 
        for (int j = 0; j < count_array[i]; j++) 
            begin[array_cursor++] = i;
             
    free(count_array);
}

int main(int argc, char *argv[]) {
    const int number_count = argc - 1;
    int *table = malloc(sizeof(int) * number_count);

    for (int i = 0; i < number_count; i++) {
        table[i] = atoi(argv[i + 1]);
    }

    counting_sort(table, table + number_count);

    for (int i = 0; i < number_count; i++) {
        printf("%d ", table[i]);
    }
    putchar('\n');

    free(table);
    return 0;
}
