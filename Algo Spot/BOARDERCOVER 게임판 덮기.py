"""
1. 판에서 L 격자가 들어갈 수 있는 공간 찾기
2. L 격자가 들어갈 곳을 #으로 바꾼 뒤 재귀호출
"""
import sys


def find_white(board, loc):
    row, col = loc
    for i in range(row, len(board)):
        if i == row:
            find_range = range(col, len(board[0]))
        else:
            find_range = range(len(board[0]))
        for j in find_range:
            if board[i][j] == '.':
                return i, j

    return None, None


def counting(board, loc, block_count):
    if block_count == 0:
        return 1

    # 흰 칸 찾기
    row, col = find_white(board, loc)

    # 경우의수
    count = 0

    # 격자가 아래 중 하나의 모양에 들어갈 수 있는 경우
    # .. ..
    # .X X.
    if row + 1 < len(board) and col + 1 < len(board[0]) and board[row][col + 1] == '.':
        board[row][col] = '#'
        board[row][col + 1] = '#'

        if board[row + 1][col] == '.':
            board[row + 1][col] = '#'
            count += counting(board, (row, col), block_count - 1)
            board[row + 1][col] = '.'

        if board[row + 1][col + 1] == '.':
            board[row + 1][col + 1] = '#'
            count += counting(board, (row, col), block_count - 1)
            board[row + 1][col + 1] = '.'

        board[row][col] = '.'
        board[row][col + 1] = '.'

    # 격자가 아래와 같다면
    #  .X  .X
    # X.. ..X
    if row + 1 < len(board) and board[row + 1][col] == '.':
        board[row][col] = '#'
        board[row + 1][col] = '#'

        if col + 1 < len(board[0]) and board[row + 1][col + 1] == '.':
            board[row + 1][col + 1] = '#'
            count += counting(board, (row, col), block_count - 1)
            board[row + 1][col + 1] = '.'

        if 0 < col and board[row + 1][col - 1] == '.':
            board[row + 1][col - 1] = '#'
            count += counting(board, (row, col), block_count - 1)
            board[row + 1][col - 1] = '.'

        board[row][col] = '.'
        board[row + 1][col] = '.'

    return count


def solution():
    sys_input = sys.stdin.readline

    row, col = map(int, sys_input().split())

    board = [list(sys_input().rstrip()) for _ in range(row)]

    dot_count = 0
    for row_list in board:
        dot_count += row_list.count('.')

    answer = 0
    if dot_count % 3 == 0:
        answer = counting(board, (0, 0), dot_count // 3)

    print(answer)


tc = int(input())

for _ in range(tc):
    solution()
