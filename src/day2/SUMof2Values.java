import java.util.HashMap;
import java.util.Scanner;

public class SUMof2Values {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int x = sc.nextInt();

        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int current = a[i];
            int needed = x - current;

            if (map.containsKey(needed)) {
                System.out.println((map.get(needed) + 1) + " " + (i + 1));
                return;
            }

            map.put(current, i);
        }

        System.out.println("IMPOSSIBLE");
    }
}
