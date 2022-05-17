import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    private int size;
    private Node first, last;

    private class Node
    {
        Item item;
        Node next;
        Node prev;

        public Node(Item i)
        {
            item = i;
            next = null;
            prev = null;
        }
    }

    // construct an empty deque
    public Deque()
    {
        first = null;
        last = null;
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() { return first == null; }

    // return the number of items on the deque
    public int size() { return size; }

    // add the item to the front
    public void addFirst(Item item)
    {
        if (item == null)
            throw new IllegalArgumentException();
        Node addOne = new Node(item);
        if (first == null)
        {
            first = addOne;
            last = addOne;
        }
        else
        {
            first.prev = addOne;
            addOne.next = first;
            first = addOne;
        }
        size++;
    }

    // add the item to the back
    public void addLast(Item item)
    {
        if (item == null)
            throw new IllegalArgumentException();
        Node addOne = new Node(item);
        if (last == null)
        {
            first = addOne;
            last = addOne;
        }
        else
        {
            last.next = addOne;
            addOne.prev = last;
            last = addOne;
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst()
    {
        if (isEmpty())
            throw new java.util.NoSuchElementException();
        Item ans = first.item;
        first = first.next;
        if (first != null)
            first.prev = null;
        size--;
        if (size == 0)
        {
            first = null;
            last = null;
        }
        return ans;
    }

    // remove and return the item from the back
    public Item removeLast()
    {
        if (isEmpty())
            throw new java.util.NoSuchElementException();
        Item ans = last.item;
        last = last.prev;
        if (last != null)
            last.next = null;
        size--;
        if (size == 0)
        {
            first = null;
            last = null;
        }
        return ans;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() { return new DequeIterator(); }

    private class DequeIterator implements Iterator<Item>
    {
        private Node cur = first;

        public boolean hasNext() { return cur != null; }
        public void remove() { throw new UnsupportedOperationException(); }
        public Item next()
        {
            if (cur == null)
                throw new java.util.NoSuchElementException();

            Item item = cur.item;
            cur = cur.next;
            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args)
    {
        Deque<String> d = new Deque<String>();
        StdOut.println(d.isEmpty());
        d.addFirst("a");
        d.addFirst("e");
        for (String i : d)
        {
            StdOut.print(i + " ");
        }
        StdOut.printf("\n");
        StdOut.println(d.removeFirst());
        for (String i : d)
        {
            StdOut.print(i + " ");
        }
        StdOut.printf("\n");
        StdOut.println(d.removeLast());
        for (String i : d)
        {
            StdOut.print(i + " ");
        }
        StdOut.printf("\n");
        StdOut.println(d.size());
        StdOut.println(d.isEmpty());
    }

}
