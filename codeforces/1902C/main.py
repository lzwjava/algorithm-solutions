import math
import sys
from os import path

if path.exists('in.txt'):
    sys.stdin = open('in.txt', 'r')

input = sys.stdin.readline

t = int(input())


def gcd(a, b):
    if b == 0:
        return a
    else:
        return gcd(b, a % b)


for _ in range(t):
    n = int(input())
    a = list(map(int, input().split()))

    m = max(a)

    an = len(a)

    b = []
    for i in range(n):
        if a[i] != m:
            b.append(m - a[i])

    bn = len(b)

    x = 1

    if bn > 0:

        if bn == 1:
            d = 1
        else:
            bi = gcd(b[0], b[1])
            for j in range(1, bn):
                bj = gcd(b[0], b[j])
                if bj < bi:
                    bi = bj
                if bi == 1:
                    break
            d = bi
            
        x *= d

    ops = 0
    for i in range(n):
        if a[i] != m:
            ops += (m - a[i]) // x

    an1_1 = m + x
    ops1 = ops + an

    an1_2 = m

    sa = set(a)

    while True:
        an1_2 = an1_2 - x
        if an1_2 not in sa:
            break

    ops2 = ops + (m - an1_2) // x

    if ops1 < ops2:
        print(ops1)
    else:
        print(ops2)
