package com.alifesoftware;

public class Main {

    public static void main(String[] args) {
        //testRingBuffer();
        testRingBufferOverwritable();
    }

    private static void testRingBuffer() {
        RingBuffer<Integer> rBuffer = new RingBuffer<>(6);

        // Try to dequeue from empty array
        rBuffer.dequeue();

        // Add 6 items
        rBuffer.enqueue(1);
        rBuffer.enqueue(2);
        rBuffer.enqueue(3);
        rBuffer.enqueue(4);
        rBuffer.enqueue(5);
        rBuffer.enqueue(6);

        rBuffer.dequeue();
        rBuffer.dequeue();
        rBuffer.dequeue();
        rBuffer.dequeue();

        rBuffer.enqueue(7);
        rBuffer.enqueue(8);
        rBuffer.enqueue(9);
        rBuffer.enqueue(10);
        rBuffer.enqueue(11);

        rBuffer.dequeue();
        rBuffer.dequeue();
        rBuffer.dequeue();

        rBuffer.enqueue(11);
        rBuffer.enqueue(12);
        rBuffer.enqueue(13);
        rBuffer.enqueue(14);

        rBuffer.print();

    }

    private static void testRingBufferOverwritable() {
        RingBufferOverwritable<Integer> rBuffer = new RingBufferOverwritable<>(6);

        // Try to dequeue from empty array
        rBuffer.dequeue();

        // Add 6 items
        rBuffer.enqueue(1);
        rBuffer.enqueue(2);
        rBuffer.enqueue(3);
        rBuffer.enqueue(4);
        rBuffer.enqueue(5);
        rBuffer.enqueue(6);
        rBuffer.enqueue(7);
        rBuffer.enqueue(8);

        rBuffer.print();
    }
}
