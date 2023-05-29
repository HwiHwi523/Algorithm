import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class L_988_Smallest_String_Starting_From_Leaf {
    public static void main(String[] args) {
        Solution988 solution988 = new Solution988();
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

    static class TreeNode {
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

    static class Solution988 {
        public String smallestFromLeaf(TreeNode root) {
            // 최소인 리프 노드 찾기
            int smallestLeaf = smallestLeaf(root);

            // 후보 문자열 구하기
            List<String> candidates = new ArrayList<>();
            getSmallestStrings(root, smallestLeaf, candidates, "");

            // 사전순 정렬
            candidates.sort(String::compareTo);

            return candidates.get(0);
        }

        /**
         * 최소인 리프 노드 구하기
         * @param root : 최상단 노드
         * @return : 리프 노드 중 최솟값
         */
        private int smallestLeaf(TreeNode root) {
            if (root.left == null && root.right == null)
                return root.val;

            int left = Integer.MAX_VALUE;
            if (root.left != null)
                left = smallestLeaf(root.left);

            int right = Integer.MAX_VALUE;
            if (root.right != null)
                right = smallestLeaf(root.right);

            return Math.min(left, right);
        }

        /**
         * 리프 노드가 smallest인 경로의 문자열 역순 구하기
         * @param root : 최상단 노드
         * @param smallest : 최소인 리프 노드
         * @param candidates : 후보군
         * @param str : 후보 문자열
         */
        private void getSmallestStrings(TreeNode root, int smallest, List<String> candidates, String str) {
            String curChar = String.valueOf((char) ('a' + root.val));

            // 최소 리프 노드에 도달했을 경우 후보군에 추가
            if (root.left == null && root.right == null) {
                if (root.val != smallest)
                    return;
                candidates.add(curChar + str);
            }

            // 좌측, 우측 자식 탐색
            if (root.left != null)
                getSmallestStrings(root.left, smallest, candidates, curChar + str);
            if (root.right != null)
                getSmallestStrings(root.right, smallest, candidates, curChar + str);
        }
    }
}
