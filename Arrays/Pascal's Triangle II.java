/*
  Problem Link: https://leetcode.com/problems/pascals-triangle-ii/
*/

// Approach-1 (Doing simply just like Pascal triangle is formed)
// T.C: O(N*N)
// S.C: O(N)
class Solution {

  public List<Integer> getRow(int rowIndex) {

      List<Integer> ans = new ArrayList<>();
      ans.add(1);
      if(rowIndex == 0) return ans;
      int numRows = rowIndex + 1;
      List<Integer> prev = ans; 
      for(int i = 2; i <= numRows; i++){
          List<Integer> temp1 = new ArrayList<>();
          for(int j = 1; j <= i; j++){
              if(j == 1 || j == i){
                  temp1.add(1);
              }else{
                  temp1.add(prev.get(j-2) + prev.get(j-1));
              }
          }
          if(rowIndex+1 == i) return temp1;
          prev = temp1;
      }
    
      return new ArrayList;
    }
}


// Approach-2 (Using Mathematical Formula: Calculate values for row which is asked column by column)
// T.C: O(N*R)
// S.C: O(1)
class Solution {
    
    public int findNCR(int n, int r){
        
        long res = 1;
        for(int i = 0; i < r; i++){
            res = res * (n-i);
            res = res/(i+1);    
        }

        return (int)res;
    }


    public List<Integer> getRow(int rowIndex) {

        // Better Approach TC -> O(N*R)
        List<Integer> ans = new ArrayList<>();
        for(int c = 1; c <= (rowIndex + 1); c++){
           ans.add(findNCR(rowIndex, c - 1));
        }

        return ans;

    }
}


// Approach-3 (Using Mathematical Formula : Calculate values for row which is asked in one pass)
// T.C: O(N)
// S.C: O(1)
class Solution {

    public List<Integer> getRow(int rowIndex) {

        List<Integer> ans = new ArrayList<>();
        ans.add(1);
        long res = 1;
        int n = rowIndex+1;
        for(int i = 1; i < n; i++){
            res = res * (n - i);
            res = res / i;

            ans.add((int)(res));
        }

        return ans;

    }
}
