"""

"""
import sys
from collections import deque


class Node:
    def __init__(self, num):
        self.num = num
        self.parents = []
        self.next = []

    def set_parents(self):
        if not self.parents:
            return

        idx = 1
        c_parent, c_w = self.parents[idx - 1]
        while idx <= len(c_parent.parents):
            c_grand, c_g_w = c_parent.parents[idx - 1]
            self.parents.append((c_grand, c_w + c_g_w))
            idx += 1
            c_parent, c_w = self.parents[idx - 1]


def get_nodes(n, edges):
    nodes = [Node(i) for i in range(n + 1)]

    bfs_q = deque([1])

    visit = [False] * (n + 1)
    visit[1] = True

    while bfs_q:
        front = bfs_q.popleft()

        for to, w in edges[front]:
            if visit[to]:
                continue
            visit[to] = True
            nodes[front].next.append(nodes[to])
            nodes[to].parents.append((nodes[front], w))
            bfs_q.append(to)

    return nodes


def set_parents(node):
    node.set_parents()

    for next_node in node.next:
        set_parents(next_node)


def query(node, energy):
    if node.num == 1 or energy < node.parents[0][1]:
        return node.num

    next_node, next_w = node.parents[0]
    for i in range(1, len(node.parents)):
        if energy < node.parents[i][1]:
            break
        next_node, next_w = node.parents[i]

    return query(next_node, energy - next_w)


def solution():
    sys_input = sys.stdin.readline

    n = int(sys_input())
    ants = [0] + [int(sys_input()) for _ in range(n)]
    edges = [[] for _ in range(n + 1)]
    for _ in range(n - 1):
        a, b, w = map(int, sys_input().split())
        edges[a].append((b, w))
        edges[b].append((a, w))

    nodes = get_nodes(n, edges)
    set_parents(nodes[1])

    for i in range(1, n + 1):
        print(query(nodes[i], ants[i]))


solution()
