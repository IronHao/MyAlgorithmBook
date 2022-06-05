#pragma once

#include "ArrayQueue.h"

template<class T>
ArrayQueue<T>::ArrayQueue()
{
    _size = 1;
    _count = 0;
    _head = 0;
    _arr = new T[_size];
}

template<class T>
ArrayQueue<T>::~ArrayQueue()
{
    delete[] _arr;
}

/* Return whether the queue is full or not */
template<class T>
bool ArrayQueue<T>::isFull() { return _size == _count; }

/* Resize the queue to the 'newSize' */
template<class T>
void ArrayQueue<T>::resize(int newSize)
{
    T* tempArr = new T[newSize];
    for(int i = 0; i < count(); i++)
        tempArr[i] = _arr[(_head+i)%size()];
    delete[] _arr;
    _head = 0;
    _arr = tempArr;
    _size = newSize;
}

/* Insert a new item onto queue(tail of the queue) */
template<class T>
void ArrayQueue<T>::enqueue(T item)
{
    if (isFull())
        resize(size()*2);
    _arr[(_head+count())%size()] = item;
    _count++;
}

/* Remove and return the item least recently added(head of the queue) */
template<class T>
T ArrayQueue<T>::dequeue()
{
    T ans = T();
    if (!isEmpty())
    {
        ans = _arr[_head];
        _head = (_head+1)%size();
        _count--;
    }
    if (count() > 0 && count() == size() / 4)
        resize(size()/2);
    return ans;
}
/* Return the item least recently added without removing */
template<class T>
T ArrayQueue<T>::first()
{
    T ans = T();
    if (!isEmpty())
        ans = _arr[_head];
    return ans;
}

/* Return the item latest recently added without removing */
template<class T>
T ArrayQueue<T>::last()
{
    T ans = T();
    if (!isEmpty())
        ans = _arr[(_head+count()-1)%size()];
    return ans;
}

/* Return whether the queue is empty or not */
template<class T>
bool ArrayQueue<T>::isEmpty() { return _count == 0; }

/* Return the size (allocate memory) of the queue */
template<class T>
int ArrayQueue<T>::size() { return _size; }

/* Return the number of items in queue */
template<class T>
int ArrayQueue<T>::count() { return _count; }