class Solution {
    public int[][] mergeArrays(int[][] nums1, int[][] nums2) {
        
        // Brute
        // Map<Integer, Integer> mp = new TreeMap<>();
        // for(int it[] : nums1){
        //     mp.put(it[0], mp.getOrDefault(it[0], 0)+it[1]);
        // }

        // for(int it[] : nums2){
        //     mp.put(it[0], mp.getOrDefault(it[0], 0)+it[1]);
        // }

        // System.out.print(mp);

        // int k = 0;
        // int[][] ans = new int[mp.size()][2];
        // for(Map.Entry<Integer, Integer> entry : mp.entrySet()){
        //     ans[k++] = new int[]{entry.getKey(), entry.getValue()};
        // }

        // return ans;

        // Optimal
        int n1 = nums1.length, n2 = nums2.length;
        int i = 0, j = 0;
        ArrayList<ArrayList> ans = new ArrayList<>();
        while(i < n1 && j < n2){
            ArrayList<Integer> temp = new ArrayList<>();
            if(nums1[i][0] == nums2[j][0]){
                temp.add(nums1[i][0]);
                temp.add(nums1[i][1]+nums2[j][1]);
                i++;
                j++;
            }else if(nums1[i][0] < nums2[j][0]){
                temp.add(nums1[i][0]);
                temp.add(nums1[i][1]);
                i++;
            }else{
                temp.add(nums2[j][0]);
                temp.add(nums2[j][1]);
                j++;
            }
            ans.add(temp);
        }

        while(i < n1){
            ArrayList<Integer> temp = new ArrayList<>();
            temp.add(nums1[i][0]);
            temp.add(nums1[i][1]);
            ans.add(temp);
            i++;
        }

        while(j < n2){
            ArrayList<Integer> temp = new ArrayList<>();
            temp.add(nums2[j][0]);
            temp.add(nums2[j][1]);
            ans.add(temp);
            j++;
        }

        int[][] res = new int[ans.size()][2];
        int k = 0;
        for(ArrayList<Integer> li : ans){
            res[k++] = new int[]{li.get(0),li.get(1)};
        }
        return res;

        
    }
}