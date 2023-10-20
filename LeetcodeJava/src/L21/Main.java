package L21;

public class Main {

    public static void main(String[] args) {
        Solution solution = new Solution();

        // 1
        ListNode l1_1 = makeListNode(1, 2, 4);
        ListNode l2_1 = makeListNode(1, 3, 4);
        printListNode(solution.mergeTwoLists(l1_1, l2_1));

        // 2
        ListNode l1_2 = makeListNode();
        ListNode l2_2 = makeListNode();
        printListNode(solution.mergeTwoLists(l1_2, l2_2));

        // 3
        ListNode l1_3 = makeListNode();
        ListNode l2_3 = makeListNode(0);
        printListNode(solution.mergeTwoLists(l1_3, l2_3));
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
