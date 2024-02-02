"""

"""
import sys


class SegmentTree:
    def __init__(self, n):
        self.n = n
        self.tree = [0] * (n << 2)
        self.lazy = [False] * (n << 2)

    def update(self, begin, end):
        def r_update(t_idx, f_b, f_e):
            self.__propagate(t_idx, f_b, f_e)

            if end < f_b or f_e < begin:
                return 0, 0

            if begin <= f_b and f_e <= end:
                self.lazy[t_idx] ^= True
                if self.lazy[t_idx]:
                    return self.tree[t_idx], f_e - f_b + 1 - self.tree[t_idx]
                return f_e - f_b + 1 - self.tree[t_idx], self.tree[t_idx]

            mid = (f_b + f_e) >> 1
            s1, a1 = r_update(t_idx << 1, f_b, mid)
            s2, a2 = r_update((t_idx << 1) + 1, mid + 1, f_e)

            self.tree[t_idx] += a1 + a2 - s1 - s2
            return s1 + s2, a1 + a2

        r_update(1, 0, self.n - 1)

    def query(self, begin, end):
        def r_query(t_idx, f_b, f_e):
            self.__propagate(t_idx, f_b, f_e)

            if end < f_b or f_e < begin:
                return 0

            if begin <= f_b and f_e <= end:
                return self.tree[t_idx]

            mid = (f_b + f_e) >> 1
            return r_query(t_idx << 1, f_b, mid) + r_query((t_idx << 1) + 1, mid + 1, f_e)

        print(r_query(1, 0, self.n - 1))

    def __propagate(self, t_idx, begin, end):
        if not self.lazy[t_idx]:
            return

        self.tree[t_idx] = end - begin + 1 - self.tree[t_idx]

        if begin < end:
            self.lazy[t_idx << 1] ^= True
            self.lazy[(t_idx << 1) + 1] ^= True

        self.lazy[t_idx] = False


def solution():
    sys_input = sys.stdin.readline

    n, m = map(int, sys_input().split())
    segment = SegmentTree(n)

    orders = ['update', 'query']
    for _ in range(m):
        order, a, b = map(int, sys_input().split())
        getattr(segment, orders[order])(a - 1, b - 1)


solution()
