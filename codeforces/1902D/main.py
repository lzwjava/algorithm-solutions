import sys
from os import path

if path.exists('in.txt'):
    sys.stdin = open('in.txt', 'r')

input = sys.stdin.readline

n, q = map(int, input().split())
s = input()

for _ in range(q):
    x, y, l, r = map(int, input().split())
    
