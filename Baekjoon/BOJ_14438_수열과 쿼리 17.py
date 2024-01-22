"""

"""
import sys


def get_segment(n, nums):
    segment = [0] * (n << 1)

    for i in range(n):
        segment[i + n] = nums[i]

    for i in range(n - 1, 0, -1):
        segment[i] = min(segment[i << 1], segment[(i << 1) + 1])

    return segment


def modify(n, segment, i, v):
    i += n
    segment[i] = v

    while i > 1:
        i >>= 1
        segment[i] = min(segment[i << 1], segment[(i << 1) + 1])


def find(n, segment, i, j):
    i += n
    j += n
    result = int(1e9)

    while i < j:
        if i & 1:
            result = min(result, segment[i])
            i += 1

        if j & 1:
            j -= 1
            result = min(result, segment[j])

        i >>= 1
        j >>= 1

    return result


def solution():
    sys_input = sys.stdin.readline

    n = int(sys_input())
    *nums, = map(int, sys_input().split())

    segment = get_segment(n, nums)

    for _ in range(int(sys_input())):
        query, a, b = map(int, sys_input().split())

        if query == 1:
            modify(n, segment, a - 1, b)
        else:
            print(find(n, segment, a - 1, b))


solution()
