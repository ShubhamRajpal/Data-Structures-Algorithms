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
    static int idx = 0;

    private TreeNode buildTree(String traversal, int depth){
        if(idx >= traversal.length()){
            return null;
        }

        int j = idx;
        while(j < traversal.length() && traversal.charAt(j) == '-'){
            j++;
        }

        int curDepth = j-idx;
        if(curDepth != depth) return null;

        idx += curDepth;

        int val = 0;
        while(idx < traversal.length() && Character.isDigit(traversal.charAt(idx))){
            val = 10 * val + (traversal.charAt(idx) - '0');
            idx++;
        }

        TreeNode newNode = new TreeNode(val);

        newNode.left = buildTree(traversal, depth+1);
        newNode.right = buildTree(traversal, depth+1);

        return newNode;
    }
    public TreeNode recoverFromPreorder(String traversal) {
        idx = 0;
        return buildTree(traversal, 0);
        
    }
}