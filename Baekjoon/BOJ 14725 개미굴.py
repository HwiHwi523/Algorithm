"""

"""
import sys


def print_tunnel(cursor, depth):
    keys = list(cursor.keys())
    keys.sort()
    for key in keys:
        print('--' * depth, key, sep='')
        print_tunnel(cursor[key], depth + 1)


def solution():
    sys_input = sys.stdin.readline

    n = int(sys_input())

    root = dict()
    for _ in range(n):
        words = sys_input().rstrip().split()
        cursor = root
        for word in words[1:]:
            if word not in cursor:
                cursor[word] = dict()
            cursor = cursor[word]

    print_tunnel(root, 0)


solution()
