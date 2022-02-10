"""
21 문자(5개의 문자(a, c, i, n, t) 제외)에서 서로 다른 (k - 5) 문자를 뽑는다.
그리고 매 조합마다 공통의 5 문자와 합한 뒤 모든 단어를 탐색하며 가르칠 수 있는지 확인한다.
그렇게 모든 조합에 대해 모든 단어를 탐색한 뒤 최댓값을 찾으려 한다.

+
1. 시간초과
 배운 알파벳과 단어의 알파벳을 or 연산한 뒤, 그 값의 비트수를 세려 함. (5개 문자 제외하면 단어 당 최대 10번의 연산을 하게 됨)
 그러나 그럴 필요 없이 or 연산했을 때 배운 알파벳의 비트와 똑같은지만 검사하면 됐음. 안 배운 알파벳이 들어가 있으면 결과가 달라지니까

"""
from itertools import combinations


def solution():
    n, k = map(int, input().split())

    # 각 단어를 문자에 따라 비트로 변환 (25번 비트 ~ 0번 비트 : 'z' ~ 'a')
    words_bit = [0] * n
    others_alpha_set = set()
    for i in range(n):
        for alpha in input():
            if alpha not in ['a', 'c', 'i', 'n', 't']:
                bit = (1 << (ord(alpha) - ord('a')))
                words_bit[i] |= bit
                others_alpha_set.add(bit)

    # a c i n t조차 못 가르치므로 어떤 단어도 읽을 수 없음
    if k < 5:
        print(0)
        return

    # a c i n t를 제외한 알파벳만 고려하면 됨
    comb_r = k - 5
    if comb_r > len(others_alpha_set):
        comb_r = len(others_alpha_set)

    answer = 0
    for combi in combinations(others_alpha_set, comb_r):
        # 최악 10
        combi_alpha_bit_set = 0
        for bit in combi:
            combi_alpha_bit_set |= bit

        teachable_words_count = 0
        # 최악 50
        for word in words_bit:
            bit_sum = combi_alpha_bit_set | word

            if bit_sum == combi_alpha_bit_set:
                teachable_words_count += 1

        if answer < teachable_words_count:
            answer = teachable_words_count

    print(answer)


solution()
