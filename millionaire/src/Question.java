
public class Question {
    private String question;
    private String answers[];
    public final int RIGHT_ANSWER;
    public final int LEVEL;
    private int level;

    //    private int points;
    public Question(String question, String answers[], int rightAnswer, int level) {
        this.question = question;
        this.answers = answers;
        LEVEL = level;
        RIGHT_ANSWER = rightAnswer;
    }
    public void ask() {
        System.out.println(question);
        for (int i = 0; i < answers.length; i++) {
            System.out.println("    " + (i + 1) + ") " + answers[i]);
        }
    }
    public boolean checkAnswer(int answer){
        return answer == RIGHT_ANSWER;
    }

    public int getLevel() {
        return level;
    }

    public void showRightAnswer() {
        System.out.println("Правильный ответ : " + answers[RIGHT_ANSWER - 1]);
    }
}
