t = int(raw_input())


def do_one():
    w, h, n = map(int, raw_input().split())
    p1 = []
    p1_total = 0
    p2 = []
    p2_total = 0
    for _ in xrange(h):
        p1.append(list(raw_input()))
        p1_total += sum(1 for x in p1[-1] if x == '#')
    for _ in xrange(h):
        p2.append(list(raw_input()))
        p2_total += sum(1 for x in p2[-1] if x == '#')
    l = []
    for _ in xrange(n):
        l.append(raw_input())
    p1 = list(reversed(p1))
    p2 = list(reversed(p2))
    win_val = 0
    for idx, val in enumerate(l):
        x, y = map(int, val.split())
        if (idx + win_val) % 2 == 0:
            if p2[y][x] == '#':
                p2[y][x] = 'D'
                p2_total -= 1
                if p2_total != 0:
                    win_val += 1
        else:
            if p1[y][x] == '#':
                p1[y][x] = 'D'
                p1_total -= 1
                win_val += 1
                if p1_total == 0:
                    if p2_total == 0:
                        return "draw"
                    else:
                        return "player two wins"
            else:
                if p2_total == 0:
                    return "player one wins"
    if p1_total == 0 and p1_total == 0:
        return "draw"
    if p1_total != 0 and p2_total != 0:
        return "draw"
    if p1_total == 0:
        return "player two wins"
    return "player one wins"


for _ in xrange(t):
    print(do_one())
