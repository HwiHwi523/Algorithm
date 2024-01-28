"""

"""
import sys
from collections import deque


class Node:
    def __init__(self, num):
        self.num = num
        self.cost = 0
        self.depth = 0
        self.parents = []

    def set_parents(self):
        if self.num == 1:
            return

        idx = 1
        parent = self.parents[0]

        while idx <= len(parent.parents):
            grand = parent.parents[idx - 1]
            self.parents.append(grand)
            parent = self.parents[idx]
            idx += 1


def set_tree(nodes, edges):
    bfs_q = deque([1])
    visit = [False] * len(nodes)
    visit[1] = True

    while bfs_q:
        front = bfs_q.popleft()

        nodes[front].set_parents()

        for next_node, w in edges[front]:
            if visit[next_node]:
                continue
            nodes[next_node].depth = nodes[front].depth + 1
            nodes[next_node].cost = nodes[front].cost + w
            nodes[next_node].parents.append(nodes[front])
            bfs_q.append(next_node)
            visit[next_node] = True


def balancing(node, target_depth):
    if node.depth == target_depth:
        return node

    cursor = node.parents[0]
    for i in range(1, len(node.parents)):
        if node.parents[i].depth < target_depth:
            break
        cursor = node.parents[i]

    return balancing(cursor, target_depth)


def get_lca(node_u, node_v):
    if node_u == node_v:
        return node_u

    cursor_u = node_u.parents[0]
    cursor_v = node_v.parents[0]

    for i in range(1, len(node_u.parents)):
        if node_u.parents[i] == node_v.parents[i]:
            break
        cursor_u = node_u.parents[i]
        cursor_v = node_v.parents[i]

    return get_lca(cursor_u, cursor_v)


def query1(node_u, node_v):
    if node_u.depth < node_v.depth:
        node_u, node_v = node_v, node_u

    cost = node_u.cost + node_v.cost
    node_u = balancing(node_u, node_v.depth)

    return cost - (get_lca(node_u, node_v).cost << 1)


def query2(node_u, node_v, k):
    if node_u.depth > node_v.depth:
        lca = get_lca(balancing(node_u, node_v.depth), node_v)
    else:
        lca = get_lca(node_u, balancing(node_v, node_u.depth))

    dist_lca = node_u.depth - lca.depth + 1

    if k <= dist_lca:
        return balancing(node_u, node_u.depth - k + 1).num

    return balancing(node_v, lca.depth + (k - dist_lca)).num


def solution():
    sys_input = sys.stdin.readline

    n = int(sys_input())

    nodes = [Node(i) for i in range(n + 1)]
    edges = [[] for _ in range(n + 1)]
    for _ in range(n - 1):
        u, v, w = map(int, sys_input().split())
        edges[u].append((v, w))
        edges[v].append((u, w))
    set_tree(nodes, edges)

    m = int(sys_input())
    for _ in range(m):
        order = list(map(int, sys_input().split()))
        if order[0] == 1:
            print(query1(nodes[order[1]], nodes[order[2]]))
        else:
            print(query2(nodes[order[1]], nodes[order[2]], order[3]))


solution()
