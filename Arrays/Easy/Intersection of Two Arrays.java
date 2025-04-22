/*
  Problem Link: https://leetcode.com/problems/intersection-of-two-arrays/description/
*/
// Approach-1(Using 2 sets)
T.C- O(N+M)
S.C- O(N+M)
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> st1 = new HashSet<>();
        Set<Integer> st2 = new HashSet<>();

        for(int i : nums1){
            st1.add(i);
        }

        for(int i : nums2){
            if(st1.contains(i)){
                st2.add(i);
            }
        }

        int[] intersection = new int[st2.size()];
        int i = 0;
        for(Integer j : st2){
            intersection[i++] = j;
        }

        return intersection;
    }
}


// Approach-2(Using 1 set)
T.C- O(N+M)
S.C- O(N+M)
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> st1 = new HashSet<>();
        List<Integer> temp = new ArrayList<>();

        for(int i : nums1){
            st1.add(i);
        }

        for(int i : nums2){
            if(st1.contains(i)){
                temp.add(i);
                st1.remove(i);
            }
        }

       return temp.stream().mapToInt(i -> i).toArray();
    }
}



// Approach-3(Sorting and 2 pointers)
T.C- O(N+M + NlogN + MlogM)
S.C- O(1)
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int n = nums1.length, m = nums2.length;
        List<Integer> temp = new ArrayList<>();
        int i = 0, j = 0;
        while (i < n && j < m) {
            if (nums1[i] == nums2[j]) {
                temp.add(nums1[i]);
                while (i < n - 1 && nums1[i] == nums1[i + 1]) {
                    i++;
                }

                while (j < m - 1 && nums2[j] == nums2[j + 1]) {
                    j++;
                }
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


// Approach-4(Sorting and binary Search)
T.C- O(NlogN+MlogN) Considering nums1 is of smaller size
S.C- O(1)
class Solution {

    private boolean search(int num, int[] nums1) {
        int l = 0, r = nums1.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (nums1[mid] == num) return true;

            if (nums1[mid] < num) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return false;
    }

    public int[] intersection(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);

        int n = nums1.length, m = nums2.length;
        Set<Integer> res = new HashSet<>();

        for (int num : nums2) {
            if (!res.contains(num) && search(num, nums1)) {
                res.add(num);
            }
        }

        int[] intersection = new int[res.size()];
        int j = 0;
        for (Integer i : res) {
            intersection[j++] = i;
        }

        return intersection;
    }
}
