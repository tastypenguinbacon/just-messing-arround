#include <limits.h>

#include "queue.h"
#include "../test/test.h"

bool when_empty_returns_smallest_integer();
bool when_one_element_added_returns_it();
bool when_queue_empty_it_is_empty();
bool when_queue_contains_elements_it_is_not_empty();
bool when_queue_is_empty_pop_does_not_change_anything();
bool empty_queue_is_of_length_0();
bool one_element_queue_is_of_length_1();
bool two_element_queue_is_of_length_2();
bool when_queue_has_one_element_pop_makes_it_empty();

int main() {
  test_case(when_empty_returns_smallest_integer());
  test_case(when_one_element_added_returns_it());
  test_case(when_queue_empty_it_is_empty());
  test_case(when_queue_contains_elements_it_is_not_empty());
  test_case(when_queue_is_empty_pop_does_not_change_anything());
  test_case(empty_queue_is_of_length_0());
  test_case(one_element_queue_is_of_length_1());
  test_case(two_element_queue_is_of_length_2());
  test_case(when_queue_has_one_element_pop_makes_it_empty());
  return 0;
}

bool when_empty_returns_smallest_integer() {
  queue_node* queue = create_queue();
  bool succeeded = queue_front(queue) == INT_MIN;
  delete_queue(&queue);
  return succeeded;
}

bool when_one_element_added_returns_it() {
  queue_node* queue = create_queue();
  int element = 1;
  queue_push(&queue, element);
  bool succeeded = queue_front(queue) == element;
  delete_queue(&queue);
  return succeeded;
}

bool when_queue_empty_it_is_empty() {
  queue_node* queue = create_queue();
  bool succeeded = queue_empty(queue);
  delete_queue(&queue);
  return succeeded;
}

bool when_queue_contains_elements_it_is_not_empty() {
  queue_node* queue = create_queue();
  int element = 1;
  queue_push(&queue, element);
  bool succeeded = !queue_empty(queue);
  delete_queue(&queue);
  return succeeded;
}

bool when_queue_is_empty_pop_does_not_change_anything() {
  queue_node* queue = create_queue();
  queue_pop(&queue);
  bool succeeded = queue_empty(queue);
  delete_queue(&queue);
  return succeeded;
}

bool empty_queue_is_of_length_0() {
  queue_node* queue = create_queue();
  bool succeeded = queue_size(queue) == 0;
  delete_queue(&queue);
  return succeeded;
}

bool one_element_queue_is_of_length_1() {
  queue_node* queue = create_queue();
  int element = 1;
  queue_push(&queue, element);
  bool succeeded = queue_size(queue) == 1;
  delete_queue(&queue);
  return succeeded;
}

bool two_element_queue_is_of_length_2() {
  queue_node* queue = create_queue();
  int elements[] = {1, 2};
  queue_push(&queue, elements[0]);
  queue_push(&queue, elements[1]);
  bool succeeded = queue_size(queue) == 2;
  delete_queue(&queue);
  return succeeded;
}

bool when_queue_has_one_element_pop_makes_it_empty() {
  queue_node* queue = create_queue();
  int element = 1;
  queue_push(&queue, element);
  queue_pop(&queue);
  bool succeeded = queue_empty(queue);
  delete_queue(&queue);
  return succeeded;
}
