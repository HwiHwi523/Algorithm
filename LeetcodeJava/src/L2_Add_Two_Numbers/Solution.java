package L2_Add_Two_Numbers;

public class Solution {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int sum = l1.val + l2.val;
        int exceeded = sum / 10;

        ListNode result = new ListNode(sum % 10);
        ListNode resultCursor = result;

        while (l1.next != null && l2.next != null) {
            l1 = l1.next;
            l2 = l2.next;

            sum = l1.val + l2.val + exceeded;
            exceeded = sum / 10;

            resultCursor.next = new ListNode(sum % 10);
            resultCursor = resultCursor.next;
        }

        while (l1.next != null) {
            l1 = l1.next;

            sum = l1.val + exceeded;
            exceeded = sum / 10;

            resultCursor.next = new ListNode(sum % 10);
            resultCursor = resultCursor.next;
        }

        while (l2.next != null) {
            l2 = l2.next;

            sum = l2.val + exceeded;
            exceeded = sum / 10;

            resultCursor.next = new ListNode(sum % 10);
            resultCursor = resultCursor.next;
        }

        if (exceeded != 0) {
            resultCursor.next = new ListNode(exceeded);
        }

        return result;
    }
}
