import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    static ArrayList<Question> questions;
    private static int score = 0;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        obtainQuestions();
        Question q = questions.get(0);
        q.ask();
        score += q.checkAnswer(in.nextInt());
        System.out.println("score = " + score);
    }

    private static void obtainQuestions() {
        questions = new ArrayList<>();
        Question ques = new Question("1 + 1", new String[]{"22", "2", "34", "4"}, 2, 100);
        questions.add(ques);
    }
}
