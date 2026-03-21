/*
  Problem Link: https://leetcode.com/problems/unique-binary-search-trees-ii/description/
  Similar Qn: LC 894. All Possible Full Binary Trees (https://leetcode.com/problems/all-possible-full-binary-trees/description/)
*/

// Approach (Recursion + Memoization)
// T.C: O(n * M) M = 4^n / sqrt(n) (which is nth catalan number or no of unique BSTs of n nodes) 
// S.C: O(n * M)
class Pair {
    int start, end;

    public Pair(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

class Solution {
    public List<TreeNode> solve(int start, int end, Map<Pair, List<TreeNode>> dp) {

        if (start > end) {
            List<TreeNode> temp = new ArrayList<>();
            temp.add(null);
            return temp;
        }

        if (start == end) {
            List<TreeNode> list = new ArrayList<>();
            list.add(new TreeNode(start));
            return list;
        }

        if (dp.containsKey(new Pair(start, end))) {
            return dp.get(new Pair(start, end));
        }

        List<TreeNode> result = new ArrayList<TreeNode>();
        for (int i = start; i <= end; i++) {

            List<TreeNode> leftBST = solve(start, i - 1, dp);
            List<TreeNode> rightBST = solve(i + 1, end, dp);

            for (TreeNode leftChild : leftBST) {
                for (TreeNode rightChild : rightBST) {

                    TreeNode root = new TreeNode(i);
                    root.left = leftChild;
                    root.right = rightChild;
                    result.add(root);
                }
            }
        }

        dp.put(new Pair(start, end), result);
        return result;
    }

    public List<TreeNode> generateTrees(int n) {

        Map<Pair, List<TreeNode>> dp = new HashMap<>();
        return solve(1, n, dp);
    }
}
