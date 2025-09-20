/*
  Problem Link: https://leetcode.com/problems/implement-router/
*/

//Approach (Queue for FIFO, Map for quick retrieval and sorted vector for binary search)
// T.C. : 
// addPacket()       -> O(k) worst-case (due to forwardPacket() + vector erase at front)
// forwardPacket()   -> O(k) worst-case (erase from vector front for destination timestamps)
// getCount()        -> O(log k) per query (binary search in vector)
// k = number of packets for a particular destination

// S.C. : 
// O(MAX_SIZE) total
// packetStore     -> O(MAX_SIZE)
// que             -> O(MAX_SIZE * key length)
// destTimestamps  -> O(MAX_SIZE)
class Packet {
    int source, destination, timeStamp;

    public Packet(int source, int destination, int timeStamp) {
        this.source = source;
        this.destination = destination;
        this.timeStamp = timeStamp;
    }
}

class Router {

    Queue<Packet> q;
    Map<Integer, List<Integer>> desTimeStamps;
    Set<String> uniquePackets;
    int memoryLimit;

    public Router(int memoryLimit) {
        q = new LinkedList<>();
        desTimeStamps = new HashMap<>();
        uniquePackets = new HashSet<>();
        this.memoryLimit = memoryLimit;
    }

    public boolean addPacket(int source, int destination, int timestamp) {
        String packet = source+"$"+destination+"$"+timestamp;
        if(uniquePackets.contains(packet)) return false;

      
        if(q.size() == this.memoryLimit) {
            int src = q.peek().source;
            int dest = q.peek().destination;
            int time = q.peek().timeStamp;

            String oldestPacket = src+"$"+dest+"$"+time;
            List<Integer> temp = desTimeStamps.get(dest);
            temp.remove(0);
            q.poll();
            uniquePackets.remove(oldestPacket);  
        }

        desTimeStamps.computeIfAbsent(destination, k -> new ArrayList<>()).add(timestamp);
        uniquePackets.add(packet);
        q.add(new Packet(source, destination, timestamp));

        return true;
    }

    public int[] forwardPacket() {
        if (q.isEmpty())
            return new int[] {};
        int src = q.peek().source;
        int dest = q.peek().destination;
        int time = q.peek().timeStamp;

        String oldestPacket = src + "$" + dest + "$" + time;
        List<Integer> temp = desTimeStamps.get(dest);
        temp.remove(0);
        q.poll();
        uniquePackets.remove(oldestPacket);

        return new int[] { src, dest, time };
    }

    public int getCount(int destination, int startTime, int endTime) {
        if(!desTimeStamps.containsKey(destination))  return 0;
        List<Integer> temp = desTimeStamps.get(destination);
        int lb = getLower(temp, startTime);
        int ub = getUpper(temp, endTime);

        return ub - lb + 1;
    }

    private int getLower(List<Integer> temp, int start){
        int l = 0, r = temp.size()-1;
        while( l <= r){
            int mid = l + (r-l)/2;
            if(temp.get(mid) >= start){
                r = mid-1;
            }else{
                l = mid + 1;
            }
        }

        return l;
    }

    private int getUpper(List<Integer> temp, int end){
        int l = 0, r = temp.size()-1;
        while( l <= r){
            int mid = l + (r-l)/2;
            if(temp.get(mid) <= end){
                l = mid + 1;
            }else{
                r = mid-1;
            }
        }

        return r;
    }
}

/**
 * Your Router object will be instantiated and called as such:
 * Router obj = new Router(memoryLimit);
 * boolean param_1 = obj.addPacket(source,destination,timestamp);
 * int[] param_2 = obj.forwardPacket();
 * int param_3 = obj.getCount(destination,startTime,endTime);
 */
