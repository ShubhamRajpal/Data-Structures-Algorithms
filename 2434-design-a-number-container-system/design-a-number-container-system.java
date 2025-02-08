class NumberContainers {

    private Map<Integer, Integer> indexMap;
    private Map<Integer, TreeSet<Integer>> smallestPositionMap;

    public NumberContainers() {
        indexMap = new HashMap<>();
        smallestPositionMap = new HashMap<>();    
    }
    
    public void change(int index, int number) {
        if(indexMap.containsKey(index)){
            int num = indexMap.get(index);
            TreeSet<Integer> temp =  smallestPositionMap.get(num);
            temp.remove(index);
            if(temp.isEmpty()) smallestPositionMap.remove(num);
            else smallestPositionMap.put(num, temp);
        }

        // Update the index with the new number
        indexMap.put(index,number);

        TreeSet<Integer> st = smallestPositionMap.getOrDefault(number, new TreeSet<>());
        st.add(index);
        smallestPositionMap.put(number, st);
        // smallestPositionMap.put(number, smallestPositionMap.getOrDefault(number, new TreeSet<>()).add(index));


        


    }
    
    public int find(int number) {
        if(!smallestPositionMap.containsKey(number)){
            return -1;
        }
        return smallestPositionMap.get(number).first();
    }
}

/**
 * Your NumberContainers object will be instantiated and called as such:
 * NumberContainers obj = new NumberContainers();
 * obj.change(index,number);
 * int param_2 = obj.find(number);
 */