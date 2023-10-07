package L25;

public class Main {

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
}
