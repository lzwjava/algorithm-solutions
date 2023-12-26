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

    input = sys.stdin.readline

    n = int(input())

    strs = []
    for _ in range(n):
        s = input().strip()
        strs.append(s)

    root = create_node()
    sm = 0
    for i in range(n):
        r = root
        ls = len(strs[i])
        sm += ls
        for j in range(ls):
            v = ord(strs[i][j]) - ord('a')
            if r.children[v] is None:
                r.children[v] = create_node()
                r.cnt += 1
            r = r.children[v]

    ans = 2 * n * sm

    for i in range(n):
        r = root
        ls = len(strs[i])
        for j in range(ls - 1, -1, -1):
            v = ord(strs[i][j]) - ord('a')
            if not r.children[v]:
                break
            ans -= r.cnt * 2

    print(ans)


main()
