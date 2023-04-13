"""

"""
import sys


def find_parent(parents, num):
    if parents[num] != num:
        parents[num] = find_parent(parents, parents[num])
    return parents[num]


def solution():
    sys.setrecursionlimit(1000000)
    sys_input = sys.stdin.readline

    n, m = map(int, sys_input().split())
    parents = [i for i in range(n)]

    for count in range(1, m + 1):
        a, b = map(int, sys_input().split())
        parent_a = find_parent(parents, a)
        parent_b = find_parent(parents, b)
        if parent_a == parent_b:
            print(count)
            return
        parents[parent_b] = parent_a

    print(0)


solution()
