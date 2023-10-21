package CP4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner console = new Scanner(System.in);
        Scanner file = new Scanner(new File("score.txt"));

        ArrayList<Student> students = new ArrayList<>();

        while (file.hasNext()) {
            String name = file.next();
            int score = file.nextInt();

            Student student = new Student(name, score);
            students.add(student);
        }

        System.out.println("-------------Student info------------");
        for (int i = 0, size = students.size(); i < size; i++) {
            Student s = students.get(i);
            System.out.println("Name:" + s.getName() + ". Score: " + s.getScore());
        }

        System.out.println("-------------Input score------------");

        int inputScore = 0;
        boolean isValid = false;
        do {
            System.out.print("Input score: ");
            String scoreAsString = console.next();
            try {
                inputScore = Integer.parseInt(scoreAsString);
            } catch (Exception e) {
                inputScore = -1;
            }

            if (inputScore < 0) {
                System.out.println("Input integer number only");
            } else {
                isValid = true;
            }
        } while (!isValid);

        System.out.println("-----------Filter list-----------");
        for (int i = 0, size = students.size(); i < size; i++) {
            Student s = students.get(i);
            if (s.getScore() >= inputScore) {
                System.out.println("Name:" + s.getName() + ". Score: " + s.getScore());
            }
        }
    }
}
