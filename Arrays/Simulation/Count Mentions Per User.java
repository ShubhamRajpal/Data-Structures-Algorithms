/*
  Problem Link: https://leetcode.com/problems/count-mentions-per-user/
*/

//Approach - Simply sort and simulate
//T.C : O(E log E + E * N), N = number of users , E = number of events
//S.C : O(N + E)
class Solution {

    public void processStatus(int time, boolean[] isOnline, Map<Integer, Integer> offlineUsersMp) {

        for (Map.Entry<Integer, Integer> entry : offlineUsersMp.entrySet()) {
            if (time >= entry.getValue()) {
                isOnline[entry.getKey()] = true;
            }
        }
    }

    public int[] countMentions(int numberOfUsers, List<List<String>> events) {
        int n = numberOfUsers;
        boolean[] isOnline = new boolean[n];
        Arrays.fill(isOnline, true);
        Map<Integer, Integer> offlineUsersMp = new HashMap<>();
        int[] mentionsCount = new int[n];

        // Sort the events
        Collections.sort(events, (List<String> a, List<String> b) -> {

            if (a.get(1).equals(b.get(1))) {
                return (b.get(0).charAt(0) - 'A') - (a.get(0).charAt(0) - 'A');
            }

            return Integer.valueOf(a.get(1)) - Integer.valueOf(b.get(1));
        });

        for (List<String> itr : events) {
            String event = itr.get(0);
            int time = Integer.valueOf(itr.get(1));
            String mentionStr = itr.get(2);

            processStatus(time, isOnline, offlineUsersMp);

            if (event.equals("MESSAGE")) {

                if (mentionStr.equals("ALL")) {
                    // Increase mention count for all users by 1
                    for (int i = 0; i < n; i++) {
                        mentionsCount[i]++;
                    }
                } else if (mentionStr.equals("HERE")) {
                    // Increase mention count for all online users by 1
                    for (int i = 0; i < n; i++) {
                        if (isOnline[i]) {
                            mentionsCount[i]++;
                        }
                    }
                } else {
                    // Increase mention count for only given users by 1
                    String[] users = mentionStr.split(" ");
                    for (int i = 0; i < users.length; i++) {
                        int userId = Integer.valueOf(users[i].substring(2));
                        mentionsCount[userId]++;
                    }
                }
            } else if (event.equals("OFFLINE")) {
               
                int userId = Integer.valueOf(mentionStr);
                isOnline[userId] = false;
                offlineUsersMp.put(userId, time + 60);
            }

        }

        return mentionsCount;
    }
}
