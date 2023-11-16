import bisect
from typing import List


class ItemComparable:

    def __init__(self, val: List[int]):
        self.val = val
        self.sorted_val = sorted(val)
        self.hash = hash(tuple(self.sorted_val))

    def __eq__(self, other) -> bool:
        n = len(self.sorted_val)
        eq = True
        for i in range(n):
            if self.sorted_val[i] != other.sorted_val[i]:
                eq = False
                break
        return eq

    def __hash__(self) -> int:
        return self.hash


class Solution:
    def threeSum(self, nums: List[int]) -> List[List[int]]:
        n = len(nums)

        sorted_nums = sorted(nums)

        ans = set()
        for i in range(0, n - 2):
            for j in range(i + 1, n - 1):
                vk = - (nums[i] + nums[j])

                ik = bisect.bisect_left(sorted_nums, vk)

                if ik != n and sorted_nums[ik] == vk:
                    ans.add(ItemComparable([nums[i], nums[j], vk]))

        return [x.val for x in ans]


print(Solution().threeSum([-1, 0, 1, 2, -1, -4]))
