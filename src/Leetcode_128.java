import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Leetcode_128 {
    public static void main(String[] args) {

    }
}
@SuppressWarnings({"all"})
class GetSkyline {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> ans = new ArrayList<>();
        List<int[]> ps = new ArrayList<>();
        for (int[] b : buildings) {
            int l = b[0], r = b[1], h = b[2];
            ps.add(new int[]{l, -h});
            ps.add(new int[]{r, h});
        }

        Collections.sort(ps, (a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0];
            return a[1] - b[1];
        });

//        for (int [] x : ps) {
//            for (int y : x) {
//                System.out.println(y);
//            }
//        }
        //  大根堆
        PriorityQueue<Integer> q = new PriorityQueue<>((a, b) -> b - a);
        int prev = 0;
        q.add(prev);

        for (int[] p : ps) {
            int point = p[0], height = p[1];
            if (height < 0) {
                q.add(-height);
            } else {
                q.remove(height);
            }

            int cur = q.peek();
            if (cur != prev) {
                List<Integer> list = new ArrayList<>();
                list.add(point);
                list.add(cur);
                ans.add(list);
                prev = cur;
            }
        }

        return ans;
    }
}
