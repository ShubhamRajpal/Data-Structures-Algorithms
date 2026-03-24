/*
  Problem Link: https://www.geeksforgeeks.org/problems/implement-stack-using-linked-list/1 
*/

// Approach (use a temporary pointer)
// T.C: O(1)
// S.C: O(1)
// Node class
/* class Node {
    int data;
    Node next;

    Node(int new_data) {
        data = new_data;
        next = null;
    }
} */

// Stack class
class myStack {
    Node head;
    int size;

    public myStack() {
        // Initialize your data members
       head  = null;
       size = 0;
    }

    public boolean isEmpty() {
        // check if the stack is empty
        
        return size == 0;
    }

    public void push(int x) {
        // Adds an element x at the rear of the stack.
        Node newNode = new Node(x);
        newNode.next = head;
        head = newNode;
        size++;
        
    }

    public void pop() {
        // Removes the front element of the stack.
        if(head == null) return;
        
        head = head.next;
        size--;
    }

    public int peek() {
        // Returns the front element of the stack.
        // If stack is empty, return -1.
        if(head == null){
            return -1;
        }
        
        return head.data;
        
        
    }

    public int size() {
        // Returns the current size of the stack.
        
        return size;
    }
}
