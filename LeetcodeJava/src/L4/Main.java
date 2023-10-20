package L4;

public class Main {

    public static void main(String[] args) {
        Solution solution = new Solution();

        // 1
        System.out.println(solution.findMedianSortedArrays(new int[]{1, 3}, new int[]{2}));

        // 2
        System.out.println(solution.findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4}));

        // 3
        System.out.println(solution.findMedianSortedArrays(new int[]{}, new int[]{1}));
    }
}
