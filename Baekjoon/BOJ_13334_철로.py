"""

"""
import sys
from heapq import heappush, heappop


def solution():
    sys_input = sys.stdin.readline

    n = int(sys_input())
    lines = [sorted(list(map(int, sys_input().split()))) for _ in range(n)]
    lines.sort(key=lambda x: (x[1], x[0]))
    d = int(sys_input())

    cur_lines = []
    answer = 0

    for a, b in lines:
        line = [b - d, b]

        while cur_lines and cur_lines[0][0] < line[0]:
            heappop(cur_lines)

        if line[0] <= a and b <= line[1]:
            heappush(cur_lines, (a, b))
            answer = max(answer, len(cur_lines))

    print(answer)


solution()
