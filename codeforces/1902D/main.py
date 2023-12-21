import bisect
import sys
from os import path


def reversed_str(ss):
    sub = list(ss)
    sub.reverse()

    rs = ''.join(sub)
    return rs


def visit(ss):
    n = len(ss)
    xi = 0
    yi = 0
    pos_list = []
    for i in range(n + 1):
        pos_list.append(f'{xi},{yi}')
        if i == n:
            break
        if ss[i] == 'U':
            yi += 1
        elif ss[i] == 'D':
            yi -= 1
        elif ss[i] == 'L':
            xi -= 1
        elif ss[i] == 'R':
            xi += 1

    return pos_list


s_map = {}
max_size = 200002
X = [0] * max_size
Y = [0] * max_size


def ask(x, y, l, r):
    str_xy = f'{x},{y}'
    pos = s_map[str_xy]
    idx = bisect.bisect_left(pos, l)
    if idx >= len(pos):
        return False
    return pos[idx] <= r


def main():
    if path.exists('in.txt'):
        sys.stdin = open('in.txt', 'r')

    input = sys.stdin.readline

    n, q = map(int, input().split())
    s = input().strip()

    for i in range(1, n + 1):
        pass
    
    s_pos_list = visit(s)

    for i in range(len(s_pos_list)):
        pos = s_pos_list[i]
        if s_map[pos] is None:
            s_map[pos] = [i]
        else:
            s_map[pos].append(i)

    # rs_pos_list = visit(reversed_str(s))
    rs_pos_list = list(reversed(s_pos_list))

    for _ in range(q):
        x, y, l, r = map(int, input().split())
        if ask(x, y, 0, l - 1):
            print('YES')
        elif ask(x, y, r, n):
            print('YES')
        elif ask()

        l -= 1
        r -= 1
        ns = s_pos_list[:l] + s_pos_list[r + 1:]
        ns_set = set(ns)

        str_xy = f'{x},{y}'

        if str_xy in ns_set:
            print('YES')
        else:
            print('NO')

        if ns.count(f'{x},{y}') > 0:
            print('YES')
        else:
            print('NO')


main()
