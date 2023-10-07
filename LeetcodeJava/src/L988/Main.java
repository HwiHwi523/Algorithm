package L988;

public class Main {

    public static void main(String[] args) {
        Solution solution988 = new Solution();
        String a = "abz";
        System.out.println(a.compareTo("ababz"));
        // 1
        System.out.println(
            solution988.smallestFromLeaf(
                new TreeNode(0,
                    new TreeNode(1,
                        new TreeNode(3),
                        new TreeNode(4)),
                    new TreeNode(2,
                        new TreeNode(3),
                        new TreeNode(4))
                )
            )
        );

        // 2
        System.out.println(
            solution988.smallestFromLeaf(
                new TreeNode(25,
                    new TreeNode(1,
                        new TreeNode(1),
                        new TreeNode(3)),
                    new TreeNode(3,
                        new TreeNode(0),
                        new TreeNode(2))
                )
            )
        );

        // 3
        System.out.println(
            solution988.smallestFromLeaf(
                new TreeNode(2,
                    new TreeNode(2,
                        null,
                        new TreeNode(1,
                            new TreeNode(0),
                            null)),
                    new TreeNode(1,
                        new TreeNode(0),
                        null)
                )
            )
        );

        // 4
        System.out.println(
            solution988.smallestFromLeaf(
                new TreeNode(4,
                    new TreeNode(0,
                        null,
                        new TreeNode(1)),
                    new TreeNode(1)
                )
            )
        );

        // 5
        System.out.println(
            solution988.smallestFromLeaf(
                new TreeNode(25,
                    new TreeNode(1,
                        new TreeNode(0,
                            new TreeNode(1,
                                new TreeNode(0),
                                null),
                            null),
                        new TreeNode(0)),
                    null
                )
            )
        );
    }
}
