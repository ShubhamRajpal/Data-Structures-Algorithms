class Solution {
    public int numberOfAlternatingGroups(int[] colors, int k) {
        
        int n = colors.length;
        int[] arr = new int[n + k - 1];
        int K = 0;
        for (int i = 0; i < n; i++) {
            arr[K++] = colors[i];
        }

        for (int i = 0; i < k-1; i++) {
            arr[K++] = colors[i];
        }

        int i = 0, j = 1, cnt = 0;
        while (j < arr.length) {

            if (arr[j] == arr[j - 1]) {
                i = j;
                j++;
                continue;
            }

            j++;

            if (j - i < k) continue;

            cnt++;
            i++;
        }

        return cnt;
    }
}