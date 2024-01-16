"""

"""
import sys


class Node:
    def __init__(self, num):
        self.num = num
        self.prev = None
        self.next = None

    def order_a(self, other):
        self.__remove()
        self.link(other.prev)
        return 'A {} {}'.format(self.num, other.num)

    def order_b(self, other):
        self.__remove()
        self.link(other)
        return 'B {} {}'.format(self.num, other.num)

    def __remove(self):
        self.prev.next = self.next
        self.next.prev = self.prev

    def link(self, prev):
        self.prev = prev
        self.next = prev.next

        self.prev.next = self
        self.next.prev = self


def update_front(nodes, front, x, y, order):
    if order == 'A':
        if front == x:
            if nodes[x].next == nodes[y]:
                return x
            return nodes[x].next.num
        if front == y:
            return x

    if order == 'B':
        if front == x:
            return nodes[x].next.num
        if front == y:
            return y

    return front


def find_idx(lis, val):
    begin, end = 0, len(lis) - 1
    idx = -1

    while begin <= end:
        mid = (begin + end) >> 1

        if val < lis[mid]:
            idx = mid
            end = mid - 1
        else:
            begin = mid + 1

    return idx


def solution():
    sys_input = sys.stdin.readline

    n, m = map(int, sys_input().split())

    nodes = [Node(i) for i in range(n + 1)]

    front = 1
    nodes[front].next = nodes[front]
    nodes[front].prev = nodes[front]
    for i in range(2, n + 1):
        nodes[i].link(nodes[i - 1])

    for _ in range(m):
        order, x, y = sys_input().split()
        x = int(x)
        y = int(y)

        front = update_front(nodes, front, x, y, order)
        if order == 'A':
            nodes[x].order_a(nodes[y])
        else:
            nodes[x].order_b(nodes[y])

    cursor = nodes[front]
    val_list = [cursor.num]
    cursor = cursor.next
    while cursor.num != front:
        val_list.append(cursor.num)
        cursor = cursor.next

    lis = []
    tracking = []
    for idx, val in enumerate(val_list):
        if not lis or lis[-1] < val:
            lis.append(val)
            tracking.append([idx])
            continue
        insertion_idx = find_idx(lis, val)
        lis[insertion_idx] = val
        tracking[insertion_idx].append(idx)

    move_list = []
    last = None
    for idx_list in reversed(tracking):
        while last and last < idx_list[-1]:
            move_list.append(val_list[idx_list.pop()])
        last = idx_list.pop()
        while idx_list:
            move_list.append(val_list[idx_list.pop()])
    move_list.sort()

    print(len(move_list))

    if not move_list:
        return

    if move_list[0] == 1:
        front = 1
        front_next = min(set(range(1, move_list[-1] + 2)) - set(move_list))
        print(nodes[1].order_a(nodes[front_next]))
    else:
        front = update_front(nodes, front, move_list[0], move_list[0] - 1, 'B')
        print(nodes[move_list[0]].order_b(nodes[move_list[0] - 1]))

    for i in range(1, len(move_list)):
        val = move_list[i]
        front = update_front(nodes, front, val, val - 1, 'B')
        print(nodes[val].order_b(nodes[val - 1]))


solution()
