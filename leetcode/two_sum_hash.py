from typing import List


class Solution:
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        n = len(nums)
        hash = {}
        for i in range(0, n):
            remaining = target - nums[i]
            idx = hash.get(remaining)
            if idx is not None:
                return [idx, i]
            else:
                hash[nums[i]] = i


print(Solution().twoSum([2, 5, 5, 11], 10))
