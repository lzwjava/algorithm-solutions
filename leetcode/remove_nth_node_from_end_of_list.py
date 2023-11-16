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


def list_len(head: Optional[ListNode]):
    hn = 0
    while head is not None:
        hn += 1
        head = head.next
    return hn


class Solution:

    def removeNthFromEnd(self, head: Optional[ListNode], n: int) -> Optional[ListNode]:
        hn = list_len(head)

        idx = hn - n

        if idx == 0:
            return head.next
        else:
            prev = head
            root = head
            while idx > 0:
                idx -= 1
                prev = head
                head = head.next
            prev.next = head.next
            return root

    def createList(self, arr: List[int]):
        node = None
        for num in arr:
            node = ListNode(num, node)
        return node


sol = Solution()
la = sol.createList(reversed([1, 2]))
print(sol.removeNthFromEnd(la, 1))
