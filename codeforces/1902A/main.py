import sys
from os import path

if path.exists('in.txt'):
    sys.stdin = open('in.txt', 'r')

input = sys.stdin.readline

t = int(input())
for _ in range(t):
    n = int(input())
    s = input().strip()

    ok = False
    for i in range(n):
        if s[i] == '0':
            ok = True
            break

    if ok:
        print('YES')
    else:
        print('NO')
