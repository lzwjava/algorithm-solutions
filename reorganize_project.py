import os
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

def reorganize_project():
    # Define root paths
    root = Path.cwd()
    src_java = root / 'src' / 'main' / 'java' / 'com' / 'algorithm' / 'solutions'
    src_resources = root / 'src' / 'main' / 'resources'

    # Create Maven directory structure
    for platform in ['nowcoder', 'uva', 'lintcode', 'leetcode', 'hacker_rank', 'codeforces']:
        (src_java / platform).mkdir(parents=True, exist_ok=True)
        (src_resources / platform).mkdir(exist_ok=True)
    print('Created Maven directory structure')

    # Move nowcoder files
    nowcoder_root = root / 'nowcoder'
    if nowcoder_root.exists():
        for java_file in nowcoder_root.glob('*.java'):
            target_file = src_java / 'nowcoder' / java_file.name
            shutil.move(str(java_file), str(target_file))
            print(f'Moved {java_file} to {target_file}')
            add_package_declaration(target_file, 'com.algorithm.solutions.nowcoder')

        # Remove empty nowcoder directory
        if not any(nowcoder_root.iterdir()):
            nowcoder_root.rmdir()
            print('Removed empty directory nowcoder')
    else:
        print('nowcoder directory not found, skipping')

    # Move uva files
    uva_root = root / 'uva'
    if uva_root.exists():
        for problem_dir in uva_root.iterdir():
            if problem_dir.is_dir() and problem_dir.name != 'src':
                problem = problem_dir.name
                target_java_dir = src_java / 'uva' / f'p{problem}'
                target_resource_dir = src_resources / 'uva' / f'p{problem}'
                target_java_dir.mkdir(exist_ok=True)
                target_resource_dir.mkdir(exist_ok=True)

                main_java = problem_dir / 'src' / 'Main.java'
                if main_java.exists():
                    target_java = target_java_dir / 'Main.java'
                    shutil.move(str(main_java), str(target_java))
                    print(f'Moved {main_java} to {target_java}')
                    add_package_declaration(target_java, f'com.algorithm.solutions.uva.p{problem}')
                else:
                    print(f'No Main.java found in {problem_dir}/src')

                for input_file in problem_dir.glob('*.in'):
                    target_input = target_resource_dir / input_file.name
                    shutil.move(str(input_file), str(target_input))
                    print(f'Moved {input_file} to {target_input}')

                src_dir = problem_dir / 'src'
                if src_dir.exists() and not any(src_dir.iterdir()):
                    src_dir.rmdir()
                    print(f'Removed empty directory {src_dir}')

                if not any(problem_dir.iterdir()):
                    problem_dir.rmdir()
                    print(f'Removed empty directory {problem_dir}')
    else:
        print('uva directory not found, skipping')

    # Move lintcode files
    lintcode_root = root / 'lintcode'
    if lintcode_root.exists():
        for problem_dir in lintcode_root.iterdir():
            if problem_dir.is_dir():
                problem = problem_dir.name
                target_java_dir = src_java / 'lintcode' / f'p{problem}'
                target_java_dir.mkdir(exist_ok=True)

                src_dir = problem_dir / 'src' / 'com' / 'lintcode'
                if src_dir.exists():
                    for java_file in src_dir.glob('*.java'):
                        target_file = target_java_dir / java_file.name
                        shutil.move(str(java_file), str(target_file))
                        print(f'Moved {java_file} to {target_file}')
                        add_package_declaration(target_file, f'com.algorithm.solutions.lintcode.p{problem}')

                src_parent = problem_dir / 'src'
                if src_parent.exists() and not any(src_parent.rglob('*')):
                    shutil.rmtree(src_parent)
                    print(f'Removed empty directory {src_parent}')

                if not any(problem_dir.iterdir()):
                    problem_dir.rmdir()
                    print(f'Removed empty directory {problem_dir}')
    else:
        print('lintcode directory not found, skipping')

    # Move leetcode files
    leetcode_root = root / 'leetcode'
    if leetcode_root.exists():
        target_resource_dir = src_resources / 'leetcode'

        # Move top-level Python files
        for py_file in leetcode_root.glob('*.py'):
            if py_file.name != '__pycache__':
                target_file = target_resource_dir / py_file.name
                shutil.move(str(py_file), str(target_file))
                print(f'Moved {py_file} to {target_file}')

        # Handle combine_two_tables subdirectory
        combine_dir = leetcode_root / 'combine_two_tables'
        if combine_dir.exists():
            target_combine_dir = target_resource_dir / 'combine_two_tables'
            target_combine_dir.mkdir(exist_ok=True)
            for file in combine_dir.glob('*'):
                if file.suffix in ['.py', '.csv']:
                    target_file = target_combine_dir / file.name
                    shutil.move(str(file), str(target_file))
                    print(f'Moved {file} to {target_file}')

            if not any(combine_dir.iterdir()):
                combine_dir.rmdir()
                print(f'Removed empty directory {combine_dir}')

        # Remove __pycache__ and empty leetcode directory
        pycache_dir = leetcode_root / '__pycache__'
        if pycache_dir.exists():
            shutil.rmtree(pycache_dir)
            print(f'Removed __pycache__ directory')

        if not any(leetcode_root.iterdir()):
            leetcode_root.rmdir()
            print('Removed empty directory leetcode')
    else:
        print('leetcode directory not found, skipping')

    # Move hacker_rank files
    hacker_rank_root = root / 'hacker_rank'
    if hacker_rank_root.exists():
        target_resource_dir = src_resources / 'hacker_rank'
        for sql_file in hacker_rank_root.glob('*.sql'):
            target_file = target_resource_dir / sql_file.name
            shutil.move(str(sql_file), str(target_file))
            print(f'Moved {sql_file} to {target_file}')

        if not any(hacker_rank_root.iterdir()):
            hacker_rank_root.rmdir()
            print('Removed empty directory hacker_rank')
    else:
        print('hacker_rank directory not found, skipping')

if __name__ == '__main__':
    reorganize_project()
    print('Reorganization complete.')