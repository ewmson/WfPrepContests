t = int(raw_input())
import math

def dist(p1, p2):
    x,y = p1
    x2,y2 = p2
    return math.sqrt((x-x2)**2 + (y-y2)**2)
def do_one():
    r,n = map(int, raw_input().split())
    p = []
    for _ in xrange(n):
        p.append(map(int, raw_input().split()))
    perim = 0
    for idx, pp in enumerate(p[1:]):
        perim += dist(pp, p[idx])
    perim += dist(p[-1], p[0])

    ans = 1 - ((2*math.pi * r)/float(perim))

    if ans > 1 or ans < 0:
        return "Not possible"
    return ans


for _ in xrange(t):
    print(do_one())