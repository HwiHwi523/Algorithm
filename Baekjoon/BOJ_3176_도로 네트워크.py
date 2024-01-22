"""

"""
import sys
from collections import deque


class Node:
    def __init__(self, num):
        self.num = num
        self.depth = 0
        self.parents = []

    def set_parents(self):
        if self.num == 1:
            return

        idx = 1
        parent, min_val, max_val = self.parents[0]
        while idx <= len(parent.parents):
            grand, g_min, g_max = parent.parents[idx - 1]
            self.parents.append((grand, min(min_val, g_min), max(max_val, g_max)))
            parent, min_val, max_val = self.parents[idx]
            idx += 1


def get_nodes(n, edges):
    nodes = [Node(i) for i in range(n + 1)]

    bfs_q = deque([1])

    visit = [False] * (n + 1)
    visit[1] = True

    while bfs_q:
        front = bfs_q.popleft()

        nodes[front].set_parents()

        for to, w in edges[front]:
            if visit[to]:
                continue
            visit[to] = True
            nodes[to].depth = nodes[front].depth + 1
            nodes[to].parents.append((nodes[front], w, w))
            bfs_q.append(to)

    return nodes


def balancing(node, target_depth):
    if node.depth == target_depth:
        return node, 1_000_000, -1

    next_node, next_min, next_max = node.parents[0]

    for i in range(1, len(node.parents)):
        if node.parents[i][0].depth < target_depth:
            break
        next_node, temp_min, temp_max = node.parents[i]
        next_min = min(next_min, temp_min)
        next_max = max(next_max, temp_max)

    target_node, min_val, max_val = balancing(next_node, target_depth)
    return target_node, min(next_min, min_val), max(next_max, max_val)


def get_common_ancestor(node1, node2):
    if node1 == node2:
        return 1_000_000, -1

    next_node1, min_val1, max_val1 = node1.parents[0]
    next_node2, min_val2, max_val2 = node2.parents[0]

    for i in range(1, len(node1.parents)):
        if node1.parents[i][0] == node2.parents[i][0]:
            break
        next_node1 = node1.parents[i][0]
        min_val1 = min(min_val1, node1.parents[i][1])
        max_val1 = max(max_val1, node1.parents[i][2])

        next_node2 = node2.parents[i][0]
        min_val2 = min(min_val2, node2.parents[i][1])
        max_val2 = max(max_val2, node2.parents[i][2])

    target_min, target_max = get_common_ancestor(next_node1, next_node2)
    return min(min_val1, min_val2, target_min), max(max_val1, max_val2, target_max)


def query(node1, node2):
    if node1.depth < node2.depth:
        node1, node2 = node2, node1

    node1, min_val1, max_val1 = balancing(node1, node2.depth)

    target_min, target_max = get_common_ancestor(node1, node2)
    return min(min_val1, target_min), max(max_val1, target_max)


def solution():
    sys_input = sys.stdin.readline

    n = int(sys_input())
    edges = [[] for _ in range(n + 1)]
    for _ in range(n - 1):
        a, b, w = map(int, sys_input().split())
        edges[a].append((b, w))
        edges[b].append((a, w))

    nodes = get_nodes(n, edges)

    for _ in range(int(sys_input())):
        d, e = map(int, sys_input().split())
        print(*query(nodes[d], nodes[e]))


solution()
