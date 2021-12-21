use std::fs::File;
use std::io::prelude::*;
use std::io::BufReader;

fn solve(f: &mut dyn Read) {
    let mut reader = BufReader::new(f);
    let mut line = String::new();
    reader.read_line(&mut line).unwrap();
    let t: u32 = line.trim().parse().expect("");

    for i in 0..t {
        let mut input = String::new();
        reader.read_line(&mut input).unwrap();
        let n: u32 = input.trim().parse().expect("");
        input = String::new();
        reader.read_line(&mut input).unwrap();
        let rs: Vec<u32> = input
            .trim()
            .split(' ')
            .map(|s| s.parse().unwrap())
            .collect();
        let mut c: u32 = 0;
        for r in rs.iter() {
            if (*r == 1 || *r == 3) {
                c = c + 1;
            }
        }
        println!("{}", c);
    }
}

fn main() {
    #[cfg(ONLINE_JUDGE)]
    solve(&mut io::stdin());

    #[cfg(not(ONLINE_JUDGE))]
    solve(&mut File::open("1.in").unwrap());
}
