package com.alifesoftware;

import com.sun.istack.internal.NotNull;

/**
 * Created by anuj on 11/13/16.
 *
 * This RingBuffer overwrites existing items
 * in the buffer on enqueue operation when the
 * buffer is full
 *
 */
public class RingBufferOverwritable<E> {
    // Default Size for Ring Buffer, in case
    private final static int DEFAULT_SIZE = 10;

    // Array to store the items in buffer
    private E[] buffer;

    // Read Index
    private int read = 0;

    // Write Index
    private int write = 0;

    // Current Size
    private int size = 0;

    /**
     * Default Constructor - uses default
     * size for the buffer
     *
     */
    @SuppressWarnings("unchecked")
    public RingBufferOverwritable() {
        buffer = (E[]) new Object[DEFAULT_SIZE];
    }

    /**
     * Constructor with given size for the
     * buffer
     *
     * @param bufferSize
     */
    @SuppressWarnings("unchecked")
    public RingBufferOverwritable(final int bufferSize) {
        // Handle negative or 0 size
        if(bufferSize <= 0) {
            throw new IllegalArgumentException("Buffer size must be greater than 0");
        }
        buffer = (E[]) new Object[bufferSize];
    }

    /**
     * Method to enqueue an object in the
     * buffer
     *
     * @param toSave
     * @return
     */
    public synchronized boolean enqueue(@NotNull E toSave) {
        // Check if the object to be saved is null
        if(toSave == null) {
            System.out.println("Object to enqueue is null - return");
            return false;
        }

        // If the buffer is full, return false
        if(isBufferFull()) {
            System.out.println("Buffer is full - dequeue and enqueue");
            dequeue();
        }

        // Add the object to the buffer, and increment
        // the write index
        buffer[write++] = toSave;

        // Note: Special case is that when write index
        // crosses the index bounds, then reset the position
        // to 1
        if(write == buffer.length) {
            write = 0;
        }

        // Increment the size
        size++;

        System.out.println("Write: " + write + " Read: " + read + " Size: " + size + " Added: " + toSave);
        return true;
    }

    /**
     * Method to dequeue an object from the
     * buffer
     *
     * @return
     */
    public synchronized E dequeue() {
        // If buffer is empty, return null
        if(isEmpty()) {
            System.out.println("Buffer is empty - return null");
            return null;
        }

        // Return the object at index read, and increment
        // the read index
        E retVal = buffer[read++];

        // Note: Special case is that when read index
        // crosses the index bounds, then reset the position
        // to 1
        if(read == buffer.length) {
            System.out.println("Resetting read index to 0");
            read = 0;
        }

        // Decrement the size
        size--;

        System.out.println("Write: " + write + " Read: " + read + " Size: " + size + " Removed: " + retVal);
        return retVal;
    }

    /**
     * Method to check if the buffer is empty
     *
     * @return
     */
    private boolean isEmpty() {
        return (size == 0);
    }

    /**
     * Method to check if buffer is full
     *
     * This is perhaps the most interesting
     * method of this class
     *
     * @return
     */
    private boolean isBufferFull() {
        return (size == buffer.length);
    }

    /**
     * Method to print the buffer
     *
     */
    public void print() {
        System.out.println("Dumping the Buffer\n\n");
        int ndx = read;
        while(ndx <= write) {
            System.out.println("Index: " + ndx + " Value: " + buffer[ndx]);
            ndx++;
        }
    }
}
