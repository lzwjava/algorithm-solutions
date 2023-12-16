import sys
from os import path

if path.exists('in.txt'):
    sys.stdin = open('in.txt', 'r')

input = sys.stdin.readline

t = int(input())

for _ in range(t):
    ds = input().strip()
    n = len(ds)
    assert n == 6
    sum1 = 0
    for i in range(0, 3):
        sum1 += ord(ds[i]) - ord('0')
    sum2 = 0
    for i in range(3, 6):
        sum2 += ord(ds[i]) - ord('0')
    if sum1 == sum2:
        print('YES')
    else:
        print('NO')
