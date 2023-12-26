import sys
from os import path


def main():
    if path.exists('in.txt'):
        sys.stdin = open('in.txt', 'r')

    input = sys.stdin.readline

    n = int(input())
    a = list(map(int, input().split()))
    print(a)
    for i in range(n - 1):
        u, v = map(int, input().split())
        print(u, v)
    q = int(input())
    for i in range(q):
        x, y, k = map(int, input().split())
        print(x, y, k)


main()
