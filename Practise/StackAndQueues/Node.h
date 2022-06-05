#pragma once

using namespace std;

/* The one node in Linked-list */
template <class T>
class Node
{
private:
    T _val;
    Node<T>* _next;
public:
    Node(T val);
    Node(T val, Node<T>* next);
    ~Node();

    /* Get methods */
    T getVal();
    Node* getNext();
    
    /* Set methods */
    void setVal(T val);
    void setNext(Node<T>* next);
};