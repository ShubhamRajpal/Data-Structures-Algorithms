/*
  Problem Link: https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended
*/

//Approach (Using Sorting and heap and hreedily picking the ones ending earliest)
//T.C : O(nlogn)
//S.C : O(n)
class Solution {
    public int maxEvents(int[][] events) {
        Arrays.sort(events, (a,b) -> a[0] - b[0]);

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int curDay = events[0][0];
        int cnt = 0;
        int i = 0;
        int n = events.length;

        while(!pq.isEmpty() || i < n){

            if(pq.isEmpty()){
                curDay = events[i][0];
            }

            while(i < n && curDay == events[i][0]){
                pq.offer(events[i][1]);
                i++;
            }

            if(!pq.isEmpty()){
                pq.remove();
                cnt++;
            }

            curDay++;

            while(!pq.isEmpty() && pq.peek() < curDay){
                pq.remove();
            }
        }   
        

        return cnt;
    }
}
