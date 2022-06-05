#pragma once

#include "Bag.h"

template<class T>
Bag<T>::Bag() 
{
    _head = nullptr;
    _size = 0;    
}

template<class T>
Bag<T>::~Bag() {}

/* Insert in the head, no need to traverse, time complexity: O(1) */ 
template<class T>
void Bag<T>::add(T val)
{
    Node<T>* n = new Node<T>(val);
    n->setNext(_head);
    _head = n;
    _size++;
}

/* Return whether the bag is empty or not */
template<class T>
bool Bag<T>::isEmpty() { return _size == 0; }

/* Return the number of elements in bag */
template<class T>
int Bag<T>::size() { return _size; }

/* Store the value in array and return */
template<class T>
T* Bag<T>::elements()
{
    T* ans = new T[_size];
    int count = 0;
    Node<T>* cur = _head;
    while (cur != nullptr)
    {
        ans[count] = cur->getVal(); 
        cur = cur->getNext();
        count++;
    }
    return ans;
};
