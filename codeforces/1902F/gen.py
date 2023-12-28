import random

n = random.randint(2, 10)
vertices = list(range(1, n + 1))
random.shuffle(vertices)

edges = [(vertices[i], vertices[i + 1]) for i in range(n - 1)]

a = [random.randint(0, 15) for _ in range(n)]

q = random.randint(1, 5)

queries = []
for _ in range(q):
    x = random.choice(vertices)
    y = random.choice(vertices)
    k = random.randint(0, 15)
    queries.append((x, y, k))

with open('in1.txt', 'w') as file:
    file.write(f"{n}\n")
    file.write(" ".join(map(str, a)) + "\n")
    for edge in edges:
        file.write(f"{edge[0]} {edge[1]}\n")
    file.write(f"{q}\n")
    for query in queries:
        file.write(f"{query[0]} {query[1]} {query[2]}\n")

print("Random test data generated and saved to 'in1.txt'.")
