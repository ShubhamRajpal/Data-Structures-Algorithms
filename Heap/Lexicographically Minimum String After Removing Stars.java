/*
  Problem Link: https://leetcode.com/problems/lexicographically-minimum-string-after-removing-stars/description/
*/

//Simple approach using heap
//T.C : O(nlogn)
//S.C : O(n)
class Pair {
    char first;
    int second;

    public Pair(char f, int s) {
        first = f;
        second = s;
    }
}

class Solution {
    public String clearStars(String s) {
        int n = s.length();

        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> {
            if (a.first == b.first) {
                return b.second - a.second;
            }
            return a.first - b.first;
        });

        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < n; i++) {
            if (sb.charAt(i) != '*') {
                pq.offer(new Pair(sb.charAt(i), i));
            } else if (sb.charAt(i) == '*') {
                int idx = pq.peek().second;
                sb.setCharAt(idx, '*');
                pq.poll();
            }
        }

        StringBuilder res = new StringBuilder();
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) != '*')
                res.append(sb.charAt(i));
        }

        return res.toString();
    }
}
