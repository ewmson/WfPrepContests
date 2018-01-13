t = int(raw_input())


def do_one():
    pizzas = int(raw_input())
    mapping = {}
    mapping2 = {}
    for pizza in xrange(pizzas):
        raw_input()
        p1 = set(raw_input().split()[1:])
        p2 = set(raw_input().split()[1:])
        for word in p1:
            if word in mapping:
                mapping[word] = p2.intersection(mapping[word])
            else:
                mapping[word] = p2

        for word in p2:
            if word in mapping2:
                mapping2[word] = p1.intersection(mapping2[word])
            else:
                mapping2[word] = p1
    l = []
    for word in mapping:
        for w in mapping[word]:
            if w in mapping2 and word in mapping2[w]:
                l.append((word, w))
    l.sort()
    for pair in l:
        print '(' + pair[0]+',', pair[1]+')'
    print


for _ in xrange(t):
    do_one()
