class Solution {
    public int[] queryResults(int limit, int[][] queries) {
        int m = queries.length;

        Map<Integer, Integer> ballMap = new HashMap<>();
        Map<Integer, Integer> distinctColors = new HashMap<>();

        int[] res = new int[m];
        for (int i = 0; i < m; i++) {
            int ball = queries[i][0];
            int ballColor = queries[i][1];
            if (ballMap.containsKey(ball)) {
                int prevColor = ballMap.get(ball);
                distinctColors.put(prevColor, distinctColors.getOrDefault(prevColor, 0)-1);
                if(distinctColors.get(prevColor) == 0){
                    distinctColors.remove(prevColor);
                }
            }
            ballMap.put(ball, ballColor);
            distinctColors.put(ballColor, distinctColors.getOrDefault(ballColor, 0)+1);
            

            res[i] = distinctColors.size();
        }

        return res;
    }
}