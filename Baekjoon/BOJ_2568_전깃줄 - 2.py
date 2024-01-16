"""

"""
import sys


def find_idx(lis, val):
    begin, end = 0, len(lis) - 1
    idx = -1

    while begin <= end:
        mid = (begin + end) >> 1

        if val < lis[mid]:
            idx = mid
            end = mid - 1
        else:
            begin = mid + 1

    return idx


def solution():
    sys_input = sys.stdin.readline

    n = int(sys_input())
    nums = sorted(list(map(int, sys_input().split())) for _ in range(n))

    lis = []
    tracking = []

    for line, val in nums:
        if not lis or lis[-1] < val:
            lis.append(val)
            tracking.append([line])
            continue

        insertion_idx = find_idx(lis, val)
        lis[insertion_idx] = val
        tracking[insertion_idx].append(line)

    stack = []
    answer = []
    for lines in reversed(tracking):
        while stack and stack[-1] < lines[-1]:
            answer.append(lines.pop())

        stack.append(lines.pop())

        while lines:
            answer.append(lines.pop())

    print(len(answer), *answer[::-1], sep='\n')


solution()
