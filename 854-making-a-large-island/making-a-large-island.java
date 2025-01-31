class DisjointSet{
    List<Integer> size, rank, parent;
    
    public DisjointSet(int V){
        size = new ArrayList<>();
        rank = new ArrayList<>();
        parent = new ArrayList<>();
        for(int i = 0; i < V+1; i++){
            size.add(1);
            rank.add(0);
            parent.add(i);
        }    
    }
    
    public int findUPar(int node){
        if(parent.get(node) == node) return node;
        
        int ulp = findUPar(parent.get(node));
        parent.set(node, ulp);
        return parent.get(node);
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
    
    
    public void UnionBySize(int u, int v){
        int ulp_u = findUPar(u);
        int ulp_v = findUPar(v);
        if(ulp_u == ulp_v) return;
        
        if(size.get(ulp_u) < size.get(ulp_v)){
            parent.set(ulp_u, ulp_v);
            size.set(ulp_v, size.get(ulp_u)+size.get(ulp_v));
        }else{
            parent.set(ulp_v, ulp_u);
            size.set(ulp_u, size.get(ulp_u)+size.get(ulp_v));
        }
    }
}

class Solution {
    public int largestIsland(int[][] grid) {
        
        int n = grid.length;
        DisjointSet ds = new DisjointSet(n*n);
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == 0) continue;
                int[] dr = {-1, 0, 1, 0};
                int[] dc = {0, 1, 0, -1};
                for(int k = 0; k < 4; k++){
                    int nrow = i + dr[k];
                    int ncol = j + dc[k];
                    if(nrow >= 0 && nrow < n && ncol >=0 && ncol < n && grid[nrow][ncol] == 1){
                        int u = i * n + j;
                        int v = nrow * n + ncol;
                        ds.UnionBySize(u, v);
                    }
                }

            }
        }

        int maxSize = 0;
        for(int row = 0; row < n; row++){
            for(int col = 0; col < n; col++){
                if(grid[row][col] == 1) continue;
                int[] dr = {-1, 0, 1, 0};
                int[] dc = {0, 1, 0, -1};
                HashSet<Integer> components = new HashSet<>();
                for(int k = 0; k < 4; k++){
                    int nrow = row + dr[k];
                    int ncol = col + dc[k];
                    if(nrow >= 0 && nrow < n && ncol >=0 && ncol < n && grid[nrow][ncol] == 1){
                        int v = nrow * n + ncol;
                        components.add(ds.findUPar(v));
                    }
                }
                int cnt = 0;
                for(Integer parents : components){
                    cnt += ds.size.get(parents);
                }
                maxSize = Math.max(maxSize, cnt+1);
            }
        }

        for(int cellNo = 0; cellNo < n*n; cellNo++){
            maxSize = Math.max(maxSize, ds.size.get(ds.findUPar(cellNo)));
        }

        return maxSize;

    }
}