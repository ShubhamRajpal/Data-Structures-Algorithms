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
class FindElements {
    public TreeNode temp;
    Set<Integer> present;

    public FindElements(TreeNode root) {
        temp = root;
        present = new HashSet<>();

        // DFS
        // temp.val = 0;
        // present.add(0);
        // dfsRecover(temp);

        // BFS
        bfsRecover(temp);
    }

    public boolean find(int target) {
        return present.contains(target);

    }

    public void dfsRecover(TreeNode node) {
        if (node == null)
            return;

        if (node.left != null) {
            node.left.val = node.val * 2 + 1;
            present.add(node.left.val);
            dfsRecover(node.left);
        }

        if (node.right != null) {
            node.right.val = node.val * 2 + 2;
            present.add(node.right.val);
            dfsRecover(node.right);
        }
    }

    public void bfsRecover(TreeNode root) {

        if(root == null) return;

        Queue<TreeNode> q = new LinkedList<>();
        root.val = 0;
        q.add(root);
        

        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            present.add(node.val);

            if (node.left != null) {
                node.left.val = node.val * 2 + 1;
                q.add(node.left);
            }

            if (node.right != null) {
                node.right.val = node.val * 2 + 2;
                q.add(node.right);
            }
        }

    }

}

/**
 * Your FindElements object will be instantiated and called as such:
 * FindElements obj = new FindElements(root);
 * boolean param_1 = obj.find(target);
 */