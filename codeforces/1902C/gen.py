import random
import sys

sys.stdout = open('in.txt', 'w')

print(1)

n = random.randint(5, 10)

print(n)

for _ in range(n):
    print(random.randint(-10, 10), end=' ')
