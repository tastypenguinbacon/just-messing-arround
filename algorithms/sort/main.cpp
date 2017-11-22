#include <iostream>
#include <vector>
#include "sort.hpp"

int main(int argc, char *argv[]) {
    const int number_count = argc - 1;
    std::vector<int> vect;

    for (int i = 0; i < number_count; i++) 
        vect.push_back(atoi(argv[i + 1]));

    custom::sort(vect);

    for (int i : vect) 
        std::cout << i << " ";
    std::cout << std::endl;
    return 0;
}
