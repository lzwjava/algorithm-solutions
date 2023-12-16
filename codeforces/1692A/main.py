import sys
from os import path

if path.exists('in.txt'):
    sys.stdin = open('in.txt', 'r')

input = sys.stdin.readline

t = int(input())

for _ in range(t):
    ds = list(map(int, input().split()))
    assert len(ds) == 4
    count = 0
    for i in range(1, 4):
        if ds[i] > ds[0]:
            count += 1

    print(count)
