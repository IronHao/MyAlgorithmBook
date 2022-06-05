#pragma once

#include "LinkedListQueue.h"

template<class T>
LinkedListQueue<T>::LinkedListQueue()
{
    _first = nullptr;
    _last = nullptr;
}

template<class T>
LinkedListQueue<T>::~LinkedListQueue()
{
    delete _first;
    delete _last;
}
    
/* Insert a new item onto queue(tail of the queue) */
template<class T>
void LinkedListQueue<T>::enqueue(T item)
{
    Node<T>* temp = new Node<T>(item);
    if (!isEmpty())
    {
        _last->setNext(temp);
        _last = temp;
    }
    else
    {
        _last = temp;
        if (_first == nullptr)
            _first = temp;
    }
    _count++;
}
    
/* Remove and return the item least recently added(head of the queue) */
template<class T>
T LinkedListQueue<T>::dequeue()
{
    T ans;
    if (!isEmpty())
    {
        ans = _first->getVal();
        Node<T>* temp = _first;
        _first = _first->getNext();
        delete temp;
        _count--;
    }
    else
        ans = T();
    return ans;
}

/* Return the item least recently added without removing */
template<class T>
T LinkedListQueue<T>::first()
{
    T ans;
    if (!isEmpty())
        ans = _first->getVal();
    else
        ans = T();
    return ans;
}
/* Return the item latest recently added without removing */
template<class T>    
T LinkedListQueue<T>::last()
{
    T ans;
    if (!isEmpty())
        ans = _last->getVal();
    else
        ans = T();
    return ans;
}

/* Return whether the queue is empty or not */
template<class T>
bool LinkedListQueue<T>::isEmpty() { return _first == nullptr; }

/* Return the number of items in queue */
template<class T>
int LinkedListQueue<T>::count() { return _count; }