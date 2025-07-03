package com.lzw.solutions.uva.p119;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }
    
    class Giving {
        String from;
        int money;
        int toCount;
        String[] toNames;

        Giving(String from, int money, int toCount, String[] toNames) {
            this.from = from;
            this.money = money;
            this.toCount = toCount;
            this.toNames = toNames;
        }
    }
    
    Integer getNetWorth(HashMap<String, Integer> map, String name) {
        Integer netWorth = map.get(name);
        if (netWorth == null) {
            netWorth = 0;
        }
        return netWorth;
    }

    void setNetWorth(HashMap<String, Integer> map, String name, Integer v) {
        map.put(name, v);
    }
   
    void solve() throws IOException {
        String line = in.readLine();        
        while (true) {        
            int n = Integer.parseInt(line);
            String[] names = new String[n];
            line = in.readLine();
            StringTokenizer st = new StringTokenizer(line);
            for (int i = 0; i < n; i++) {
                names[i] = st.nextToken();
            }
            ArrayList<Giving> givings = new ArrayList<>();
            while (true) {
                line = in.readLine();
                if (line == null) {
                    break;
                }
                st = new StringTokenizer(line);
                int count = st.countTokens();
                if (count == 1) {
                    break;
                }
                String from = st.nextToken();
                int money = Integer.parseInt(st.nextToken());
                int toCount = Integer.parseInt(st.nextToken());
                String[] toNames = new String[toCount];
                for (int i = 0; i < toCount; i++) {
                    String toName = st.nextToken();
                    toNames[i] = toName;
                }
                Giving giving = new Giving(from, money, toCount, toNames);
                givings.add(giving);
            }
            HashMap<String, Integer> map = new HashMap<>();
            for (Giving giving : givings) {
                Integer netWorth = getNetWorth(map, giving.from);                
                int eachMoney = 0;
                if (giving.toCount>0){
                    eachMoney= giving.money / giving.toCount;
                }
                int totalMoney = eachMoney * giving.toCount;

                netWorth -= totalMoney;
                setNetWorth(map, giving.from, netWorth);

                for (int i = 0; i < giving.toCount; i++) {
                    String toName = giving.toNames[i];
                    Integer toNetWorth = getNetWorth(map, toName);
                    toNetWorth += eachMoney;
                    setNetWorth(map, toName, toNetWorth);
                }
            }
            for (String name : names) {
                Integer netWorth = getNetWorth(map, name);
                out.append(String.format("%s %d\n", name, netWorth));
            }
            if (line == null) {
                break;
            }
            out.append('\n');
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
