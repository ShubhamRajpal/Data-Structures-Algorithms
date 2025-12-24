/*
  Problem Link: https://leetcode.com/problems/apple-redistribution-into-boxes
*/

//Approach-1 (Using Arrays.sort so that we can simply assign the apples to largest boxes first)
//T.C : O(n + m log m)
//S.C : O(1)
class Solution {
    public int minimumBoxes(int[] apple, int[] capacity) {
        
        int totalApples = Arrays.stream(apple).sum();
        
        // Sort capacity in descending order
        Arrays.sort(capacity);

        int minBoxes = 0;
        int n = capacity.length;
        for(int cap = n-1; cap >= 0; cap--){
            minBoxes += capacity[cap];
            if(minBoxes >= totalApples){
                return n - cap;
            }
        }
        return 0; 
    }
}

//Approach-2 (Using counting sort so that we can simply assign the apples to largest boxes first)
//T.C : O(n + m)
//S.C : O(1)
class Solution {
    public int minimumBoxes(int[] apple, int[] capacity) {

        int totalApples = Arrays.stream(apple).sum();

        // Counting sort frequency array (capacity range: 1 to 50)
        int[] cap = new int[51];
        for (int c : capacity) {
            cap[c]++;
        }

        // Use boxes with maximum capacity first
        int curCap = 50;
        int boxes = 0;
        while (totalApples > 0) {
            while (totalApples > 0 && cap[curCap] > 0) {
                cap[curCap]--;
                boxes++;
                totalApples -= curCap;
            }
            curCap--;
        }

        return boxes;
    }
}
