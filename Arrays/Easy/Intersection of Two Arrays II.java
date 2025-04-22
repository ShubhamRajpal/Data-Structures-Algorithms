/*
  Problem Link: https://leetcode.com/problems/intersection-of-two-arrays-ii/description/
*/

// Approach-1(Using map and List)
// T.C- O(N+M)
// S.C- O(N+M)
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {

        int n = nums1.length, m = nums2.length;
        Map<Integer, Integer> nums1freq = new HashMap<>();
        for (int i = 0; i < n; i++) {
            nums1freq.put(nums1[i], nums1freq.getOrDefault(nums1[i], 0) + 1);
        }

        List<Integer> temp = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            if (nums1freq.containsKey(nums2[i]) && nums1freq.get(nums2[i]) > 0) {
                temp.add(nums2[i]);
                nums1freq.put(nums2[i], nums1freq.getOrDefault(nums2[i], 0) - 1);
            }
        }

        return temp.stream().mapToInt(i -> i).toArray();
    }
}


// Approach-2(Sorting both arrays)
// T.C- O(N+M)
// S.C- O(1)
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);

        int n = nums1.length, m = nums2.length;
        List<Integer> temp = new ArrayList<>();
        int i = 0, j = 0;

        while (i < n && j < m) {
            if (nums1[i] == nums2[j]) {
                temp.add(nums1[i]);
                i++;
                j++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                j++;
            }
        }

        return temp.stream().mapToInt(Integer::intValue).toArray();
    }
}
