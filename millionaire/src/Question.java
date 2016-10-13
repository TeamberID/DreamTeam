
public class Question {
    private String question;
    private String answers[];
    private final int RIGHT_ANSWER;
    private int points;
    public Question(String question, String answers[], int rightAnswer, int points) {
        this.question = question;
        this.answers = answers;
        this.points = points;
        RIGHT_ANSWER = rightAnswer;
    }
    public void ask() {
        System.out.println(question);
        for (int i = 0; i < answers.length; i++) {
            System.out.println("    " + (i + 1) + ") " + answers[i]);
        }
    }
    public int checkAnswer(int answer){
        return answer == RIGHT_ANSWER ? points : 0;
    }
}
