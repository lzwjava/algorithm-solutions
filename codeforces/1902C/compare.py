import subprocess

subprocess.run(["python", "gen.py"], check=True)

script_to_run = "other_script.py"

try:
    result = subprocess.run(["python", script_to_run], text=True, stdout=subprocess.PIPE, check=True)
except subprocess.CalledProcessError as e:
    print(f"Error: {e}")
else:

    output = result.stdout

    output_file = "output.txt"

    with open(output_file, "w") as file:
        file.write(output)

    print(f"Output saved to {output_file}")
