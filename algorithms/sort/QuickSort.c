#include <stdio.h>
#include <stdlib.h>

void swap(int *a, int *b) {
   int c = *a;
   *a = *b;
   *b = c;
}

int quick_sort_rearange(int *begin, int *end) {
    int pivot = begin[0];
    int size = end - begin;
    swap(begin, end - 1);
    int begin_index = 0;
    int end_index = size - 1;

    for (int i = 1; i < size; i++) {
        if (begin[begin_index] > pivot) 
            swap(begin + begin_index, begin + --end_index);
        else 
            begin_index++;
    }
    swap(begin + begin_index, end - 1);
    return begin_index;
}

void quick_sort(int *begin, int *end) {
    if (end - begin <= 1)
        return;
    int middle_index = quick_sort_rearange(begin, end);
    quick_sort(begin, begin + middle_index);
    quick_sort(begin + middle_index + 1, end);
}

int main(int argc, char* argv[]) {
    const int number_count = argc - 1;
    int *numbers = malloc(sizeof(int) * number_count);

    for (int i = 0; i < number_count; i++) 
        numbers[i] = atoi(argv[i + 1]);

    quick_sort(numbers, numbers + number_count);

    for (int i = 0; i < number_count; i++) 
        printf("%d ", numbers[i]);
    putchar('\n');
    free(numbers);
    return 0;
}
