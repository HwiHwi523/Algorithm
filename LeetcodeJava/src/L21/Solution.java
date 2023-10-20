package L21;

public class Solution {

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummyHeader = new ListNode(-1);
        ListNode cursor = dummyHeader;

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                cursor.next = list1;
                list1 = list1.next;
            } else {
                cursor.next = list2;
                list2 = list2.next;
            }
            cursor = cursor.next;
        }

        while (list1 != null) {
            cursor.next = list1;
            list1 = list1.next;
            cursor = cursor.next;
        }

        while (list2 != null) {
            cursor.next = list2;
            list2 = list2.next;
            cursor = cursor.next;
        }

        return dummyHeader.next;
    }
}
