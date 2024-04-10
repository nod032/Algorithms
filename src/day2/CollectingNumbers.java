import java.util.Scanner;

public class CollectingNumbers {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[] positions = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int number = scan.nextInt();
            positions[number] = i;
        }
        int rounds = 1;
        for (int i = 2; i <= n; i++) {
            if (positions[i] < positions[i - 1]) {
                rounds++;
            }
        }
        System.out.println(rounds);
    }
}
