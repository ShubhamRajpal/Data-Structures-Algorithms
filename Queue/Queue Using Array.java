/*
  Problem Link: https://www.geeksforgeeks.org/problems/implement-queue-using-array/1
*/

// Approach - (using extra space)
// T.C - O(1) for all operations
// S.C - O(n) for extra array

class myQueue {

    // Constructor
    int[] arr;
    int front, rear, size, curSize;
    
    public myQueue(int n) {
        // Define Data Structures
        arr = new int[n];
        front = -1;
        rear = -1;
        size = n;
        curSize = 0;
    }

    public boolean isEmpty() {
        // Check if queue is empty
        return curSize == 0;
    }

    public boolean isFull() {
        return curSize == size;
        // Check if queue is full
    }

    public void enqueue(int x) {
        // Enqueue
        if(curSize == size) return;
        
        if(front == -1) front++;
        rear++;
        if(rear >= size){
            rear %= size;
        }
        arr[rear] = x;
        curSize++;
    }

    public void dequeue() {
        // Dequeue
        if(curSize == 0) return;
        
        arr[front] = 0;
        front++;
        
        curSize--;
        
    }

    public int getFront() {
        // Get front element
        if(curSize == 0){
            return -1;
        }
        
        return arr[front];
    }

    public int getRear() {
        // Get last element
        
        if(curSize == 0){
            return -1;
        }
        
        return arr[rear];
    }
}
