import java.io.*;
import java.util.Scanner;

public class WeightTracker {
    private static final String FILE_NAME = "weight_data.txt"; // 体重を保存するファイル

    public void trackWeight() {
        Scanner scanner = new Scanner(System.in);
        double previousWeight = readPreviousWeight(); // 前回の体重を取得

        // 現在の体重を入力
        System.out.print("現在の体重を入力してください: ");
        double currentWeight = scanner.nextDouble();
        
        // 体重差を計算
        if (previousWeight != -1) { // 初回実行でない場合
            double difference = currentWeight - previousWeight;
            System.out.printf("前回との差: %.2f kg\n", difference);
        } else {
            System.out.println("前回の記録がありません。");
        }

        // 現在の体重をファイルに保存
        saveCurrentWeight(currentWeight);
        System.out.println("体重を保存しました。");

        scanner.close();
    }

    // ファイルから前回の体重を読み込む
    private double readPreviousWeight() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            return Double.parseDouble(br.readLine()); // ファイルの1行目を読み取る
        } catch (IOException | NumberFormatException e) {
            return -1; // データがない場合は -1 を返す
        }
    }

    // 現在の体重をファイルに保存する
    private void saveCurrentWeight(double weight) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            bw.write(String.valueOf(weight)); // 体重をファイルに書き込む
        } catch (IOException e) {
            System.out.println("ファイルに書き込めませんでした。");
        }
    }
}
