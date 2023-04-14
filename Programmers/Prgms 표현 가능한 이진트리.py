"""

"""


def add_padding(bin_number):
    exp = 0
    while (1 << exp) - 1 < len(bin_number):
        exp += 1
    padding_count = (1 << exp) - 1 - len(bin_number)
    return (padding_count * '0') + bin_number


def is_expressed(bin_number, begin, end):
    if begin >= end:
        return True
    mid = (begin + end) >> 1

    left = is_expressed(bin_number, begin, mid - 1)
    if not left:
        return False

    right = is_expressed(bin_number, mid + 1, end)
    if not right:
        return False

    if bin_number[mid] == '0' and '1' in bin_number[begin:end + 1]:
        return False

    return True


def solution(numbers):
    answer = []

    for number in numbers:
        bin_number = add_padding(str(bin(number))[2:])
        answer.append(int(is_expressed(bin_number, 0, len(bin_number) - 1)))

    return answer


print(solution([7, 42, 5]))
