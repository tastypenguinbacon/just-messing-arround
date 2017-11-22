#include <vector>

#include "sort.hpp"

void custom::sort(std::vector<int>& vect) {
    for (std::size_t i = 1; i < vect.size(); i++) {
        int key = vect[i], j;

        for (j = i - 1; j >= 0 && vect[j] > key ; j--) 
            vect[j + 1] = vect[j];
        vect[j + 1] = key;
    }
}

