import sys
from os import path

if path.exists('in.txt'):
    sys.stdin = open('in.txt', 'r')

input = sys.stdin.readline

n, q = map(int, input().split())
s = input().strip()


def reversed_str(ss):
    sub = list(ss)
    sub.reverse()

    rs = ''.join(sub)
    return rs


for _ in range(q):
    x, y, l, r = map(int, input().split())
    l -= 1
    r -= 1
    rs = s[:l] + reversed_str(s[l:r + 1]) + s[r + 1:]
    xi = 0
    yi = 0
    visited = False
    for i in range(n + 1):
        if xi == x and yi == y:
            visited = True
            break
        if i == n:
            break
        if rs[i] == 'U':
            yi += 1
        elif rs[i] == 'D':
            yi -= 1
        elif rs[i] == 'L':
            xi -= 1
        elif rs[i] == 'R':
            xi += 1

    if visited:
        print('YES')
    else:
        print('NO')
