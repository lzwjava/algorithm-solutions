import sys
from os import path

g = []
x, y, k = 0, 0, 0
n = 0
a = []
all_routes = []


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


def dfs(cur, visited, routes):
    if cur == y:
        all_routes.append(routes.copy())
        return

    for v in g[cur]:
        if not visited[v]:
            visited[v] = True
            routes.append(v)
            dfs(v, visited, routes)
            routes.pop()
            visited[v] = False


def in_simple_path(vj):
    for i in range(len(all_routes)):
        route = all_routes[i]
        for j in range(len(route)):
            if route[j] == vj:
                return True
    return False


def get_all_routes():
    visited = [False] * n
    routes = list()
    visited[x] = True
    routes.append(x)
    dfs(x, visited, routes)


def meet_constraint(selected):
    ok = True
    for i in range(n):
        if selected[i] and not in_simple_path(i):
            ok = False
            break

    if ok:
        av = 0

        for i in range(n):
            if selected[i]:
                if not av:
                    av = a[i]
                else:
                    av = av ^ a[i]
        if av == k:
            ans = list()
            for i in range(n):
                if selected[i]:
                    ans.append(i)
            # print(ans)
            return True

    return False


def main():
    if path.exists('in.txt'):
        sys.stdin = open('in.txt', 'r')

    input = sys.stdin.readline

    global n, a, g, x, y, k, all_routes

    n = int(input())
    a = list(map(int, input().split()))

    g = [set() for _ in range(n)]
    for i in range(n - 1):
        u, v = map(int, input().split())
        u -= 1
        v -= 1
        g[u].add(v)
        g[v].add(u)

    q = int(input())
    for i in range(q):
        x, y, k = map(int, input().split())
        x -= 1
        y -= 1

        all_routes = []

        get_all_routes()
        # print(all_routes)

        selected = [False] * n
        res = permutation(selected, 0, meet_constraint)
        if res:
            print('YES')
        else:
            print('NO')


main()
