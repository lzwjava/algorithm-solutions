import math
import sys
from os import path

if path.exists('in.txt'):
    sys.stdin = open('in.txt', 'r')

input = sys.stdin.readline

t = int(input())

for _ in range(t):
    a = list(map(int, input().split()))
    n = a[0]
    P = a[1]
    l = a[2]
    t = a[3]

    weeks = math.ceil(n / 7)

    work_days = 0

    if weeks > 0:
        if (l + 2 * t) * weeks >= P:
            day = math.ceil(P / (l + 2 * t))
            work_days += day
            P = 0
        else:
            P -= (l + 2 * t) * weeks
            work_days += weeks
