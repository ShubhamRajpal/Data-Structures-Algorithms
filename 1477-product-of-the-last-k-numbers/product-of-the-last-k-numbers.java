class ProductOfNumbers {

    private List<Integer> numsList;

    public ProductOfNumbers() {
        numsList = new ArrayList<Integer>();
        numsList.add(1);    
    }
    
    public void add(int num) {
        // Brute TC -> O(N)
        // numsList.add(num);

        // Optimal
        int n = numsList.size() - 1;
        if(num  == 0){
            numsList = new ArrayList<>();
            numsList.add(1);
        }else{
            numsList.add(numsList.get(n) * num);
        }
    }
    
    public int getProduct(int k) {
        
        // Brute TC -> O(N*k)
        // int n = numsList.size();
        // int prod = 1;
        // for(int i = n-k; i < n; i++){
        //     prod *= numsList.get(i);
        // }

        // return prod;


        // Optimal O(1)
        int n = numsList.size() - 1;
        if(n < k) return 0;

        return numsList.get(n) / numsList.get(n-k);
    }
}

/**
 * Your ProductOfNumbers object will be instantiated and called as such:
 * ProductOfNumbers obj = new ProductOfNumbers();
 * obj.add(num);
 * int param_2 = obj.getProduct(k);
 */