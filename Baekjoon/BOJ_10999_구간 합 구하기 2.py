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
        self.tree[t_idx] += self.__init_tree(t_idx << 1, nums, begin, mid)
        self.tree[t_idx] += self.__init_tree((t_idx << 1) + 1, nums, mid + 1, end)

        return self.tree[t_idx]

    def update(self, begin, end, k):
        def r_update(t_idx, f_b, f_e):
            if end < f_b or f_e < begin:
                return
            if begin <= f_b and f_e <= end:
                self.lazy[t_idx] += k
                return

            self.tree[t_idx] += k * (min(f_e, end) - max(f_b, begin) + 1)

            mid = (f_b + f_e) >> 1
            r_update(t_idx << 1, f_b, mid)
            r_update((t_idx << 1) + 1, mid + 1, f_e)

        r_update(1, 0, self.n - 1)

    def query(self, begin, end):
        def r_query(t_idx, f_b, f_e):
            if end < f_b or f_e < begin:
                return 0
            if begin <= f_b and f_e <= end:
                return self.tree[t_idx] + self.lazy[t_idx] * (f_e - f_b + 1)

            if self.lazy[t_idx]:
                self.tree[t_idx] += self.lazy[t_idx] * (f_e - f_b + 1)
                self.lazy[t_idx << 1] += self.lazy[t_idx]
                self.lazy[(t_idx << 1) + 1] += self.lazy[t_idx]
                self.lazy[t_idx] = 0

            mid = (f_b + f_e) >> 1
            return r_query(t_idx << 1, f_b, mid) + r_query((t_idx << 1) + 1, mid + 1, f_e)

        return r_query(1, 0, self.n - 1)


def solution():
    sys_input = sys.stdin.readline

    n, m, k = map(int, sys_input().split())
    segment = SegmentTree(n, [int(sys_input()) for _ in range(n)])

    for _ in range(m + k):
        order, *info = map(int, sys_input().split())

        if order == 1:
            segment.update(info[0] - 1, info[1] - 1, info[2])
        else:
            print(segment.query(info[0] - 1, info[1] - 1))


solution()
