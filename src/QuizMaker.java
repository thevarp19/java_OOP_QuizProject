import java.io.IOException;
import java.util.Scanner;

public class QuizMaker {
    public static void main(String[] args) throws IOException, InvalidQuizFormatException {
        Scanner s=new Scanner(System.in);
        System.out.print("Enter _____.txt file: ");
        String name=s.next();
      Quiz quiz=new Quiz(name);
        quiz.loadFromFile();
        System.out.println(quiz.welcome());
      quiz.start();


    }
}
