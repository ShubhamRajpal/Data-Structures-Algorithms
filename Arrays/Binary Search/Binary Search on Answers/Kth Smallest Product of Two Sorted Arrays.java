/*
  Problem Link: https://leetcode.com/problems/kth-smallest-product-of-two-sorted-arrays
*/

//Approach (Binary Search on Answer)
//T.C : O(log(maxP-minP) * n * log(m)
//S.C : O(1)
class Solution {
    public long kthSmallestProduct(int[] nums1, int[] nums2, long k) {
        long left = -1_000_000_0000L; // -1e10
        long right = 1_000_000_0000L; // 1e10
        long result = 0;

        while (left <= right) {
            long midProduct = left + (right - left) / 2;
            long count = countLessEqual(nums1, nums2, midProduct);

            if (count >= k) {
                result = midProduct;
                right = midProduct - 1;
            } else {
                left = midProduct + 1;
            }
        }

        return result;
    }

    private long countLessEqual(int[] nums1, int[] nums2, long midProduct) {
        long count = 0;
        int n = nums2.length;

        for (int a : nums1) {
            if (a >= 0) {
                int l = 0, r = n - 1, pos = -1;
                while (l <= r) {
                    int m = l + (r - l) / 2;
                    long product = 1L * a * nums2[m];
                    if (product <= midProduct) {
                        pos = m;
                        l = m + 1;
                    } else {
                        r = m - 1;
                    }
                }
                count += (pos + 1);
            } else {
                int l = 0, r = n - 1, pos = n;
                while (l <= r) {
                    int m = l + (r - l) / 2;
                    long product = 1L * a * nums2[m];
                    if (product <= midProduct) {
                        pos = m;
                        r = m - 1;
                    } else {
                        l = m + 1;
                    }
                }
                count += (n - pos);
            }
        }

        return count;
    }
}
