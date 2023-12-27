import sys
from os import path

g = []
x, y, k = 0, 0, 0
n = 0
a = list()


def permutation(selected, i, func):
    if i == n:
        ok = func(selected)
        return ok
    selected[i] = False
    if permutation(selected, i + 1, func):
        return True
    selected[i] = True
    if permutation(selected, i + 1, func):
        return True
    return False


def dfs(cur, vj, visited):
    if cur == vj:
        return True
    if cur == y:
        return False

    for v in g[cur]:
        if not visited[v]:
            visited[v] = True
            if dfs(v, vj, visited):
                return True
            visited[v] = False
    return False


def in_simple_path(vj):
    visited = [False] * n
    return dfs(x, vj, visited)


def meet_constraint(selected):
    ok = True
    for i in range(n):
        if selected[i] and not in_simple_path(selected[i]):
            ok = False
            break

    if ok:
        av = None

        for i in range(n):
            if selected[i]:
                if not av:
                    av = a[i]
                else:
                    av = av ^ a[i]


def main():
    if path.exists('in.txt'):
        sys.stdin = open('in.txt', 'r')

    input = sys.stdin.readline

    global n, a, g, x, y, k

    n = int(input())
    a = list(map(int, input().split()))

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
        permutation(selected, 0, meet_constraint)
        print(x, y, k)


main()
