use std::fs::File;
use std::io;
use std::io::prelude::*;
use std::io::BufReader;

fn main() -> std::io::Result<()> {
    let f = File::open("1.in")?;
    // let f = io::stdin();
    let mut reader = BufReader::new(f);
    let mut line = String::new();
    reader.read_line(&mut line)?;
    let t: u32 = line.trim().parse().expect("");

    for i in 0..t {
        let mut input = String::new();
        reader.read_line(&mut input)?;
        let n: u32 = input.trim().parse().expect("");
        input = String::new();
        reader.read_line(&mut input)?;
        let rs: Vec<i32> = input
            .trim()
            .split(' ')
            .map(|s| s.parse().unwrap())
            .collect();
        println!("{}", n);
    }
    Ok(())
}
