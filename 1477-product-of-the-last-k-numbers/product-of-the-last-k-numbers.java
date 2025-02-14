class ProductOfNumbers {

    private List<Integer> numsList;

    public ProductOfNumbers() {
        numsList = new ArrayList<Integer>();    
    }
    
    public void add(int num) {
        numsList.add(num);
    }
    
    public int getProduct(int k) {
        int n = numsList.size();
        int prod = 1;
        for(int i = n-k; i < n; i++){
            prod *= numsList.get(i);
        }

        return prod;
    }
}

/**
 * Your ProductOfNumbers object will be instantiated and called as such:
 * ProductOfNumbers obj = new ProductOfNumbers();
 * obj.add(num);
 * int param_2 = obj.getProduct(k);
 */