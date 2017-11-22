#include <algorithm>
#include <vector>

#include "sort.hpp"

static void quick_sort(std::vector<int> &vect, int begin, int end);

void custom::sort(std::vector<int> &vect) {
    quick_sort(vect, 0, vect.size());
}

void quick_sort(std::vector<int> &vect, int begin, int end) {
    if (end - begin <= 1) 
        return;
    int pivot = vect[begin];
    int m = begin;
    for (int i = begin + 1; i < end; i++)
        if (vect[i] < pivot) 
            std::swap(vect[++m], vect[i]);
    std::swap(vect[begin], vect[m]);
    quick_sort(vect, begin, m);
    quick_sort(vect, m + 1,  end);
}

