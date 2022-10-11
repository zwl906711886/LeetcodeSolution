import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Leetcode_1235 {

    public static void main(String[] args) {
        Integer[] x = new Integer[]{1, 2, 3};
        Arrays.sort(x, (A, B) -> B - A);
        for (Integer integer : x) {
            System.out.println(integer);
        }
    }
}

class JobScheduling {
    public int jobScheduling(int[] s, int[] e, int[] p) {
        int n = s.length;
        // 按照结束时间排序的小根堆

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);

        Integer[] idx = new Integer[n];
        for (int i = 0; i < n; i++) idx[i] = i;
        Arrays.sort(idx, (a, b) -> s[a] - s[b]);
        int max = 0;
        for (int i = 0; i < n; i++) {
            int start = s[idx[i]];
            while (!pq.isEmpty() && pq.peek()[1] <= start) {
                max = Math.max(max, pq.poll()[0]);
            }
            pq.offer(new int[]{max + p[idx[i]], e[idx[i]]});
        }
        while (!pq.isEmpty()) {
            max = Math.max(max, pq.poll()[0]);
        }
        return max;
    }
}
