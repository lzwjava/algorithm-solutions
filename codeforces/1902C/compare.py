import subprocess

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


run(["python", "main.py"], "out/1.txt")
run(["./a.out"], "out/2.txt")
