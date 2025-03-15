import java.util.ArrayList;
import java.util.Scanner;

public class WeightTracker {

    //入力された体重をリストで記録
   private ArrayList<Integer> weight = new ArrayList<>();

    public void weighttracker(){

        Scanner sc = new Scanner(System.in);
        int userweight = sc.nextInt();
        weight.add(userweight);

        System.out.println("今日の体重: " + weight + "kg");

        sc.close();
    }

    public void diffweight() {
        System.out.println(weight.size()-1 - weight.size() -2);
    }
}
