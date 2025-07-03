class Solution:
    def intToRoman(self, num: int) -> str:
        vs = [1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1]
        symbols = ['M', 'CM', 'D', 'CD', 'C', 'XC', 'L', 'XL', 'X', 'IX', 'V', 'IV', 'I']
        n = len(vs)
        cnts = []
        for i in range(0, n):
            cnt = num // vs[i]
            cnts.append(cnt)
            num -= cnt * vs[i]

        ans = ''

        for i in range(0, n):
            if cnts[i] > 0:
                for _ in range(cnts[i]):
                    ans += symbols[i]

        return ans

print(Solution().intToRoman(1994))
