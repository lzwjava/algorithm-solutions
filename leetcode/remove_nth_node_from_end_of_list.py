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
    def removeNthFromEnd(self, head: Optional[ListNode], n: int) -> Optional[ListNode]:
        return None

    def createList(self, arr: List[int]):
        node = None
        for num in arr:
            node = ListNode(num, node)
        return node


sol = Solution()
la = sol.createList(reversed([1, 2, 3, 4, 5]))
sol.removeNthFromEnd(la, 1)
