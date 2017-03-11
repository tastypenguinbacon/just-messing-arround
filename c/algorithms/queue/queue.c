#include <stdlib.h>
#include <limits.h>

#include "queue.h"

struct queue_node {
  queue_node *next;
  int value;
};

queue_node* create_queue() {
  return NULL;
}

void queue_push(queue_node **queue, int value) {
  queue_node *new_queue = malloc(sizeof(queue_node));
  new_queue->next = *queue;
  new_queue->value = value;
  *queue = new_queue;
}

int queue_front(queue_node *queue) {
  return queue ? queue->value : INT_MIN;
}

unsigned char queue_empty(queue_node *queue) {
  return queue == NULL;
}

void queue_pop(queue_node** queue) {
  if (queue_empty(*queue))
    return;
  queue_node* to_delete = *queue;
  *queue = (*queue)->next;
  free(to_delete);
}

int queue_size(queue_node* queue) {
  if (queue_empty(queue))
    return 0;
  return 1 + queue_size(queue->next);
}

void delete_queue(queue_node** queue) {
  while (!queue_empty(*queue)) {
      queue_pop(queue);
  }
}
