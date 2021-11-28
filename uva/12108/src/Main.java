import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    enum State {
        AWAKE,
        SLEEP       
    };

    class Student {
        int awakeDuration;
        int sleepDuration;
        int initialPosition;
        State state; 
        int timeIntoState;  // at this time, into the state
    }
   
    void work() {
        Scanner sc = new Scanner(System.in);
        int num = 1;
        for (;;) {
            int n = sc.nextInt();
            if (n == 0) {
                break;
            }
            ArrayList<Student> students = new ArrayList<>();
            int maxState = 1;
            for (int i = 0; i < n; i++) {
                Student s = new Student();
                s.awakeDuration = sc.nextInt();
                s.sleepDuration = sc.nextInt();
                s.initialPosition = sc.nextInt();
                if (s.initialPosition <= s.awakeDuration) {
                    s.state = State.AWAKE;
                    s.timeIntoState = 1- s.initialPosition+1;
                } else {
                    s.state = State.SLEEP;
                    int b = s.initialPosition - s.awakeDuration;
                    s.timeIntoState = 1 - b + 1;
                }
                maxState *= (s.awakeDuration + s.sleepDuration);
                students.add(s);
            }
            int t = 1;
            int maxTime = maxState*10;
            for (; t < maxTime; t++) {
                // for (int i = 0; i < n; i++) {
                //     System.out.print(String.format("%s ", students.get(i).state.toString()));
                // }
                // System.out.println();

                // t finish , cal t + 1
                int sleepCount = 0, awakeCount = 0;
                for (int i = 0; i < n; i++) {
                    Student s = students.get(i);
                    if (s.state == State.AWAKE) {
                        awakeCount++;
                    } else {
                        sleepCount++;
                    }
                }
                if (awakeCount == n) {
                    break;
                }
                for (int i = 0; i < n; i++) {
                    Student s = students.get(i);
                    int duration = t - s.timeIntoState + 1;
                    int stateDuration;
                    if (s.state == State.AWAKE) {
                        stateDuration = s.awakeDuration;
                    } else {
                        stateDuration = s.sleepDuration;
                    }
                    if (duration == stateDuration) {
                        // change state
                        if (s.state == State.AWAKE) {
                            // change to sleep
                            if (sleepCount > awakeCount) {
                                s.state = State.SLEEP;
                                s.timeIntoState = t + 1;
                            } else {
                                s.state = State.AWAKE;
                                s.timeIntoState = t + 1;
                            }
                        } else {                            
                            // sleep, should wake up
                            s.state = State.AWAKE;
                            s.timeIntoState = t + 1;
                        }
                    }
                }
            }
            System.out.print(String.format("Case %d: ", num));
            if (t < maxTime) {
                System.out.println(t);
            } else {
                System.out.println(-1);
            }
            num++;
        }
        sc.close();
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

        new Main().work();

        if (isLocal) {
            inStream.close();
            // outStream.close();
        }
    }
}
