/*
  Problem Link: https://leetcode.com/problems/maximize-happiness-of-selected-children/
*/

//Approach-1 (Using Sorting)
//T.C : O(nlogn)
//S.C : O(1)
class Solution {
    public long maximumHappinessSum(int[] happiness, int k) {
        int n = happiness.length;
        Arrays.sort(happiness);

        long maxSum = 0;
        int cntChild = 0;
        for (int i = n - 1; i >= 0; i--) {

            maxSum += Math.max(happiness[i] - cntChild, 0);
            cntChild++;

            if (cntChild == k) {
                break;
            }
        }

        return maxSum;
    }
}

//Approach-2 (Using Max Heap)
//T.C : O(nlogn)
//S.C : O(n)
class Solution {
    public long maximumHappinessSum(int[] happiness, int k) {
        long result = 0;
        int count = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder()); // max-heap

        for (int hap : happiness) {
            pq.offer(hap);
        }

        for (int i = 0; i < k; i++) {
            int hap = pq.poll();
            result += Math.max(hap - count, 0);
            count++;
        }

        return result;
    }
}
