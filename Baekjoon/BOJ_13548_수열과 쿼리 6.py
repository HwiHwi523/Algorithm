"""

"""
import sys


def solution():
    sys_input = sys.stdin.readline

    n = int(sys_input())
    *nums, = map(int, sys_input().split())

    q = int(sys_input())
    queries = [[idx] + list(map(lambda x: int(x) - 1, sys_input().split())) for idx in range(q)]

    sq = int(n ** 0.5)
    queries.sort(key=lambda x: (x[1] // sq, x[2]))

    max_num = max(nums)
    counts = [0] * (max(max_num, n) + 1)
    cand_by_count = [set() for _ in range(len(counts))]
    cand_by_count[0].update(set(nums))

    left, right, max_count = 0, -1, 0
    answer = [0] * q
    for idx, a, b in queries:
        while right < b:
            right += 1
            count = counts[nums[right]]
            counts[nums[right]] += 1
            cand_by_count[count].remove(nums[right])
            cand_by_count[count + 1].add(nums[right])
            max_count = max(max_count, counts[nums[right]])

        while b < right:
            count = counts[nums[right]]
            cand_by_count[count].remove(nums[right])
            cand_by_count[count - 1].add(nums[right])
            if max_count == count and not cand_by_count[count]:
                max_count -= 1
            counts[nums[right]] -= 1
            right -= 1

        while left < a:
            count = counts[nums[left]]
            cand_by_count[count].remove(nums[left])
            cand_by_count[count - 1].add(nums[left])
            if max_count == counts[nums[left]] and not cand_by_count[count]:
                max_count -= 1
            counts[nums[left]] -= 1
            left += 1

        while a < left:
            left -= 1
            count = counts[nums[left]]
            counts[nums[left]] += 1
            cand_by_count[count].remove(nums[left])
            cand_by_count[count + 1].add(nums[left])
            max_count = max(max_count, counts[nums[left]])

        answer[idx] = max_count

    print(*answer, sep='\n')


solution()
