import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] queue;
    private int capacity;
    private int size;

    // construct an empty randomized queue
    public RandomizedQueue()
    {
        capacity = 1;
        size = 0;
        queue = (Item[]) new Object[capacity];
    }

    // is the randomized queue empty?
    public boolean isEmpty() { return size == 0; }

    // return the number of items on the randomized queue
    public int size() { return size; }

    // add the item
    public void enqueue(Item item)
    {
        if (item == null)
            throw new IllegalArgumentException();

        if (size == capacity)
            resize();
        queue[size] = item;
        size++;
    }

    // remove and return a random item
    public Item dequeue()
    {
        if (isEmpty())
            throw new java.util.NoSuchElementException();

        int removePos = StdRandom.uniform(0, size) % capacity;
        Item ans = queue[removePos];
        while (removePos != size)
        {
            queue[removePos] = queue[(removePos+1) % capacity];
            removePos++;
        }
        size--;
        return ans;
    }

    // return a random item (but do not remove it)
    public Item sample() 
    {
        if (isEmpty())
            throw new java.util.NoSuchElementException();

        return queue[StdRandom.uniform(0, size) % capacity]; 
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() { return new RandomizedQueueIterator(); }

    private class RandomizedQueueIterator implements Iterator<Item>
    {
        private int curPos = 0;

        public boolean hasNext() { return curPos != size; }
        public void remove() { throw new UnsupportedOperationException(); }
        public Item next()
        {
            if (curPos == size)
                throw new java.util.NoSuchElementException();
            return queue[curPos++];
        }
    }

    private void resize()
    {
        Item[] copy = (Item[]) new Object[capacity*2];
        for (int i = 0; i < size(); i++)
        {
            copy[i] = queue[i];
        }
        queue = copy;
        capacity = capacity * 2;
    }

    // unit testing (required)
    public static void main(String[] args)
    {
        RandomizedQueue<Integer> q = new RandomizedQueue<>();
        StdOut.println(q.isEmpty());
        q.enqueue(13);
        q.enqueue(15);
        q.enqueue(20);
        StdOut.println(q.sample());
        for (Integer i : q)
        {
            StdOut.print(String.valueOf(i) + " ");
        }
        StdOut.print("\n");
        StdOut.println(q.dequeue());
        for (Integer i : q)
        {
            StdOut.print(i + " ");
        }
        StdOut.print("\n");
    }

}