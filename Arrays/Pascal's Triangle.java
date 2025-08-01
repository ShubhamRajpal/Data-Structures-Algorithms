/*
  Problem Link: https://leetcode.com/problems/pascals-triangle/
*/
//Approach-1 (Doing simply just like Pascal triangle is formed)
// T.C: O(N*N)
// S.C: O(N)
class Solution {

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();
        ans.add(Arrays.asList(1));
       
        List<Integer> prev = ans.get(ans.size() - 1); 
        for(int i = 2; i <= numRows; i++){
            List<Integer> temp1 = new ArrayList<>();
            for(int j = 1; j <= i; j++){
                if(j == 1 || j == i){
                    temp1.add(1);
                }else{
                    temp1.add(prev.get(j-2) + prev.get(j-1));
                }
            }
            ans.add(temp1);
            prev = temp1;
        }

        return ans;

    }
}

//Approach-1 (Using Mathematical Formula : (R-1) C (C-1)
// T.C: O(N*R)
// S.C: O(1)
class Solution {
    public List<Integer> generateRow(int row){
        long res = 1;
        List<Integer> tempList = new ArrayList<>();
        tempList.add(1);
        for(int j = 1; j < row; j++){
            res = res * (row-j);
            res = res/j;
            tempList.add((int)res);
        }

        return tempList;
    }

    public List<List<Integer>> generate(int numRows) {

        List<List<Integer>> ans = new ArrayList<>();
        ans.add(Arrays.asList(1));
        for(int i = 2; i <= numRows; i++){
            ans.add(generateRow(i));
        }

        return ans;

    }
}
