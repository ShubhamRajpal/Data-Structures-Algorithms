/*
  Problem Link: https://leetcode.com/problems/zero-array-transformation-iii/
*/

//Approach (Using heaps)
//T.C : O(QlogQ + n + QlogQ), where Q: No. of Queries, n: size of array
//S.C : O(Q)
class Solution {
    public int maxRemoval(int[] nums, int[][] queries) {
        int Q = queries.length;
        int n = nums.length;
        
        // Max-heap for available queries (stores end indices)
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);

        // Min-heap for used queries (stores end indices)
        PriorityQueue<Integer> past = new PriorityQueue<>();

        // Sort queries by start index
        Arrays.sort(queries, (a, b) -> {
            return a[0] - b[0];
        });

        int used = 0;
        int j = 0;
        for (int i = 0; i < n; i++) {
            
            // Push all queries starting at index i
            while (j < Q && queries[j][0] == i) {
                maxHeap.offer(queries[j][1]);
                j++;
            }
            
            // Apply effect of already used queries
            nums[i] -= past.size();
  
            // Apply more queries if needed
            while (nums[i] > 0 && !maxHeap.isEmpty() && maxHeap.peek() >= i) {
                int ending = maxHeap.peek();
                maxHeap.poll();
                used++;
                past.offer(ending);
                nums[i]--;
            }
            
            // If we can't make nums[i] zero
            if (nums[i] > 0) {
                return -1;
            }

            // Remove expired queries
            while (!past.isEmpty() && past.peek() <= i) {
                past.poll();
            }
        }

        return Q - used;
    }
}
