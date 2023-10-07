package L23;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution {

    public ListNode mergeKLists(ListNode[] lists) {
        Queue<ListNode> listNodeQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
        for (ListNode listNode : lists) {
            if (listNode != null) {
                listNodeQueue.add(listNode);
            }
        }

        ListNode answer = new ListNode();
        ListNode cursor = answer;
        while (!listNodeQueue.isEmpty()) {
            ListNode top = listNodeQueue.poll();  // 최솟값 뽑기
            if (top.next != null)  // List 다시 넣기
            {
                listNodeQueue.add(top.next);
            }

            // 일차원 배열에 추가 후 커서 이동
            cursor.next = new ListNode(top.val);
            cursor = cursor.next;
        }

        return answer.next;
    }
}
