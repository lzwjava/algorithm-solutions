import math
import sys
from os import path

if path.exists('in.txt'):
    sys.stdin = open('in.txt', 'r')

input = sys.stdin.readline

t = int(input())

pm = int(math.sqrt(1e9))

is_primes = [True] * (pm + 1)

pms = []

for i in range(2, pm):
    if is_primes[i]:
        pms.append(i)
        for j in range(i * i, pm + 1, i):
            is_primes[j] = False

pmn = len(pms)

for _ in range(t):
    n = int(input())
    a = list(map(int, input().split()))

    m = max(a)

    print(m)

    b = []
    for i in range(n):
        if a[i] != m:
            b.append(m - a[i])

    bn = len(b)

    x = 1

    for j in range(pmn):
        p = pms[j]
        while True:
            ok = True
            for i in range(bn):
                if b[i] % p != 0:
                    ok = False
                    break

            if ok:
                x *= p
                for i in range(bn):
                    b[i] /= p
            else:
                break

        greater = True
        for i in range(bn):
            if p <= b[i]:
                greater = False
                break
        if greater:
            break

    ops = 0
    for i in range(n):
        if a[i] != m:
            ops += (m - a[i]) / x
    