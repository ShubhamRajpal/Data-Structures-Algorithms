class Solution {
    public int[] assignElements(int[] groups, int[] elements) {
        int n = groups.length;
        int m = elements.length;

        Map<Integer, Integer> factorsMap = new HashMap<>();
        for(int i = 0; i < m; i++){
            if(!factorsMap.containsKey(elements[i])) factorsMap.put(elements[i], i);
        }

        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            int minIndex = m;
            
            for(int j = 1; j * j <= groups[i]; j++){
                if(groups[i] % j == 0){
                    if(factorsMap.containsKey(j)){
                        int idx1 = factorsMap.get(j);
                        minIndex = Math.min(minIndex, idx1);
                    }

                    if(factorsMap.containsKey(groups[i] / j)){
                        int idx2 = factorsMap.get(groups[i] / j);
                        minIndex = Math.min(minIndex, idx2);
                    }
                }
            }

            res[i] = minIndex == m ? -1 : minIndex; 
        }

        return res;
    }
}