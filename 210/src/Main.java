import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;

public class Main {

    enum StatementType {
        ASSIGNMENT,
        OUTPUT,
        BEGIN_MUTUAL_EXCLUSION,
        END_MUTUAL_EXCLUSION,
        STOP_EXCLUSION
    }    
    
    class Statement {
        StatementType type;
        String content;
        String variable;
        int value;
        int index;

        void parseType() {
            if (content.equals("lock")) {
                this.type = StatementType.BEGIN_MUTUAL_EXCLUSION;
            } else if (content.equals("unlock")) {
                this.type = StatementType.END_MUTUAL_EXCLUSION;
            } else if (content.equals("end")) {
                this.type = StatementType.STOP_EXCLUSION;
            } else if (content.contains("print")) {
                this.type = StatementType.OUTPUT;
                int begin = content.indexOf("print") + "print".length() + 1;
                String variable = content.substring(begin).trim();
                this.variable = variable;
            } else if (content.contains("=")) {
                this.type = StatementType.ASSIGNMENT;
                String splits[] = content.split("=");
                String variable = splits[0].trim();
                String value = splits[1].trim();
                this.variable = variable;
                this.value = Integer.valueOf(value);
            } else {
                assert (false);
            }
        }
    }

    enum ProgramStatus {
        RUNNING,
        BLOCKED,
        READY
    }
    
    class Program {
        int id;
        HashMap<String, Integer> variables;
        ArrayList<Statement> statements;
        int currentIndex;
        int quantum;
        ProgramStatus status;
        
        Program() {
            variables = new HashMap<>();
        }
        
        boolean finished() {
            return currentIndex == statements.size();
        }
    }
   
    void work() {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t > 0) {
            int programNums = sc.nextInt();
            int exeTimes[] = new int[5];
            for (int j = 0; j < 5; j++) {
                exeTimes[j] = sc.nextInt();
            }
            int quantum = sc.nextInt();
            ArrayList<Statement> statements = new ArrayList<>();
            int index = 0;
            while (sc.hasNextLine()) {
                String s = sc.nextLine();
                if (s.isEmpty()) {
                    continue;
                }
                Statement statement = new Statement();
                statement.content = s;
                statement.index = index;
                statement.parseType();
                statements.add(statement);
                index++;
            }
            int queueSize = 100;
            Queue<Integer> runningQueue = new ArrayBlockingQueue<>(queueSize);
            Queue<Integer> readyQueue = new ArrayBlockingQueue<>(queueSize);
            Queue<Integer> blockedQueues = new ArrayBlockingQueue<>(queueSize);

            ArrayList<Program> programs = new ArrayList<>();
            for (int j = 0; j < programNums; j++) {
                Program program = new Program();
                program.id = j + 1;
                program.statements = statements;
                program.currentIndex = 0;
                program.quantum = quantum;
                programs.add(program);
                runningQueue.add(program.id);
            }

            for (;;) {
                boolean allFinished = true;
                for (int i = 0; i < programNums; i++) {
                    Program program = programs.get(i);
                    if (!program.finished()) {
                        allFinished = false;
                        break;
                    }
                }
                if (allFinished) {
                    break;
                }
            }
            System.out.println();
            t--;            
        }
        sc.close();
    }

    public static void main(String[] args) throws Exception {
        FileInputStream inStream = null;
        PrintStream outStream = null;
        boolean isLocal = System.getProperty("os.name").equals("Mac OS X");        
        if (isLocal) {
            inStream = new FileInputStream("1.in");
            outStream = new PrintStream("1.out");
            System.setIn(inStream);
            System.setOut(outStream);
        }

        new Main().work();

        if (isLocal) {
            inStream.close();
            // outStream.close();
        }
    }
}
