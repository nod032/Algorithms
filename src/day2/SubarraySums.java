import java.util.HashMap;
import java.util.Scanner;

public class SubarraySums {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int x = scan.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scan.nextInt();
        }
        HashMap<Long, Integer> prefixCount = new HashMap<>();
        prefixCount.put(0L, 1);
        long currentPrefixSum = 0;
        int result = 0;

        for (int num : arr) {
            currentPrefixSum += num;
            result += prefixCount.getOrDefault(currentPrefixSum - x, 0);
            prefixCount.put(currentPrefixSum, prefixCount.getOrDefault(currentPrefixSum, 0) + 1);
        }
        System.out.println(result);
    }
}
