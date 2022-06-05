#pragma once

#include "Node.h"
#include "Node.cpp"

/* 
 * Queue data structure, linked-list implementation. FIFO.
 */
template<class T>
class LinkedListQueue
{
private:
    Node<T>* _first;
    Node<T>* _last;
    int _count;
public:
    LinkedListQueue();
    ~LinkedListQueue();

    /* Insert a new item onto queue(tail of the queue) */
    void enqueue(T item);
    /* Remove and return the item least recently added(head of the queue) */
    T dequeue();
    /* Return the item least recently added without removing */
    T first();
    /* Return the item latest recently added without removing */
    T last();
    /* Return whether the queue is empty or not */
    bool isEmpty();
    /* Return the number of items in queue */
    int count();
};