package L2_Add_Two_Numbers;

public class Main {

    public static void main(String[] args) {
        Solution solution = new Solution();

        // 1
        ListNode l1_1 = makeListNode(2, 4, 3);
        ListNode l2_1 = makeListNode(5, 6, 4);
        printListNode(solution.addTwoNumbers(l1_1, l2_1));

        // 2
        ListNode l1_2 = makeListNode(0);
        ListNode l2_2 = makeListNode(0);
        printListNode(solution.addTwoNumbers(l1_2, l2_2));

        // 3
        ListNode l1_3 = makeListNode(9, 9, 9, 9, 9, 9, 9);
        ListNode l2_3 = makeListNode(9, 9, 9, 9);
        printListNode(solution.addTwoNumbers(l1_3, l2_3));
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
