package L988;

import java.util.ArrayList;
import java.util.List;

public class Solution {

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
     *
     * @param root : 최상단 노드
     * @return : 리프 노드 중 최솟값
     */
    private int smallestLeaf(TreeNode root) {
        if (root.left == null && root.right == null) {
            return root.val;
        }

        int left = Integer.MAX_VALUE;
        if (root.left != null) {
            left = smallestLeaf(root.left);
        }

        int right = Integer.MAX_VALUE;
        if (root.right != null) {
            right = smallestLeaf(root.right);
        }

        return Math.min(left, right);
    }

    /**
     * 리프 노드가 smallest인 경로의 문자열 역순 구하기
     *
     * @param root       : 최상단 노드
     * @param smallest   : 최소인 리프 노드
     * @param candidates : 후보군
     * @param str        : 후보 문자열
     */
    private void getSmallestStrings(TreeNode root,
        int smallest, List<String> candidates, String str) {
        String curChar = String.valueOf((char) ('a' + root.val));

        // 최소 리프 노드에 도달했을 경우 후보군에 추가
        if (root.left == null && root.right == null) {
            if (root.val != smallest) {
                return;
            }
            candidates.add(curChar + str);
        }

        // 좌측, 우측 자식 탐색
        if (root.left != null) {
            getSmallestStrings(root.left, smallest, candidates, curChar + str);
        }
        if (root.right != null) {
            getSmallestStrings(root.right, smallest, candidates, curChar + str);
        }
    }
}
