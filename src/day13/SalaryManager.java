package day13;

import java.util.*;

public class SalaryManager {
    static Scanner scanner = new Scanner(System.in);
    static int[] salaries;
    static int[] tree;
    static int size;
    static Map<Integer, Integer> salaryToIndex = new HashMap<>();
    static List<Integer> indexToSalary = new ArrayList<>();
    static TreeMap<Integer, Integer> allSalaries = new TreeMap<>();

    public static void main(String[] args) {
        int N = scanner.nextInt();
        int numQueries = scanner.nextInt();
        salaries = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            int salary = scanner.nextInt();
            salaries[i] = salary;
            allSalaries.put(salary, allSalaries.getOrDefault(salary, 0) + 1);
        }
        prepareCompressedSalaries();
        tree = new int[2 * size];
        buildTree(1, 0, size - 1);
        for (int i = 0; i < numQueries; i++) {
            String type = scanner.next();
            int a = scanner.nextInt();
            int b = scanner.nextInt();

            if (type.equals("!")) {
                updateSalary(a, b);
            } else if (type.equals("?")) {
                int result = querySalaries(a, b);
                System.out.println(result);
            }
        }
        scanner.close();
    }
    private static void prepareCompressedSalaries() {
        int index = 0;
        for (Integer salary : allSalaries.keySet()) {
            salaryToIndex.put(salary, index);
            indexToSalary.add(salary);
            index++;
        }
        size = indexToSalary.size();
    }

    private static void buildTree(int node, int start, int end) {
        if (start == end) {
            tree[node] = allSalaries.getOrDefault(indexToSalary.get(start), 0);
        } else {
            int mid = (start + end) / 2;
            buildTree(2 * node, start, mid);
            buildTree(2 * node + 1, mid + 1, end);
            tree[node] = tree[2 * node] + tree[2 * node + 1];
        }
    }
    private static void updateSalary(int employeeIndex, int newSalary) {
        int oldSalary = salaries[employeeIndex];
        if (oldSalary != newSalary) {
            updateOrAddSalary(oldSalary, -1);
            updateOrAddSalary(newSalary, 1);
            salaries[employeeIndex] = newSalary;
        }
    }
    private static void ensureCapacity(int requiredIndex) {
        if (requiredIndex >= size) {
            int originalSize = size;
            while (size <= requiredIndex) {
                size *= 2;
            }
            tree = Arrays.copyOf(tree, 2 * size);
        }
    }

    private static void updateOrAddSalary(int salary, int delta) {
        if (!salaryToIndex.containsKey(salary)) {
            int newIndex = indexToSalary.size();
            salaryToIndex.put(salary, newIndex);
            indexToSalary.add(salary);
            allSalaries.put(salary, 0);
            ensureCapacity(newIndex);
        }
        allSalaries.put(salary, allSalaries.get(salary) + delta);
        updateTree(1, 0, size - 1, salaryToIndex.get(salary), delta);
    }

    private static void updateTree(int node, int start, int end, int index, int delta) {
        if (start == end) {
            tree[node] += delta;
        } else {
            int mid = (start + end) / 2;
            if (index <= mid) {
                updateTree(2 * node, start, mid, index, delta);
            } else {
                updateTree(2 * node + 1, mid + 1, end, index, delta);
            }
            tree[node] = tree[2 * node] + tree[2 * node + 1];
        }
    }


    private static int querySalaries(int low, int high) {
        Integer lowIndex = getSafeIndex(low, true);
        Integer highIndex = getSafeIndex(high, false);
        if (lowIndex == null || highIndex == null || lowIndex > highIndex) {
            return 0;
        }
        return queryTree(1, 0, size - 1, lowIndex, highIndex);
    }

    private static Integer getSafeIndex(Integer salary, boolean isLow) {
        if (salaryToIndex.containsKey(salary)) {
            return salaryToIndex.get(salary);
        }
        if (isLow) {
            Integer higher = indexToSalary.stream().filter(s -> s >= salary).min(Integer::compareTo).orElse(null);
            return higher != null ? salaryToIndex.get(higher) : null;
        } else {
            Integer lower = indexToSalary.stream().filter(s -> s <= salary).max(Integer::compareTo).orElse(null);
            return lower != null ? salaryToIndex.get(lower) : null;
        }
    }



    private static int queryTree(int node, int start, int end, int queryStart, int queryEnd) {
        if (queryStart > end || queryEnd < start) {
            return 0;
        }
        if (queryStart <= start && end <= queryEnd) {
            return tree[node];
        }
        int mid = (start + end) / 2;
        return queryTree(2 * node, start, mid, queryStart, queryEnd) + queryTree(2 * node + 1, mid + 1, end, queryStart, queryEnd);
    }
}
