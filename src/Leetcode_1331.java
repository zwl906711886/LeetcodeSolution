import javafx.util.Pair;

import java.util.*;

public class Leetcode_1331 {

    public static void main(String[] args) {
        Pair<Integer, Integer> a = new Pair<>(1, 2);


    }
}

class ArrayRankTransform{
    public int[] arrayRankTransform(int[] a) {
        int n = a.length;
        int[] ans = new int[n];
        int[] b = new int[n];
        System.arraycopy(a, 0, b, 0, n);
        Arrays.sort(b);
        Map<Integer, Integer> map = new HashMap<>();
        int rk = 1;
        for (int i = 0; i < n; i++) {
            if (!map.containsKey(b[i])) {
                map.putIfAbsent(b[i], rk);
                rk++;
            }
        }
        for (int i = 0; i < n; i++) {
            ans[i] = map.get(a[i]);
        }
        return ans;
    }
}
