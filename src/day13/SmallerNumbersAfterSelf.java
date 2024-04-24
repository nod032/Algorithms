package day13;

import java.util.Arrays;

public class SmallerNumbersAfterSelf {
    private int[] tree;
    private final int maxVal = 20001;

    public int[] countSmaller(int[] ints) {
        int n = ints.length;
        int[] counts = new int[n];
        tree = new int[4 * maxVal];

        for (int i = n - 1; i >= 0; i--) {
            int shiftedValue = ints[i] + 10000;
            counts[i] = query(1, 0, maxVal - 1, 0, shiftedValue - 1);
            update(1, 0, maxVal - 1, shiftedValue);
        }
        return counts;
    }

    private void update(int node, int start, int end, int idx) {
        if (start == end) {
            tree[node]++;
        } else {
            int mid = (start + end) / 2;
            if (idx <= mid) {
                update(2 * node, start, mid, idx);
            } else {
                update(2 * node + 1, mid + 1, end, idx);
            }
            tree[node] = tree[2 * node] + tree[2 * node + 1];
        }
    }

    private int query(int node, int start, int end, int l, int r) {
        if (r < start || end < l) {
            return 0;
        }
        if (l <= start && end <= r) {
            return tree[node];
        }
        int mid = (start + end) / 2;
        int leftQuery = query(2 * node, start, mid, l, r);
        int rightQuery = query(2 * node + 1, mid + 1, end, l, r);
        return leftQuery + rightQuery;
    }

    public static void main(String[] args) {
        SmallerNumbersAfterSelf solution = new SmallerNumbersAfterSelf();
        int[] param_1 = {5, 2, 6, 1};
        int[] result = solution.countSmaller(param_1);
        System.out.println(Arrays.toString(result));
    }
}
