class Solution {
    public int minOperations(int[] nums, int k) {
        PriorityQueue<Long> pq = new PriorityQueue<>();

        for(long it : nums){
            pq.offer(it);
        }

        int cnt = 0;
        while(!pq.isEmpty() && pq.peek() < k){
            long min = pq.poll();
            long max = pq.poll();

            cnt++;
            pq.offer((min*2) + max);
           
        }

        return cnt;
    }
}