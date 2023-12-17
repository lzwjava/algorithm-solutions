import random
import sys

sys.stdout = open('in.txt', 'w')

t = 10

print(t)

for _ in range(t):

    n = random.randint(10, 30)

    print(n)

    start_range = -200
    end_range = 200
    batch_size = n

    if batch_size <= end_range - start_range + 1:
        random_numbers = random.sample(range(start_range, end_range + 1), batch_size)
        for i in range(batch_size):
            if i != 0:
                print(' ', end='')
            print(random_numbers[i], end='')
        print('')
    else:
        print("Batch size is larger than the range of distinct numbers.")
