import bisect
import sys
from os import path

s_map = {}
max_size = 200002
X = [0] * max_size
Y = [0] * max_size


def ask(x, y, l, r):
    if (x, y) not in s_map:
        return False

    pos = s_map[(x, y)]
    idx = bisect.bisect_left(pos, l)
    if idx >= len(pos):
        return False
    return pos[idx] <= r


def main():
    if path.exists('in.txt'):
        sys.stdin = open('in.txt', 'r')

    input = sys.stdin.readline

    n, q = map(int, input().split())
    s = input().strip()

    for i in range(1, n + 1):
        si = s[i - 1]
        X[i] = X[i - 1]
        Y[i] = Y[i - 1]
        if si == 'U':
            Y[i] += 1
        elif si == 'D':
            Y[i] -= 1
        elif si == 'L':
            X[i] -= 1
        else:
            X[i] += 1

    for i in range(0, n + 1):
        pair = (X[i], Y[i])
        if pair in s_map:
            s_map[pair].append(i)
        else:
            s_map[pair] = [i]

    for _ in range(q):
        x, y, l, r = map(int, input().split())
        if ask(x, y, 0, l - 1):
            print('YES')
        elif ask(x, y, r, n):
            print('YES')
        elif ask(X[l - 1] + X[r] - x, Y[l - 1] + Y[r] - y, l, r):
            print('YES')
        else:
            print('NO')


main()
