from typing import List


class Solution:
    def longestCommonPrefix(self, strs: List[str]) -> str:
        min_len = 1 << 31
        for s in strs:
            min_len = min(min_len, len(s))
        i = 0
        base_str = strs[0]
        while i < min_len:
            ok = True
            ch = base_str[i]
            for s in strs:
                if s[i] != ch:
                    ok = False
                    break
            if ok:
                i += 1
            else:
                break
        if i == min_len:
            return base_str[0:i]
        else:
            if i != 0:
                return base_str[0:i]
            else:
                return ''


print(Solution().longestCommonPrefix(["flower", "flow", "flight"]))
