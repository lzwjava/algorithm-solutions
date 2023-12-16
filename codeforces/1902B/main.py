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

    pts1 = l + 2 * t

    if double_weeks > 0:
        if pts1 * double_weeks >= P:
            day = math.ceil(P / pts1)
            work_days += day
            P = 0
        else:
            P -= pts1 * double_weeks
            work_days += double_weeks

    if P > 0:
        other_days = n - double_weeks * 14

        if other_days != 0:
            pts2 = l + t

            if other_days > 7:
                P -= pts1
                work_days += 1
            else:
                P -= pts2
                work_days += 1

        if P > 0:
            left_days = n - work_days

            need_days = math.ceil(P / l)
            assert need_days <= left_days

            work_days += need_days

    rest_days = n - work_days
    print(rest_days)
