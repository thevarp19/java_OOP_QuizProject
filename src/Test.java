import java.util.ArrayList;
import java.util.Collections;

public class Test extends Question{
    String description;
    String correctAnswer;
    int numOfOptions=4;
    ArrayList<String> labels=new ArrayList<>();
    @Override
    public void setAnswer(String answer) {correctAnswer=answer;}
    @Override
    public void setDescription(String description) {this.description=description;}
    @Override
    public String getDescription() {return description;}
    public void setOptions(ArrayList<String> options){
        Collections.shuffle(options);
        this.labels=options;
    }
    public String getAnswer() {
        if (correctAnswer.equals(labels.get(0))) {
            return "A";
        } else if (correctAnswer.equals(labels.get(1))) {
            return "B";
        } else if (correctAnswer.equals(labels.get(2))) {
            return "C";
        } else {
            return "D";}
    }
    public int getOptionAt(){return numOfOptions;}
    @Override
    public String toString(){return getDescription()+"\nA) "+labels.get(0)+"\nB) "+labels.get(1)+"\nC) "+labels.get(2)+"\nD) "+labels.get(3);}
}
