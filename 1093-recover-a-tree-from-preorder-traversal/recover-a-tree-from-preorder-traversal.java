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
class Solution {

    private TreeNode buildTree(int[] idx, String traversal, int depth){
        if(idx[0] >= traversal.length()){
            return null;
        }

        int j = idx[0];
        while(j < traversal.length() && traversal.charAt(j) == '-'){
            j++;
        }

        int curDepth = j-idx[0];
        if(curDepth != depth) return null;

        idx[0] += curDepth;

        int val = 0;
        while(idx[0] < traversal.length() && Character.isDigit(traversal.charAt(idx[0]))){
            val = 10 * val + (traversal.charAt(idx[0]) - '0');
            idx[0]++;
        }

        TreeNode newNode = new TreeNode(val);

        newNode.left = buildTree(idx, traversal, depth+1);
        newNode.right = buildTree(idx, traversal, depth+1);

        return newNode;
    }
    public TreeNode recoverFromPreorder(String traversal) {
        int[] idx = new int[1];
        return buildTree(idx, traversal, 0);
        
    }
}