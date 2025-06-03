/*
  Problem Link:  https://leetcode.com/problems/maximum-candies-you-can-get-from-boxes
*/

//Approach-1 (Using DFS)
//T.C : O(n), where n =  number of boxes, we don't visit any box more than once
//S.C : O(n)
class Solution {

    private int dfs(int box, boolean[] visited, int[] status, int[] candies, int[][] keys, int[][] containedBoxes,
            Set<Integer> boxesFound) {
        if (visited[box]) {
            return 0;
        }

        if (status[box] == 0) {
            boxesFound.add(box);
            return 0;
        }

        visited[box] = true;
        int candiesCnt = candies[box];

        for (int insideBox : containedBoxes[box]) {
            candiesCnt += dfs(insideBox, visited, status, candies, keys, containedBoxes, boxesFound);
        }

        for (int boxKey : keys[box]) {
            status[boxKey] = 1;
            if (boxesFound.contains(boxKey)) {
                candiesCnt += dfs(boxKey, visited, status, candies, keys, containedBoxes, boxesFound);
            }
        }

        return candiesCnt;

    }

    public int maxCandies(int[] status, int[] candies, int[][] keys, int[][] containedBoxes, int[] initialBoxes) {
        int maxCandies = 0;
        int n = status.length;
        Set<Integer> boxesFound = new HashSet<>();
        boolean[] visited = new boolean[n];

        for (int box : initialBoxes) {
            maxCandies += dfs(box, visited, status, candies, keys, containedBoxes, boxesFound);
        }

        return maxCandies;
    }
}


//Approach-2 (Using BFS)
//T.C : O(n), where n =  number of boxes, we don't visit any box more than once
//S.C : O(n)
class Solution {

    private int bfs(boolean[] visited, int[] status, int[] candies, int[][] keys, int[][] containedBoxes,
            Set<Integer> boxesFound, int[] initialBoxes) {

        int candiesCnt = 0;
        Queue<Integer> q = new LinkedList<>();
        for (int box : initialBoxes) {
            boxesFound.add(box);
            if (status[box] == 1) {
                q.add(box);
                visited[box] = true;
                candiesCnt += candies[box];
            }
        }

        while (!q.isEmpty()) {
            int curBox = q.poll();

            for (int insideBox : containedBoxes[curBox]) {
                boxesFound.add(insideBox);
                if (status[insideBox] == 1 && !visited[insideBox]) {
                    visited[insideBox] = true;
                    candiesCnt += candies[insideBox];
                    q.add(insideBox);
                }
            }

            for (int boxKey : keys[curBox]) {
                status[boxKey] = 1;
                if (boxesFound.contains(boxKey) && !visited[boxKey]) {
                    visited[boxKey] = true;
                    candiesCnt += candies[boxKey];
                    q.add(boxKey);
                }
            }
        }

        return candiesCnt;

    }

    public int maxCandies(int[] status, int[] candies, int[][] keys, int[][] containedBoxes, int[] initialBoxes) {

        int n = status.length;
        Set<Integer> boxesFound = new HashSet<>();
        boolean[] visited = new boolean[n];

        return bfs(visited, status, candies, keys, containedBoxes, boxesFound, initialBoxes);

    }
}
