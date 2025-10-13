/*
  Problem Link: https://leetcode.com/problems/find-resultant-array-after-removing-anagrams/
*/


//Approach-1 (simple traverse and check)
//T.C : O(n*m)
//S.C : O(1)
public class Solution {

    private boolean checkAnagram(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }

        int[] arr = new int[26];

        for (int i = 0; i < s1.length(); i++) {
            arr[s1.charAt(i) - 'a']++;
        }

        for (int i = 0; i < s2.length(); i++) {
            arr[s2.charAt(i) - 'a']--;
        }

        for (int i = 0; i < 26; i++) {
            if (arr[i] != 0) {
                return false;
            }
        }

        return true;
    }

    public List<String> removeAnagrams(String[] words) {
        int n = words.length;
        List<String> result = new ArrayList<>();

        result.add(words[0]);

        for (int i = 1; i < n; i++) {
            String lastWord = result.get(result.size() - 1);
            if (!checkAnagram(words[i], lastWord)) {
                result.add(words[i]);
            }
        }

        return result;
    }
}



//Approach-2 (Sort string words[i] and check if it equals previous string)
//T.C : O(n*logm)
//S.C : O(1)
class Solution {
    public List<String> removeAnagrams(String[] words) {
        List<String> ans = new ArrayList<>();
        int n = words.length;
        String last = "";
        for (int i = 0; i < n; i++) {
            char ch1[] = words[i].toCharArray();
            Arrays.sort(ch1);
            String cur = String.valueOf(ch1);
            if(last.equals(cur)){
                continue;
            }else{
                ans.add(words[i]);
                last = cur;
            }
        }

        return ans;

    }
}
