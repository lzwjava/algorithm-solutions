import random
import string
import sys

n = 100194

strings = []

sys.stdout = open('in.txt', 'w')


def generate_random_string(length):
    return ''.join(random.choice(string.ascii_lowercase) for _ in range(length))


for _ in range(n):
    max_length = 100
    length = random.randint(1, max_length)

    random_str = generate_random_string(length)
    strings.append(random_str)

print(n)
for s in strings:
    print(s)
