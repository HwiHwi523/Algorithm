"""

"""
import sys


def find_insertion_idx(lis, val):
    begin = 0
    end = len(lis) - 1
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

    lis = []
    for val in map(int, sys_input().split()):
        if not lis or lis[-1] < val:
            lis.append(val)
            continue
        lis[find_insertion_idx(lis, val)] = val

    print(n - len(lis))


solution()
