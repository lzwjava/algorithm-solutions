from typing import List


class Solution:

    def __init__(self):
        self.ans = []

    def letterCombinations(self, digits: str) -> List[str]:
        h = {'2': 'abc', '3': 'def', '4': 'ghi', '5': 'jkl', '6': 'mno', '7': 'pqrs', '8': 'tuv', '9': 'wxyz'}
        chs = []
        n = len(digits)
        for i in range(n):
            ch = h[digits[i]]
            chs.append(ch)

        self.ans = []

        selected = [n]
        self.permutation(chs, selected, 0, n)

        return self.ans

    def permutation(self, chs: List[str], selected: List[int], i: int, n: int):
        if i == n:
            concat = ''
            for j in range(n):
                concat += chs[j][selected[j]]
            self.ans.append(concat)

        cn = len(chs[i])

        for k in range(cn):
            selected[i] = k
            self.permutation(chs, selected, i + 1, n)


print(Solution().letterCombinations('23'))
