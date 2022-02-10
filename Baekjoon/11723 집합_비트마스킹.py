"""
집합에 원소를 추가, 제거하는 문제다.
파이썬 set()의 add, remove, in 연산의 시간복잡도가 O(1)이기 때문에 이것을 사용해도 충분히 풀 수 있다.
그러나 비트마스킹 연습을 위해 하나의 정수의 비트들을 사용하여 풀어보려 한다.

+
시간초과가 됐는데 input()은 느리기 때문에 sys.stdin.readline을 사용해야 한다고 한다.
"""
import sys


def solution():
    input = sys.stdin.readline

    n = int(input())
    bit_set = 0  # 0000 0000 0000 0000 0000 0

    for i in range(n):
        order = input().split()
        if order[0] not in ['all', 'empty']:
            x_bit = (1 << int(order[1]))
        order = order[0]

        if order == 'add':
            bit_set |= x_bit  # or 연산으로 x에 위치한 비트를 1로 변경

        elif order == 'remove':
            bit_set &= ~x_bit  # and, not 연산으로 x에 위치한 비트를 0으로 변경

        elif order == 'check':
            print(int((bit_set & x_bit) != 0))  # and 연산으로 x에 위치한 비트의 상태 출력

        elif order == 'toggle':
            bit_set ^= x_bit  # xor 연산으로 x에 위치한 비트를 0이나 1로 변경

        elif order == 'all':
            bit_set = (1 << 21) - 1  # 1 0000 ... 0000 -> 0 1111 ... 1111

        elif order == 'empty':
            bit_set = 0  # 0000 0000 ... 0000 0 초기화


solution()
