#pragma once

#include "ArrayStack.h"

template<class T>
ArrayStack<T>::ArrayStack()
{
    _size = 1;
    _count = 0;
    _arr = new T[_size];
}

template<class T>
ArrayStack<T>::~ArrayStack()
{
    delete[] _arr;
}

/* Return whether the stack is full or not */
template<class T>
bool ArrayStack<T>::isFull() { return _size == _count; }

/* Resize the stack to the 'newSize' */
template<class T>
void ArrayStack<T>::resize(int newSize)
{
    T* tempArr = new T[newSize];
    for (int i = 0; i < count(); i++)
        tempArr[i] = _arr[i];
    delete[] _arr;
    _arr = tempArr;
    _size = newSize;
}

/* Push the 'item' to the top of the stack */
template<class T>
void ArrayStack<T>::push(T item)
{
    // Resize the array(Double the size of array) if the stack is full
    if (isFull())
        resize(size()*2);
    // Push the item
    _arr[_count++] = item;
}

/* Pop(delete) and return the item in the top of the stack. Return nullptr if the stack is empty */
template<class T>
T ArrayStack<T>::pop()
{
    // Pop the item
    T ans = T();
    if (!isEmpty())
        ans = _arr[--_count];
    // Resize the array(halve the size) if the count is one-quarter full
    if (count() > 0 && count() == size() / 4)
        resize(size()/2);
    return ans;
}

/* Get the item in the top of the stack, no delete. Return nullptr if the stack is empty */
template<class T>
T ArrayStack<T>::top() 
{
    T ans = T();
    if (!isEmpty())
        ans = _arr[_count-1]; 
    return ans; 
}

/* Return whether the stack is empty or not */
template<class T>
bool ArrayStack<T>::isEmpty() { return _count == 0; }

/* Return the size (allocate memory) of the stack */
template<class T>
int ArrayStack<T>::size() { return _size; }

/* Return the number of items in the stack */
template<class T>
int ArrayStack<T>::count() { return _count; }