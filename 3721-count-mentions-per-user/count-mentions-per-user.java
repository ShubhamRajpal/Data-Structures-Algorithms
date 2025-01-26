class Solution {
    public int[] countMentions(int numberOfUsers, List<List<String>> events) {
        int n = events.size();
        Map<Integer, Integer> offline = new HashMap<>();
        int[] res = new int[numberOfUsers];
        Collections.sort(events, new Comparator<List<String>>() {
            public int compare(List<String> l1, List<String> l2){
                if(l1.get(1).equals(l2.get(1))){
                    return (l2.get(0).charAt(0) - 'A') - (l1.get(0).charAt(0) - 'A'); 
                }else return Integer.parseInt(l1.get(1)) - Integer.parseInt(l2.get(1));
            }
        });
                
        int t = 0;
        for(int i = 0; i < n; i++){
            String event = events.get(i).get(0);
            int curTime = Integer.parseInt(events.get(i).get(1));
            if(event.equals("MESSAGE")){
                String mentionString = events.get(i).get(2);
                if(mentionString.equals("ALL")){
                    for(int j = 0;j < numberOfUsers; j++){
                        res[j]++;
                    }
                }
                else if(mentionString.equals("HERE")){
                    for(int j = 0; j < numberOfUsers; j++){
                        if(!offline.containsKey(j)){
                            res[j] += 1;
                        }else if(offline.get(j)+60 <= curTime){
                            res[j] += 1;
                            offline.remove(j);
                        }
                    }
                }else{
                    String[] usersId = events.get(i).get(2).split(" ");
                    for(int j = 0; j < usersId.length; j++){
                        int ind1 = Integer.parseInt(usersId[j].substring("id".length()));
                            res[ind1]++;  
                    }
                }
            }else if(event.equals("OFFLINE")){
                int ind = Integer.parseInt(events.get(i).get(2));
                offline.put(ind, curTime);
            }
        }
            return res;
    }
}