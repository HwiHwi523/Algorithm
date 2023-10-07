package L19;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummyHeader = new ListNode(-1, head);

        List<ListNode> stack = new ArrayList<>();

        ListNode cursor = dummyHeader;
        while (cursor != null) {
            stack.add(cursor);
            cursor = cursor.next;
        }

        for (int i = 0; i < n; i++) {
            stack.remove(stack.size() - 1);
        }

        ListNode beforeRemoved = stack.get(stack.size() - 1);
        beforeRemoved.next = beforeRemoved.next.next;

        return dummyHeader.next;
    }
}
