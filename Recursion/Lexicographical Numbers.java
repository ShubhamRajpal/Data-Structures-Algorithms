/*
  Problem Link: https://leetcode.com/problems/lexicographical-numbers/description/
*/

//Approach-1 (Simple Recursion - DFS)
//T.C : O(n) - We visit each number (1 to n) only once.
//S.C : O(d) - where d is the number of digits in n i.e. log10(n)
class Solution {

    private void solve(int curr, int n, List<Integer> result) {
        if (curr > n) {
            return;
        }
        
        result.add(curr);  // Add current number to result
        
        // Generate the next number by appending digits 0 to 9
        for (int nextDigit = 0; nextDigit <= 9; nextDigit++) {
            int nextNum = curr * 10 + nextDigit;
            
            if (nextNum > n) {
                return;
            }
            
            solve(nextNum, n, result);  // Recursive call
        }
    }

    public List<Integer> lexicalOrder(int n) {
        List<Integer> result = new ArrayList<>();
        
        // Start from numbers 1 to 9
        for (int num = 1; num <= 9; num++) {
            solve(num, n, result);
        }
        
        return result;  // Return the result list
    }
}

//Approach-2 (Using constant space)
//T.C : O(n) - We visit each number (1 to n) only once.
//S.C : O(1)
class Solution {
    public List<Integer> lexicalOrder(int n) {
        List<Integer> ans = new ArrayList<>();
        int curNum = 1;

        for (int i = 0; i < n; i++) {
            ans.add(curNum);

            if (curNum * 10 > n) {
                // Check if curNum has exceed the n, then move one level back up
                while (curNum >= n || curNum % 10 == 9) {
                    curNum /= 10;
                }
                curNum += 1;
            } else {
                curNum *= 10;
            }
        }

        return ans;
    }
}
