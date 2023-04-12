public class L_1361_Validate_Binary_Tree_Nodes {
    public static void main(String[] args) {
        Solution1361 solution1361 = new Solution1361();

        System.out.println("1 : " + solution1361.validateBinaryTreeNodes(4,
                new int[] {1, -1, 3, -1},
                new int[] {2, -1, -1, -1}));
        System.out.println("2 : " + solution1361.validateBinaryTreeNodes(4,
                new int[] {1,-1,3,-1},
                new int[] {2,3,-1,-1}));
        System.out.println("3 : " + solution1361.validateBinaryTreeNodes(2,
                new int[] {1,0},
                new int[] {-1,-1}));
    }
}

class Solution1361 {
    boolean check(int node, int[] leftChild, int[] rightChild, boolean[] visit) {
        if (visit[node])  // 이미 방문했다면 유효하지 않은 트리
            return false;
        visit[node] = true;

        boolean isLeftValid = true;
        if (leftChild[node] != -1)
            isLeftValid = check(leftChild[node], leftChild, rightChild, visit);

        boolean isRightValid = true;
        if (rightChild[node] != -1)
            isRightValid = check(rightChild[node], leftChild, rightChild, visit);

        return isLeftValid && isRightValid;
    }

    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        boolean[] visit = new boolean[n];

        // degree-in 개수를 세어 root 부터 탐색을 시작하도록 구현
        int[] inCount = new int[n];
        for (int i = 0; i < n; i++) {
            if (leftChild[i] != -1)
                inCount[leftChild[i]]++;
            if (rightChild[i] != -1)
                inCount[rightChild[i]]++;
        }

        // root 찾기
        boolean result = false;
        for (int i = 0; i < n; i++) {
            if (inCount[i] == 0) {  // root부터 탐색 시작
                result = check(i, leftChild, rightChild, visit);
                break;  // 정상적인 트리라면 root는 하나만 존재하므로 바로 반복 종료
            }
        }

        // 아직 방문하지 않은 곳이 존재한다면 여러 개의 트리로 구성된 것이므로 false 반환
        if (result) {
            for (boolean visited :visit){
                if (!visited)
                    return false;
            }
        }

        return result;
    }
}