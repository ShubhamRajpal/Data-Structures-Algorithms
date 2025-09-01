/*
    Problem Link: https://leetcode.com/problems/maximum-average-pass-ratio/
*/

/*
    Why normal Greedy fails ?
    Assigning extra students to the class with the least passing ratio assumes that improving the worst-performing class will maximize the overall average.
    However, this ignores the fact that the rate of improvement (or marginal gain) varies depending on the current number of passing and total students
    in the class. A class with a low passing ratio might require many additional students to see a significant improvement in its passing ratio,
    while a class with a higher passing ratio might see a much larger improvement with the same number of extra students.
*/


//Approach-1 : (Chossing class with max delta/improvement everytime) -> TLE
//T.C : O(extraStudents * n)
//S.C : O(n) 
class Solution {
    public double maxAverageRatio(int[][] classes, int extraStudents) {
        int n = classes.length;

        // Array to store the current pass ratio for each class
        double[] PR = new double[n];
        for (int i = 0; i < n; i++) {
            double ratio = (double) classes[i][0] / classes[i][1];
            PR[i] = ratio;
        }

        while (extraStudents-- > 0) {
            // Array to store the updated pass ratio for each class
            double[] updatedPR = new double[n];
            for (int i = 0; i < n; i++) {
                double newRatio = (double) (classes[i][0] + 1) / (classes[i][1] + 1);
                updatedPR[i] = newRatio;
            }

            // Find the class that has the maximum improvement in pass ratio
            int bestClassIdx = 0;
            double bestDelta = 0;

            for (int i = 0; i < n; i++) {
                double delta = updatedPR[i] - PR[i];
                if (delta > bestDelta) {
                    bestDelta = delta;
                    bestClassIdx = i;
                }
            }

            // Update the best class with an extra student
            PR[bestClassIdx] = updatedPR[bestClassIdx];
            classes[bestClassIdx][0]++;
            classes[bestClassIdx][1]++;
        }

        // Calculate the final average pass ratio
        double result = 0.0;
        for (int i = 0; i < n; i++) {
            result += PR[i];
        }

        return result / n;
    }
}



//Approach-2 : (Chossing class with max delta/improvement everytime - Improving with max heap)
//T.C : O(extraStudents * log(n))
//S.C : O(n)
import java.util.PriorityQueue;

class Solution {
    public double maxAverageRatio(int[][] classes, int extraStudents) {
        int n = classes.length;

        // Priority queue to act as a max-heap, storing pairs of {max-delta, index}
        PriorityQueue<double[]> pq = new PriorityQueue<>((a, b) -> Double.compare(b[0], a[0]));

        // Initialize the priority queue with the delta values and indices
        for (int i = 0; i < n; i++) {
            double currentPR = (double) classes[i][0] / classes[i][1];
            double newPR = (double) (classes[i][0] + 1) / (classes[i][1] + 1);
            double delta = newPR - currentPR;
            pq.offer(new double[]{delta, i});
        }

        // Allocate extra students
        while (extraStudents-- > 0) {
            // Extract the class with the maximum delta
            double[] curr = pq.poll();
            int idx = (int) curr[1];

            // Update the class with an extra student
            classes[idx][0]++;
            classes[idx][1]++;

            // Recalculate the delta for this class
            double currentPR = (double) classes[idx][0] / classes[idx][1];
            double newPR = (double) (classes[idx][0] + 1) / (classes[idx][1] + 1);
            double delta = newPR - currentPR;

            // Push the updated delta and index back into the priority queue
            pq.offer(new double[]{delta, idx});
        }

        // Calculate the final average pass ratio
        double result = 0.0;
        for (int i = 0; i < n; i++) {
            result += (double) classes[i][0] / classes[i][1];
        }

        return result / n;
    }
}
