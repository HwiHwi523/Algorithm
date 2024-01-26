"""

"""
import sys


def set_merge_sort_tree(n, ms_tree):
    for i in range(n - 1, 0, -1):
        left = i << 1
        right = left + 1
        l_cursor = 0
        r_cursor = 0

        while l_cursor < len(ms_tree[left]) and r_cursor < len(ms_tree[right]):
            if ms_tree[left][l_cursor] <= ms_tree[right][r_cursor]:
                ms_tree[i].append(ms_tree[left][l_cursor])
                l_cursor += 1
            else:
                ms_tree[i].append(ms_tree[right][r_cursor])
                r_cursor += 1

        while l_cursor < len(ms_tree[left]):
            ms_tree[i].append(ms_tree[left][l_cursor])
            l_cursor += 1

        while r_cursor < len(ms_tree[right]):
            ms_tree[i].append(ms_tree[right][r_cursor])
            r_cursor += 1


def query(n, ms_tree, i, j, k):
    def get_count(arr):
        begin = 0
        end = len(arr) - 1
        idx = len(arr)

        while begin <= end:
            mid = (begin + end) >> 1

            if k < arr[mid]:
                idx = mid
                end = mid - 1
            else:
                begin = mid + 1

        return len(arr) - idx

    i += n
    j += n
    count = 0

    while i < j:
        if i & 1:
            count += get_count(ms_tree[i])
            i += 1

        if j & 1:
            j -= 1
            count += get_count(ms_tree[j])

        i >>= 1
        j >>= 1

    return count


def solution():
    sys_input = sys.stdin.readline

    n = int(sys_input())

    ms_tree = [[] for _ in range(n << 1)]
    for i, v in enumerate(map(int, sys_input().split())):
        ms_tree[i + n].append(v)
    set_merge_sort_tree(n, ms_tree)

    for _ in range(int(sys_input())):
        i, j, k = map(int, sys_input().split())
        print(query(n, ms_tree, i - 1, j, k))


solution()
