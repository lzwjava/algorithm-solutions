# https://codeforces.com/contest/1902/submission/235551541
import sys
from os import path

nax = 2 * 1007
alfa = 26


def czyt():
    wcz = input()
    return [ord(ch) - ord('a') for ch in wcz]


def daj(a, b):
    for i, j in enumerate(graf[a]):
        if j[1] == b:
            return i
    global m
    m += 1
    graf[a].append((m, b))
    return m


def dfs(v, gl):
    ret = [war[v][0], war[v][1]]
    for h in graf[v]:
        i = h[0]
        wez = dfs(i, 1)
        ret[0] += wez[0]
        ret[1] += wez[1]
    if gl:
        global wyn
        wyn -= 2 * ret[0] * ret[1]
    return ret


def main():
    if path.exists('in.txt'):
        sys.stdin = open('in.txt', 'r')

    global n, graf, war, wyn, m
    n = int(input())
    graf = [[] for _ in range(nax)]
    war = [[0, 0] for _ in range(nax)]
    wyn = 0
    m = 0

    for _ in range(1, n + 1):
        wek = czyt()
        for h in range(2):
            v = 0
            for i in wek:
                v = daj(v, i)
            war[v][h] += 1
            wek.reverse()
        wyn += 2 * n * len(wek)

    dfs(0, 0)
    print(wyn)


if __name__ == "__main__":
    main()
