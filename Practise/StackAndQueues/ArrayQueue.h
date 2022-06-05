#pragma once

/* 
 * Queue data structure, linked-list implementation. FIFO.
 */
template<class T>
class ArrayQueue
{
private:
    T* _arr;
    int _head;
    int _size;
    int _count;

    /* Return whether the queue is full or not */
    bool isFull();
    /* Resize the queue to the 'newSize' */
    void resize(int newSize);
public:
    ArrayQueue();
    ~ArrayQueue();

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
    /* Return the size (allocate memory) of the queue */
    int size();
    /* Return the number of items in queue */
    int count();
};