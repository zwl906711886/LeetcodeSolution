import java.util.ArrayList;
import java.util.List;

public class Leetcode_1260 {

}

class shiftGrid {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int n = grid.length;
        int m = grid[0].length;
        List<List<Integer>> ans = new ArrayList<>();
        int[][] res = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int idx = i * m + j + k;
                idx %= (n * m);
                int row = idx / m, col = idx % m;
                res[row][col] = grid[i][j];
            }
        }
        for (int i = 0; i < n; i++) {
            List<Integer> ret = new ArrayList<>();
            for (int j = 0; j < m; j++) {
                ret.add(res[i][j]);
            }
            ans.add(ret);
        }
        return ans;
    }
}
