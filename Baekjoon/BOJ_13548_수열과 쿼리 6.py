"""

"""
import sys


class Node:
    def __init__(self, count):
        self.count = count
        self.nums = set()
        self.prev = self
        self.next = self


def increase(before, after, num):
    after.nums.add(num)
    if len(after.nums) == 1:
        after.prev = before.prev
        after.next = before
        before.prev.next = after
        before.prev = after

    before.nums.remove(num)
    if len(before.nums) == 0:
        before.prev.next = before.next
        before.next.prev = before.prev


def decrease(before, after, num):
    after.nums.add(num)
    if len(after.nums) == 1:
        after.prev = before
        after.next = before.next
        before.next.prev = after
        before.next = after

    before.nums.remove(num)
    if len(before.nums) == 0:
        before.prev.next = before.next
        before.next.prev = before.prev


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
    nodes = [Node(i) for i in range(max(max_num, n) + 1)]

    root = Node(-1)
    root.prev = root.next = nodes[0]
    nodes[0].prev = nodes[0].next = root
    nodes[0].nums.update(set(nums))

    left, right = 0, -1
    answer = [0] * q
    for idx, a, b in queries:
        while right < b:
            right += 1
            count = counts[nums[right]]
            increase(nodes[count], nodes[count + 1], nums[right])
            counts[nums[right]] += 1

        while b < right:
            count = counts[nums[right]]
            decrease(nodes[count], nodes[count - 1], nums[right])
            counts[nums[right]] -= 1
            right -= 1

        while left < a:
            count = counts[nums[left]]
            decrease(nodes[count], nodes[count - 1], nums[left])
            counts[nums[left]] -= 1
            left += 1

        while a < left:
            left -= 1
            count = counts[nums[left]]
            increase(nodes[count], nodes[count + 1], nums[left])
            counts[nums[left]] += 1

        answer[idx] = root.next.count

    print(*answer, sep='\n')


solution()
