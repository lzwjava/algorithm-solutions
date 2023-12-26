# https://github.com/ibrahimhabibeg/codeforces/blob/main/1902/E/sol.cpp

import sys
from os import path


class TrieNode:
    def __init__(self):
        self.children = [None] * 26
        self.cnt = 0


def create_node():
    return TrieNode()


def main():
    if path.exists('in.txt'):
        sys.stdin = open('in.txt', 'r')

    n = int(input())
    s = [input() for _ in range(n)]
    sm = 0
    root = create_node()

    for i in range(n):
        sm += len(s[i])
        r = root
        for j in range(len(s[i])):
            if not r.children[ord(s[i][j]) - ord('a')]:
                r.children[ord(s[i][j]) - ord('a')] = create_node()
            r = r.children[ord(s[i][j]) - ord('a')]
            r.cnt += 1

    res = 2 * n * sm

    for i in range(n):
        r = root
        for j in range(len(s[i]) - 1, -1, -1):
            if not r.children[ord(s[i][j]) - ord('a')]:
                break
            r = r.children[ord(s[i][j]) - ord('a')]
            res -= 2 * r.cnt

    print(res)


if __name__ == "__main__":
    main()
