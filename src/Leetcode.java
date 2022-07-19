import java.util.HashMap;
import java.util.Map;

public class Leetcode {

    public static void main(String[] args) {

    }
}


class MyCalendarTwo {
    Map<Integer, int[]> tree;
    public MyCalendarTwo() {
        tree = new HashMap<>();
    }

    public boolean book(int start, int end) {
        update(1, 0, 1000000000, start, end - 1, 1);
        tree.putIfAbsent(1, new int[2]);
        if (tree.get(1)[0] > 2) {
            update(1, 0, 1000000000, start, end - 1, -1);
            return false;
        }
        return true;
    }

    public void update(int idx, int l, int r, int ql, int qr, int val) {

        tree.putIfAbsent(idx, new int[2]); //第一维维护最大值， 第二维维护最大值
        if (ql <=l && r <= qr) {
            tree.get(idx)[0] += val;
            tree.get(idx)[1] += val;
            return;
        }
        int mid = l + r >> 1;
        if (ql <= mid) update(idx * 2, l, mid, ql, qr, val);
        if (qr > mid) update(idx * 2 + 1, mid + 1, r, ql, qr, val);
        tree.putIfAbsent(idx * 2, new int[2]);
        tree.putIfAbsent(idx * 2 + 1, new int[2]);
        tree.get(idx)[0] = tree.get(idx)[1] + Math.max(tree.get(idx * 2)[0], tree.get(idx * 2 + 1)[0]);

    }
}

/**
 * Your MyCalendarTwo object will be instantiated and called as such:
 * MyCalendarTwo obj = new MyCalendarTwo();
 * boolean param_1 = obj.book(start,end);
 */
