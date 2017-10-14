#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void merge(int *begin, int *middle, int *end) {
    const int temp_size = end - begin;
    int *temp = malloc(sizeof(int) * temp_size);
    
    int i, j;
    for (i = 0, j = 0; begin + i != middle && middle + j != end;) {
        if (begin[i] < middle[j]) {
            temp[i + j] = begin[i];
            i++;
        } else {
            temp[i + j] = middle[j];
            j++;
        }
    }

    for (; begin + i != middle; i++) 
       temp[i + j] = begin[i]; 
    for (; middle + j != end; j++)
        temp[i + j] = middle[j];
    memcpy(begin, temp, (end - begin) * sizeof(int));
}

void merge_sort(int *begin, int *end) {
    if (end - begin <= 1) 
        return;
    const int size = end - begin;
    const int middle = (size + 1) / 2;
    merge_sort(begin, begin + middle);
    merge_sort(begin + middle, end);
    merge(begin, begin + middle, end);
}

int main(int argc, char* argv[]) {
    const int number_count = argc - 1;
    int *numbers = malloc(sizeof(int) * number_count);
    for (int i = 0; i < number_count; i++)
        numbers[i] = atoi(argv[i + 1]);

    merge_sort(numbers, numbers + number_count);

    for (int i = 0; i < number_count; i++)
        printf("%d ", numbers[i]);
    putchar('\n');

    free(numbers);
    return 0;
}
