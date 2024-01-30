"""

"""
import sys


class Trie:
    def __init__(self):
        self.count = 0
        self.next = [None, None]

    def add(self, num):
        cursor = self
        for d in range(29, -1, -1):
            bit = ((1 << d) & num) > 0
            if cursor.next[bit] is None:
                cursor.next[bit] = Trie()
            cursor = cursor.next[bit]
        cursor.count += 1

    def remove(self, num):
        def r_remove(node, depth):
            if node.next.count(None) == 2:
                node.count -= 1
                return node.count == 0

            bit = ((1 << depth) & num) > 0
            if r_remove(node.next[bit], depth - 1):
                node.next[bit] = None
            return node.next.count(None) == 2

        r_remove(self, 29)

    def get_max(self, num):
        def r_get_max(node, depth):
            if node.next.count(None) == 2:
                return ''

            bit = ((1 << depth) & num) > 0
            if bit:
                if node.next[0] is not None:
                    return '1' + r_get_max(node.next[0], depth - 1)
                return '0' + r_get_max(node.next[1], depth - 1)

            if node.next[1] is not None:
                return '1' + r_get_max(node.next[1], depth - 1)
            return '0' + r_get_max(node.next[0], depth - 1)

        return int(r_get_max(self, 29), 2)


def solution():
    sys_input = sys.stdin.readline

    n = int(sys_input())
    trie = Trie()
    trie.add(0)

    for _ in range(n):
        order, num = map(int, sys_input().split())

        if order == 1:
            trie.add(num)
        elif order == 2:
            trie.remove(num)
        elif order == 3:
            print(trie.get_max(num))


solution()
