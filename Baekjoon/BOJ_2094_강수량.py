"""

"""
import sys


def solution():
    def query(i, j):
        if i == j:
            return 0

        max_p = 0

        while i < j:
            if i & 1:
                max_p = max(max_p, segment[i])
                i += 1

            if j & 1:
                j -= 1
                max_p = max(max_p, segment[j])

            i >>= 1
            j >>= 1

        return max_p

    def idx_of(p_year):
        begin = 0
        end = n - 1
        idx, found = n, False

        while begin <= end:
            mid = (begin + end) >> 1

            if p_year <= years[mid]:
                end = mid - 1
                idx = mid
                if p_year == years[mid]:
                    found = True
                    break
            else:
                begin = mid + 1

        return idx + n, found

    sys_input = sys.stdin.readline

    n = int(sys_input())
    segment = [0] * (n << 1)
    years = [0] * n

    for i in range(n):
        year, p = map(int, sys_input().split())
        years[i] = year
        segment[i + n] = p

    for i in range(n - 1, 0, -1):
        segment[i] = max(segment[i << 1], segment[(i << 1) + 1])

    for _ in range(int(sys_input())):
        y, x = map(int, sys_input().split())

        y_idx, y_found = idx_of(y)
        x_idx, x_found = idx_of(x)
        between_p = query(
            y_idx + (1 if y_found else 0),
            x_idx
        )

        if y_found and x_found:
            if x_idx - y_idx == x - y and between_p < segment[x_idx] <= segment[y_idx]:
                print('true')

            elif segment[y_idx] < segment[x_idx] or between_p >= segment[x_idx]:
                print('false')

            else:
                print('maybe')

        elif y_found:
            if segment[y_idx] <= between_p:
                print('false')
            else:
                print('maybe')

        elif x_found:
            if between_p >= segment[x_idx]:
                print('false')
            else:
                print('maybe')

        else:
            print('maybe')


solution()
