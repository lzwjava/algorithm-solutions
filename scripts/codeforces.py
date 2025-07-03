import shutil
from pathlib import Path

def add_package_declaration(java_file, package):
    """Add package declaration to the top of a Java file if not already present."""
    with open(java_file, 'r', encoding='utf-8') as f:
        content = f.read()

    if not content.strip().startswith('package '):
        new_content = f'package {package};\n\n{content}'
        with open(java_file, 'w', encoding='utf-8') as f:
            f.write(new_content)
        print(f'Added package declaration to {java_file}')
    else:
        print(f'Package declaration already exists in {java_file}')

def reorganize_codeforces():
    # Define root paths
    root = Path.cwd()
    src_java = root / 'src' / 'main' / 'java' / 'com' / 'algorithm' / 'solutions' / 'codeforces'
    src_resources = root / 'src' / 'main' / 'resources' / 'codeforces'
    codeforces_root = root / 'codeforces'

    # Create codeforces directory structure
    src_java.mkdir(parents=True, exist_ok=True)
    src_resources.mkdir(exist_ok=True)
    print('Created codeforces Maven directory structure')

    if codeforces_root.exists():
        for problem_dir in codeforces_root.iterdir():
            if problem_dir.is_dir():
                problem = problem_dir.name
                target_java_dir = src_java / f'p{problem}'
                target_resource_dir = src_resources / f'p{problem}'
                target_java_dir.mkdir(exist_ok=True)
                target_resource_dir.mkdir(exist_ok=True)

                # Handle all *.java files in src directory
                src_dir = problem_dir / 'src'
                if src_dir.exists():
                    for java_file in src_dir.glob('*.java'):
                        target_java = target_java_dir / java_file.name
                        shutil.move(str(java_file), str(target_java))
                        print(f'Moved {java_file} to {target_java}')
                        add_package_declaration(target_java, f'com.algorithm.solutions.codeforces.p{problem}')
                else:
                    print(f'No src directory found in {problem_dir}')

                # Handle all *.in files in problem directory
                for input_file in problem_dir.glob('*.in'):
                    target_input = target_resource_dir / input_file.name
                    shutil.move(str(input_file), str(target_input))
                    print(f'Moved {input_file} to {target_input}')

                # Clean up src directory if empty
                if src_dir.exists() and not any(src_dir.iterdir()):
                    src_dir.rmdir()
                    print(f'Removed empty directory {src_dir}')

                # Clean up problem directory if empty
                if not any(problem_dir.iterdir()):
                    problem_dir.rmdir()
                    print(f'Removed empty directory {problem_dir}')
    else:
        print('codeforces directory not found, skipping')

if __name__ == '__main__':
    reorganize_codeforces()
    print('Codeforces reorganization complete.')