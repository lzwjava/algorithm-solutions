from typing import List


class Solution:

    def area(self, height: List[int], i: int, j: int):
        return min(height[i], height[j]) * (j - i)

    def maxArea(self, height: List[int]) -> int:
        n = len(height)
        left = 0
        right = n - 1
        max_area = 0
        while left < right:
            max_area = max(max_area, self.area(height, left, right))
            if height[left] < height[right]:
                left += 1
            elif height[left] > height[right]:
                right -= 1
            else:
                left += 1
                right -= 1

        return max_area


print(Solution().maxArea([2, 3, 4, 5, 18, 17, 6]))
