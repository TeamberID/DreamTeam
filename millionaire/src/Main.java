import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Main {
    static ArrayList<ArrayList<Question>> leveledQuestions;
    private static int score = 0;
    private static Scanner console;
    private static int amountInLevel[];
    private static int delta = 300;
    private static int currentLevel = 0;
    private static int maxLevel;

    enum States{
        Running,
        GameOver,
        Victory,
        Init,
        Exit
    }
    private static States state;

    public static void main(String[] args) {
        console = new Scanner(System.in);
        state = States.Init;

        while(true){
            switch (state){
                case Init:
                    try {
                        obtainQuestions();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    amountInLevel = new int[6];
                    amountInLevel[0] = 10;
                    amountInLevel[1] = 10;
                    amountInLevel[2] = 8;
                    amountInLevel[3] = 6;
                    amountInLevel[4] = 5;
                    amountInLevel[5] = 2;
                    currentLevel = 0;
                    state = States.Running;
                    score = 0;
                    break;
                case Running:
                    gameLoop();
                    break;
                case GameOver:
                    System.out.println("sorry you lose, do you wanna play again (y or n)?");
                    state = (console.next().contains("y")) ? States.Init : States.Exit;
                    break;
                case Victory:
                    System.out.println("congratulations you won whith " + score + " points" +
                            ", do you wanna play again (y or n)?");
                    state = (console.next().contains("y")) ? States.Init : States.Exit;
                    break;
                case Exit:
                    System.out.println("Good buy");
                    return;
            }
        }






    }

    private static void gameLoop(){
        while(state == States.Running){
            if(!askRandomQuestion()){
                state = States.GameOver;
                return;
            }
            if(leveledQuestions.get(currentLevel).size() == 0 || amountInLevel[currentLevel]-- == 0){
                if(currentLevel == maxLevel){
                    state = States.Victory;
                    return;
                }
                System.out.println("Вы закончили уровень, хотите перейти на более сложный или забрать очки?");
                System.out.println("Перейти (y or n)");
                if(!console.next().contains("y")){
                    state = States.Victory;
                }
                currentLevel++;
            }
        }
    }

    private static boolean askRandomQuestion() {
        int number = (int) (Math.random() * (float)leveledQuestions.get(currentLevel).size());

        Question q = leveledQuestions.get(currentLevel).get(number);
        q.ask();
//        System.out.println("number = " + number);
//        System.out.println("level = " + q.getLevel());
//        System.out.println("amountInLevel[" + currentLevel + "] = " + amountInLevel[currentLevel]);
        if(q.checkAnswer(myGetInt())){
            score += (leveledQuestions.get(currentLevel).remove(number).getLevel() + 1) * delta;
            System.out.println("Правильно. Ваш счет = " + score);
            return true;
        }else {
            q.showRightAnswer();
            System.out.println("Вы проиграли");
            return false;
        }
    }

    public static int myGetInt(){
        int res;
        while(true) {
            try {
                res = console.nextInt();
                if(res >= 1 && res <= 4){
                    break;
                }
            }catch (InputMismatchException e){
                console.nextLine();
            }

            System.out.println("Пожалуйста введите корректный номер [1,4]");
        }
        return res;
    }

    private static void obtainQuestions() throws IOException {
        leveledQuestions = new ArrayList<>();
        BufferedReader in;
        try {
             in = new BufferedReader(new FileReader(new File("LargeBase.txt")));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Sorry cannot obtain leveledQuestions");
        }
        while(in.ready()){
//            System.out.println("I am here");
            int level = Integer.valueOf(in.readLine());
            maxLevel = Math.max(maxLevel,level);
            level--;
            String body = in.readLine();
            int numOfAns = 4;
            String answers[] = new String[numOfAns];
            for (int i = 0; i < numOfAns; i++) {
                answers[i] = in.readLine();
            }
            int right = Integer.valueOf(in.readLine());
            while(leveledQuestions.size() <= level){
                leveledQuestions.add(new ArrayList<>());
            }
            leveledQuestions.get(level).add(new Question(body, answers, right, level));
            in.readLine();
        }
    }
}
