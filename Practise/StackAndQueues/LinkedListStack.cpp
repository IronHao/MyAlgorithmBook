#pragma once

#include "LinkedListStack.h"

template<class T>
LinkedListStack<T>::LinkedListStack()
{
    _top = nullptr;
    _count = 0;
}

template<class T>
LinkedListStack<T>::~LinkedListStack()
{
    delete _top;
}

/* Push the 'item' to the top of the stack */
template<class T>
void LinkedListStack<T>::push(T item)
{
    Node<T>* temp = new Node<T>(item);
    temp->setNext(_top);
    _top = temp;
    _count++;
}

/* Pop(delete) and return the item in the top of the stack */
template<class T>
T LinkedListStack<T>::pop()
{
    T ans;
    if (!isEmpty())
    {
        ans = _top->getVal();
        Node<T>* temp = _top;
        _top = _top->getNext();
        delete temp;
        _count--;
    }
    else
        ans = T();
    return ans;
}

/* Get the item in the top of the stack, no delete */
template<class T>
T LinkedListStack<T>::top()
{
    T ans;
    if (!isEmpty())
        ans = _top->getVal();
    else
        ans = T();
    return ans;
}

/* Return whether the stack is empty or not */
template<class T>
bool LinkedListStack<T>::isEmpty() { return _count == 0; }

/* Return the number of items in the stack */
template<class T>
int LinkedListStack<T>::count() { return _count; }