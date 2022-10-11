import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N = 1000000 + 10;
    static int n;
    static int[] primes = new int[N], d = new int[N], mp = new int[N];
    static boolean[] del = new boolean[N];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] a = new int[n], SZ = new int[n];
        int[] b = new int[100];
        ArrayList arrayList = new ArrayList();
        Vector<Integer> vector = new Vector();
        List list = new ArrayList();
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
            int t = a[i];
            while (t > 0) {
                t /= 10;
                SZ[i] += 1;
            }
        }
        int[] pw = new int[16];
        pw[0] = 1;
        for (int i = 1; i <= 10; i++) {
            pw[i] = pw[i - 1] * 10 % k;
        }
        HashMap<Integer, Integer>[] hashMap = new HashMap[16];

        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= n; j++) {
                Integer integer = hashMap[i].get(pw[j] * a[i]  % k);
                int key1 = pw[j] * a[i]  % k;
                hashMap[i].replace(key1, integer + 1);
            }
        }
        long res = 0;
        for (int i = 1; i <= n; i++) {
            int sz = SZ[i];
            int base = a[i] % k;
            int need = (k - base) % k;
            res += hashMap[sz].get(need);
            if (a[i] * pw[sz] % k == need) res -= 1;
        }
        System.out.println(res);

    }
}
