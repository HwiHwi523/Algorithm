package L1361;

public class Main {

    public static void main(String[] args) {
        Solution solution1361 = new Solution();

        System.out.println("1 : " + solution1361.validateBinaryTreeNodes(4,
            new int[]{1, -1, 3, -1},
            new int[]{2, -1, -1, -1}));
        System.out.println("2 : " + solution1361.validateBinaryTreeNodes(4,
            new int[]{1, -1, 3, -1},
            new int[]{2, 3, -1, -1}));
        System.out.println("3 : " + solution1361.validateBinaryTreeNodes(2,
            new int[]{1, 0},
            new int[]{-1, -1}));
    }
}
