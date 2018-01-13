t = int(raw_input())


def do_one():
    l,s = map(int,raw_input().split())
    l = [" " for _ in xrange(l)]
    valid = True
    for _ in xrange(s):
        p, s = raw_input().split()
        p = int(p)
        s = s.split('*')
        start = s[0]
        end = ""
        if len(s) == 2:
            end = s[1]
        for idx, c in enumerate(start):
            v =l[p+idx -1]
            if v == " " or v == c:
                l[p+idx -1] = c
            else:
                valid = False
        for idx, c in enumerate(reversed(end)):
            v = l[-1 * (idx+1)]
            if v == " " or v == c:
                l[-1 * (idx+1)] = c
            else:
                valid = False
    if " " in l:
        valid = False

    if not valid:
        return "IMPOSSIBLE"

    return ''.join(l)



for _ in xrange(t):
    print(do_one())