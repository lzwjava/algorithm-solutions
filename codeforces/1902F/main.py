import sys
from os import path


def permutation(selected, i, n):
    if i == n:
        pass
    selected[i] = 0
    permutation(selected, i + 1, n)
    selected[i] = 1
    permutation(selected, i + 1, n)


def main():
    if path.exists('in.txt'):
        sys.stdin = open('in.txt', 'r')

    input = sys.stdin.readline

    n = int(input())
    a = list(map(int, input().split()))
    print(a)

    g = [list()] * n
    for i in range(n - 1):
        u, v = map(int, input().split())
        u -= 1
        v -= 1
        g[u].append(v)
        g[v].append(u)

    q = int(input())
    for i in range(q):
        x, y, k = map(int, input().split())

        print(x, y, k)


main()
