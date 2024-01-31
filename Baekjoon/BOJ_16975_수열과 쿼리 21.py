"""

"""
import sys


class SegmentTree:
    def __init__(self, n, nums):
        self.n = n
        self.tree = [0] * (n << 2)
        self.lazy = [0] * (n << 2)

        self.__init_tree(1, nums, 0, n - 1)

    def __init_tree(self, t_idx, nums, begin, end):
        if begin == end:
            self.tree[t_idx] = nums[begin]
            return nums[begin]

        mid = (begin + end) >> 1

        val = self.__init_tree(t_idx << 1, nums, begin, mid)
        val += self.__init_tree((t_idx << 1) + 1, nums, mid + 1, end)

        self.tree[t_idx] = val
        return self.tree[t_idx]

    def update(self, begin, end, k):
        def r_update(t_idx, f_b, f_e, t_b, t_e):
            if t_e < f_b or f_e < t_b:
                return
            if t_b <= f_b and f_e <= t_e:
                self.lazy[t_idx] += k
                return

            mid = (f_b + f_e) >> 1

            r_update(t_idx << 1, f_b, mid, t_b, t_e)
            r_update((t_idx << 1) + 1, mid + 1, f_e, t_b, t_e)

        r_update(1, 0, self.n - 1, begin, end)

    def query(self, target):
        def r_query(t_idx, f_b, f_e):
            if target < f_b or f_e < target:
                return 0
            if f_b == f_e == target:
                return self.tree[t_idx] + self.lazy[t_idx]

            if self.lazy[t_idx]:
                self.lazy[t_idx << 1] += self.lazy[t_idx]
                self.lazy[(t_idx << 1) + 1] += self.lazy[t_idx]
                self.lazy[t_idx] = 0

            mid = (f_b + f_e) >> 1

            return r_query(t_idx << 1, f_b, mid) + r_query((t_idx << 1) + 1, mid + 1, f_e)

        return r_query(1, 0, self.n - 1)


def solution():
    sys_input = sys.stdin.readline

    n = int(sys_input())
    segment = SegmentTree(n, list(map(int, sys_input().split())))

    for _ in range(int(sys_input())):
        order, *info = map(int, sys_input().split())

        if order == 1:
            segment.update(info[0] - 1, info[1] - 1, info[2])
        else:
            print(segment.query(info[0] - 1))


solution()
