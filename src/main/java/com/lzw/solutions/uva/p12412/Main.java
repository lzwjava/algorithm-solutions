package com.lzw.solutions.uva.p12412;

import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    class Student {
        String sid;
        int cid;
        String name;
        int scores[] = new int[4];
        int total;
        double average;
        int rank;
        int order;

        @Override
        public String toString() {
            return String.format(
                    "%d %s %d %s %d %d %d %d %d %.2f",
                    this.rank,
                    this.sid,
                    this.cid,
                    this.name,
                    this.scores[0],
                    this.scores[1],
                    this.scores[2],
                    this.scores[3],
                    this.total,
                    this.average);
        }
    }

    class Course {
        int courseId;
        double average = 0;
        double total = 0;
        int passed = 0;
        int failed = 0;
        int studentCount = 0;

        public String name() {
            if (this.courseId == 0) {
                return "Chinese";
            } else if (this.courseId == 1) {
                return "Mathematics";
            } else if (this.courseId == 2) {
                return "English";
            } else if (this.courseId == 3) {
                return "Programming";
            }
            return "";
        }
    }

    boolean isNameNotSid(String input) {
        return input.length() > 1 && Character.isUpperCase(input.charAt(0));
    }

    void work() {

        Scanner sc = new Scanner(System.in);

        StringBuilder sb = new StringBuilder();
        sb.append("Welcome to Student Performance Management System (SPMS).\n");
        sb.append("\n");
        sb.append("1 - Add\n");
        sb.append("2 - Remove\n");
        sb.append("3 - Query\n");
        sb.append("4 - Show ranking\n");
        sb.append("5 - Show Statistics\n");
        sb.append("0 - Exit\n");
        String prompt = sb.toString();

        HashMap<String, Student> students = new HashMap<>();

        int order = 0;

        for (; ; ) {
            System.out.println(prompt);
            int code = sc.nextInt();
            if (code == 0) {
                break;
            } else if (code == 1) {
                for (; ; ) {
                    System.out.println("Please enter the SID, CID, name and four scores. Enter 0 to finish.");
                    String sid = sc.next();
                    if (sid.equals("0")) {
                        break;
                    } else {
                        Student s = new Student();
                        s.sid = sid;
                        s.cid = sc.nextInt();
                        s.name = sc.next();
                        s.scores[0] = sc.nextInt();
                        s.scores[1] = sc.nextInt();
                        s.scores[2] = sc.nextInt();
                        s.scores[3] = sc.nextInt();

                        if (students.get(s.sid) != null) {
                            System.out.println("Duplicated SID.");
                        } else {
                            s.total = 0;
                            for (int i = 0; i < 4; i++) {
                                s.total += s.scores[i];
                            }
                            s.average = s.total * 1.0 / 4;
                            s.order = order;
                            order++;
                            students.put(s.sid, s);
                        }
                    }
                }
            } else if (code == 2) {
                for (; ; ) {
                    System.out.println("Please enter SID or name. Enter 0 to finish.");
                    String input = sc.next();
                    if (input.equals("0")) {
                        break;
                    } else {
                        int removeCount = 0;
                        if (isNameNotSid(input)) {
                            ArrayList<Student> values = new ArrayList<>();
                            values.addAll(students.values());
                            for (Student s : values) {
                                if (s.name.equals(input)) {
                                    Student v = students.remove(s.sid);
                                    if (v != null) {
                                        removeCount++;
                                    }
                                }
                            }
                        } else {
                            // sid
                            Student v = students.remove(input);
                            if (v != null) {
                                removeCount++;
                            }
                        }
                        System.out.println(String.format("%d student(s) removed.", removeCount));
                    }
                }
            } else if (code == 3) {
                Collection<Student> values = students.values();
                ArrayList<Student> studentList = new ArrayList<>();
                studentList.addAll(values);
                studentList.sort(new Comparator<Student>() {

                    @Override
                    public int compare(Student o1, Student o2) {
                        return o2.total - o1.total;
                    }
                });

                int lastRank = 0;
                int lastTotal = -1;
                int pos = 0;
                for (Student s : studentList) {
                    if (s.total != lastTotal) {
                        s.rank = pos + 1;
                        lastRank = s.rank;
                        lastTotal = s.total;
                    } else {
                        s.rank = lastRank;
                    }
                    pos++;
                }

                studentList.sort(new Comparator<Student>() {
                    @Override
                    public int compare(Main.Student o1, Main.Student o2) {
                        return o1.order - o2.order;
                    }
                });

                for (; ; ) {
                    System.out.println("Please enter SID or name. Enter 0 to finish.");
                    String input = sc.next();
                    if (input.equals("0")) {
                        break;
                    } else {
                        if (isNameNotSid(input)) {
                            // name
                            ArrayList<Student> sorted = new ArrayList<>();
                            for (Student s : studentList) {
                                if (s.name.equals(input)) {
                                    sorted.add(s);
                                }
                            }
                            for (Student s : sorted) {
                                System.out.println(s.toString());
                            }
                        } else {
                            Student s = students.get(input);
                            if (s != null) {
                                System.out.println(s.toString());
                            }
                        }
                    }
                }
            } else if (code == 4) {
                System.out.println("Showing the ranklist hurts students' self-esteem. Don't do that.");
            } else if (code == 5) {
                System.out.println("Please enter class ID, 0 for the whole statistics.");

                int cid = sc.nextInt();
                Course courses[] = new Course[4];
                for (int i = 0; i < 4; i++) {
                    courses[i] = new Course();
                    courses[i].courseId = i;
                }
                int passed[] = new int[5];
                for (Student s : students.values()) {
                    if (s.cid == cid || cid == 0) {
                        int pass = 0;
                        for (int i = 0; i < 4; i++) {
                            int score = s.scores[i];
                            Course c = courses[i];
                            c.studentCount++;
                            c.total += score;
                            c.average = c.total * 1.0 / c.studentCount;
                            if (score >= 60) {
                                c.passed++;
                                pass++;
                            } else {
                                c.failed++;
                            }
                        }
                        passed[pass]++;
                    }
                }
                int totalPassed[] = Arrays.copyOf(passed, passed.length);
                int total = 0;
                for (int i = 4; i >= 1; i--) {
                    total += passed[i];
                    totalPassed[i] = total;
                }
                for (int i = 0; i < 4; i++) {
                    StringBuilder s = new StringBuilder();
                    Course c = courses[i];
                    s.append(c.name());
                    s.append("\n");
                    s.append("Average Score: ");
                    s.append(String.format("%.2f", c.average));
                    s.append("\n");
                    s.append("Number of passed students: ");
                    s.append(c.passed);
                    s.append("\n");
                    s.append("Number of failed students: ");
                    s.append(c.failed);
                    s.append("\n");
                    System.out.println(s.toString());
                }
                System.out.println("Overall:");
                System.out.println(String.format("Number of students who passed all subjects: %d", totalPassed[4]));
                System.out.println(
                        String.format("Number of students who passed 3 or more subjects: %d", totalPassed[3]));
                System.out.println(
                        String.format("Number of students who passed 2 or more subjects: %d", totalPassed[2]));
                System.out.println(
                        String.format("Number of students who passed 1 or more subjects: %d", totalPassed[1]));
                System.out.println(String.format("Number of students who failed all subjects: %d", totalPassed[0]));
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
            inStream = new FileInputStream("2.in");
            outStream = new PrintStream("2.out");
            System.setIn(inStream);
            System.setOut(outStream);
        }

        new Main().work();
    }
}
