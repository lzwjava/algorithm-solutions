# https://codeforces.com/contest/1902/submission/235566192

from collections import defaultdict
import sys


class StringHash:
    def __init__(self, s=""):
        self.BUILD_REVERSE = True
        self.HASH_P = 2 ** 32 - 13337
        self.HASH_COUNT = 2
        self.HASH_MULT = [0, 0]
        self.HASH_INV = [0, 0]
        self.HASH_P2 = self.HASH_P * self.HASH_P
        self.str = ""
        self.prefix = [[0], [0]]
        self.inv_prefix = [[0], [0]]

        self.build(s)

    def add_char(self, c):
        self.str += c
        for h in range(self.HASH_COUNT):
            self.prefix[h].append(self.HASH_MULT[h] * self.prefix[h][-1] + ord(c))

            if len(self.hash_pow[h]) < len(self.prefix[h]):
                self.hash_pow[h].append(self.HASH_MULT[h] * self.hash_pow[h][-1])

            if self.BUILD_REVERSE:
                self.inv_prefix[h].append((self.inv_prefix[h][-1] + ord(c)) * self.HASH_INV[h])

    def pop_char(self):
        self.str = self.str[:-1]
        for h in range(self.HASH_COUNT):
            self.prefix[h].pop()
            if self.BUILD_REVERSE:
                self.inv_prefix[h].pop()

    def build(self, s):
        self.str = ""
        for h in range(self.HASH_COUNT):
            self.hash_pow[h] = [1]
            self.prefix[h] = [0]

            if self.BUILD_REVERSE:
                self.inv_prefix[h] = [0]

        for c in s:
            self.add_char(c)

    def _single_hash(self, h, start, end):
        power = self.hash_pow[h][end - start]
        return (power + self.prefix[h][end] + self.HASH_P2 - self.prefix[h][start] * power) % self.HASH_P

    def substring_hash(self, start, end):
        assert 0 <= start <= end <= self.length()
        result = self._single_hash(0, start, end)
        if self.HASH_COUNT > 1:
            result += (self._single_hash(1, start, end) << 32)
        return result

    def reverse_substring_hash(self, start, end):
        assert self.BUILD_REVERSE
        assert 0 <= start <= end <= self.length()
        result = self._reverse_single_hash(0, start, end)
        if self.HASH_COUNT > 1:
            result += (self._reverse_single_hash(1, start, end) << 32)
        return result

    def length(self):
        return len(self.prefix[0]) - 1


def main():
    N = int(input())
    S = [input() for _ in range(N)]
    L = [len(s) for s in S]
    hash_objects = [StringHash(s) for s in S]

    prefix_hash_count = defaultdict(int)

    for i in range(N):
        for length in range(1, L[i] + 1):
            prefix_hash_count[hash_objects[i].substring_hash(0, length)] += 1

    def get_count(h):
        return prefix_hash_count[h]

    save = 0

    for i in range(N):
        matched = 0

        for length in range(L[i], 0, -1):
            h = hash_objects[i].reverse_substring_hash(L[i] - length, L[i])
            count = get_count(h)
            save += (count - matched) * length * 2
            matched = count

    total = 2 * N * sum(L) - save
    print(total)


if __name__ == "__main__":
    main()
