import sys
from os import path

if path.exists('in.txt'):
    sys.stdin = open('in.txt', 'r')

input = sys.stdin.readline

t = int(input())
for _ in range(t):
    n = int(input())
    s = input().strip()
    zeros = 0
    for i in range(0, n - 1):
        if s[i] != s[i + 1]:
            zeros += 1
    n0 = 0
    for i in range(n):
        if s[i] == '0':
            n0 += 1
    n1 = n - n0
    if zeros + n0 > n1:
        print('YES')
    else:
        print('NO')
