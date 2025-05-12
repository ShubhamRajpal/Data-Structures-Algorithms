/*
  Problem Link:  https://leetcode.com/problems/finding-3-digit-even-numbers
*/

//Approach-1 (Trying all possibilities using 3 for loops for 3 digits)
//T.C : O(n^3 + SlogS), S = total 3 digits even numbers
//S.C : O(S)
class Solution {
    Set<Integer> temp;

    private void solve(int idx, int[] digits, boolean[] vis, int res) {

        if (idx == 3) {
            if (res % 2 == 0) {
                temp.add(res);
            }
            return;
        }

        for (int i = 0; i < digits.length; i++) {
            if (vis[i] || (idx == 0 && digits[i] == 0) || (idx == 2 && digits[i] % 2 != 0)) {
                continue;
            }

            vis[i] = true;
            res = res * 10 + digits[i];
            solve(idx + 1, digits, vis, res);
            res /= 10;
            vis[i] = false;
        }
    }

    public int[] findEvenNumbers(int[] digits) {
        int n = digits.length;
        boolean[] vis = new boolean[n];
        temp = new HashSet<>();
        solve(0, digits, vis, 0);

        int[] res = new int[temp.size()];
        int j = 0;
        for (int it : temp) {
            res[j++] = it;
        }

        Arrays.sort(res);

        return res;

    }
}


//Approach-2 (Smartly finding valid digits for each position)
//T.C : O(1)
//S.C : O(1)
class Solution {
    Map<Integer, Integer> freq;

    private void solve(int idx, int[] digits, int res, List<Integer> temp) {

        if (idx == 3) {
            if (res % 2 == 0) {
                temp.add(res);
            }
            return;
        }

        for (int i = 0; i <= 9; i++) {
            if (!(freq.containsKey(i) && freq.get(i) != 0) || (idx == 0 && i == 0) || (idx == 2 && i % 2 == 1))
                continue;

            freq.put(i, freq.getOrDefault(i, 0) - 1);
            res = res * 10 + i;
            solve(idx + 1, digits, res, temp);
            freq.put(i, freq.getOrDefault(i, 0) + 1);
            res /= 10;
        }
    }

    public int[] findEvenNumbers(int[] digits) {
        int n = digits.length;
        freq = new HashMap<>();
        for (int i : digits) {
            freq.put(i, freq.getOrDefault(i, 0) + 1);
        }
        List<Integer> temp = new ArrayList<>();
        solve(0, digits, 0, temp);

        return temp.stream().mapToInt(i -> i).toArray();

    }
}
