string = """qwertyuiop
asdfghjkl
zxcvbnm"""

mapping = {}
lines = string.split('\n')
for idx, c in enumerate(lines):
    for i, ch in enumerate(c):
        mapping[ch] = (idx, i)

t = int(raw_input())


def distance(other, this):
    x, y = mapping[other]
    x2, y2 = mapping[this]
    return abs(x - x2) + abs(y - y2)


def find_d(other_word, st):
    s = 0
    for other, this in zip(other_word, st):
        s += distance(other, this)
    return s, other_word


def do_one():
    st, l = raw_input().split()
    l = int(l)
    results = []
    for other in xrange(l):
        other_word = raw_input()
        results.append(find_d(other_word, st))

    results.sort()
    for result in results:
        print(result[1] + " " + str(result[0]))
for _ in xrange(t):
    do_one()
