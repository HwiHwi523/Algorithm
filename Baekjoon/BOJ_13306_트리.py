"""

"""
import sys


def find_parent(parents, num):
    if parents[num] != num:
        parents[num] = find_parent(parents, parents[num])
    return parents[num]


def solution():
    sys.setrecursionlimit(201_000)
    sys_input = sys.stdin.readline

    n, q = map(int, sys_input().split())
    init_parents = [0, 1] + [int(sys_input()) for _ in range(n - 1)]
    cur_parents = [i for i in range(n + 1)]
    queries = [list(map(int, sys_input().split())) for _ in range(q + n - 1)]
    answer = []

    for query in reversed(queries):
        if query[0] == 0:
            p_group = find_parent(cur_parents, init_parents[query[1]])
            b_group = find_parent(cur_parents, query[1])
            cur_parents[b_group] = p_group
        else:
            c_group = find_parent(cur_parents, query[1])
            d_group = find_parent(cur_parents, query[2])

            answer.append('YES' if c_group == d_group else 'NO')

    print(*answer[::-1], sep='\n')


solution()
