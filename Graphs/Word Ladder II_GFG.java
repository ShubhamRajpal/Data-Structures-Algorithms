class Solution {
    public ArrayList<ArrayList<String>> findSequences(String startWord,
                                                      String targetWord,
                                                      String[] wordList) {
       ArrayList<ArrayList<String>> ans = new ArrayList<>();
       Set<String> st = new HashSet<>();
       for(int i = 0;i < wordList.length; i++){
           st.add(wordList[i]);
       }
       
       Queue<ArrayList<String>> q = new LinkedList<>();
       ArrayList<String> ls = new ArrayList<>();
       ls.add(startWord);
       q.add(ls);
       ArrayList<String> usedOnLevel = new ArrayList<>();
       usedOnLevel.add(startWord);
       int level = 0;
       
       while(!q.isEmpty()){
           ArrayList<String> temp = q.peek();
           q.remove();
           
           // erase all words that have been used on previous level to transform
           if(temp.size() > level){
               level++;
               for(String s : usedOnLevel){
                   st.remove(s);
               }
           }
           
           String word = temp.get(temp.size()-1);
           if(word.equals(targetWord)){
               // first sequence where we reached
                if(ans.size() == 0){
                   ans.add(temp);
                }else if(ans.get(0).size() == temp.size()) ans.add(temp);
           }
           
           for(int i = 0;i < word.length(); i++){
               for(char ch = 'a' ; ch <= 'z'; ch++){
                   
                   char[] replacedCharArray = word.toCharArray();
                   replacedCharArray[i] = ch;
                   String replacedString = new String(replacedCharArray);
                   if(st.contains(replacedString)){
                       temp.add(replacedString);
                       ArrayList<String> temp1 = new ArrayList<>(temp);
                       q.add(temp1);
                       
                       //mark as visited on that level
                       usedOnLevel.add(replacedString);
                       temp.remove(temp.size()-1);
                   }
               }
           }
           
       }
       
       return ans;
       
    }
}
