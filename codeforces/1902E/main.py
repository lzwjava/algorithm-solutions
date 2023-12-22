import sys
from os import path


def collapse(a, b):
    if a == '':
        return b
    elif b == '':
        return a
    elif a[-1] == b[0]:
        return collapse(a[0:], b[1:])
    else:
        return a + b


def main():
    if path.exists('in.txt'):
        sys.stdin = open('in.txt', 'r')

    input = sys.stdin.readline

    n = int(input())

    strs = []
    for _ in range(n):
        str = input()
        strs.append(str)

    sum = 0
    for i in range(n):
        for j in range(n):
            res = collapse(strs[i], strs[j])
            sum += len(res)
    print(sum)


main()
