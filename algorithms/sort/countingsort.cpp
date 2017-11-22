#include <algorithm>
#include <vector>
#include "sort.hpp"

void custom::sort(std::vector<int> &vec) {
    std::size_t count = *std::max_element(vec.begin(), vec.end()) + 1;
    std::vector<int> occurance_count(count);
    std::fill(occurance_count.begin(), occurance_count.end(), 0);

    for (int i : vec)
        occurance_count[i]++;

    int cursor = 0;
    for (std::size_t i = 0; i < count; i++) 
        for (int j = 0; j < occurance_count[i]; j++) 
            vec[cursor++] = i;
}
