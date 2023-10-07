package L23;

public class Main {

    public static void main(String[] args) {
        ListNode[] listNodes = new ListNode[3];
        listNodes[0] = new ListNode(1, new ListNode(4, new ListNode(5)));
        listNodes[1] = new ListNode(1, new ListNode(3, new ListNode(4)));
        listNodes[2] = new ListNode(2, new ListNode(6));

        Solution solution = new Solution();
        System.out.println(solution.mergeKLists(listNodes));
    }
}
