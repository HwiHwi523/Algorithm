package L19;

public class Main {

    public static void main(String[] args) {
        Solution solution = new Solution();

        // 1
        ListNode listNode1 = makeListNode(1, 2, 3, 4, 5);
        printListNode(solution.removeNthFromEnd(listNode1, 2));

        // 2
        ListNode listNode2 = makeListNode(1);
        printListNode(solution.removeNthFromEnd(listNode2, 1));

        // 3
        ListNode listNode3 = makeListNode(1, 2);
        printListNode(solution.removeNthFromEnd(listNode3, 1));
    }

    static ListNode makeListNode(int... elems) {
        ListNode header = new ListNode(-1);
        ListNode cursor = header;

        for (int num : elems) {
            cursor.next = new ListNode(num);
            cursor = cursor.next;
        }

        return header.next;
    }

    static void printListNode(ListNode root) {
        while (root.next != null) {
            System.out.print(root.val + " ");
            root = root.next;
        }
        System.out.println(root.val);
    }
}
