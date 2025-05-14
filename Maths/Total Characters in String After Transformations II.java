/*
    Problem Link: https://leetcode.com/problems/total-characters-in-string-after-transformations-ii
*/

//Approach - Using Matrix Exponentiation
//T.C : O(n + log(t))
//S.C : O(26*26) ~ O(1)
class Solution {
    int MOD = (int) 1e9 + 7;

    private List<List<Integer>> matrixMultiplication(List<List<Integer>> A, List<List<Integer>> B) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            List<Integer> row = new ArrayList<>(Collections.nCopies(26, 0));
            result.add(row);
        }

        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                long sum = 0;
                for (int k = 0; k < 26; k++) {
                    sum = (sum + 1L * A.get(i).get(k) * B.get(k).get(j)) % MOD;
                }

                result.get(i).set(j, (int) sum);
            }
        }

        return result;

    }

    private List<List<Integer>> matrixExponentiation(List<List<Integer>> base, int exponent) {

        List<List<Integer>> identity = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            List<Integer> row = new ArrayList<>(Collections.nCopies(26, 0));
            row.set(i, 1);
            identity.add(row);
        }

        if (exponent == 0) {
            return identity;
        }

        List<List<Integer>> half = matrixExponentiation(base, exponent / 2);
        List<List<Integer>> result = matrixMultiplication(half, half);

        if (exponent % 2 != 0) {
            result = matrixMultiplication(result, base);
        }

        return result;
    }

    public int lengthAfterTransformations(String s, int t, List<Integer> nums) {

        int[] freq = new int[26];
        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }

        // Build transformation matrix T
        List<List<Integer>> T = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            T.add(new ArrayList<>(Collections.nCopies(26, 0)));
        }

        for (int i = 0; i < 26; i++) {
            for (int add = 1; add <= nums.get(i); add++) {
                int newIdx = (i + add) % 26;
                T.get(newIdx).set(i, T.get(newIdx).get(i) + 1);
            }
        }

        // Matrix exponentiation
        List<List<Integer>> result = matrixExponentiation(T, t);

        // Apply transformation to frequency vector
        int[] updatedFreq = new int[26];
        for (int i = 0; i < 26; i++) {
            long value = 0;
            for (int j = 0; j < 26; j++) {
                value = (value + 1L * result.get(i).get(j) * freq[j]) % MOD;
            }

            updatedFreq[i] = (int) value;
        }

        int resultantLength = 0;
        for (int val : updatedFreq) {
            resultantLength = (resultantLength + val) % MOD;
        }

        return resultantLength;

    }
}
