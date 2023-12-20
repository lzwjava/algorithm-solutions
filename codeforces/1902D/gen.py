import random
import sys

sys.stdout = open('in.txt', 'w')

n = random.randint(1, 20000)
q = random.randint(1, 20000)

cs = 'UDLR'

s = ''

print(f'{n} {q}')

for i in range(n):
    idx = random.randint(0, 3)
    s += cs[idx]

print(s)

for _ in range(q):
    xi = random.randint(-10, 10)
    yi = random.randint(-10, 10)
    l = random.randint(1, n)
    r = random.randint(1, n)

    if r > l:
        temp = l
        l = r
        r = temp

    print(f'{xi} {yi} {l} {r}')
