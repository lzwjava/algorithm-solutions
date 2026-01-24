#!/usr/bin/env python3
import sys
import os
import shutil
import subprocess

def main():
    if len(sys.argv) != 3:
        print("Usage: python create_solution.py <platform> <problem_id>")
        print("Example: python create_solution.py uva 12166")
        print("platform: uva or codeforces")
        sys.exit(1)

    platform = sys.argv[1]
    problem_id = sys.argv[2].strip()

    if platform not in ['uva', 'codeforces']:
        print("Invalid platform. Use 'uva' or 'codeforces'.")
        sys.exit(1)

    # Assume running from project root
    base_dir = os.getcwd()
    src_java = os.path.join(base_dir, 'src', 'main', 'java', 'com', 'lzw', 'solutions', 'sample', 'pjava_sample_buf', 'Main.java')

    if not os.path.exists(src_java):
        print(f"Template not found: {src_java}")
        sys.exit(1)

    target_java_dir = os.path.join(base_dir, 'src', 'main', 'java', 'com', 'lzw', 'solutions', platform, f'p{problem_id}')
    target_java = os.path.join(target_java_dir, 'Main.java')

    os.makedirs(target_java_dir, exist_ok=True)

    # Copy and update package
    shutil.copy2(src_java, target_java)

    with open(target_java, 'r') as f:
        content = f.read()

    old_package = 'com.lzw.solutions.sample.pjava_sample_buf'
    new_package = f'com.lzw.solutions.{platform}.p{problem_id}'
    content = content.replace(old_package, new_package)

    with open(target_java, 'w') as f:
        f.write(content)

    # Resources dir for 1.in
    resources_dir = os.path.join(base_dir, 'src', 'main', 'resources', platform, f'p{problem_id}')
    input_file = os.path.join(resources_dir, '1.in')
    os.makedirs(resources_dir, exist_ok=True)

    # Read from macOS clipboard (pbpaste)
    try:
        clipboard_content = subprocess.check_output(['pbpaste']).decode('utf-8').strip()
        if clipboard_content:
            with open(input_file, 'w') as f:
                f.write(clipboard_content)
            print(f"[OK] Created {target_java}")
            print(f"[OK] Created {input_file} from clipboard")
        else:
            print(f"[WARN]  Created {target_java}, but clipboard empty - add 1.in manually")
    except subprocess.CalledProcessError:
        print(f"[ERR] Failed to read clipboard. Created {target_java}, add {input_file} manually.")
    except Exception as e:
        print(f"[ERR] Error with clipboard: {e}. Created {target_java}, add {input_file} manually.")

if __name__ == '__main__':
    main()
