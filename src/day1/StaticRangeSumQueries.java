import java.util.Scanner;

public class StaticRangeSumQueries {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int numberOfInts = scan.nextInt();
        int numberOfQueries = scan.nextInt();

        int[] ints = new int[numberOfInts];
        long[] prefixSum = new long[numberOfInts + 1];

        for (int i = 0; i < numberOfInts; i++) {
            ints[i] = scan.nextInt();
            prefixSum[i + 1] = prefixSum[i] + ints[i];
        }

        StringBuilder output = new StringBuilder();

        for (int i = 0; i < numberOfQueries; i++) {
            int A = scan.nextInt();
            int B = scan.nextInt();
            output.append(prefixSum[B] - prefixSum[A - 1]).append("\n");
        }

        scan.close();
        System.out.print(output.toString());
    }
}
