package L25;

public class Solution {

    public ListNode reverseKGroup(
        ListNode head, int k) {
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
            if (result == null) {
                result = prevNode;
            }
            if (firstNode.next != null) {
                firstNode.next.next = prevNode;
            }
            firstNode.next = head;
            prevNode = firstNode;
        }

        return result;
    }
}
