/*
  Problem Link: https://www.geeksforgeeks.org/problems/huffman-encoding3345/1
*/

// Approach (using min-heap and preorder traversal)
// T.C: O(n log n)
// S.C: O(n)
class Node{
    int val, idx;
    Node left, right;
    
    public Node(int val, int idx){
        this.val = val;
        this.idx = idx;
        this.left = null;
        this.right = null;
    }
}

class Solution {
    
    ArrayList<String> ans;
    
    public void solve(Node root, String cur){
        if(root == null){
            return;
        }
        
        if(root.left == null && root.right == null){
            if(cur == ""){
                cur += "0";
            }
            ans.add(cur);
            return;
        }
        
        
        solve(root.left, cur + "0");
        solve(root.right, cur + "1");
        
    }
    public ArrayList<String> huffmanCodes(String s, int f[]) {
        // Code here
        PriorityQueue<Node> pq = new PriorityQueue<>((a,b) -> {
            if(a.val == b.val){
                return a.idx - b.idx;
            }
            
            return a.val - b.val;
        });
        
        
        for(int i = 0; i < f.length; i++){
            pq.offer(new Node(f[i], i));
        }
        
        while(pq.size() > 1){
            Node lt = pq.poll();
            Node rt = pq.poll();
            
            Node newNode = new Node(lt.val+rt.val, Math.min(lt.idx, rt.idx));
            newNode.left = lt;
            newNode.right = rt;
            pq.offer(newNode);
        }
        
        ans = new ArrayList<>();
        Node root = pq.poll();
        solve(root, "");
        return ans;
        
    }
}
