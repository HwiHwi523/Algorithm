"""
집합에 원소를 추가, 제거하는 문제다.
파이썬 set()을 사용
"""
import sys


def solution():
    input = sys.stdin.readline

    n = int(input())
    num_set = set()

    for i in range(n):
        order = input().split()
        if order[0] not in ['all', 'empty']:
            x = int(order[1])
        order = order[0]

        if order == 'add':
            num_set.add(x)

        elif order == 'remove':
            if x in num_set:
                num_set.remove(x)

        elif order == 'check':
            print(int(x in num_set))

        elif order == 'toggle':
            if x in num_set:
                num_set.remove(x)
            else:
                num_set.add(x)

        elif order == 'all':
            num_set.update(range(1, 21))

        elif order == 'empty':
            num_set.clear()


solution()
