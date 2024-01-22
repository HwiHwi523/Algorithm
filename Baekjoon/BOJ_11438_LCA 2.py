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
        parent = self.parents[0][0]
        while idx <= len(parent.parents):
            grand, g_depth = parent.parents[idx - 1]
            self.parents.append((grand, g_depth))
            parent = self.parents[idx][0]
            idx += 1


def get_nodes(n, edges):
    nodes = [Node(i) for i in range(n + 1)]

    bfs_q = deque([(1, 0)])

    visit = [False] * (n + 1)
    visit[1] = True

    while bfs_q:
        front, depth = bfs_q.popleft()

        nodes[front].depth = depth
        nodes[front].set_parents()

        for next_node in edges[front]:
            if visit[next_node]:
                continue
            visit[next_node] = True
            nodes[next_node].parents.append((nodes[front], depth))
            bfs_q.append((next_node, depth + 1))

    return nodes


def balancing(node, target_depth):
    if node.depth == target_depth:
        return node

    next_node = node.parents[0][0]

    for i in range(1, len(node.parents)):
        if node.parents[i][1] < target_depth:
            break

        next_node = node.parents[i][0]

    return balancing(next_node, target_depth)


def get_common_ancestor(node1, node2):
    if node1.num == node2.num:
        return node1.num

    next_node1 = node1.parents[0][0]
    next_node2 = node2.parents[0][0]

    for i in range(1, len(node1.parents)):
        if node1.parents[i][0] == node2.parents[i][0]:
            break

        next_node1 = node1.parents[i][0]
        next_node2 = node2.parents[i][0]

    return get_common_ancestor(next_node1, next_node2)


def query(node1, node2):
    if node1.depth < node2.depth:
        node1, node2 = node2, node1

    node1 = balancing(node1, node2.depth)

    return get_common_ancestor(node1, node2)


def solution():
    sys_input = sys.stdin.readline

    n = int(sys_input())
    edges = [[] for _ in range(n + 1)]
    for _ in range(n - 1):
        a, b = map(int, sys_input().split())
        edges[a].append(b)
        edges[b].append(a)

    nodes = get_nodes(n, edges)

    for _ in range(int(sys_input())):
        a, b = map(int, sys_input().split())
        print(query(nodes[a], nodes[b]))


solution()
