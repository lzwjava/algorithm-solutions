from typing import Optional


class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next


class Solution:

    def toArr(self, l: Optional[ListNode]):
        arr = []
        while l is not None:
            arr.append(l.val)
            l = l.next
        return arr

    def addTwoNumbers(self, l1: Optional[ListNode], l2: Optional[ListNode]) -> Optional[ListNode]:
