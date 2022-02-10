"""
21 문자(5개의 문자(a, c, i, n, t) 제외)에서 서로 다른 (k - 5) 문자를 뽑는다.
그리고 매 조합마다 공통의 5 문자와 합한 뒤 모든 단어를 탐색하며 가르칠 수 있는지 확인한다.
그렇게 모든 조합에 대해 모든 단어를 탐색한 뒤 최댓값을 찾으려 한다.

+
시간초과
비트마스킹 써야 할 듯
"""
from itertools import combinations


def solution():
    n, k = list(map(int, input().split()))
    words = [set(list(input())) for _ in range(n)]

    if k < 5:
        print(0)
        return

    # 공통 문자 집합과 그 외 문자 집합 생성
    common_alpha_set = set(['a', 'c', 'i', 'n', 't'])
    others_alpha_set = set()
    for ch in 'abcdefghijklmnopqrstuvwxyz':
        others_alpha_set.add(ch)
    others_alpha_set -= common_alpha_set

    answer = 0
    for combi in combinations(others_alpha_set, k - 5):
        combi_alpha_set = common_alpha_set | set(combi)

        teachable_words_count = 0
        for word in words:
            if len(combi_alpha_set | word) <= k:
                teachable_words_count += 1

        if answer < teachable_words_count:
            answer = teachable_words_count

    print(answer)


solution()
