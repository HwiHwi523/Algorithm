"""

"""
import sys


def get_segment(precip):
    segments = [0] * (len(precip) << 1)

    for i in range(len(precip), len(segments)):
        segments[i] = precip[i - len(precip)]

    for i in range(len(precip) - 1, 0, -1):
        segments[i] = max(segments[i << 1], segments[(i << 1) + 1])

    return segments


def query(n, segment, i, j):
    if i == j:
        return 0

    i += n
    j += n
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


def solution():
    def idx_of(p_year):
        begin = 0
        end = len(years) - 1
        idx, found = len(years), False

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

        return idx, found

    sys_input = sys.stdin.readline

    n = int(sys_input())
    years = []
    precip = []

    for i in range(n):
        year, p = map(int, sys_input().split())
        years.append(year)
        precip.append(p)

    segment = get_segment(precip)

    for _ in range(int(sys_input())):
        y, x = map(int, sys_input().split())

        y_idx, y_found = idx_of(y)
        x_idx, x_found = idx_of(x)
        between_p = query(
            n,
            segment,
            y_idx + (1 if y_found else 0),
            x_idx
        )

        if y_found and x_found:
            if x_idx - y_idx == x - y and between_p < precip[x_idx] <= precip[y_idx]:
                print('true')

            elif precip[y_idx] < precip[x_idx] or between_p >= precip[x_idx]:
                print('false')

            else:
                print('maybe')

        elif y_found:
            if precip[y_idx] <= between_p:
                print('false')
            else:
                print('maybe')

        elif x_found:
            if between_p >= precip[x_idx]:
                print('false')
            else:
                print('maybe')

        else:
            print('maybe')


solution()
