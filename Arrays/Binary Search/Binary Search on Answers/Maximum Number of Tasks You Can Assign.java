/*
  Problem Link: https://leetcode.com/problems/maximum-number-of-tasks-you-can-assign/description/
*/

//Approach (Binary Search on Answer)
//T.C : O(MLogM+NLogN+Min(M,N)∗Log^2(Min(M,N))), M = tasks.size(), N = workers.size()
//S.C : O(Min(M,N))
class Solution {

    private boolean canAssign(int mid, int[] tasks, int[] workers, int pills, int strength) {

        TreeMap<Integer, Integer> wMap = new TreeMap<>();
        int pillsRemaining = pills;
        for (int i = workers.length - mid; i < workers.length; i++) {
            wMap.put(workers[i], wMap.getOrDefault(workers[i], 0) + 1);
        }

        for (int i = mid - 1; i >= 0; i--) {
            Integer last = wMap.lastKey();
            if (last >= tasks[i]) {
                wMap.put(last, wMap.get(last) - 1);
                if (wMap.get(last) == 0) {
                    wMap.remove(last);
                }
            } else {
                if (pillsRemaining == 0)
                    return false;

                int requiredStrength = tasks[i] - strength;
                //Check if we can get a worker having strength greater than or equal to requiredStrength. If No, that means mid no. of tasks can't be done
                Integer weakestWorker = wMap.ceilingKey(requiredStrength);
                if (weakestWorker == null)
                    return false;

                // If yes, Take the weakest worker and give it a pill to make its strength equal to strongest task strength
                wMap.put(weakestWorker, wMap.get(weakestWorker) - 1);
                if (wMap.get(weakestWorker) == 0)
                    wMap.remove(weakestWorker);
                pillsRemaining--;

            }
        }

        return true;
    }

    public int maxTaskAssign(int[] tasks, int[] workers, int pills, int strength) {
        int n = tasks.length;
        int m = workers.length;

        Arrays.sort(tasks);
        Arrays.sort(workers);

        int l = 1, r = Math.min(n, m), ans = 0;
        while (l <= r) {
            int mid = l + (r - l) / 2;

            if (canAssign(mid, tasks, workers, pills, strength)) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return ans;
    }
}
