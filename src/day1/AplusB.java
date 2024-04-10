package day1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class AplusB {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> numbers = new ArrayList<>();
        int quantity = scanner.nextInt();
        int A, B;
        for (int i = 0; i < quantity; i++) {
            A = scanner.nextInt();
            numbers.add(A);
            B = scanner.nextInt();
            numbers.add(B);
        }
        for (int i = 0; i < 2 * quantity; i+=2) {
            System.out.println(numbers.get(i) + numbers.get(i+1));
        }
    }


    
}
