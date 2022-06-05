public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        items = (T[]) new Object[8]; // Java里成功创建泛型的唯一方式就是创建一个被擦除类型的新数组,然后对其转型
        size = 0;
    }


    /** Adds an item of type T to the front of the deque. */
    public void addFirst(T item) {
        size += 1;
        if (nextFirst == nextLast) {
            resize(size * 4);
        } else {
            items[nextFirst] = item;
        }
        if (nextFirst == 0) {
            nextFirst = size - 1;
        } else {
            nextFirst -= 1;
        }
    }

    /** Adds an item of type T to the back of the deque.*/
    public void addLast(T item) {
        size += 1;
        if (nextFirst == nextLast) {
            resize(size * 4);
        } else {
            items[nextLast] = item;
        }
        if ((nextLast + 1) == size) {
            nextLast = 0;
        } else {
            nextLast += 1;
        }
    }

    /** Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        return size == 0;

    }

    /** Returns the number of items in the deque. */
    public int size() {
        return size;
    }

    /**  Prints the items in the deque from first to last, separated by a space. */
    public void printDeque() {
        int pos = (nextFirst + 1) % size;
        int count = 0;
        while (count < size) {
            System.out.print(items[pos]);
            pos = (pos + 1) % size;
            count += 1;
        }
    }

    /** Removes and returns the item at the front of the deque. If no such item exists, returns null. */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        nextFirst += 1;
        size -= 1;
        if (size == 0) {
            return null;
        } else {
            return items[nextFirst];
        }
    }

    /** Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null. Must not alter the deque! */
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        return items[index];
    }

    public void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        System.arraycopy(items, 0, a, 0, size);
        items = a;
    }

}
