import sys
from os import path


class TrieNode:
    def __init__(self):
        self.children = {}
        self.cnt = 0


def create_node():
    return TrieNode()


def main():
    if path.exists('in.txt'):
        sys.stdin = open('in.txt', 'r')

    input = sys.stdin.readline

    n = int(input())
    sm = 0

    root = create_node()

    strs = []

    for _ in range(n):
        s = input().strip()
        strs.append(s)
        ls = len(s)
        sm += ls
        r = root

        for j in range(ls):
            v = ord(s[j]) - ord('a')

            if v not in r.children:
                r.children[v] = create_node()

            r = r.children[v]
            r.cnt += 1

    ans = 2 * n * sm

    for i in range(n):
        r = root
        ls = len(strs[i])

        for j in range(ls - 1, -1, -1):
            v = ord(strs[i][j]) - ord('a')

            if v not in r.children:
                break

            r = r.children[v]
            ans -= 2 * r.cnt

    print(ans)


if __name__ == "__main__":
    main()
