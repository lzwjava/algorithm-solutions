from typing import List


class Solution:

    def __init__(self):
        self.vs = [1000, 500, 100, 50, 10, 5, 1]
        self.symbols = ['M', 'D', 'C', 'L', 'X', 'V', 'I']
        self.n = len(self.vs)

    def value(self, s: str) -> int:
        for i in range(0, self.n):
            if self.symbols[i] == s:
                return self.vs[i]
        return -1

    def romanToInt(self, s: str) -> int:
        i = 0
        sn = len(s)
        ans = 0
        while True:
            v = self.value(s[i])
            if i + 1 == sn:
                ans += v
                break
            nv = self.value(s[i + 1])
            if nv > v:
                ans += (nv - v)
                i += 2
                if i == sn:
                    break

        return ans


print(Solution().romanToInt("MCMXCIV"))
