#include <vector>
#include <memory>
#include <algorithm>
#include "sort.hpp"


void custom::sort(std::vector<int>& vect) {
    bool bubbled = true;
    while (bubbled) {
        bubbled = false;
        for (size_t i = 1; i < vect.size(); i++) {
            if (vect[i] < vect[i - 1]) {
                bubbled = true;
                std::swap(vect[i - 1], vect[i]);
            } 
        }
    }
}
