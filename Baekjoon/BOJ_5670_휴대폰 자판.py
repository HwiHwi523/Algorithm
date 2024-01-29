"""

"""
import sys


class Trie:
    def __init__(self, ch):
        self.ch = ch
        self.is_end = False
        self.next = dict()


def get_dictionary(words):
    trie = Trie('')
    trie.is_end = True

    for word in words:
        cursor = trie
        for ch in word:
            if ch not in cursor.next:
                cursor.next[ch] = Trie(ch)
            cursor = cursor.next[ch]
        cursor.is_end = True

    return trie


def get_count(trie, word):
    cursor = trie
    count = 0

    for ch in word:
        if len(cursor.next) > 1 or cursor.is_end:
            count += 1
        cursor = cursor.next[ch]

    return count


def solution():
    sys_input = sys.stdin.readline

    while True:
        try:
            n = int(sys_input())
            words = [sys_input().rstrip() for _ in range(n)]
        except:
            break

        trie = get_dictionary(words)
        count = sum(get_count(trie, word) for word in words)
        print('{:.2f}'.format(count / n))


solution()
