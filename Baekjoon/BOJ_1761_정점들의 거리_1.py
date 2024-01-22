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
        parent, w = self.parents[0]

        while idx <= len(parent.parents):
            grand, grand_w = parent.parents[idx - 1]
            self.parents.append((grand, w + grand_w))
            parent, w = self.parents[idx]
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
            nodes[to].parents.append((nodes[front], w))
            bfs_q.append(to)

    return nodes


def balancing(node1, target_depth):
    if node1.depth == target_depth:
        return node1, 0

    next_node, w = node1.parents[0]
    for i in range(1, len(node1.parents)):
        if node1.parents[i][0].depth < target_depth:
            break
        next_node, w = node1.parents[i]

    next_node, total_w = balancing(next_node, target_depth)
    return next_node, total_w + w


def get_distance(node1, node2):
    if node1 == node2:
        return 0

    next1, w1 = node1.parents[0]
    next2, w2 = node2.parents[0]
    for i in range(1, len(node1.parents)):
        if node1.parents[i][0] == node2.parents[i][0]:
            break
        next1, w1 = node1.parents[i]
        next2, w2 = node2.parents[i]

    return get_distance(next1, next2) + w1 + w2


def query(node1, node2):
    if node1.depth < node2.depth:
        node1, node2 = node2, node1
    node1, w = balancing(node1, node2.depth)

    return get_distance(node1, node2) + w


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
        a, b = map(int, sys_input().split())
        print(query(nodes[a], nodes[b]))


solution()
