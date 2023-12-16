import sys
from os import path

if path.exists('in.txt'):
    sys.stdin = open('in.txt', 'r')

input = sys.stdin.readline

t = int(input())

for _ in range(t):
    s = input().strip()
    if s.upper() == 'YES':
        print('Yes')
    else:
        print('No')
