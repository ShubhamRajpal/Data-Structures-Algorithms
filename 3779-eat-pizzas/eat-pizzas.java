class Solution {
    public long maxWeight(int[] pizzas) {
        int n = pizzas.length;
        Arrays.sort(pizzas);
        int totalDays = n/4;
        int evenCount = totalDays/2;
        int oddCount = totalDays - evenCount;

        int right = n-1;
        long maxWeight = 0;
        for(int i = 0; i < oddCount; i++){
            maxWeight += pizzas[right--];
        }

        right--;
        for(int i = 0; i < evenCount; i++){
            if(right <= 0) break;
            maxWeight += pizzas[right];
            right -= 2;
        }

        return maxWeight;
        
    }
}