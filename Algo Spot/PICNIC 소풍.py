"""
입력 받은 친구 정보를 조합하여 경우의수를 구한다.
"""


def counting(n, cur, connected, friend_info):
    if len(connected) == n:
        return 1

    count = 0
    for i in range(cur, len(friend_info)):
        if len(connected & friend_info[i]) == 0:
            connected |= friend_info[i]
            count += counting(n, i + 1, connected, friend_info)
            connected -= friend_info[i]

    return count


def solution():
    n, m = map(int, input().split())

    friend_info = []
    friend_pair = list(map(int, input().split()))
    for i in range(1, 2 * m, 2):
        pair = (friend_pair[i - 1], friend_pair[i])
        if pair[0] > pair[1]:
            pair = (pair[1], pair[0])
        friend_info.append(set(pair))

    print(counting(n, 0, set(), friend_info))


tc = int(input())

for _ in range(tc):
    solution()
