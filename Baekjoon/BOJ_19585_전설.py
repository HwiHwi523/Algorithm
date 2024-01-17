"""

"""
import sys


class Trie:
    class Node:
        def __init__(self, ch=None):
            self.ch = ch
            self.is_end_of_word = False
            self.next = dict()

        def link(self, other_ch):
            if other_ch not in self.next:
                self.next[other_ch] = self.__class__(other_ch)
            return self.next[other_ch]

    def __init__(self):
        self.root = self.Node()

    def add(self, word):
        cursor = self.root

        for ch in word:
            cursor = cursor.link(ch)

        cursor.is_end_of_word = True

    def can_win(self, team_name, nicknames):
        cursor = self.root
        nickname_begin_idx_list = []

        for idx, ch in enumerate(team_name):
            if ch not in cursor.next:
                break
            cursor = cursor.next[ch]

            if cursor.is_end_of_word:
                nickname_begin_idx_list.append(idx + 1)

        for idx in nickname_begin_idx_list:
            if team_name[idx:] in nicknames:
                return True

        return False


def solution():
    sys_input = sys.stdin.readline

    c, n = map(int, sys_input().split())

    trie = Trie()
    for _ in range(c):
        trie.add(sys_input().rstrip())

    nicknames = {sys_input().rstrip() for _ in range(n)}

    q = int(sys_input())
    for _ in range(q):
        team_name = sys_input().rstrip()
        print('Yes' if trie.can_win(team_name, nicknames) else 'No')


solution()
