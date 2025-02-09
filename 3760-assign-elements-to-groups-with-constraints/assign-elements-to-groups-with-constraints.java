class Solution {
    public int[] assignElements(int[] groups, int[] elements) {
        int n = groups.length;
        int m = elements.length;

        Map<Integer, Integer> factorsMap = new HashMap<>();
        for(int i = 0; i < m; i++){
             factorsMap.putIfAbsent(elements[i], i);
        }

        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            int minIndex = m;
            
            for(int j = 1; j * j <= groups[i]; j++){
                if(groups[i] % j == 0){
                    if(factorsMap.containsKey(j)){
                        minIndex = Math.min(minIndex, factorsMap.get(j));
                    }

                    if(j != groups[i] / j  && factorsMap.containsKey(groups[i] / j)){
                        minIndex = Math.min(minIndex, factorsMap.get(groups[i] / j));
                    }
                }
            }

            res[i] = minIndex == m ? -1 : minIndex; 
        }

        return res;
    }
}