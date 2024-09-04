package Task3;

import java.util.Arrays;

public class Heap {
    private int[] heap;
    private int size;
    private int capacity;

    // Constructor to initialize the heap
    public Heap(int capacity) {
        this.capacity = capacity;
        this.heap = new int[capacity];
        this.size = 0;
    }

    // Insert a new element into the heap
    public void insert(int value) {
        if (size >= capacity) {
            System.out.println("Heap is full");
            return;
        }
        heap[size] = value;  // Add new value at the end of the heap
        size++;
        heapifyUp(size - 1);  // Restore heap property
    }

    // Heapify up to maintain the heap property
    private void heapifyUp(int i) {
        int x;
        while (i > 0) {
            x = (i - 1) / 2;  // Find parent index
            if (heap[i] < heap[x]) {  // Compare child with parent (Min-Heap)
                swap(i, x);  // Swap if child is smaller
                i = x;  // Move up to parent's index
            } else {
                break;  // Heap property is restored
            }
        }
    }

    // Delete the root element (Min for Min-Heap)
    public int delete() {
        if (size == 0) {
           System.out.println("Heap is empty");
        }
        int root = heap[0];  // Store the root to return
        heap[0] = heap[size - 1];  // Move the last element to the root
        size--;
        heapifyDown(0);  // Restore heap property
        return root;
    }

    // Heapify down to maintain the heap property
    private void heapifyDown(int i) {
        int leftChild, rightChild, smallest;
        while (true) {
            leftChild = 2 * i + 1;  // Calculate left child index
            rightChild = 2 * i + 2;  // Calculate right child index
            smallest = i;

            // Check if left child exists and is smaller
            if (leftChild < size && heap[leftChild] < heap[smallest]) {
                smallest = leftChild;
            }

            // Check if right child exists and is smaller
            if (rightChild < size && heap[rightChild] < heap[smallest]) {
                smallest = rightChild;
            }

            // If the smallest is not the current index, swap
            if (smallest != i) {
                swap(i, smallest);
                i = smallest;  // Move down to the smallest child's index
            } else {
                break;  // Heap property is restored
            }
        }
    }

    // Swap two elements in the heap
    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    // Search for an element in the heap
    public boolean search(int value) {
        for (int i = 0; i < size; i++) {
            if (heap[i] == value) {
                return true;  // Element found
            }
        }
        return false;  // Element not found
    }

    // Sort the heap using heap sort
    public void sort() {
        int originalSize = size;  // Store the original size
        for (int i = size - 1; i >= 0; i--) {
            swap(0, i);  // Move the root to the end of the array
            size--;  // Reduce the size of the heap
            heapifyDown(0);  // Restore the heap property
        }
        size = originalSize;  // Restore the original size after sorting
    }

    // Get the root element (min for Min-Heap)
    public int getRoot() {
        if (size == 0) {
            System.out.println("Heap is empty");
        }
        return heap[0];
    }

    // Replace the root element with a new value and reheapify
    public void replace(int value) {
        if (size == 0) {
            System.out.println("Heap is empty");
        }
        heap[0] = value;  // Replace root with new value
        heapifyDown(0);  // Reheapify to maintain heap property
    }

    // Merge another heap into the current heap
    public void merge(Heap otherHeap) {
        for (int i = 0; i < otherHeap.size; i++) {
            insert(otherHeap.heap[i]);
        }
    }

    // Extract the root element (min for Min-Heap)
    public int extractRoot() {
        return delete();  // Delete operation already removes and returns the root
    }

    // Display the heap
    public void displayHeap() {
        int[] actualHeap = Arrays.copyOfRange(heap, 0, size);
        System.out.println(Arrays.toString(actualHeap));
    }


    public static void main(String[] args) {
        Heap minHeap = new Heap(5);  // Create a Min-Heap with capacity 5

        // Insert elements
        minHeap.insert(3);
        minHeap.insert(1);
        minHeap.insert(6);
        minHeap.insert(5);

        System.out.println("Heap after insertion:");
        minHeap.displayHeap();

        // Delete root element
        System.out.println("Deleted root: " + minHeap.delete());
        minHeap.displayHeap();

        // Search for an element
        System.out.println("Search for 5: " + minHeap.search(5));

        // Sort the heap
        System.out.println("Heap before sorting:");
        minHeap.displayHeap();
        minHeap.sort();
        System.out.println("Heap after sorting:");
        minHeap.displayHeap();

        // Get the root element
        System.out.println("Root element: " + minHeap.getRoot());

        // Replace the root element
        minHeap.replace(4);
        System.out.println("Heap after replacing root:");
        minHeap.displayHeap();

        // Extract the root element
        System.out.println("Extracted root: " + minHeap.extractRoot());
        minHeap.displayHeap();

        // Merge with another heap
        Heap anotherHeap = new Heap(5);
        anotherHeap.insert(7);
        anotherHeap.insert(9);
        minHeap.merge(anotherHeap);
        System.out.println("Heap after merging:");
        minHeap.displayHeap();
    }
}
