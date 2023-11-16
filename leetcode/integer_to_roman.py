class Solution:
    def intToRoman(self, num: int) -> str:
        vs = [1000, 500, 100, 50, 10, 5, 1]
        symbols = ['M', 'D', 'C', 'L', 'X', 'V', 'I']
        n = len(vs)
        cnts = []
        for i in range(0, n):
            cnt = num // vs[i]
            cnts.append(cnt)
            num -= cnt * vs[i]

        for i in range(0, n):
            if cnts[i] > 0:
                for _ in range(cnts[i]):
                    print(symbols[i], end='')


Solution().intToRoman(58)
