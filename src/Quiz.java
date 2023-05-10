import java.io.*;
import java.util.*;

public class Quiz {
    Scanner in=new Scanner(System.in);
    Test test=new Test();
    FillIn fill=new FillIn();
    String name;
    ArrayList<String> questions=new ArrayList<>();
    ArrayList<ArrayList<String>> variety=new ArrayList<>();
    ArrayList<String> correctAnswer=new ArrayList<>();
    ArrayList<Integer>shuffleId=new ArrayList<>();
    int IdSH;
    int and;
    int variety_size;

    public Quiz(String name) {
            this.name = name;

    }

    public void ShuffleQuestion(int id){
        IdSH=shuffleId.get(id);
        variety_size=variety.get(IdSH).size();
        addQuestion();
    }

    public String getName() {
        return name;
    }

    public void addQuestion(){
     if(variety_size == test.getOptionAt()){
         test.setDescription(questions.get(IdSH));
         test.setOptions(variety.get(IdSH));
         test.setAnswer(correctAnswer.get(IdSH));
         and=0;
     }else{
         fill.setDescription(questions.get(IdSH));
         fill.setAnswer(correctAnswer.get(IdSH));
         and=1;
     }

    }
    public void ShuffleId(){
        for(int i=0;i<questions.size();i++){
            shuffleId.add(i);}
        Collections.shuffle(shuffleId);
    }

    public void loadFromFile() throws IOException {
        File file = new File(getName());
        Scanner in=new Scanner(file);
        String line;
        int count_var=-1;
        while(in.hasNextLine()){
            line=in.nextLine();
            if(line.contains("-")){continue;}
            if(line.contains("?")) {
                questions.add(line);
                variety.add(new ArrayList<>());
                count_var++;
            }if(line.contains(")")){
                String []var=line.split(" *\\) *");
                correctAnswer.add(var[0]);
                for(int i=0;i<var.length;i++)
                    variety.get(count_var).add(var[i]);
            }
        }
    }
    int corrAns=0;
    public void finish(int a){
        System.out.println("\nCorrect Answers: "+a+"/6 ("+(a*100)/6+".0%) ");
    }

    public String welcome() {
        return "===================================================="+"\nWELCOME TO "+getName().replace(".txt","")+" QUIZ!\n"+"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
    }
    String choice;
    public void start(){
        ShuffleId();
        for(int i=0;i<6;i++) {
            ShuffleQuestion(i);
            if(and==0){
            System.out.println((i+1)+". "+test.toString()+"\n--------------------------------------");}
            else{
            System.out.println((i+1)+". "+fill.toString()+"\n--------------------------------------");}
            System.out.print("Enter the correct choice: ");
            QuizFormat();
        }
        finish(corrAns);
    }
    public void QuizFormat(){
        while (true) {
            try {
                choice = in.next();
                if(and==0&&!(choice.equals("A")||choice.equals("B")||choice.equals("C")||choice.equals("D"))) throw new InvalidQuizFormatException();
            }
            catch (InvalidQuizFormatException ex1) {
                System.out.print("Invalid choice! Try again(Ex: A, B, ...): ");
            continue;}
            if(and==0&&choice.equals(test.getAnswer()))
            {System.out.println("Correct!\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");corrAns++;}
            else if(and==1&&choice.equals(fill.getAnswer()))
            {System.out.println("Correct!\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");corrAns++;}
            else{System.out.println("Incorrect!\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");}
            break;
        }



    }
}