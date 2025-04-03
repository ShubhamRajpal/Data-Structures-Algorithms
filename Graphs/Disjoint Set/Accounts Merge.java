/*
Problem Link: https://leetcode.com/problems/accounts-merge/
T.C.- O(N+E) + O(E*4ɑ) + O(N*(ElogE + E)) where N = no. of indices or nodes and E = no. of emails. 
The first term is for visiting all the emails. The second term is for merging the accounts. And the third term is for sorting the emails and storing them in the answer array.

S.C.- O(N)+ O(N) +O(2N) ~ O(N) where N = no. of nodes/indices. 
The first and second space is for the ‘mergedMail’ and the ‘ans’ array. The last term is for the parent and size array used inside the Disjoint set data structure.
*/

class DisjointSet {
    ArrayList<Integer> parent = new ArrayList<>();
    ArrayList<Integer> size = new ArrayList<>();

    public DisjointSet(int n) {
        for (int i = 0; i <= n; i++) {
            parent.add(i);
            size.add(1);
        }
    }

    public int findUPar(int node) {
        if (parent.get(node) == node) return node;

        int ulp = findUPar(parent.get(node));
        parent.set(node, ulp);
        return parent.get(node);
    }

    public void unionBySize(int u, int v) {
        int ulp_u = findUPar(u);
        int ulp_v = findUPar(v);
        if (ulp_u == ulp_v)
            return;
        if (size.get(ulp_v) < size.get(ulp_u)) {
            parent.set(ulp_v, ulp_u);
            size.set(ulp_u, size.get(ulp_u) + size.get(ulp_v));
        } else {
            parent.set(ulp_u, ulp_v);
            size.set(ulp_v, size.get(ulp_u) + size.get(ulp_v));
        }
    }

}

class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        int n = accounts.size();
        DisjointSet ds = new DisjointSet(n);
        HashMap<String, Integer> mp = new HashMap<>();

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < accounts.get(i).size(); j++) {
                String str = accounts.get(i).get(j);
                if (mp.containsKey(str)) {
                    ds.unionBySize(i, mp.get(str));
                } else {
                    mp.put(str, i);
                }
            }
        }

        ArrayList<ArrayList<String>> mergeEmailsList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            mergeEmailsList.add(new ArrayList<>());
        }

        for (Map.Entry<String, Integer> entry : mp.entrySet()) {
            String email = entry.getKey();
            int node = ds.findUPar(entry.getValue());
            mergeEmailsList.get(node).add(email);
        }

        List<List<String>> ans = new ArrayList<>();

        for (int i = 0; i < mergeEmailsList.size(); i++) {
            if (mergeEmailsList.get(i).size() == 0)
                continue;
            Collections.sort(mergeEmailsList.get(i));
            String name = accounts.get(i).get(0);
            List<String> temp = new ArrayList<>();
            temp.add(name);
            for (String it : mergeEmailsList.get(i)) {
                temp.add(it);
            }
            ans.add(temp);
        }

        return ans;

    }
}
