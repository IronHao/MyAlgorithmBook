#include <iostream>
#include <fstream>
#include <string>

using namespace std;

/* Use the quick-find method */
class QuickFindUF
{
private:
    int* _root;
    int _count;

    int length(int* arr) { return _msize(arr) / sizeof(*arr); }

public:
    QuickFindUF(int size)
    {
        _root = new int[size];
        // Init, the root is itself.
        for (int i = 0; i < size; i++)
            _root[i] = i;
        _count = size;
    }
    ~QuickFindUF()
    {
        delete[] _root;
    }

    /* Check whether these nodes are connected */ 
    bool isConnected(int node1, int node2) { return _root[node1] == _root[node2]; }

    /* Union the node1 and node2, let all node's root is same as node1_root to node2_root */
    void unionNode(int node1, int node2)
    {
        int node1_root = _root[node1];
        int node2_root = _root[node2];
        // Same root, already connect, no need to do anything
        if (node1_root == node2_root)
            return;
        // Connect
        int len = length(_root);
        for (int i = 0; i < len; i++)
            if (_root[i] == node1_root)
                _root[i] = node2_root;
        _count--;
    }

    /* Return the identification(root) of the specific node */
    int find(int node)  { return _root[node]; }

    /* Return the number of connected component */ 
    int count() { return _count; }

    /* Show the nubmer of nodes, the number of connected component and the "nodes -> it's root" */
    void Show()
    {
        int len = length(_root);
        cout << "The nodes number: " + to_string(len) << endl;
        cout << "The connected components number: " + to_string(count()) << endl;
        cout << "Node\t -> it's root" << endl;
        for(int i = 0; i < len; i++)
            cout << to_string(i) + "\t -> " + to_string(find(i)) << endl;
    }
};

/*
 * A simple test:
 * 1. Read the 'Sample.txt' file, which fisrt row specifies the number of nodes and the other rows each specify the two nodes should union.   
 * 2. Do union and show the answer.
 */
int main()
{
    // Read data from file 
    ifstream fin;
    fin.open("Sample.txt", ios::in);
    if (!fin.is_open())
        cerr << "Cannot open the file" << endl;
    char buf[1021] = {0};
    // First line is the number of nodes
    fin.getline(buf, sizeof(buf));
    QuickFindUF qf = QuickFindUF(stoi(string(buf)));
    int node1, node2, separator;
    string s;
    while (fin.getline(buf, sizeof(buf)))
    {
        s = string(buf);
        separator = s.find(" ");
        node1 = stoi(s.substr(0, separator));
        node2 = stoi(s.substr(separator+1));
        qf.unionNode(node1, node2);
    }
    qf.Show();
    return 0;
}


