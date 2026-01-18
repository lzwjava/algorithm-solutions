#!/usr/bin/env python3
import sys
import re
import subprocess
import textwrap

def main():
    try:
        code = subprocess.check_output(['pbpaste']).decode('utf-8')
    except Exception as e:
        print(f"Failed to read pasteboard: {e}", file=sys.stderr)
        sys.exit(1)

    # Clean trailing whitespace from each line
    lines = [line.rstrip() for line in code.splitlines()]
    code = '\n'.join(lines) + '\n'

    # Dedent the code to fix indentation issues
    try:
        code = textwrap.dedent(code)
    except:
        pass  # Ignore if dedent fails

    # Check if it's Java code
    if 'public class' not in code:
        print('Not Java code')
        sys.exit(1)


    # Find and rename class (MainPro, MainPlus, etc. to Main)
    match = re.search(r'public\s+class\s+(\w+)', code)
    if match:
        old_class = match.group(1)
        if old_class.startswith('Main') and old_class != 'Main' and len(old_class) > 4:
            code = re.sub(r'\b' + re.escape(old_class) + r'\b', 'Main', code)
            print(f'Renamed {old_class} to Main')

    # Copy cleaned code back to pasteboard
    try:
        subprocess.run(['pbcopy'], input=code.encode('utf-8'), check=True)
    except Exception as e:
        print(f"Failed to write to pasteboard: {e}", file=sys.stderr)
        sys.exit(1)

    print('The code is adjusted, please submit')

if __name__ == '__main__':
    main()
