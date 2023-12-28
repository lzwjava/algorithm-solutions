import subprocess


def gen():
    subprocess.run(["python", "gen.py"], check=True)


def run(args, output_file):
    try:
        result = subprocess.run(args, text=True, stdout=subprocess.PIPE, check=True)
    except subprocess.CalledProcessError as e:
        print(f"Error: {e}")
    else:

        output = result.stdout

        with open(output_file, "w") as file:
            file.write(output)

        print(f"Output saved to {output_file}")


def diff() -> bool:
    file1 = "out/1.txt"
    file2 = "out/2.txt"

    command = ["diff", file1, file2]

    try:

        result = subprocess.run(command, text=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE, check=True)

        stdout = result.stdout
        stderr = result.stderr

        if stdout:
            print("Differences found:")
            print(stdout)
            return True
        else:
            print("No differences found.")
            return False
    except subprocess.CalledProcessError as e:
        print(f"Error: {e}")
        return True


while True:
    gen()

    run(["python", "simple.py"], "out/1.txt")
    run(["./a.out"], "out/2.txt")

    has_diff = diff()
    if has_diff:
        break

print('end')
