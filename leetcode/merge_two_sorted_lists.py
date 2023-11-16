from typing import Optional
from list_node import ListNode


class Solution:
    def mergeTwoLists(self, list1: Optional[ListNode], list2: Optional[ListNode]) -> Optional[ListNode]:
        root = None
        tail = None
        while list1 is not None or list2 is not None:

            if (list1 is not None and list2 is not None and list1.val <= list2.val) or list2 is None:
                useList1 = True
                node = ListNode(list1.val)
            else:
                useList1 = False
                node = ListNode(list2.val)

            if root is None:
                root = node
                tail = node
            else:
                tail.next = node
                tail = node

            if useList1:
                list1 = list1.next
            else:
                list2 = list2.next

        return root


print(Solution().mergeTwoLists(ListNode.from_list([1, 2, 4]), ListNode.from_list([1, 3, 4])))
