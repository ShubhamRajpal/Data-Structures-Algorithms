/*
  Problem Link: https://leetcode.com/problems/calculate-money-in-leetcode-bank/
*/

//Approach-1 (Simple simulation)
//T.C : O(N) 
//S.C : O(1)
class Solution {
    public int totalMoney(int n) {
        int start = 1, daysCnt = 1, prev = 1, sum = 0;
        while(n > 0){
            if(daysCnt == 8){
                prev++;
                start = prev;
                daysCnt = 1;
            }

            sum += start;
            start++;
            daysCnt++;
            n--;
        }

        return sum;
    }
}


//Approach-2 (Using AP and a for loop)
//T.C : O(1)
//S.C : O(1)
class Solution {
    public int totalMoney(int n) {
        int terms = n / 7; // Weeks

        int first = 28;
        int last = 28 + (terms - 1) * 7; // AP formula for n-th term

        int result = terms * (first + last) / 2; // Sum of nth terms in an AP

        // Final week remaining days = n % 7
        int startMoney = 1 + terms;

        for (int day = 1; day <= (n % 7); day++) {
            result += startMoney;
            startMoney++;
        }

        return result;
    }
}


//Approach-3 (Using AP completely)
//T.C : O(1) - see the video above to know why
//S.C : O(1)
class Solution {
    public int totalMoney(int n) {
        int fullWeeks = n / 7;
        int extraDays = n % 7;

        int sum1 = ((7 * fullWeeks) * (fullWeeks + 7)) / 2;
        int sum2 = (extraDays * (2 * fullWeeks + extraDays + 1)) / 2;

        return sum1 + sum2;
    }
}
