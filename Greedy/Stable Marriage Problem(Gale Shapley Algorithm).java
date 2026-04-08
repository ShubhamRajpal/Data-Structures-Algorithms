/*
  Problem Link: https://www.geeksforgeeks.org/problems/stable-marriage-problem/1
*/

/*
  Algorithm - 
      Initialize all men and women to free
      while there exist a free man m who still has a woman w to propose to 
      {
          w = highest-ranked woman on m's preference list to whom he has not yet proposed
       if w is free
             (m, w) become engaged
       else let m' be the man currently engaged to w
       if w prefers m to m'
                (m, w) become engaged
                 m' becomes free
       else
                w remains engaged with m'
      }.  
*/

// Approach (Gale Shapley Algorithm)
// T.C: O(n^2)
// S.C: O(n^2)
class Solution {
    public int[] stableMarriage(int[][] men, int[][] women) {
        // code here
        int n = men.length;
        int[] menPartner = new int[n];
        int[] womenPartner = new int[n];
        int[] nextProposal = new int[n];
        Arrays.fill(menPartner, -1);
        Arrays.fill(womenPartner, -1);
        
        int[][] ranks = new int[n][n];
        for(int w = 0; w < n; w++){
            for(int rank = 0; rank < n; rank++){
                ranks[w][women[w][rank]] = rank;
            }
        }
        
        Queue<Integer> freeMan = new LinkedList<>();
        for(int i = 0; i < n; i++){
            freeMan.add(i);
        }
        
        
        while(!freeMan.isEmpty()){
            int man = freeMan.poll();
            
            int woman = men[man][nextProposal[man]];
            nextProposal[man]++;
            if(womenPartner[woman] == -1){
                womenPartner[woman] = man;
                menPartner[man] = woman;
            }else{
                int currentMan = womenPartner[woman];
                
                if(ranks[woman][man] < ranks[woman][currentMan]){
                     womenPartner[woman] = man;
                     menPartner[man] = woman;
                     freeMan.add(currentMan);
                     menPartner[currentMan] = -1;
                }else{
                    freeMan.add(man);
                }
            }
        }
        
        return menPartner;
        
    }
}
