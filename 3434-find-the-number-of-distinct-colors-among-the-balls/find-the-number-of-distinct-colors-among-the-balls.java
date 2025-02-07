class Solution {
    public int[] queryResults(int limit, int[][] queries) {
        int m = queries.length;

        Map<Integer, Integer> colorMap = new HashMap<>();
        Map<Integer, Set<Integer>> distinctColors = new HashMap<>();
        int[] res = new int[m];
        for (int i = 0; i < m; i++) {
            int ball = queries[i][0];
            int ballColor = queries[i][1];
            if (colorMap.containsKey(ball)) {
                int curColor = colorMap.get(ball);
                Set<Integer> temp = distinctColors.get(curColor);
                if (!temp.isEmpty() && temp.contains(ball)) {
                    temp.remove(ball);
                    if(temp.isEmpty()) distinctColors.remove(curColor);
                    else distinctColors.put(curColor, temp);
                }
            }
            colorMap.put(ball, ballColor);
            Set<Integer> st = distinctColors.getOrDefault(ballColor, new HashSet<>());
            st.add(ball);
            distinctColors.put(ballColor, st);

            res[i] = distinctColors.size();
        }

        System.out.println(colorMap);
        System.out.println(distinctColors);

        return res;
    }
}