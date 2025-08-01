import java.io.*;
import java.util.Scanner;


public class WeightTracker {
    private static final String FILE_NAME = "weight_data.txt"; // 体重を保存するファイル
    private static final String USER_INFOMATION = "userinfomation.txt";
    String userName;
    int firstweight;
    int height;
    int goalsetting;

    public void trackWeight() {
        Scanner scanner = new Scanner(System.in);
        double previousWeight = readPreviousWeight(); // 前回の体重を取得

        // 現在の体重を入力
        System.out.print("現在の体重を入力してください: ");
        double currentWeight = scanner.nextDouble();

        //初回のみ入力
        firstRecode();
        firstRecode();//二回目以降実行されない

        //BMIを計算

        
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

    //初回入力
    public void firstRecode() {
        boolean firsttime = true;
        if(firsttime) {
            Scanner sc = new Scanner(System.in);

            //名前の入力
            System.out.println("名前を入力してください。");
            userName = sc.nextLine();

            //現在の体重の入力
            System.out.pritln("体重を入力してください。");
            firstweight = sc.nextInt();

            //目標体重を入力
            System.out.println("目標体重を入力してください。");
            goalsetting = sc.nextInt();

            //身長の入力
            System.out.println("身長を入力してください。");
            height = sc.nextInt();

            saveUserInfomation(userName, firstweight, goalsetting, height);

            firsttime = false;//二回目以降実行されないように

        }

    }

    // ファイルから前回の体重を読み込む(初期の方法)
    /*private double readPreviousWeight() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            return Double.parseDouble(br.readLine()); // ファイルの1行目を読み取る
        } catch (IOException | NumberFormatException e) {
            return -1; // データがない場合は -1 を返す
        }
    }*/

    //Roomから値を取得する方法に変更
    private double readPreviousWeight() {
    try {
        AppDatabase db = Room.databaseBuilder(
            getApplicationContext(),
            AppDatabase.class, "weight-database"
        ).allowMainThreadQueries().build();

        WeightEntry latest = db.weightDao().getLatest();
        if (latest != null) {
            return latest.weight;
        } else {
            return -1;
        }

    } catch (Exception e) {
        System.out.println("読み込み失敗: " + e.getMessage());
        e.printStackTrace();
        return -1;
    }
}



    // 現在の体重をファイルに保存する(初期の方法)
    /*private void saveCurrentWeight(double weight) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            bw.write(String.valueOf(weight)); // 体重をファイルに書き込む
        } catch (IOException e) {
            System.out.println("ファイルに書き込めませんでした。");
        }
    }*/

    //保存方法をRoomに変更
    private void saveCurrentWeight(double weight) {
    // 日付を取得（例："2025-08-01"）
    String date = java.time.LocalDate.now().toString();

    // エントリ作成
    WeightEntry entry = new WeightEntry();
    entry.weight = (float) weight;
    entry.date = date;

    // データベースに保存
    try{
    AppDatabase db = Room.databaseBuilder(
        getApplicationContext(),
        AppDatabase.class, "weight-database"
    ).allowMainThreadQueries().build(); // 本番は非同期にする

    db.weightDao().insert(entry);

    System.out.println("体重をデータベースに保存しました。");
    } catch(Exception e) {
        System.out.println("データベースの保存に失敗しました。");
        e.printStackTrace();//デバッグ用のエラー内容
    }
}

     private void saveUserInfomation(){
        
    }
}
