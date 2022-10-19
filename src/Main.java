import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner sc = new Scanner(System.in);

        int[] a = new int[]{1, 2, 3, 4};
//        Integer[] arr = new Integer[]{1, 3, 2, 4};
        List<Integer> arr = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            arr.add(i);
        }
        Collections.sort(arr);
        for (int i : a) {
            System.out.println(i);
        }
        System.out.println(123);
    }
}
