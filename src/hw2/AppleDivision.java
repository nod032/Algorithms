package hw2;

import java.util.Scanner;

public class AppleDivision {

    static long minDif = Long.MAX_VALUE;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        long[] masses = new long[n];

        for (int i = 0; i < n; i++) {
            masses[i] = scanner.nextLong();
        }
        scanner.close();

        findMinDif(masses, 0, 0, 0);
        System.out.println(minDif);
    }

    public static void findMinDif(long[] masses, int index, long sum1, long sum2) {
        if (index == masses.length) {
            long dif = Math.abs(sum1 - sum2);
            if (dif < minDif) {
                minDif = dif;
            }
        } else {
            // Assign a current apple to each group to search all possibilities
            findMinDif(masses, index + 1, sum1 + masses[index], sum2);
            findMinDif(masses, index + 1, sum1, sum2 + masses[index]);
        }
    }
}
