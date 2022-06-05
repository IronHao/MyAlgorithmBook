#pragma once

/* 
 * Stack data structure, array implementation. FILO.
 */
template <class T>
class ArrayStack
{
private:
    T* _arr;
    int _size;
    int _count;

    /* Return whether the stack is full or not */
    bool isFull();
    /* Resize the stack to the 'newSize' */
    void resize(int newSize);

public:
    ArrayStack();
    ~ArrayStack();

    /* Push the 'item' to the top of the stack */
    void push(T item);
    /* Pop(delete) and return the item in the top of the stack */
    T pop();
    /* Get the item in the top of the stack, no delete */
    T top();
    /* Return whether the stack is empty or not */
    bool isEmpty();
    /* Return the size (allocate memory) of the stack */
    int size();
    /* Return the number of items in the stack */
    int count();
};