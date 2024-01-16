"""

"""
import sys


def find_idx(lis, val):
    begin, end = 0, len(lis) - 1
    idx = -1

    while begin <= end:
        mid = (begin + end) >> 1

        if val <= lis[mid]:
            idx = mid
            end = mid - 1
        else:
            begin = mid + 1

    return idx


def solution():
    sys_input = sys.stdin.readline

    for _ in range(int(sys_input())):
        n = int(sys_input())

        lis = []
        tracking = []

        for idx, val in enumerate(map(int, sys_input().split())):
            if not lis or lis[-1] < val:
                lis.append(val)
                tracking.append([idx + 1])
                continue

            insertion_idx = find_idx(lis, val)
            lis[insertion_idx] = val
            tracking[insertion_idx].append(idx + 1)

        stack = []
        for idx_list in reversed(tracking):
            while stack and stack[-1] < idx_list[-1]:
                idx_list.pop()
            stack.append(idx_list[-1])

        print(len(stack))
        print(*stack[::-1])


solution()
