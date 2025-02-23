/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class Solution {
    private TreeNode buildTree(int[] preorder, int preStart, int preEnd, int[] postorder, int postStart, int postEnd,
            Map<Integer, Integer> postIndMap) {
        System.out.println("Pre: "+preStart+" "+preEnd+" Post: " + postStart+" "+postEnd);
        if(preStart > preEnd || postStart > postEnd){
            return null;
        }

        TreeNode newNode = new TreeNode(preorder[preStart]);

        if(preStart >= preEnd) return newNode;

        int leftRoot = preorder[preStart+1];
        int postRoot = postIndMap.get(leftRoot);
        int numsLeft = postRoot - postStart + 1;

        newNode.left = buildTree(preorder, preStart+1, preStart+numsLeft, postorder, postStart, postRoot, postIndMap);
        newNode.right = buildTree(preorder, preStart+numsLeft+1, preEnd, postorder, postRoot+1, postEnd-1, postIndMap);

        return newNode;

    }

    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        int n = preorder.length;
        Map<Integer, Integer> postIndMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            postIndMap.put(postorder[i], i);
        }

        return buildTree(preorder, 0, preorder.length - 1, postorder, 0, postorder.length - 1, postIndMap);
    }
}