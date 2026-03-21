/*
  Problem Link: https://leetcode.com/problems/all-possible-full-binary-trees/description/
  Similar Qn: LC 95. Unique Binary Search Trees II(https://leetcode.com/problems/unique-binary-search-trees-ii/description/)
*/

// Approach (Recursion + Memoization)
// T.C: O(M) M = 2 ^ (n/2) which is nth catalan number 
// T.C: O(n * M) n = no. of nodes 
class Solution {
    public List<TreeNode> solve(int n, Map<Integer, List<TreeNode>> dp) {

        if (n % 2 == 0) {
            return new ArrayList<TreeNode>();
        }

        if (n == 1) {
            List<TreeNode> list = new ArrayList<>();
            list.add(new TreeNode(0));
            return list;
        }

        if (dp.containsKey(n)) {
            return dp.get(n);
        }

        List<TreeNode> result = new ArrayList<TreeNode>();
        for (int i = 1; i < n; i += 2) {

            List<TreeNode> leftTree = solve(i, dp);
            List<TreeNode> rightTree = solve(n - i - 1, dp);

            for (TreeNode leftChild : leftTree) {
                for (TreeNode rightChild : rightTree) {

                    TreeNode root = new TreeNode(0);
                    root.left = leftChild;
                    root.right = rightChild;
                    result.add(root);
                }
            }
        }

        dp.put(n, result);
        return result;
    }

    public List<TreeNode> allPossibleFBT(int n) {
        Map<Integer, List<TreeNode>> dp = new HashMap<>();
        return solve(n, dp);
    }
}
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
