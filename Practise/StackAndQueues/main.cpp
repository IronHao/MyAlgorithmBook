#include "Bag.h"
#include "Bag.cpp"
#include "ArrayStack.h"
#include "ArrayStack.cpp"
#include "LinkedListStack.h"
#include "LinkedListStack.cpp"
#include "ArrayQueue.h"
#include "ArrayQueue.cpp"
#include "LinkedListQueue.h"
#include "LinkedListQueue.cpp"

#include <iostream>
#include <cstdlib>
#include <ctime>
#include <string>

using namespace std;

/* 
 *  Simple test 
 *  1. Test the Bag structure:
        A. randomly input 10 elements, store them in bag and output all elements in bag.
    2. Test the Stack(array or linked-list implementation) structure:
        A. input serveral lines(one line as a item), and push them to stack, then pop and print it until the stack is empty.
    3. Test the Queue(array or linked-list implementation) structure:
        A. input serveral lines(one line as a item), and enqueue them, then dequeue and print it until the queue is empty.
 */
int main()
{
    /* Part1: Bag */
    // srand((int)time(0));
    // Bag<int> b = Bag<int>();
    // for (int i = 0; i < 10; i++)
    //     b.add(rand()%100);
    // int* ans = b.elements();
    // for (int i = 0; i < b.size(); i++)
    //     cout << ans[i] << " ";
    // cout << endl;

    /* Part2-1: ArrayStack */
    // ArrayStack<string> as = ArrayStack<string>();
    // string buf;
    // while (cin >> buf)
    // {
    //     if (buf == "End")
    //         break;
    //     as.push(buf);
    // }
    // cout << "The number of items is: " << as.count() << endl;
    // cout << "The size of stack is: " << as.size() << endl;
    // cout << "The top item of stack is: " << as.top() << endl;
    // cout << as.isEmpty() << endl;
    // while (!as.isEmpty())
    // {
    //     cout << as.pop() << " ";
    // }
    // cout << endl;
    // cout << "The number of items is: " << as.count() << endl;
    // cout << "The size of stack is: " << as.size() << endl;
    // cout << "The top item of stack is: " << as.top() << endl;

    /* Part2-2: LinkedListStack */
    // LinkedListStack<string> ls = LinkedListStack<string>();
    // string buf;
    // while (cin >> buf)
    // {
    //     if (buf == "End")
    //         break;
    //     ls.push(buf);
    // }
    // cout << "The number of items is: " << ls.count() << endl;
    // cout << "The top item of stack is: " << ls.top() << endl;
    // cout << ls.isEmpty() << endl;
    // while (!ls.isEmpty())
    // {
    //     cout << ls.pop() << " ";
    // }
    // cout << endl;
    // cout << "The number of items is: " << ls.count() << endl;
    // cout << "The top item of stack is: " << ls.top() << endl;
    // return 0;

    /* Part3-1: ArrayQueue */
    ArrayQueue<string> aq = ArrayQueue<string>();
    string buf;
    while (cin >> buf)
    {
        if (buf == "End")
            break;
        aq.enqueue(buf);
    }
    cout << "The number of items is: " << aq.count() << endl;
    cout << "The size of queue is: " << aq.size() << endl;
    cout << "The first item of queue is: " << aq.first() << endl;
    cout << "The last item of queue is: " << aq.last() << endl;
    cout << aq.isEmpty() << endl;
    while (!aq.isEmpty())
    {
        cout << aq.dequeue() << " ";
    }
    cout << endl;
    cout << "The number of items is: " << aq.count() << endl;
    cout << "The size of queue is: " << aq.size() << endl; 
    cout << "The first item of queue is: " << aq.first() << endl;
    cout << "The last item of queue is: " << aq.last() << endl;

    /* Part3-2: LinkedListQueue */
    // LinkedListQueue<string> lq = LinkedListQueue<string>();
    // string buf;
    // while (cin >> buf)
    // {
    //     if (buf == "End")
    //         break;
    //     lq.enqueue(buf);
    // }
    // cout << "The number of items is: " << lq.count() << endl;
    // cout << "The first item of queue is: " << lq.first() << endl;
    // cout << "The last item of queue is: " << lq.last() << endl;
    // cout << lq.isEmpty() << endl;
    // while (!lq.isEmpty())
    // {
    //     cout << lq.dequeue() << " ";
    // }
    // cout << endl;
    // cout << "The number of items is: " << lq.count() << endl;
    //  cout << "The first item of queue is: " << lq.first() << endl;
    // cout << "The last item of queue is: " << lq.last() << endl;
    return 0;
}