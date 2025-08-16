/*
  Problem Link:  https://leetcode.com/problems/maximum-69-number/
*/

//Approach-1 (With string converting)
//T.C : O(d) , d = number of dogits in num
//S.C : O(d)
class Solution {
    public int maximum69Number (int num) {
        StringBuilder n = new StringBuilder(String.valueOf(num));
        for(int i = 0; i < n.length(); i++){
            if(n.charAt(i) == '6'){
                n.setCharAt(i,'9');
                break;
            }
        }
        
        return Integer.parseInt(n.toString());
    }
}


//Approach-2 (Without converting to string)
//T.C : O(d) , d = number of digits in num
//S.C : O(1)
class Solution {
    public int maximum69Number(int num) {
        int temp = num;
        int idx = 0, pow = -1;
        while (temp != 0) {
            if (temp % 10 == 6) {
                pow = idx;
            }
            temp /= 10;
            idx++;
        }

        if (pow == -1) return num;

        num = num + ((int) Math.pow(10, pow) * 3);
        return num;
    }
}
