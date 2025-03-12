/*
  Time Complexity: O(N+E) + O(E logE) + O(E*4α*2)   where N = no. of nodes and E = no. of edges. 
  O(N+E) for extracting edge information from the adjacency list. 
  O(E logE) for sorting the array consists of the edge tuples. 
  Finally, we are using the disjoint set operations inside a loop. 
  The loop will continue to E times. Inside that loop, there are two disjoint set operations like findUPar() and UnionBySize() each taking 4 and so it will result in 4*2. That is why the last term O(E*4*2) is added.

  Space Complexity: O(N) + O(N) + O(E) 
  O(E) space is taken by the array that we are using to store the edge information. 
  And in the disjoint set data structure, we are using two N-sized arrays i.e. a parent and a size array (as we are using unionBySize() function 
  otherwise, a rank array of the same size if unionByRank() is used) which result in the first two terms O(N).

  Problem Link: https://www.geeksforgeeks.org/problems/minimum-spanning-tree/1
*/

class Pair{
    int distance, node;
    public Pair(int d, int n){
        distance = d;
        node = n;
    }
}

class Edge implements Comparable<Edge>{
    int src, dst, weight;
    public Edge(int s, int d, int w){
        this.src = s;
        this.dst = d;
        this.weight = w;
    }
    
    public int compareTo(Edge CompareEdge){
        return this.weight - CompareEdge.weight;
    }
};

class DisjointSet{
    List<Integer> size, parent;
    
    public DisjointSet(int V){
        size = new ArrayList<>();
        parent = new ArrayList<>();
        for(int i = 0; i <= V; i++){
            size.add(1);
            parent.add(i);
        }    
    }
    
    public int findUPar(int node){
        if(parent.get(node) == node) return node;
        
        int ulp = findUPar(parent.get(node));
        parent.set(node, ulp);
        return ulp;
    }
    
    public void UnionBySize(int u, int v){
        int ulp_u = parent.get(u);
        int ulp_v = parent.get(v);
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
    public static int spanningTree(int V, int E, List<List<int[]>> adj) {
        
        List<Edge> edges = new ArrayList<Edge>();
        // O(N + E)
        for(int i = 0; i < V; i++){
            for(int j = 0; j < adj.get(i).size(); j++){
                int adjNode = adj.get(i).get(j)[0];
                int wt = adj.get(i).get(j)[1];
                int node = i;
                
                Edge temp = new Edge(i, adjNode, wt);
                edges.add(temp);
            }
        }
        // O(M log M)
        Collections.sort(edges);
        DisjointSet ds = new DisjointSet(V); 
        int maxWt = 0;

      O(M * 4 * alpha * 2)
        for(int i = 0; i < edges.size(); i++){
                int s = edges.get(i).src;
                int d = edges.get(i).dst;
                int wt = edges.get(i).weight;
                
                if(ds.findUPar(s) != ds.findUPar(d)){
                    maxWt += wt;
                    ds.UnionBySize(s, d);
                }
        }
        
        return maxWt;
        
    }
}
