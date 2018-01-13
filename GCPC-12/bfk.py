t = int(raw_input())


def do_one():
    sm, sc,si = map(int, raw_input().split())
    program = list(raw_input())
    mem = [0] * sm
    mem_idx = 0
    input_idx = 0
    chars = raw_input()
    mapping = {}
    l = []
    num_inst = 0
    for idx, c in enumerate(program):
        if c == '[':
            l.append(idx)
        if c == ']':
            pair = l.pop()
            mapping[idx] = pair
            mapping[pair] = idx
    idx = 0
    exited = set()
    s = []
    while idx < len(program) and num_inst < 50000001:
        num_inst += 1
        command = program[idx]
        if command == '-':
            mem[mem_idx] -= 1
            idx += 1
            if mem[mem_idx] < 0:
                mem[mem_idx] += 256
        elif command == '+':
            mem[mem_idx] += 1
            idx += 1
            if mem[mem_idx] > 255:
                mem[mem_idx] -= 256
        elif command == '<':
            mem_idx -= 1
            idx += 1
            if mem_idx < 0:
                mem_idx += len(mem)
        elif command == '>':
            mem_idx += 1
            idx += 1
            if mem_idx >= len(mem):
                mem_idx -= len(mem)
        elif command == '.':
            #print(chr(mem[mem_idx]))
            idx +=1
            continue
        elif command == ',':
            idx += 1
            mem[mem_idx] = ord(chars[input_idx]) if input_idx < len(chars) else 255
            input_idx += 1
        elif command == ']':
            new_idx = mapping[idx] + 1
            if mem[mem_idx] != 0:
                idx = new_idx
            else:
                exited.add(mapping[idx])
                exited.add(idx)
                idx += 1
                s.pop()
        elif command == '[':
            new_idx = mapping[idx] + 1
            if mem[mem_idx] == 0:
                idx = new_idx
            else:
                idx += 1
                s.append(idx-1)

    if idx != len(program):
        for _ in reversed(s):
            if _ not in exited:
                return "Loops {} {}".format(_, mapping[_])
        assert(False)
    return "Terminates"





for _ in xrange(t):
    print(do_one())