"""

"""
import sys


def update(n, segment, i, v):
    i += n
    amount = v - segment[i]

    while i >= 1:
        segment[i] += amount
        i >>= 1


def query(n, segment, i, j):
    i += n
    j += n
    result = 0

    while i < j:
        if i & 1:
            result += segment[i]
            i += 1

        if j & 1:
            j -= 1
            result += segment[j]

        i >>= 1
        j >>= 1

    return result


def solution():
    sys_input = sys.stdin.readline

    n = int(sys_input())
    segment = [0] * (n << 1)
    segment[n:] = map(int, sys_input().split())
    for i in range(n - 1, 0, -1):
        segment[i] = segment[i << 1] + segment[(i << 1) + 1]

    update_queries = [[]]
    get_queries = []
    for _ in range(int(sys_input())):
        order, *info = map(int, sys_input().split())

        if order == 1:
            update_queries.append(info)
        else:
            get_queries.append([len(get_queries)] + info)
    get_queries.sort(key=lambda x: (x[1]))

    answer = [0] * len(get_queries)
    q_idx = 0
    u_idx = 1
    while q_idx < len(get_queries) and u_idx < len(update_queries):
        while q_idx < len(get_queries) and get_queries[q_idx][1] < u_idx:
            gq = get_queries[q_idx]
            answer[gq[0]] = query(n, segment, gq[2] - 1, gq[3])
            q_idx += 1

        update(n, segment, update_queries[u_idx][0] - 1, update_queries[u_idx][1])
        u_idx += 1

    while q_idx < len(get_queries):
        gq = get_queries[q_idx]
        answer[gq[0]] = query(n, segment, gq[2] - 1, gq[3])
        q_idx += 1

    print(*answer, sep='\n')


solution()
