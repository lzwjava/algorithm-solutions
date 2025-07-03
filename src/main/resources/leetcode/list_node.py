from typing import List


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

    @staticmethod
    def from_list(arr: List[int]):
        reversed_arr = reversed(arr)

        node = None
        for num in reversed_arr:
            node = ListNode(num, node)
        return node
