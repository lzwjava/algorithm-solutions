import sys
from os import path


def main():
    if path.exists('in.txt'):
        sys.stdin = open('in.txt', 'r')

    input = sys.stdin.readline

    n = int(input())

    strs = []
    for _ in range(n):
        str = input()
        strs.append(str)

    
main()
