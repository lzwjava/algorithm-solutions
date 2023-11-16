class Solution:

    @staticmethod
    def parenthese(a, b):
        return (a == '(' and b == ')') or (a == '[' and b == ']') or (a == '{' and b == '}')

    def isValid(self, s: str) -> bool:
        n = len(s)
        stack = []
        for i in range(n):
            ch = s[i]
            if i == 0:
                stack.append(ch)
            else:
                if len(stack) == 0:
                    stack.append(ch)
                else:
                    top = stack[-1]
                    if self.parenthese(top, ch):
                        stack.pop()
                    else:
                        stack.append(ch)

        return len(stack) == 0


print(Solution().isValid('()[]{}'))
