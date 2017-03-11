#ifndef __QUEUE_H__
#define __QUEUE_H_

typedef struct queue_node queue_node;

queue_node* create_queue();

void queue_push(queue_node **queue, int value);

void queue_pop(queue_node **queue);

int queue_front(queue_node *);

unsigned char queue_empty(queue_node *);

int queue_size(queue_node *);

void delete_queue(queue_node **);

#endif
