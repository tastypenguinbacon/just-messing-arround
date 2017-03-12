generator = (x ** 2 for x in range(1000) if x % 2 == 0)


for x in generator:
    print(x)
