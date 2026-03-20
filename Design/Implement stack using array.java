/*
  Problem Link: https://www.geeksforgeeks.org/problems/implement-stack-using-array/1
*/

// T.C: O(1) for all(push, pop, peek, isFull, isEmpty)
// S.C: O(n), where n = size of array used to store stack elements
class myStack {
    
    int[] arr;
    int front;
    int size;
    public myStack(int n) {
        // Define Data Structures
        arr = new int[n];
        front = -1;
        size = n;
    }

    public boolean isEmpty() {
        // check if the stack is empty
        return front == -1;
    }

    public boolean isFull() {
        // check if the stack is full
        return front >= size - 1;
    }

    public void push(int x) {
        // Inserts x at the top of the stack
        front++;
        arr[front] = x;
    }

    public void pop() {
        // Removes an element from the top of the stack
        if(front == -1){
            return;
        }
        arr[front] = 0;
        front--;
    }

    public int peek() {
        // Returns the top element of the stack
        if(front == -1){
            return -1;
        }
        
        return arr[front];
        
    }
}
