package Task2;

class Queue {
    Node head, tail;
    int size;

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            next = null;
        }
    }

    public Queue() {
        this.tail = null;
        this.head = null;
        this.size = 0; // Initialize size to 0
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void enqueue(int item) {
        Node node = new Node(item);
        if (isEmpty()) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
        size++; // Increment size when enqueueing
    }

    public int dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return Integer.MIN_VALUE; // Return a sentinel value if the queue is empty
        }
        int item = head.data;
        if (head != tail) {
            head = head.next;
        } else {
            head = null; // If there was only one node, set head and tail to null
            tail = null;
        }
        size--; // Decrement size when dequeueing
        return item;
    }

    public int getSize() {
        return size; // Return the current size of the queue
    }
}