package day13;

public class HouseRobber {
    public static int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        int[] arr = new int[nums.length];
        arr[0] = nums[0];
        arr[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < nums.length; i++) {
            arr[i] = Math.max(arr[i - 1], nums[i] + arr[i - 2]);
        }
        return arr[nums.length - 1];
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 1};
        System.out.println("Maximum amount that can be robbed is: " + rob(nums1));

        int[] nums2 = {2, 7, 9, 3, 1};
        System.out.println("Maximum amount that can be robbed is: " + rob(nums2));
    }
}
