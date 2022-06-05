#pragma once

#include "Node.h"
#include "Node.cpp"


/* 
 * Stack data structure, linked-list implementation. FILO.
 */
template<class T>
class LinkedListStack
{
private:
    /* data */
    Node<T>* _top;
    int _count;
public:
    LinkedListStack();
    ~LinkedListStack();

    /* Push the 'item' to the top of the stack */
    void push(T item);
    /* Pop(delete) and return the item in the top of the stack */
    T pop();
    /* Get the item in the top of the stack, no delete */
    T top();
    /* Return whether the stack is empty or not */
    bool isEmpty();
    /* Return the number of items in the stack */
    int count();
};