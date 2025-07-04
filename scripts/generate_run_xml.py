import argparse
import os
import xml.etree.ElementTree as ET

def generate_run_xml(problem_number):
    # Define paths
    script_dir = "scripts"
    run_dir = ".run"
    template_file = os.path.join(script_dir, "Main.run.xml")
    output_file = os.path.join(run_dir, f"MainP{problem_number}.run.xml")

    # Ensure .run directory exists
    os.makedirs(run_dir, exist_ok=True)

    # Read the template XML file
    with open(template_file, 'r') as file:
        xml_content = file.read()

    # Replace p140 with the new problem number
    new_xml_content = xml_content.replace('p140', f'p{problem_number}')
    new_xml_content = new_xml_content.replace('name="Main"', f'name="MainP{problem_number}"')

    # Write the new XML file
    with open(output_file, 'w') as file:
        file.write(new_xml_content)

    print(f"Generated {output_file} for problem p{problem_number}")

def main():
    parser = argparse.ArgumentParser(description="Generate a new Main run XML file for a given problem number.")
    parser.add_argument("problem_number", type=str, help="Problem number (e.g., 138 for p138)")
    args = parser.parse_args()

    generate_run_xml(args.problem_number)

if __name__ == "__main__":
    main()