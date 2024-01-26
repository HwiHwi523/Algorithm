"""

"""
import sys
from collections import defaultdict


def update(result, nums, bucket, left, right, sq, is_add=True):
    l_group = left // sq
    r_group = right // sq

    if l_group == r_group:
        for idx in range(left, right + 1):
            result[nums[idx]] += 1 if is_add else -1
        return

    for idx in range(left, min((l_group + 1) * sq, len(nums))):
        result[nums[idx]] += 1 if is_add else -1

    for idx in range(r_group * sq, right + 1):
        result[nums[idx]] += 1 if is_add else -1

    if l_group + 1 < r_group:
        for k, v in bucket[r_group - 1].items():
            result[k] += v if is_add else -v
        for k, v in bucket[l_group].items():
            result[k] -= v if is_add else -v


def get_max_elem(result, left, right):
    elem, count = max(result.items(), key=lambda x: x[1])

    if ((right - left + 1) >> 1) < count:
        return elem

    return None


def solution():
    sys_input = sys.stdin.readline

    n, _ = map(int, sys_input().split())
    *nums, = map(int, sys_input().split())

    bucket = [defaultdict(int)]
    cur_bucket = 0
    sq = int(n ** 0.5)
    for idx, val in enumerate(nums):
        if cur_bucket < idx // sq:
            cur_bucket += 1
            bucket.append(bucket[-1].copy())
        bucket[-1][val] += 1

    q = int(sys_input())
    queries = [[idx] + list(map(lambda x: int(x) - 1, sys_input().split())) for idx in range(q)]
    queries.sort(key=lambda x: (x[1] // sq, x[2]))

    answer = ['no'] * q
    left, right = 0, -1
    result = defaultdict(int)

    for idx, a, b in queries:
        if right < b:
            update(result, nums, bucket, right + 1, b, sq)
        elif right > b:
            update(result, nums, bucket, b + 1, right, sq, False)
        right = b

        if left < a:
            update(result, nums, bucket, left, a - 1, sq, False)
        elif left > a:
            update(result, nums, bucket, a, left - 1, sq)
        left = a

        max_elem = get_max_elem(result, left, right)
        if max_elem is not None:
            answer[idx] = 'yes ' + str(max_elem)

    print(*answer, sep='\n')


solution()
