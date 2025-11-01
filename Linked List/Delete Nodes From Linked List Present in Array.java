/*
  Problem Link: https://leetcode.com/problems/delete-nodes-from-linked-list-present-in-array/
*/

// Approach (Simplly Store array values in set and update the pointers in LL)
// T.C: O(n+m),  where m = length of LL and n = length of nums 
// S.C: O(m)
class Solution {
    public ListNode modifiedList(int[] nums, ListNode head) {
        Set<Integer> present = new HashSet<>();
        for (int i : nums) {
            present.add(i);
        }

        ListNode temp = head;
        ListNode newHead = null, prev = null;

        while (temp != null) {
            if (!present.contains(temp.val)) {
                if (newHead == null) {
                    newHead = temp;
                } else {
                    prev.next = temp;
                }

                prev = temp;
            }
            temp = temp.next;
        }

        prev.next = null;

        return newHead;

    }
}
