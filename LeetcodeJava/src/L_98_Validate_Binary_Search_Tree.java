public class L_98_Validate_Binary_Search_Tree {
    public static void main(String[] args) {
        Solution98 solution98 = new Solution98();

        System.out.println("1 : " + solution98.isValidBST(new TreeNode(2,
                new TreeNode(1), new TreeNode(3))));
        System.out.println("2 : " + solution98.isValidBST(new TreeNode(5,
                new TreeNode(1), new TreeNode(4,
                new TreeNode(3), new TreeNode(6)))));
        System.out.println("3 : " + solution98.isValidBST(new TreeNode(5,
                new TreeNode(4), new TreeNode(6,
                new TreeNode(3), new TreeNode(7)))));
        System.out.println("4 : " + solution98.isValidBST(new TreeNode(-2147483648, new TreeNode(-2147483648), null)));
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution98 {
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