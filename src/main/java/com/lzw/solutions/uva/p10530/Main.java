package com.lzw.solutions.uva.p10530;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    class Guess {
        int num;
        String response;

        Guess(int num, String response) {
            this.num = num;
            this.response = response;
        }
    }

    void solve() throws IOException {
        while (true) {
            String line = in.readLine();
            int n = Integer.parseInt(line);
            if (n == 0) {
                break;
            }
            String response = in.readLine();
            ArrayList<Guess> guesses = new ArrayList<>();
            do {
                guesses.add(new Guess(n, response));
                if (response.equals("right on")) {
                    break;
                }
                n = Integer.parseInt(in.readLine());
                response = in.readLine();
            } while (true);
            Guess lastGuess = guesses.get(guesses.size() - 1);
            boolean inconsistent = false;
            for (int i = 0; i < guesses.size() - 1; i++) {
                Guess guess = guesses.get(i);
                if ((guess.num < lastGuess.num && guess.response.equals("too low"))
                        || (guess.num > lastGuess.num && guess.response.equals("too high"))) {
                    continue;
                } else {
                    inconsistent = true;
                    break;
                }
            }
            if (inconsistent) {
                out.append("Stan is dishonest\n");
            } else {
                out.append("Stan may be honest\n");
            }
        }
    }

    void close() throws IOException {
        if (in != null) {
            in.close();
        }
        if (out != null) {
            out.flush();
            out.close();
        }
    }

    public static void main(String[] args) throws Exception {
        FileInputStream inStream = null;
        PrintStream outStream = null;
        boolean isLocal = System.getProperty("os.name").equals("Mac OS X");
        if (isLocal) {
            inStream = new FileInputStream("1.in");
            // outStream = new PrintStream("1.out");
            System.setIn(inStream);
            // System.setOut(outStream);
        }

        Main main = new Main();
        main.solve();
        main.close();

        if (isLocal) {
            inStream.close();
            // outStream.close();
        }
    }
}
