"""

"""
import sys


def solution():
    sys_input = sys.stdin.readline

    n, atk = map(int, sys_input().split())

    queries = []
    for _ in range(n):
        t, a, h = map(int, sys_input().split())
        queries.append((t, a, h))
        if t == 2:
            atk += a

    answer = -1
    min_hp = 1
    for t, a, h in reversed(queries):
        if t == 1:
            dmg = ((h - 1) // atk) * a
            min_hp += dmg
            answer = max(answer, min_hp)
        else:
            atk -= a
            min_hp = max(min_hp - h, 1)

    print(answer)


solution()
