
Problem Link: https://leetcode.com/problems/find-all-possible-recipes-from-given-supplies/?envType=daily-question&envId=2025-03-21

// Approach-1 - Brute Force
// T.C : (n^2 * m)
// S.C : O(n+S)
class Solution {
    public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {

        int n = recipes.length;
        int m = supplies.length;
        Set<String> ingredient = new HashSet<>();

        for (String i : supplies) {
            ingredient.add(i);
        }

        Set<String> result = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                boolean check = true;

                for (int k = 0; k < ingredients.get(j).size(); k++) {
                    if (result.contains(ingredients.get(j).get(k))) continue;

                    if (!ingredient.contains(ingredients.get(j).get(k))) {
                        check = false;
                        break;
                    }
                }

                if (check) {
                    result.add(recipes[j]);
                    ingredient.add(recipes[j]);
                }

            }
        }

        List<String> ans = new ArrayList<>();
        for (String s : result) {
            ans.add(s);
        }

        return ans;

    }
}


// Approach-2 - Using Topological Sorting
// T.C : (n + m + S)
// S.C : O(n+S)
class Solution {
    public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {

        int n = recipes.length;
        int m = supplies.length;
        Set<String> suppliesAvailable = new HashSet<>();

        for (String i : supplies) {
            suppliesAvailable.add(i);
        }

        Map<String, List<Integer>> adj = new HashMap<>();

        int[] indegree = new int[n];

        for (int i = 0; i < n; i++) {

            for (String ingd : ingredients.get(i)) {
                if (!suppliesAvailable.contains(ingd)) {
                    adj.putIfAbsent(ingd, new ArrayList<Integer>());
                    adj.get(ingd).add(i);
                    indegree[i]++;
                }
            }
        }

        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }

        List<String> ans = new ArrayList<>();
        while (!q.isEmpty()) {
            int front = q.poll();
            String recipe = recipes[front];
            ans.add(recipe);

            if(adj.containsKey(recipe)){
                for (Integer adjNode : adj.get(recipe)) {
                    indegree[adjNode]--;
                    if (indegree[adjNode] == 0) {
                        q.add(adjNode);
                    }
                }
            }
        }

        return ans;

    }
}
