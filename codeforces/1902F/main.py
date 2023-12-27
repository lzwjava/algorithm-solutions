import sys
from os import path


def permutation(selected, i, n, func):
    if i == n:
        ok = func(selected)
        return ok
    selected[i] = False
    if permutation(selected, i + 1, n, func):
        return True
    selected[i] = True
    if permutation(selected, i + 1, n, func):
        return True
    return False


def meet_constraint(selected):
    pass


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
        selected = [False] * n
        permutation(selected, 0, n, )
        print(x, y, k)


main()
