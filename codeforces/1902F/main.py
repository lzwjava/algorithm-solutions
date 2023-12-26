import sys
from os import path


def main():
    if path.exists('in.txt'):
        sys.stdin = open('in.txt', 'r')

    input = sys.stdin.readline

    n = int(input())
    a = list(map(int, input().split()))
    for i in range(n - 1):
        b = list(map(int, input().split()))
        u = b[0]
        v = b[1]
        print(u, v)
    q = int(input())
    for i in range(q):
        c = list(map(int, input().split()))
        x = c[0]
        y = c[1]
        k = c[2]
        print(x, y, k)


main()
