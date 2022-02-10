"""
비트마스크를 이용해 00 0000 0001 ~ 11 1111 1110 범위에 대해 0번 선거구, 1번 선거구로 나누어 풀 생각이다.
그리고 각 경우의수마다 0번 선거구, 1번 선거구 모두 각 지역끼리 연결되어 있는지 확인한다.
연결되어 있다면 각 선거구마다 인구수 차이를 계산하고 최솟값을 갱신한다.
지역은 최대 10개이므로 선거구를 나누는 최대 경우의수는 1021 (0...0, 1...1 제외)가 나오며,
연결을 확인하는 부분에서는 아무리 최악으로 잡아도 약 100 번 이내의 수행을 한다.
따라서 시간복잡도 부분에서 문제는 없을 것 같다.
"""


def check_connected(district, adj):
    connected = {district[0]}
    non_connected = set(district[1:])

    while len(non_connected) > 0:
        new_connected = set()
        for region in connected:
            for dest_region in non_connected:
                if dest_region in adj[region]:
                    new_connected.add(dest_region)

        if not new_connected:
            return False

        connected |= new_connected
        non_connected -= new_connected

    return True


def solution():
    n = int(input())  # 지역의 수
    regions = list(map(int, input().split()))  # 각 지역의 인구수
    adj = dict()  # 각 지역의 인접 정보
    for i in range(n):
        adj_info = list(map(int, input().split()))
        adj[i] = set([adj_info[j] - 1 for j in range(1, len(adj_info))])

    answer = 1 << 30
    for divided in range(1, (1 << n) - 1):
        # 비트의 상태에 따라 선거구 나누기
        district_0 = [i for i in range(n) if divided & (1 << i) == 0]
        district_1 = [i for i in range(n) if divided & (1 << i) != 0]

        # 각 선거구의 연결 여부 확인하여 연결되어 있지 않다면 continue
        if not check_connected(district_0, adj) or not check_connected(district_1, adj):
            continue

        # 각 선거구의 인구수와 차이 구하기
        population_dist_0 = sum([regions[reg] for reg in district_0])
        population_dist_1 = sum([regions[reg] for reg in district_1])
        dif = abs(population_dist_0 - population_dist_1)

        # 최솟값 갱신
        if answer > dif:
            answer = dif

    if answer == 1 << 30:
        answer = -1

    print(answer)


solution()
