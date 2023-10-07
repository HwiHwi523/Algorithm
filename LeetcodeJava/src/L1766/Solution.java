package L1766;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class Solution {

    static class Tree {

        int num;
        int value;
        int ancestor;
        Tree parent;
        Set<Tree> child;

        public Tree(int num, int value) {
            this.num = num;
            this.value = value;
            this.ancestor = -1;
            this.parent = null;
            this.child = new HashSet<>();
        }
    }

    public int[] getCoprimes(int[] nums, int[][] edges) {
        // 노드 생성
        Tree[] nodes = new Tree[nums.length];
        for (int i = 0; i < nums.length; i++) {
            nodes[i] = new Tree(i, nums[i]);
        }

        // 연결
        for (int[] edge : edges) {
            nodes[edge[0]].child.add(nodes[edge[1]]);
            nodes[edge[1]].child.add(nodes[edge[0]]);
        }

        // child 에서 부모 삭제
        removeParentFromChild(nodes[0]);

        // 각 노드의 서로소 찾기
        Queue<Tree> bfsQ = new ArrayDeque<>();
        bfsQ.add(nodes[0]);

        while (!bfsQ.isEmpty()) {
            Tree top = bfsQ.poll();
            findCoprime(top);
            for (Tree child : top.child) {
                bfsQ.add(child);
            }
        }

        // 결과 취합 후 반환
        int[] result = new int[nums.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = nodes[i].ancestor;
        }
        return result;
    }

    // 각 노드의 서로소 노드를 구하는 메서드
    private void findCoprime(Tree node) {
        Tree cursor = node.parent;
        while (cursor != null) {
            // 커서의 수와 서로소라면 커서 노드 번호 저장
            if (isCoprime(node.value, cursor.value)) {
                node.ancestor = cursor.num;
                break;
            }
            // 이미 앞에서 구했다면 커서의 ancestor 그대로 사용
            if (cursor.value == node.value) {
                node.ancestor = cursor.ancestor;
                break;
            }
            cursor = cursor.parent;
        }
    }

    // child 에서 부모 삭제하는 메서드
    private void removeParentFromChild(Tree root) {
        for (Tree child : root.child) {
            child.parent = root;
            child.child.remove(root);
            removeParentFromChild(child);
        }
    }

    private boolean isCoprime(int x, int y) {
        while (y != 0) {
            int temp = x % y;
            x = y;
            y = temp;
        }
        return x == 1;
    }
}
