"""
모든 막대의 길이는 이진수에서 하나의 비트의 값에 해당한다.
다시 말해, 23cm의 막대는 16cm, 4cm, 2cm, 1cm로 이루어진다.
즉 주어지는 X를 이진수로 나타냈을 때 1의 비트수를 구하면 된다.
"""


def solution(x):
    answer = 0

    while x > 0:
        # 시프트 연산하며 0번째 비트의 상태를 확인
        if x & 1 == 1:
            answer += 1
        x >>= 1

    return answer


print(solution(int(input())))
