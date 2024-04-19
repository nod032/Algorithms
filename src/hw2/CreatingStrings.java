package hw2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CreatingStrings {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        scanner.close();

        char[] chars = input.toCharArray();
        Arrays.sort(chars);
        List<String> results = new ArrayList<>();
        boolean[] used = new boolean[chars.length];
        backtrack(results, new StringBuilder(), chars, used);

        System.out.println(results.size());
        for (String result : results) {
            System.out.println(result);
        }
    }

    private static void backtrack(List<String> results, StringBuilder current, char[] chars, boolean[] used) {
        if (current.length() == chars.length) {
            results.add(current.toString());
        } else {
            for (int i = 0; i < chars.length; i++) {
                if (used[i] || (i > 0 && chars[i] == chars[i-1] && !used[i-1])) {
                    // In case we try to use the same character again or getting the duplicate values but with swapped indices, we skip the permutation.
                    continue;
                }
                used[i] = true;
                current.append(chars[i]);
                backtrack(results, current, chars, used);
                current.deleteCharAt(current.length() - 1);
                used[i] = false;
            }
        }
    }
}
