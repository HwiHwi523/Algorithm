MAX = 1 << 30


def solution(weight, dp, visit, current):
    visit |= 1 << current
    if dp[current][visit] > 0:
        return dp[current][visit]

    if visit == (1 << len(weight)) - 1:
        if weight[current][0] == 0:
            return MAX
        dp[current][visit] = weight[current][0]

    else:
        min_dist = MAX
        for i in range(len(weight)):
            # 아직 방문하지 않았다면 방문
            if visit & (1 << i) == 0 and weight[current][i] != 0:
                dist = weight[current][i] + solution(weight, dp, visit, i)
                if 0 < dist < min_dist:
                    min_dist = dist

        dp[current][visit] = min_dist

    return dp[current][visit]


n = int(input())
weight = []
for i in range(n):
    weight.append(list(map(int, input().split())))

dp = [[0] * (1 << n) for _ in range(n)]

print(solution(weight, dp, 0, 0))
