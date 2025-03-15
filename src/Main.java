import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("体重を記録してください");
        WeightTracker w = new WeightTracker();
        w.weighttracker();

        Scanner sc = new Scanner(System.in);
        System.out.println("前日の記録との比較を確認しますか？(はい: 1,いいえ: 2)");
        if(sc.hasNextInt()) {
        int num = sc.nextInt(); 
        if(num == 1) {
            w.diffweight();
        }else{
            System.out.println("記録を終了します。");
            }
        }
        sc.close();
    }
}