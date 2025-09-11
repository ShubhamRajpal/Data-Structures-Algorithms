/*
  Problem Link: https://leetcode.com/problems/sort-vowels-in-a-string/
*/

//Approach-1 (Store vowels in list and sort them)
//T.C : O(nlogn)
//S.C : O(n)
class Solution {
    private boolean isVowel(char c){

    return c == 'a' || c=='e' || c=='i' || c=='o' || c=='u' || c=='A' || c=='E' || c =='I' || c=='O' || c=='U';
    }
    public String sortVowels(String s) {
        StringBuilder sb = new StringBuilder();
        List<Character> vowels = new ArrayList<>();
        for (char ch : s.toCharArray()) {
            if(isVowel(ch)) vowels.add(ch);
        }

        Collections.sort(vowels);
        int it = 0;
        for(char c: s.toCharArray()){
            if(!isVowel(c)){
                sb.append(c);
            }else{
                sb.append(vowels.get(it));
                it++;
            }
        }

        return sb.toString();
    }
}


//Approach-2 (Without sorting. Counting the vowels - counting sort)
//T.C : O(n)
//S.C : O(1)
class Solution {
    private boolean isVowel(char c) {

        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'A' || c == 'E' || c == 'I' || c == 'O'
                || c == 'U';
    }

    public String sortVowels(String s) {
        StringBuilder sb = new StringBuilder();
        int[] vowelsCnt = new int[53];
        for (char ch : s.toCharArray()) {
            if (isVowel(ch)) {
                vowelsCnt[ch - 'A']++;
            }
        }

        String sortedVowels = "AEIOUaeiou";
        int it = 0;
        for (char c : s.toCharArray()) {
            if (!isVowel(c)) {
                sb.append(c);
            } else {
                while (vowelsCnt[sortedVowels.charAt(it) - 'A'] == 0) {
                    it++;
                }
                sb.append(sortedVowels.charAt(it));
                vowelsCnt[sortedVowels.charAt(it) - 'A']--;
            }
        }

        return sb.toString();
    }
}
