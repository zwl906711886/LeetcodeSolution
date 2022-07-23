import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Leetcode_115 {
}
@SuppressWarnings({"all"})
class SequenceReconstruction{
    int N = 10000 + 10, M = N;
    int[] h = new int[N], ne = new int[M], e = new int[M];
    int idx = 0;
    int[] in = new int[N];
    void add(int a, int b) {
        e[idx] = b; ne[idx] = h[a]; h[a] = idx++;
    }
    public boolean sequenceReconstruction(int[] nums, int[][] sequences) {
        Arrays.fill(h, -1);
        int n = nums.length;
        for (int[] x : sequences) {
            int len = x.length;
            for (int i = 1; i < len; i++) {
                add(x[i - 1], x[i]);
                in[x[i]]++;
            }
        }
        Queue<Integer> queue = new ArrayDeque();
        for (int[] x : sequences) {
            for (int y : x) {
                if (in[y] == 0) {
                    queue.offer(y);
                }
            }
        }
        int loc = 0;
        while (!queue.isEmpty()) {
            if (queue.size() >= 2) return false;
            int t = queue.poll();
            System.out.println(t + "  " + nums[loc]);
            if (t != nums[loc++]) return false;
            for (int i = h[t]; i != -1; i = ne[i]) {
                int j = e[i];
                if (--in[j] == 0) {
                    queue.offer(j);
                }
            }
        }
        return true;
    }
}
