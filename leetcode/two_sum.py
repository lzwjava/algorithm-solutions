from typing import List, Tuple


class Solution:
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        n = len(nums)

        sorted_nums = [(i, num) for i, num in enumerate(nums)]

        sorted_nums = sorted(sorted_nums, key=lambda x: x[1])

        for i in range(0, n - 1):

            other = target - nums[i]
            sorted_idx = self.binary_search(sorted_nums, other)
            idx = sorted_nums[sorted_idx][0]

            ans = -1
            if idx == i:
                if sorted_nums[sorted_idx - 1][1] == nums[i]:
                    ans = sorted_idx - 1
                elif sorted_nums[sorted_idx + 1][1] == nums[i]:
                    ans = sorted_idx + 1
                return [i, ans]
            else:
                return [i, idx]

    def binary_search(self, nums: List[Tuple[int, int]], value: int):
        n = len(nums)
        i = 0
        j = n
        while i < j:
            m = (i + j) // 2
            mv = nums[m][1]
            if mv == value:
                return m
            elif mv < value:
                i = m + 1
            else:
                j = m

        return -1


print(Solution().twoSum([3, 2, 4], 6))
