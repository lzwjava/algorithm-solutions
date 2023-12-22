import sys
from os import path


def collapse(a, b):
    if len(a) == 0:
        return b
    elif len(b) == 0:
        return a
    elif a[-1] == b[0]:
        return collapse(a[0:-1], b[1:])
    else:
        return a + b


def main():
    if path.exists('in.txt'):
        sys.stdin = open('in.txt', 'r')

    input = sys.stdin.readline

    n = int(input())

    strs = []
    for _ in range(n):
        s = input().strip()
        strs.append(s)

    cs = 0
    for i in range(n):
        for j in range(n):
            res = collapse(strs[i], strs[j])
            cs += len(res)
    print(cs)


main()
