"""

"""
import sys
from math import sqrt


def inc(counts, k):
    counts[k] += 1
    return counts[k] == 1


def dec(counts, k):
    counts[k] -= 1
    return counts[k] == 0


def solution():
    sys_input = sys.stdin.readline

    n = int(sys_input())
    *nums, = map(int, sys_input().split())

    queries = []
    for idx in range(int(sys_input())):
        i, j = map(int, sys_input().split())
        queries.append((i - 1, j - 1, idx))
    sq = int(sqrt(len(queries)))
    queries.sort(key=lambda x: (x[0] // sq, x[1], x[2]))

    result = [0] * len(queries)
    cur_count = 0
    counts = [0] * (max(nums) + 1)
    left, right = 0, -1
    for i, j, idx in queries:
        while right < j:
            right += 1
            cur_count += inc(counts, nums[right])
        while j < right:
            cur_count -= dec(counts, nums[right])
            right -= 1

        while left < i:
            cur_count -= dec(counts, nums[left])
            left += 1
        while left > i:
            left -= 1
            cur_count += inc(counts, nums[left])

        result[idx] = cur_count

    print(*result, sep='\n')


solution()
