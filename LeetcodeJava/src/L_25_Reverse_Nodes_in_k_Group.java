public class L_25_Reverse_Nodes_in_k_Group {
    public static void main(String[] args) {
        Solution solution = new Solution();

        // 1
        ListNode listNode1 = new ListNode(1,
                new ListNode(2,
                        new ListNode(3,
                                new ListNode(4,
                                        new ListNode(5)))));
        printListNode(solution.reverseKGroup(listNode1, 2));

        // 2
        ListNode listNode2 = new ListNode(1,
                new ListNode(2,
                        new ListNode(3,
                                new ListNode(4,
                                        new ListNode(5)))));
        printListNode(solution.reverseKGroup(listNode2, 3));

        // 2
        ListNode listNode3 = new ListNode(1,
                new ListNode(2,
                        new ListNode(3,
                                new ListNode(4,
                                        new ListNode(5)))));
        printListNode(solution.reverseKGroup(listNode3, 5));
    }
    static void printListNode(ListNode listNode) {
        if (listNode == null) {
            System.out.println();
            return;
        }
        System.out.print(listNode.val + " ");
        printListNode(listNode.next);
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    static class Solution {
        public ListNode reverseKGroup(ListNode head, int k) {
            // 노드 개수 세기
            int groupCount = 0;
            ListNode cursor = head;
            while (cursor != null) {
                cursor = cursor.next;
                groupCount++;
            }
            groupCount /= k;

            // 각 그룹에 대해 역순으로 바꾸기
            ListNode result = null;  // 첫 번째 그룹의 가장 마지막 노드
            ListNode prevNode = null;
            ListNode nextNode;
            for (int i = 0; i < groupCount; i++) {  // 각 그룹에 대해 적용
                ListNode firstNode = head;

                for (int j = 0; j < k; j++) {
                    nextNode = head.next;
                    head.next = prevNode;
                    prevNode = head;
                    head = nextNode;
                }

                // 첫 번째 그룹의 가장 마지막 노드 저장
                if (result == null)
                    result = prevNode;
                if (firstNode.next != null)
                    firstNode.next.next = prevNode;
                firstNode.next = head;
                prevNode = firstNode;
            }

            return result;
        }
    }
}
