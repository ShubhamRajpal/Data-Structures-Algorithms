/*
Problem Link: https://www.geeksforgeeks.org/problems/number-of-islands/1?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=number-of-islands
T.C: O(Q*4α) ~ O(Q) where Q = no. of queries. The term 4α is so small that it can be considered constant.
S.C: O(N*M) + O(N*M)+ O(Q), Twice O(N*M) for parent and rank lists
*/

class DisjointSet{
    List<Integer> rank, parent;
    
    public DisjointSet(int V){
        rank = new ArrayList<>();
        parent = new ArrayList<>();
        for(int i = 0; i <= V; i++){
            size.add(1);
            rank.add(0);
            parent.add(i);
        }    
    }
    
    public int findUPar(int node){
        if(parent.get(node) == node) return node;
        
        int ulp = findUPar(parent.get(node));
        parent.set(node, ulp);
        return ulp;
    }
    
    public void UnionByRank(int u, int v){
        int ulp_u = findUPar(u);
        int ulp_v = findUPar(v);
        if(ulp_u == ulp_v) return;
        
        if(rank.get(ulp_u) < rank.get(ulp_v)){
            parent.set(ulp_u, ulp_v);
        }   
        else if(rank.get(ulp_v) < rank.get(ulp_u)){
            parent.set(ulp_v, ulp_u);
        }else{
            parent.set(ulp_u, ulp_v);
            rank.set(ulp_v, rank.get(ulp_v) + 1);
        }   
    }
}
    
    
class Solution {
    
    public List<Integer> numOfIslands(int rows, int cols, int[][] operators) {
        //Your code here
         
        DisjointSet ds = new DisjointSet(rows * cols);
        int[][] vis = new int[rows][cols];
        List<Integer> ans = new ArrayList<>();
        int n = operators.length;
        int cnt = 0;
        
        for(int i = 0; i < n; i++){
            
            int row = operators[i][0];
            int col = operators[i][1];
            
            if(vis[row][col] == 1) {
                ans.add(cnt);
                continue;
            }    
            
            vis[row][col] = 1;
            cnt++;
            
            int[] dr = {-1, 0, 1, 0};
            int[] dc = {0, 1, 0, -1};
            
            for(int j = 0; j < 4; j++){
                int nrow = row + dr[j];
                int ncol = col + dc[j];
                
                if(nrow >= 0 && nrow < rows && ncol >=0 && ncol < cols && vis[nrow][ncol] == 1){
                    int u = row * cols + col;
                    int v = nrow * cols + ncol;
                    if(ds.findUPar(u) != ds.findUPar(v)){
                        cnt--;
                        ds.UnionByRank(u, v);
                    }
                }
            }
            
            ans.add(cnt);
            
            
        }
        return ans;
    }
    
}
