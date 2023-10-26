package L124;

public class Solution {

    public int maxPathSum(TreeNode root) {
        return getPathSum(root).getFullPathSum();
    }

    private Result getPathSum(TreeNode node) {
        if (node == null) {
            return new Result(0, Integer.MIN_VALUE);
        }

        Result leftPathSum = getPathSum(node.left);
        Result rightPathSum = getPathSum(node.right);

        int onePathSum = getMax(
            leftPathSum.getOnePathSum() + node.val,
            rightPathSum.getOnePathSum() + node.val,
            node.val
        );

        int fullPathSum = getMax(
            leftPathSum.getFullPathSum(),
            rightPathSum.getFullPathSum(),
            onePathSum,
            leftPathSum.getOnePathSum() + rightPathSum.getOnePathSum() + node.val
        );

        return new Result(onePathSum, fullPathSum);
    }

    private int getMax(int... nums) {
        int max = nums[0];

        for (int num : nums) {
            max = Math.max(max, num);
        }

        return max;
    }
}

class Result {

    private final int onePathSum;
    private final int fullPathSum;

    public Result(int onePathSum, int fullPathSum) {
        this.onePathSum = onePathSum;
        this.fullPathSum = fullPathSum;
    }

    public int getOnePathSum() {
        return onePathSum;
    }

    public int getFullPathSum() {
        return fullPathSum;
    }
}
