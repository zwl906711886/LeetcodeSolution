public class Leetcode_1184 {

    public static void main(String[] args) {

    }
}

class DistanceBetweenBusStops{
    public int distanceBetweenBusStops(int[] distance, int start, int destination) {
        int n = distance.length;
        int ans = 1000000000;
        int sum = 0;
        int p = start;
        while (p != destination) {
            sum += distance[p];
            p = (p + 1) % n;
        }
        ans = Math.min(ans, sum);
        p = start;
        sum = 0;

        while (p != destination) {
            p = ((p - 1) % n + n) % n;
            sum += distance[p];

        }
        ans = Math.min(ans, sum);
        return ans;


    }
}
