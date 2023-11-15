from typing import Optional, List


class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

    def __str__(self):
        arr = []
        node = self
        while node is not None:
            arr.append(node.val)
            node = node.next
        return arr.__str__()


class Solution:

    def toArr(self, l: Optional[ListNode]):
        arr = []
        while l is not None:
            arr.append(l.val)
            l = l.next
        return arr

    def createList(self, arr: List[int]):
        node = None
        for num in arr:
            node = ListNode(num, node)
        return node

    def addTwoNumbers(self, l1: Optional[ListNode], l2: Optional[ListNode]) -> Optional[ListNode]:
        arr1 = self.toArr(l1)
        arr2 = self.toArr(l2)
        len1 = len(arr1)
        len2 = len(arr2)
        max_len = max(len1, len2)
        inc = 0

        arr3 = []
        for i in range(0, max_len):
            idx1 = len1 - 1 - i
            idx2 = len2 - 1 - i

            if idx1 >= 0:
                v1 = arr1[idx1]
            else:
                v1 = 0

            if idx2 >= 0:
                v2 = arr2[idx2]
            else:
                v2 = 0

            s = v1 + v2 + inc

            if s >= 10:
                nv = s % 10
                inc = 1
            else:
                nv = s
                inc = 0

            arr3.append(nv)

        if inc == 1:
            arr3.append(inc)

        return self.createList(arr3)


sol = Solution()
l1 = sol.createList([2, 4, 3])
l2 = sol.createList([5, 6, 4])

print(sol.addTwoNumbers(l1, l2))
