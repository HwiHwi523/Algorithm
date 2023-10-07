package L98;

public class Main {

    public static void main(String[] args) {
        Solution solution98 = new Solution();

        System.out.println("1 : " + solution98.isValidBST(new TreeNode(2,
            new TreeNode(1), new TreeNode(3))));
        System.out.println("2 : " + solution98.isValidBST(new TreeNode(5,
            new TreeNode(1), new TreeNode(4,
            new TreeNode(3), new TreeNode(6)))));
        System.out.println("3 : " + solution98.isValidBST(new TreeNode(5,
            new TreeNode(4), new TreeNode(6,
            new TreeNode(3), new TreeNode(7)))));
        System.out.println("4 : " + solution98.isValidBST(
            new TreeNode(-2147483648, new TreeNode(-2147483648), null)));
    }
}
