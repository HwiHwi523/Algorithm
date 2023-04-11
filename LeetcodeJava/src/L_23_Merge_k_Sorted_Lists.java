import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class L_23_Merge_k_Sorted_Lists {
    public static void main(String[] args) {
        ListNode[] listNodes = new ListNode[3];
        listNodes[0] = new ListNode(1,new ListNode(4, new ListNode(5)));
        listNodes[1] = new ListNode(1, new ListNode(3, new ListNode(4)));
        listNodes[2] = new ListNode(2, new ListNode(6));

        Solution23 solution = new Solution23();
        System.out.println(solution.mergeKLists(listNodes));
    }
}


// Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }

}
class Solution23 {
    public ListNode mergeKLists(ListNode[] lists) {
        Queue<ListNode> listNodeQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
        for (ListNode listNode : lists)
            if (listNode != null)
                listNodeQueue.add(listNode);

        ListNode answer = new ListNode();
        ListNode cursor = answer;
        while (!listNodeQueue.isEmpty()) {
            ListNode top = listNodeQueue.poll();  // 최솟값 뽑기
            if (top.next != null)  // List 다시 넣기
                listNodeQueue.add(top.next);

            // 일차원 배열에 추가 후 커서 이동
            cursor.next = new ListNode(top.val);
            cursor = cursor.next;
        }

        return answer.next;
    }
}