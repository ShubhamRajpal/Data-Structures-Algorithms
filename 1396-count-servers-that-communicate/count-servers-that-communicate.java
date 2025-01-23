class Pair{
    int first, second;
    public Pair(int f, int s){
        first = f;
        second = s;
    }
}

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
        int ulp_u = parent.get(u);
        int ulp_v = parent.get(v);
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
    
    public int countServers(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        DisjointSet ds = new DisjointSet(n*m);
        // Traversing in each row to connect the same row servers
        for(int i = 0; i < n; i++){
            int prevServer = -1;
            for(int j = 0; j < m; j++){
                if(grid[i][j] == 1){
                    int curServer = i * m + j;
                    if(prevServer != -1){
                        ds.UnionBySize(prevServer, curServer);
                    }
                    prevServer = curServer;
                }
            }
        }

         // Traversing in each col to connect the same column servers
        for(int j = 0; j < m; j++){
            int prevServer = -1;
            for(int i = 0; i < n; i++){
                if(grid[i][j] == 1){
                    int curServer = i * m + j;
                    if(prevServer != -1){
                        ds.UnionBySize(prevServer, curServer);
                    }
                    prevServer = curServer;
                }
            }
        }

        System.out.println(ds.size);

        int cnt = 0;
        for(int i = 0; i < n*m; i++){
           if(ds.parent.get(i) == i && ds.size.get(i) > 1){
                cnt += ds.size.get(i); 
           }
        }
         return cnt;


    }
}