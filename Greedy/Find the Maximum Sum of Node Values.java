/*
  Problem Link: https://leetcode.com/problems/find-the-maximum-sum-of-node-values/description/
*/

//Approach-1 (Greedily picking nodes to xor)
//T.C : O(n)
//S.C : O(1)
class Solution {
    public long maximumValueSum(int[] nums, int k, int[][] edges) {
        long sum = 0;
        long count = 0;
        long minLoss = Integer.MAX_VALUE;

        for(long num : nums){
            if((num ^ k ) > num){
                sum += (num ^ k);
                count++;
            }else{
                sum += num;
            }

            minLoss = Math.min(minLoss, Math.abs(num - (num ^ k)));
        }

        if(count % 2 == 0){
            return sum;
        }

        return sum - minLoss;
    }
}

//Approach-2(Greedy  + Sorting)
//T.C : O(nlogn)
//S.C : O(n)
class Solution {
    public long maximumValueSum(int[] nums, int k, int[][] edges) {
        List<Integer> fayda = new ArrayList<>();
        long normalSum = 0;

        for (int num : nums) {
            fayda.add((num ^ k) - num);
            normalSum += (long) num;
        }

        Collections.sort(fayda, Collections.reverseOrder());

        for (int i = 0; i < fayda.size() - 1; i += 2) {
            long pairSum = fayda.get(i) + fayda.get(i + 1);

            if (pairSum > 0) {
                normalSum += pairSum;
            }
        }
        return normalSum;
    }
}
