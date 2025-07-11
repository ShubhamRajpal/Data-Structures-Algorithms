/*
  Problem Link: https://leetcode.com/problems/meeting-rooms-iii/
*/

//Approach-1 (Brute Force - Do as said)
//T.C : O(mlogm +m*n) , where Let n = number of rooms, m =  number of meetings
//S.C : O(n)
class Solution {
    public int mostBooked(int n, int[][] meetings) {
        int m = meetings.length;
        int[] usedRoomsCount = new int[n];

        Arrays.sort(meetings, (a, b) -> a[0] - b[0]);

        long[] roomFreeTime = new long[n];
        Arrays.fill(roomFreeTime, -1);

        for (int[] meeting : meetings) {
            int start = meeting[0];
            int end = meeting[1];
            int duration = end - start;

            long earliestAvailableRoomTime = Long.MAX_VALUE;
            int earliestAvailableRoom = -1;
            boolean found = false;

            for (int i = 0; i < n; i++) {
                if (roomFreeTime[i] <= start) {
                    roomFreeTime[i] = end;
                    usedRoomsCount[i]++;
                    found = true;
                    break;
                }

                if (roomFreeTime[i] < earliestAvailableRoomTime) {
                    earliestAvailableRoomTime = roomFreeTime[i];
                    earliestAvailableRoom = i;
                }
            }

            if (!found) {
                roomFreeTime[earliestAvailableRoom] += duration;
                usedRoomsCount[earliestAvailableRoom]++;
            }

        }

        long maxRoomsAllocated = Long.MIN_VALUE;
        int roomNo = n;

        for (int i = 0; i < n; i++) {
            if (maxRoomsAllocated < usedRoomsCount[i]) {
                maxRoomsAllocated = usedRoomsCount[i];
                roomNo = i;
            }
        }

        return roomNo;
    }
}

//Approach-2 (Use priority Queue to find the first available meeting room)
//T.C : O(mlogm + m*log(n)) , where Let n = number of rooms, m =  number of meetings
//S.C : O(n)
class Pair {
    long endTime;
    int roomNo;

    public Pair(long time, int ind) {
        endTime = time;
        roomNo = ind;
    }
}

class Solution {
    public int mostBooked(int n, int[][] meetings) {
        int m = meetings.length;
        int[] usedRoomsCount = new int[n];

        Arrays.sort(meetings, (a, b) -> a[0] - b[0]);

        PriorityQueue<Integer> availableRooms = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            availableRooms.offer(i);
        }
        PriorityQueue<Pair> usedRooms = new PriorityQueue<Pair>((a, b) -> {

            if (Long.compare(a.endTime, b.endTime) == 0) {
                return Integer.compare(a.roomNo, b.roomNo);
            }

            return Long.compare(a.endTime, b.endTime);
        });

        for (int[] meeting : meetings) {
            int start = meeting[0];
            int end = meeting[1];
            int duration = end - start;

            while (!usedRooms.isEmpty() && usedRooms.peek().endTime <= start) {
                int room = usedRooms.poll().roomNo;
                availableRooms.offer(room);
            }

            if (!availableRooms.isEmpty()) {
                int roomNo = availableRooms.poll();
                usedRooms.offer(new Pair(end, roomNo));
                usedRoomsCount[roomNo]++;
            } else {
                Pair top = usedRooms.peek();
                long curEndTime = top.endTime;
                int room = top.roomNo;
                usedRooms.poll();
                curEndTime += duration;
                usedRoomsCount[room]++;
                usedRooms.offer(new Pair(curEndTime, room));
            }

        }

        long maxRoomsAllocated = Long.MIN_VALUE;
        int roomNo = n;

        for (int i = 0; i < n; i++) {
            if (maxRoomsAllocated < usedRoomsCount[i]) {
                maxRoomsAllocated = usedRoomsCount[i];
                roomNo = i;
            }
        }

        return roomNo;
    }
}
