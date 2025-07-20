/*
  Problem Link: https://leetcode.com/problems/delete-duplicate-folders-in-system/description/
*/

//Approach (Using trie)
//T.C : O(N * L * ClogC), N = total Paths, L = average length of each path, C is the average number of children per node
//S.C : ~O(N * L), we store all the paths in the trie, approximated value.
class TrieNode {
    Map<String, TrieNode> refMap = new HashMap<>();
    String subFolder = "";
    boolean deleted = false;
}

class Solution {

    Map<String, List<TrieNode>> subFolderMap = new HashMap<>();

    public String findSubFolders(TrieNode parent){
        if(parent.refMap.isEmpty()) return "";

        List<String> childSubFolders = new ArrayList<>();
        for(String child : parent.refMap.keySet()){
            String childString = findSubFolders(parent.refMap.get(child));
            childSubFolders.add(child + "("+childString+")");
        }

        Collections.sort(childSubFolders);
        String parentSubFolder = String.join("", childSubFolders);
        parent.subFolder = parentSubFolder;
        subFolderMap.computeIfAbsent(parentSubFolder, k -> new ArrayList<>()).add(parent);
        return parentSubFolder;
    }


    public void markDuplicateNodes(){
        for(List<TrieNode> nodes : subFolderMap.values()){
            if(nodes.size() > 1){
                for(TrieNode node : nodes){
                    node.deleted = true;
                }
            }
        }
    }

    public void solve(TrieNode root, List<String> temp, List<List<String>> res){
        for(String folder : root.refMap.keySet()){
            TrieNode child = root.refMap.get(folder);
            if(child.deleted) continue;
            temp.add(folder);
            res.add(new ArrayList<>(temp));
            solve(child, temp, res);
            temp.remove(temp.size()-1);
        }
    }

    public List<List<String>> deleteDuplicateFolder(List<List<String>> paths) {

        TrieNode root = new TrieNode();

        //Store the folder paths in the Trie
        for (List<String> path : paths) {
            TrieNode node = root;
            for (String folder : path) {
                if (!node.refMap.containsKey(folder)) {
                    node.refMap.put(folder, new TrieNode());
                }

                node = node.refMap.get(folder);
            }
        }

        //Store the subFolder structure for each node in subFolder Map
        findSubFolders(root);

        // Mark the duplicate nodes in Trie
        markDuplicateNodes();

        // Recursively find folders in Trie to build the answer
        List<List<String>> res = new ArrayList<>();
        solve(root, new ArrayList<>(), res);
        return res;

    }
}
