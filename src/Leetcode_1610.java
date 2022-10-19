import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Leetcode_1610 {
    public static void main(String[] args) {

    }
}

class VisiblePoints{
    double eps = 1e-9;
    public int visiblePoints(List<List<Integer>> points, int angle, List<Integer> location) {
        int x = location.get(0), y = location.get(1);
        List<Double> list = new ArrayList<>();
        double pi = Math.PI, t = angle * pi / 180;
        int cnt = 0;
        for (List<Integer> p : points) {
            int a = p.get(0), b = p.get(1);
            if (a == x && b == y) {
                ++cnt;
                continue;
            }
            list.add(Math.atan2(b - y, a - x) + pi);
        }
        Collections.sort(list);
        int n = list.size(), max = 0;
        for (int i = 0; i < n; i++) list.add(list.get(i) + 2 * pi);
        for (int i = 0, j = 0; i < 2 * n; i++) {
            while (j < i && list.get(i) - list.get(j) > t + eps) j++;
            max = Math.max(max, j - i + 1);
        }
        return max + cnt;
    }
}
