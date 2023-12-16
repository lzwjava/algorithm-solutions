import sys
from os import path

if path.exists('in.txt'):
    sys.stdin = open('in.txt', 'r')

input = sys.stdin.readline

t = int(input())

for _ in range(t):
    a = list(map(int, input().split()))
    assert len(a) == 3
    if a[0] + a[1] == a[2] or a[0] + a[2] == a[1] or a[1] + a[2] == a[0]:
        print('YES')
    else:
        print('NO')

