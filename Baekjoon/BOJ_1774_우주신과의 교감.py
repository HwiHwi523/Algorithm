"""

"""
import sys
from math import sqrt


def calc_dist(n1, n2):
    return sqrt((n1[0] - n2[0]) ** 2 + (n1[1] - n2[1]) ** 2)


def find_parent(parents, num):
    if parents[num] != num:
        parents[num] = find_parent(parents, parents[num])
    return parents[num]


def solution():
    sys_input = sys.stdin.readline

    n, m = map(int, sys_input().split())
    nodes = [[]] + [list(map(int, sys_input().split())) for _ in range(n)]
    connected = {tuple(map(int, sys_input().split())) for _ in range(m)}

    edges = []
    for i in range(1, n):
        for j in range(i + 1, n + 1):
            edges.append((calc_dist(nodes[i], nodes[j]), (i, j)))
    edges.sort()

    parents = list(range(n + 1))
    for a, b in connected:
        a_parent = find_parent(parents, a)
        b_parent = find_parent(parents, b)

        if a_parent == b_parent:
            continue

        parents[a_parent] = b_parent

    result = 0
    for dist, edge in edges:
        a_parent = find_parent(parents, edge[0])
        b_parent = find_parent(parents, edge[1])

        if a_parent == b_parent:
            continue

        parents[a_parent] = b_parent

        result += dist

    print('{:0.2f}'.format(result))


solution()
