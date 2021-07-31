import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;

public class Main {

    class RunSystem {
        final static int queueSize = 100;
        HashMap<String, Integer> variables;
        Program lockingProgram;
        LinkedList<Integer> readyQueue;
        Queue<Integer> blockedQueues;

        RunSystem() {
            this.variables = new HashMap<>();
            this.lockingProgram = null;
            this.readyQueue = new LinkedList<>();
            this.blockedQueues = new ArrayBlockingQueue<>(queueSize);
        }
    }

    enum StatementType {
        ASSIGNMENT, OUTPUT, BEGIN_MUTUAL_EXCLUSION, END_MUTUAL_EXCLUSION, STOP_EXCLUSION
    }    

    enum RunResult {
        SUCCESSS,
        LOCKING,
        UNLOCKING,
        STOP
    }
    
    class Statement {
        StatementType type;
        String content;
        String variable;
        int value;
        int index;
        int time;
        RunSystem system;

        Statement(RunSystem system) {
            this.system = system;
        }

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

        void setTime(int times[]) {
            this.time = times[this.type.ordinal()];
        }

        RunResult run(Program program) {
            if (this.type == StatementType.ASSIGNMENT) {
                this.system.variables.put(this.variable, this.value);
            } else if (this.type == StatementType.BEGIN_MUTUAL_EXCLUSION) {
                if (this.system.lockingProgram != null) {
                    return RunResult.LOCKING;
                } else {
                    this.system.lockingProgram = program;
                }           
            } else if (this.type == StatementType.END_MUTUAL_EXCLUSION) {
                assert (this.system.lockingProgram != null);
                this.system.lockingProgram = null;
                return RunResult.UNLOCKING;
            } else if (this.type == StatementType.OUTPUT) {
                Integer v = this.system.variables.get(this.variable);
                if (v == null) {
                    v = 0;
                }
                System.out.println((program.id + 1) + ": " + v);                
            } else if (this.type == StatementType.STOP_EXCLUSION) {
                return RunResult.STOP;
            }
            return RunResult.SUCCESSS;
        }
    }

    enum ProgramStatus {
        RUNNING,
        BLOCKED,
        READY,
        STOPPED
    }
    
    class Program {
        int id;        
        ArrayList<Statement> statements;
        int currentIndex; // not running now, will run 
        int quantum;
        ProgramStatus status;
        RunSystem system;

        Program(RunSystem system) {
            this.system = system;            
        }
        
        void run() {
            if (this.status == ProgramStatus.STOPPED) {
                return;
            }
            this.status = ProgramStatus.RUNNING;
            int currentQuantum = this.quantum;
            while (currentQuantum > 0) {
                Statement statement = statements.get(this.currentIndex);
                RunResult result = statement.run(this);
                currentQuantum -= statement.time;
                if (result == RunResult.SUCCESSS) {
                    this.currentIndex++;
                    this.status = ProgramStatus.RUNNING;
                } else if (result == RunResult.LOCKING) {
                    this.system.blockedQueues.add(this.id);
                    this.status = ProgramStatus.BLOCKED;
                    break;
                } else if (result == RunResult.UNLOCKING) {
                    this.status = ProgramStatus.RUNNING;
                    this.currentIndex++;
                    Integer programId = this.system.blockedQueues.poll();
                    if (programId != null) {
                        this.system.readyQueue.addFirst(programId);
                    }
                } else if (result == RunResult.STOP) {
                    this.currentIndex++;
                    this.status = ProgramStatus.STOPPED;
                    break;
                }
            }
            if (this.status != ProgramStatus.STOPPED && 
                this.status != ProgramStatus.BLOCKED) {
                // into the ready queue
                this.status = ProgramStatus.READY;
                this.system.readyQueue.add(this.id);
            }
        }
    }
   
    void work() {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t > 0) {
            RunSystem runSystem = new RunSystem();

            int programNums = sc.nextInt();
            int exeTimes[] = new int[5];
            for (int j = 0; j < 5; j++) {
                exeTimes[j] = sc.nextInt();
            }
            int quantum = sc.nextInt();
            
            ArrayList<Program> programs = new ArrayList<>();

            for (int i = 0; i < programNums; i++) {
                Program program = new Program(runSystem);
                program.id = i;
                program.currentIndex = 0;
                program.quantum = quantum;
                programs.add(program);
                runSystem.readyQueue.add(program.id);

                ArrayList<Statement> statements = new ArrayList<>();
                int index = 0;
                while (true) {
                    String s = sc.nextLine();
                    if (s.isEmpty()) {
                        continue;
                    }
                    Statement statement = new Statement(runSystem);
                    statement.content = s;
                    statement.index = index;
                    statement.parseType();
                    statement.setTime(exeTimes);
                    statements.add(statement);
                    index++;
                    if (statement.type == StatementType.STOP_EXCLUSION) {
                        break;
                    }
                }
                program.statements = statements;                
            }
                     
            for (;;) {
                Integer programId = runSystem.readyQueue.peek();
                if (programId == null) {
                    break;
                }
                runSystem.readyQueue.remove();                                
                Program program = programs.get(programId);
                program.run();
            }
            t--;
            if (t > 0) {
                System.out.println();
            }
        }
        sc.close();
    }

    public static void main(String[] args) throws Exception {
        FileInputStream inStream = null;
        PrintStream outStream = null;
        boolean isLocal = System.getProperty("os.name").equals("Mac OS X");        
        if (isLocal) {
            inStream = new FileInputStream("1.in");
            // outStream = new PrintStream("2.out");
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
