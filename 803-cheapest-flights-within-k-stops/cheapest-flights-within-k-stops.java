


class Tuple{
    int first, second, third;
    public Tuple(int f, int s, int t){
        first = f;
        second = s;
        third = t;
    }
}


class Pair{
    int first, second;
    public Pair(int f, int s){
        first = f;
        second = s;
    }
}

class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        
        ArrayList<ArrayList<Pair>> adj = new ArrayList<>(); 
        for(int i = 0 ; i < n; i++){
            adj.add(new ArrayList<>());
        }

        for(int i = 0; i < flights.length; i++){
            adj.get(flights[i][0]).add(new Pair(flights[i][1], flights[i][2]));
        }

        Queue<Tuple> q = new LinkedList<Tuple>();
        q.add(new Tuple(0,src,0));

        int cost[] = new int[n];
        for(int i = 0; i < n; i++) cost[i] = (int)(1e9);
        cost[src] = 0;

        while(!q.isEmpty()){
            Tuple t = q.peek();
            int stops = t.first;
            int node = t.second;
            int dist = t.third;
            q.remove();

            if(stops > k) break;
            
            for(Pair it : adj.get(node)){
                int adjNode = it.first;
                int dis = it.second;

                if(dis + dist < cost[adjNode] && stops <= k){
                    cost[adjNode] = dis + dist;
                    q.add(new Tuple(stops + 1, adjNode, cost[adjNode]));
                }
            }
        }

        if(cost[dst] == 1e9) return -1;
        return cost[dst];
    }
}