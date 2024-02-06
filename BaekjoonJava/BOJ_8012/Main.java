package BOJ_8012;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        List<Integer>[] edges = inputEdges(n, br);
        Node[] nodes = new Node[n + 1];
        for (int i = 1; i <= n; i++) {
            nodes[i] = new Node(i);
        }
        makeTree(nodes, 1, edges, new boolean[n + 1]);

        int m = Integer.parseInt(br.readLine());
        int cur = 1;
        int answer = 0;

        br.readLine();
        while (--m > 0) {
            int next = Integer.parseInt(br.readLine());

            Node curNode = nodes[cur];
            Node nextNode = nodes[next];

            Node lca = getLca(
                    balancing(curNode, nextNode.depth),
                    balancing(nextNode, curNode.depth)
            );

            answer += curNode.depth + nextNode.depth - (lca.depth << 1);
            cur = next;
        }

        System.out.println(answer);
    }

    static class Node {

        int num;
        int depth;
        List<Node> child;
        List<Node> parents;

        public Node(int num) {
            this.num = num;
            this.depth = 0;
            child = new ArrayList<>();
            parents = new ArrayList<>();
        }

        void link(Node node) {
            child.add(node);
            node.parents.add(this);
            node.depth = this.depth + 1;

            Node parent = this;
            int idx = 0;

            while (idx < parent.parents.size()) {
                Node grand = parent.parents.get(idx);
                node.parents.add(grand);
                parent = node.parents.get(++idx);
            }
        }
    }

    static List<Integer>[] inputEdges(int n, BufferedReader br) throws IOException {
        List<Integer>[] edges = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            edges[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            edges[a].add(b);
            edges[b].add(a);
        }

        return edges;
    }

    static void makeTree(Node[] nodes, int idx, List<Integer>[] edges, boolean[] visit) {
        visit[idx] = true;

        for (int next : edges[idx]) {
            if (visit[next]) {
                continue;
            }
            nodes[idx].link(nodes[next]);
            makeTree(nodes, next, edges, visit);
        }
    }

    static Node balancing(Node node, int targetDepth) {
        if (node.depth <= targetDepth) {
            return node;
        }

        Node curNode = node.parents.get(0);

        for (int i = 1; i < node.parents.size(); i++) {
            if (node.parents.get(i).depth < targetDepth) {
                break;
            }
            curNode = node.parents.get(i);
        }

        return balancing(curNode, targetDepth);
    }

    static Node getLca(Node node1, Node node2) {
        if (node1.equals(node2)) {
            return node1;
        }

        Node curNode1 = node1.parents.get(0);
        Node curNode2 = node2.parents.get(0);

        for (int i = 1; i < node1.parents.size(); i++) {
            if (node1.parents.get(i).equals(node2.parents.get(i))) {
                break;
            }
            curNode1 = node1.parents.get(i);
            curNode2 = node2.parents.get(i);
        }

        return getLca(curNode1, curNode2);
    }
}
