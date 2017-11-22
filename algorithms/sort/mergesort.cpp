#include <vector>
#include <algorithm>
#include <iterator>
#include "sort.hpp"

inline void merge(std::vector<int>& vect, int begin, int end);
inline int calculate_middle(int begin, int end);
static void merge_sort(std::vector<int>& vect, int begin, int end);

void custom::sort(std::vector<int>& vect) {
    merge_sort(vect, 0, vect.size());
}

void merge_sort(std::vector<int>& vect, int begin, int end) {
    if (end - begin <= 1)
        return;
    int middle = calculate_middle(begin, end);
    merge_sort(vect, begin, middle);
    merge_sort(vect, middle, end);
    merge(vect, begin, end);
}

int calculate_middle(int begin, int end) {
    return (begin + end + 1) / 2;
}

void merge(std::vector<int>& vect, int begin, int end) {
    int i, j, middle = calculate_middle(begin, end);
    std::vector<int> temp;
    for (i = begin, j = middle; i < middle && j < end;) {
        if (vect[i] < vect[j]) 
            temp.push_back(vect[i++]);
        else
            temp.push_back(vect[j++]);
    }
    using std::copy; using std::back_inserter;
    copy(vect.begin() + i, vect.begin() + middle, back_inserter(temp));
    copy(vect.begin() + j, vect.begin() + end, back_inserter(temp));
    copy(temp.begin(), temp.end(), vect.begin() + begin);
}
