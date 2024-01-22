"""

"""
import sys
from collections import deque


class Node:
    def __init__(self, num):
        self.num = num
        self.depth = 0
        self.distance = 0
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
            nodes[to].distance = nodes[front].distance + w
            nodes[to].parents.append(nodes[front])
            bfs_q.append(to)

    return nodes


def balancing(node1, target_depth):
    if node1.depth == target_depth:
        return node1

    next_node = node1.parents[0]
    for i in range(1, len(node1.parents)):
        if node1.parents[i].depth < target_depth:
            break
        next_node = node1.parents[i]

    next_node = balancing(next_node, target_depth)
    return next_node


def get_lca(node1, node2):
    if node1 == node2:
        return node1

    next1 = node1.parents[0]
    next2 = node2.parents[0]
    for i in range(1, len(node1.parents)):
        if node1.parents[i] == node2.parents[i]:
            break
        next1 = node1.parents[i]
        next2 = node2.parents[i]

    return get_lca(next1, next2)


def query(node1, node2):
    if node1.depth < node2.depth:
        node1, node2 = node2, node1

    lca = get_lca(balancing(node1, node2.depth), node2)

    return node1.distance + node2.distance - (lca.distance << 1)


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
