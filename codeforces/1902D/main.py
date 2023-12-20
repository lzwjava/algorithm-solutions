import sys
from os import path

if path.exists('in.txt'):
    sys.stdin = open('in.txt', 'r')

input = sys.stdin.readline

n, q = map(int, input().split())
s = input().strip()


def reversed_str(ss):
    sub = list(ss)
    sub.reverse()

    rs = ''.join(sub)
    return rs


def visit(ss):
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


s_pos_list = visit(s)
# rs_pos_list = visit(reversed_str(s))
rs_pos_list = list(reversed(s_pos_list))

for _ in range(q):
    x, y, l, r = map(int, input().split())
    l -= 1
    r -= 1
    ns = s_pos_list[:l] + s_pos_list[r + 1:]
    ns_set = set(ns)

    str_xy = f'{x},{y}'

    if str_xy in ns_set:
        print('YES')
    else:
        print('NO')

    rs_pos_list[l:r + 1]

    if ns.count(f'{x},{y}') > 0:
        print('YES')
    else:
        print('NO')
