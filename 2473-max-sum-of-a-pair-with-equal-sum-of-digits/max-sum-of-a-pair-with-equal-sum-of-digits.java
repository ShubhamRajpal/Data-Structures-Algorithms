class Pair{
    int first, second;
    public Pair(int f, int s){
        first = f;
        second = s;
    }
}

class Solution {

    private int findDigitsSum(int num) {
        int sum = 0;
        int temp = num;

        while (temp != 0) {
            sum += temp % 10;
            temp = temp / 10;
        }

        return sum;
    }

    public int maximumSum(int[] nums) {
        int n = nums.length;
        // int maxSum = Integer.MIN_VALUE;

        // // Brute
        // for (int i = 0; i < n; i++) {
        //     int dSum1 = findDigitsSum(nums[i]);
        //     for (int j = i + 1; j < n; j++) {
        //         int dSum2 = findDigitsSum(nums[j]);
        //         if (dSum1 == dSum2) {
        //             maxSum = Math.max(maxSum, nums[i] + nums[j]);
        //         }
        //     }
        // }

        // return maxSum == Integer.MIN_VALUE ? -1 : maxSum;

        // Better
        // int[] digitSum = new int[n];
        int maxSum = Integer.MIN_VALUE;
        // PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        Map<Integer, PriorityQueue<Integer>> maxIndexMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int digitSum = findDigitsSum(nums[i]);
            PriorityQueue<Integer> temp =  maxIndexMap.getOrDefault(digitSum, new PriorityQueue<Integer>());
            temp.offer(nums[i]);
            maxIndexMap.put(digitSum, temp);
            if(maxIndexMap.get(digitSum).size() > 2){
                maxIndexMap.get(digitSum).poll();
            }
        }
        

        for (Map.Entry<Integer, PriorityQueue<Integer>> mp : maxIndexMap.entrySet()) {
            int digitSum = mp.getKey();
            PriorityQueue<Integer> p = mp.getValue();
            if(p.size() == 2){
                int first = p.poll();
                int second = p.poll();
                maxSum = Math.max(maxSum, first+second);
            }
            
            // System.out.println(digitSum +" "+ first+" "+second);
        }

        return maxSum == Integer.MIN_VALUE ? -1 : maxSum;
    }
}