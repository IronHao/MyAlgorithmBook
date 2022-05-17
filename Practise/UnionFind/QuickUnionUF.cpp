#include <iostream>
#include <fstream>
#include <string>

using namespace std;

/* Use the quick-union method */
class QuickUnionUF
{
private:
    int* _parent;
    int _count;

    int length(int* arr) { return _msize(arr) / sizeof(*arr); }

    int depth(int node)
    {
        int dp = 1;
        while (_parent[node] != node)
        {
            node = _parent[node];
            dp++;
        }
        return dp;
    }
public:
    QuickUnionUF(int size)
    {
        _parent = new int[size];
        // Init, the parent is itself.
        for (int i = 0; i < size; i++)
            _parent[i] = i;
        _count = size;
    }
    ~QuickUnionUF()
    {
        delete[] _parent;
    }

    /* Check whether these nodes are connected */ 
    bool isConnected(int node1, int node2) { return find(node1) == find(node2); }

    /* Union the node1 and node2, let node1_root's parent to node2_root */
    void unionNode(int node1, int node2)
    {
        int node1_root = find(node1);
        int node2_root = find(node2);
        // Same root, already connect, no need to do anything
        if (node1_root == node2_root)
            return;
        // Connect, let node1 be the subtree of the node2
        _parent[node1_root] = node2_root;
        _count--;
    }

    /* Return the identification(root) of the specific node */
    int find(int node)  
    {
        // To find the root, which parent is itself.
        while (_parent[node] != node)
            node = _parent[node];
        return node;
    }

    /* Return the number of connected component */ 
    int count() { return _count; }

    /* Show the nubmer of nodes, the number of connected component, the depth and the "nodes -> it's root" */
    void show()
    {
        int len = length(_parent);
        cout << "The nodes number: " + to_string(len) << endl;
        cout << "The connected components number: " + to_string(count()) << endl;
        int dp = 0;
        int tempD;
        for(int i = 0; i < len; i++)
        {
            int tempD = depth(i);
            if (tempD > dp)
                dp = tempD;
        }
        cout << "The depth: " + to_string(dp) << endl;
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
    fin.open("Sample_mid.txt", ios::in);
    if (!fin.is_open())
        cerr << "Cannot open the file" << endl;
    char buf[1021] = {0};
    // First line is the number of nodes
    fin.getline(buf, sizeof(buf));
    QuickUnionUF qf = QuickUnionUF(stoi(string(buf)));
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
    qf.show();
    return 0;
}