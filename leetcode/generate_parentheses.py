from typing import List


class Solution:
    def __init__(self):
        self.pair = 0
        self.ans = []

    def generateParenthesis(self, n: int) -> List[str]:
        self.pair = n
        self.ans = []
        total = n * 2
        self.permutation([''] * total, 0, total, 0, 0)
        return self.ans

    def permutation(self, chs: List[str], i: int, n: int, left: int, right: int):
        if left > self.pair or right > self.pair:
            return

        if i == n:
            stack = []
            concat = ''
            for j in range(0, n):
                ch = chs[j]
                if len(stack) == 0:
                    stack.append(ch)
                else:
                    if stack[-1] == '(' and ch == ')':
                        stack.pop()
                    else:
                        stack.append(ch)
                concat += ch
            if len(stack) == 0:
                self.ans.append(concat)
            return

        chs[i] = '('
        self.permutation(chs, i + 1, n, left + 1, right)
        chs[i] = ')'
        self.permutation(chs, i + 1, n, left, right + 1)


print(Solution().generateParenthesis(3))
