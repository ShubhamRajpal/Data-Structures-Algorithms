/*
  Problem Link: https://leetcode.com/problems/remove-sub-folders-from-the-filesystem/
*/

//Approach-1 (Using set and substring find)
//T.C : O(n*L^2)
//S.C : O(n) //You can consider the length of each character as well - O(n*L)
class Solution {
    public List<String> removeSubfolders(String[] folder) {
        // Create a set from the folder array
        Set<String> folderSet = new HashSet<>(Arrays.asList(folder));
        List<String> result = new ArrayList<>();

        // Iterate over each folder in the array
        for (String currFolder : folder) {
            boolean isSubFolder = false;
            String tempFolder = currFolder;

            // Continue until currFolder is empty
            while (!currFolder.isEmpty()) {
                int position = currFolder.lastIndexOf('/');  // Find the last occurrence of '/'
                if (position == -1) break;  // Exit if there are no more '/' characters

                currFolder = currFolder.substring(0, position);  // Get the parent folder

                // Check if the parent folder exists in the set
                if (folderSet.contains(currFolder)) {
                    isSubFolder = true;  // It is a sub-folder
                    break;
                }
            }

            // If it's not a sub-folder, add it to the result list
            if (!isSubFolder) {
                result.add(tempFolder);
            }
        }

        return result;
    }
}


//Approach-2 (Using Sorting)
//T.C : O(n*logn) //You can consider the length of each folder path(L) as well - O(n*L*logn)
//S.C : O(1)
class Solution {
    public List<String> removeSubfolders(String[] folder) {
        // Sort the folder array lexicographically
        Arrays.sort(folder);
        List<String> result = new ArrayList<>();
        
        // The first folder can never be a sub-folder after sorting
        result.add(folder[0]);

        // Iterate through the sorted folders
        for (int i = 1; i < folder.length; i++) {
            String currFolder = folder[i];
            String lastFolder = result.get(result.size() - 1);
            lastFolder += "/";  // Add '/' to the last folder to check for sub-folder

            // If the current folder does not start with the last folder, it is not a sub-folder
            if (!currFolder.startsWith(lastFolder)) {
                result.add(currFolder);
            }
        }

        return result;
    }
}


//Approach-3(using Trie structure)
T.C : O(n*L)
S.C : O(n*L) In worst case, all folder paths are unique so we will have n*L segments in Trie 
class Node{
    Map<String, Node> ref;
    boolean flag;

    public Node(){
        ref = new HashMap<>();
        flag = false;
    }

    public boolean contains(String ch){
        return ref.containsKey(ch); 
    }

    public boolean isEnd(){
        return flag == true;
    }

    public Node get(String ch){
        return ref.get(ch);
    }

    public void put(String ch, Node node){
        ref.put(ch, node);
    }

    public void markEnd(){
        flag = true;
    }

}


class Solution {
    private static Node root;
    public Solution(){
        root = new Node();
    }
    public List<String> removeSubfolders(String[] folder) {
        List<String> ans = new ArrayList<>();
        for(String word : folder){
            Node curr = root;
            for(String fname :  word.split("/")){
                
                if(fname == "") continue;
                if(!curr.contains(fname)){
                    curr.put(fname, new Node());
                }
            
                curr = curr.get(fname);
             
            }

            curr.markEnd();
        }

        for(String word1 : folder){
            Node curr1 = root;
            boolean isSubFolder = false;
            for(String fname :  word1.split("/")){
                if(fname == "") continue;
                    if(curr1.isEnd()) {
                        isSubFolder = true;
                        break;
                    }
                    curr1 = curr1.get(fname);
            }
            if(!isSubFolder) ans.add(word1);

        }

        return ans;
    }
}
