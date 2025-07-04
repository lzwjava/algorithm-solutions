package com.lzw.solutions.uva.p12250;

import java.util.Scanner;

public class Main {

    void solve() {
        Scanner sc = new Scanner(System.in);
        int caseNum = 1;
        while (true) {
            String s = sc.next();
            if (s.equals("#")) {
                break;
            }
            String lang = "";
            switch (s) {
                case "HELLO":
                    lang = "ENGLISH";
                    break;
                case "HOLA":
                    lang = "SPANISH";
                    break;
                case "HALLO":
                    lang = "GERMAN";
                    break;
                case "BONJOUR":
                    lang = "FRENCH";
                    break;
                case "CIAO":
                    lang = "ITALIAN";
                    break;
                case "ZDRAVSTVUJTE":
                    lang = "RUSSIAN";
                    break;
                default:
                    lang = "UNKNOWN";
                    break;
            }
            System.out.println(String.format("Case %d: %s", caseNum, lang));
            caseNum++;
        }
        sc.close();
    }

    public static void main(String[] args) throws Exception {

        new Main().solve();
    }
}
