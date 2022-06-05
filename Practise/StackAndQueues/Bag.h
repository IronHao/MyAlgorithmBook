#pragma once

#include "Node.h"
#include "Node.cpp"

using namespace std;

/* 
 * Bag data structure, no specific store way, here we use linked-list to store the data.
 * It has only add() method, no remove() method.
 * The element in bag has no specific order.
 */
template <class T>
class Bag
{
private:
    Node<T>* _head;
    int _size;
public:
    Bag();
    ~Bag();

    /* Insert in the head, no need to traverse, time complexity: O(1) */ 
    void add(T val);

    /* Return whether the bag is empty or not */
    bool isEmpty();

    /* Return the number of elements in bag */
    int size();

    /* Store the value in array and return */
    T* elements();
};

