from typing import List


class Solution:

    def area(self, height: List[int], i: int, j: int):
        return min(height[i], height[j]) * (j - i)

    def maxArea(self, height: List[int]) -> int:
        return self.maxArea2(height)

    def maxArea2(self, height: List[int]) -> int:
        n = len(height)
        i = 0
        j = n - 1
        mi = 0
        mj = n - 1
        max_area = self.area(height, mi, mj)
        while True:
            i += 1
            if i == mj:
                break
            a1 = self.area(height, i, mj)
            if a1 > max_area:
                mi = i
                max_area = a1

        while True:
            j -= 1
            if j == mi:
                break
            a2 = self.area(height, mi, j)
            if a2 > max_area:
                mj = j
                max_area = a2

        return max_area

    def maxArea1(self, height: List[int]) -> int:
        n = len(height)
        max_area = 0
        for i in range(0, n - 1):
            for j in range(i + 1, n):
                min_h = min(height[i], height[j])
                area = min_h * (j - i)
                max_area = max(max_area, area)

        return max_area


print(Solution().maxArea([2, 3, 4, 5, 18, 17, 6]))
