public class Leetcode_1374 {
    public static void main(String[] args) {

    }
}

class GenerateTheString{
    public String generateTheString(int n) {
        StringBuilder ans = new StringBuilder();
        if (n % 2 == 1) {
            for (int i = 0; i < n; i ++) {
                ans.append("a");
            }
        } else {
            for (int i = 0; i < n - 1; i++) {
                ans.append("a");
            }
            ans.append("b");
        }
        return ans.toString();
    }
}
