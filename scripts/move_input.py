import shutil
from pathlib import Path

def move_input_files():
    # Define root path
    root = Path.cwd()
    uva_root = root / 'uva'

    # List of input files to move (based on provided find output)
    input_files = [
        'uva/src/main/resources/uva/p1589/3.in',
        'uva/src/main/resources/uva/p10000/3.in',
        'uva/src/main/resources/uva/p11624/3.in'
    ]

    for file_path in input_files:
        src_file = Path(file_path)
        if src_file.exists():
            # Extract problem number from the path (e.g., p1589 -> 1589)
            problem = src_file.parent.name[1:]  # Remove 'p' prefix
            target_dir = uva_root / problem
            target_file = target_dir / src_file.name

            # Create target directory if it doesn't exist
            target_dir.mkdir(parents=True, exist_ok=True)

            # Move the file
            shutil.move(str(src_file), str(target_file))
            print(f'Moved {src_file} to {target_file}')
        else:
            print(f'File {src_file} not found, skipping')

if __name__ == '__main__':
    move_input_files()
    print('Input file reorganization complete.')