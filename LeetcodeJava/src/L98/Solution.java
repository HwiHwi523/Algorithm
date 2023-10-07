package L98;

public class Solution {
    boolean check(TreeNode node, long begin, long end) {
        if (node == null)
            return true;
        if (node.val < begin || end < node.val)
            return false;

        boolean isLeftValid = check(node.left, begin, (long)node.val - 1);
        boolean isRightValid = check(node.right, (long)node.val + 1, end);

        return isLeftValid && isRightValid;
    }

    public boolean isValidBST(TreeNode root) {
        return check(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
}
