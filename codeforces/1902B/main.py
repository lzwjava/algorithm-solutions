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

    double_weeks = n // 14

    work_days = 0

    if double_weeks > 0:
        pts = l + 2 * t
        if pts * double_weeks >= P:
            day = math.ceil(P / pts)
            work_days += day
            P = 0
        else:
            P -= pts * double_weeks
            work_days += double_weeks
