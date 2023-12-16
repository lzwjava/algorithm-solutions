import sys
from os import path

if path.exists('in.txt'):
    sys.stdin = open('in.txt', 'r')

input = sys.stdin.readline

t = int(input())

for _ in range(t):
    r = int(input())
    if r >= 1900:
        div = 1
    elif r >= 1600:
        div = 2
    elif r >= 1400:
        div = 3
    else:
        div = 4
    print(f'Division {div}')
