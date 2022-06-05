#pragma once

#include "Node.h"

template <class T>
Node<T>::Node(T val)
{
    setVal(val);
    setNext(nullptr);
}

template <class T>
Node<T>::Node(T val, Node<T>* next)
{
    setVal(val);
    setNext(next);
}

template <class T>
Node<T>::~Node() {};

/* Get methods */
template <class T>
T Node<T>::getVal() { return _val; }

template <class T>
Node<T>* Node<T>::getNext() { return _next; }

/* Set methods */
template <class T>
void Node<T>::setVal(T val) { _val = val; }
    
template <class T>
void Node<T>::setNext(Node<T>* next) { _next = next; }