"""

"""
import sys


class SegmentTree:
    def __init__(self, n):
        self.n = n
        self.tree = [0] * (n << 2)
        self.lazy = [0] * (n << 2)

    def update(self, begin, end, v):
        def r_update(t_idx, f_b, f_e):
            if end < f_b or f_e < begin:
                return

            if begin <= f_b and f_e <= end:
                self.lazy[t_idx] += v
                return

            mid = (f_b + f_e) >> 1
            r_update(t_idx << 1, f_b, mid)
            r_update((t_idx << 1) + 1, mid + 1, f_e)

        r_update(1, 0, self.n - 1)

    def query(self, target):
        def r_query(t_idx, f_b, f_e):
            self.__propagate(t_idx)

            if target < f_b or f_e < target:
                return 0

            if target == f_b == f_e:
                return self.tree[t_idx]

            mid = (f_b + f_e) >> 1
            if f_b <= target <= mid:
                return r_query(t_idx << 1, f_b, mid)
            return r_query((t_idx << 1) + 1, mid + 1, f_e)

        return r_query(1, 0, self.n - 1)

    def __propagate(self, t_idx):
        if not self.lazy[t_idx]:
            return

        left = t_idx << 1
        if left < len(self.lazy):
            self.lazy[left] += self.lazy[t_idx]

        right = left + 1
        if right < len(self.lazy):
            self.lazy[right] += self.lazy[t_idx]

        self.tree[t_idx] += self.lazy[t_idx]
        self.lazy[t_idx] = 0


def set_euler_path(tree, idx, seq, nums, ranges):
    nums[idx] = seq
    ranges[idx].append(seq)

    for next_idx in tree[idx]:
        seq = set_euler_path(tree, next_idx, seq + 1, nums, ranges)

    ranges[idx].append(seq)

    return seq


def solution():
    sys.setrecursionlimit(101_000)
    sys_input = sys.stdin.readline

    n, m = map(int, sys_input().split())
    *parents, = map(int, sys_input().split())

    tree = [[] for _ in range(n + 1)]
    for idx, parent in enumerate(parents):
        if parent == -1:
            continue
        tree[parent - 1].append(idx)

    nums = [0] * n
    ranges = [[] for _ in range(n)]
    set_euler_path(tree, 0, 0, nums, ranges)

    segment = SegmentTree(n)

    for _ in range(m):
        order, *info = map(int, sys_input().split())

        if order == 1:
            segment.update(*ranges[info[0] - 1], info[1])
        else:
            print(segment.query(nums[info[0] - 1]))


solution()
