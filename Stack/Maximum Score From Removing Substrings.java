/*
  Problem Link: https://leetcode.com/problems/maximum-score-from-removing-substrings/
  Similar Problem : LC: 1003 Check If Word Is Valid After Substitutions (Solve this for Approach 2)
*/

//Approach-1 (Greedy + Stack)
//T.C : O(n)
//S.C : O(n)
class Solution {
    public String findStr(String pair, String s){
        Stack<Character> st = new Stack<>();
        int n = s.length();
        for(int i = 0; i < n; i++){
            if(!st.isEmpty() && st.peek() == pair.charAt(0) && s.charAt(i) == pair.charAt(1)){
                st.pop();
            }
            else st.push(s.charAt(i));
        }

        StringBuilder remString = new StringBuilder();
        while(!st.isEmpty()){
            remString.append(st.pop());
        }

        return remString.reverse().toString();
    }
    public int maximumGain(String s, int x, int y) {
        
        int n = s.length();
        Stack<Character> st = new Stack<>();
        String maxPointsPair = x >= y ? "ab" : "ba";
        String minPointsPair = maxPointsPair == "ab" ? "ba" : "ab";
        int cntX = 0, cntY = 0;
        int totalMaxScore = 0;
        String maxPointsAfterFirstPass = findStr(maxPointsPair, s);

        int maxPairPoints = (s.length() - maxPointsAfterFirstPass.length())/2;
        totalMaxScore = maxPairPoints * Math.max(x,y);

        String minPointsAfterSecondPass = findStr(minPointsPair, maxPointsAfterFirstPass);

        int minPairPoints = (maxPointsAfterFirstPass.length() - minPointsAfterSecondPass.length())/2;
        totalMaxScore += minPairPoints * Math.min(x,y);
        

        return totalMaxScore;
    }
}

//Approach-2 (Without Stack)
//T.C : O(n)
//S.C : O(1)
class Solution {
    public String findStr(String pair, String s) {
        int i = 0, j = 0;
        int n = s.length();
        StringBuilder sb = new StringBuilder(s);
        while (j < n) {
            sb.setCharAt(i, sb.charAt(j));
            i++;

            if (i >= 2 && ((sb.substring(i - 2, i).toString()).equals(pair))) {
                i -= 2;
            }
            j++;
        }

        return sb.substring(0, i).toString();

    }

    public int maximumGain(String s, int x, int y) {

        int n = s.length();
        Stack<Character> st = new Stack<>();
        String maxPointsPair = x >= y ? "ab" : "ba";
        String minPointsPair = maxPointsPair == "ab" ? "ba" : "ab";
        int cntX = 0, cntY = 0;
        int totalMaxScore = 0;
        String maxPointsAfterFirstPass = findStr(maxPointsPair, s);

        int maxPairPoints = (s.length() - maxPointsAfterFirstPass.length()) / 2;
        totalMaxScore = maxPairPoints * Math.max(x, y);

        String minPointsAfterSecondPass = findStr(minPointsPair, maxPointsAfterFirstPass);

        int minPairPoints = (maxPointsAfterFirstPass.length() - minPointsAfterSecondPass.length()) / 2;
        totalMaxScore += minPairPoints * Math.min(x, y);

        return totalMaxScore;
    }
}
