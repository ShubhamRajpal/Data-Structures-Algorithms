/*
    Time Complexity:  E log V
    Space Complexity: O(N) -> for dist array + ways array + approximate complexity for priority queue
    Problem Link: https://leetcode.com/problems/number-of-ways-to-arrive-at-destination/
*/


class Pair {
    long first, second;

    public Pair(long f, long s) {
        first = f;
        second = s;
    }
}

class Solution {
    public int countPaths(int n, int[][] roads) {

        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < roads.length; i++) {
            adj.get(roads[i][0]).add(new Pair(roads[i][1], roads[i][2]));
            adj.get(roads[i][1]).add(new Pair(roads[i][0], roads[i][2]));
        }

        PriorityQueue<Pair> pq = new PriorityQueue<Pair>((a, b) -> Long.compare(a.first, b.first));
        pq.add(new Pair(0, 0));

        long dist[] = new long[n];
        long ways[] = new long[n];

        for (int i = 0; i < n; i++) {
            dist[i] = Long.MAX_VALUE;
            ways[i] = 0;
        }

        dist[0] = 0;
        ways[0] = 1;
        int mod = (int) (1e9 + 7);

        while (!pq.isEmpty()) {
            Pair p = pq.peek();
            long dis = p.first;
            int node = (int) p.second;
            pq.remove();

            for (Pair itr : adj.get(node)) {
                int adjNode = (int) itr.first;
                long dw = itr.second;

                // That's the first time I'm visiting this node
                if (dw + dis < dist[adjNode]) {
                    dist[adjNode] = dis + dw;
                    pq.add(new Pair(dis + dw, adjNode));
                    ways[adjNode] = ways[node];
                    // If we come across the same min distance again for this node we update total
                    // ways to reach ths node
                } else if (dis + dw == dist[adjNode]) {
                    ways[adjNode] = (ways[node] + ways[adjNode]) % mod;
                }

            }
        }

        return (int) (ways[n - 1] % mod);

    }
}
