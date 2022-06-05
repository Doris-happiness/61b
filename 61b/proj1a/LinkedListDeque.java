public class LinkedListDeque<T> {
    public class IntNode {
        public T item;
        public IntNode next;
        public IntNode prev;
        public IntNode(T i, IntNode p, IntNode n) {
            item = i;
            prev = p;
            next = n;
        }
    }

    private int size;
    /** The Sentinel node*/
    IntNode dummy = new IntNode(null, null, null);

    /**  Creates an empty linked list deque. */
    public LinkedListDeque() {
        IntNode head = new IntNode(null, null, null);
        head.next = head;
        head.prev = head;
        dummy.next = head;
    }

    /** Adds an item of type T to the front of the deque. */
    public void addFirst(T item) {
        size += 1;
        IntNode head = dummy.next;
        IntNode firstNode = new IntNode(item, head, head.next);
        head.next.prev = firstNode;
        head.next = firstNode;
        /*
        IntNode node = new IntNode(item, null, null);
        node.prev = dummy.next.prev;
        dummy.next.prev.next = node;
        dummy.next.prev = node;
        node.next = dummy.next;
        dummy.next = node;

         */
    }

    /** Adds an item of type T to the back of the deque.*/
     public void addLast(T item) {
         size += 1;
         IntNode head = dummy.next;
         IntNode Lastnode = new IntNode(item, head.prev, head);
         head.prev.next = Lastnode;
         head.prev = Lastnode;
         /*
         IntNode node = new IntNode(item, null, null);
         node.prev = dummy.next.prev;
         dummy.next.prev.next = node;
         dummy.next.prev = node;
         node.next = dummy.next;

          */
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
         IntNode head = dummy.next;
         IntNode cur = head.next;
         while (cur.next != head) {
             System.out.print(cur.item);
             cur = cur.next;
         }
     }

     /** Removes and returns the item at the front of the deque. If no such item exists, returns null. */
     public T removeFirst() {
         if (!this.isEmpty()) {
             size -= 1;
             IntNode head = dummy.next;
             IntNode RemoveNode = head.next;
             head.next.next.prev = head;
             head.next = head.next.next;
             RemoveNode.next = null;
             RemoveNode.prev = null;
             return head.next.item;
             /*
             IntNode first = dummy.next;
             first.prev.next = first.next;
             first.next.prev = first.prev;
             first.prev = null;
             dummy.next = first.next;
             first.next = null;
             return dummy.next.item;

              */
         } else {
             return null;
         }
     }

     /** Removes and returns the item at the back of the deque. If no such item exists, returns null. */
     public T removeLast() {
         if (!this.isEmpty()) {
             size -= 1;
             IntNode head = dummy.next;
             IntNode removeLastNode = head.prev;
             head.prev.prev.next = head;
             head.prev = head.prev.prev;
             removeLastNode.prev = null;
             removeLastNode.next = null;
             /*
             IntNode last = dummy.next.prev;
             last.prev.next = dummy.next;
             last.next = null;
             dummy.next.prev = last.prev;
             last.prev = null;
             return dummy.next.prev.item;

              */
         }
         return null;
     }

     /** Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
      * If no such item exists, returns null. Must not alter the deque! */
     public T get(int index) {
         if (index >= size) {
             return null;
         }
         int count = 0;
         IntNode head = dummy.next;
         IntNode cur = head.next;
         while (count < index) {
             cur = cur.next;
             count += 1;
         }
         return cur.item;
     }

    /** Same as get, but uses recursion.*/
    public T getRecursive(int index) {
        return getRecursiveHelper(dummy.next.next, index).item;
    }

    private IntNode getRecursiveHelper(IntNode node, int index) {
        if (index == 0) {
            return node;
        } else if (node.next == dummy.next) {
            return null;
        }
        return getRecursiveHelper(node.next, index - 1);
    }

}
