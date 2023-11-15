from typing import List


class Solution:
    def maxArea(self, height: List[int]) -> int:
        n = len(height)
        max_area = 0
        for i in range(0, n - 1):
            for j in range(i + 1, n):
                min_h = min(height[i], height[j])
                area = min_h * (j - i)
                max_area = max(max_area, area)

        return max_area


print(Solution().maxArea([1, 8, 6, 2, 5, 4, 8, 3, 7]))
