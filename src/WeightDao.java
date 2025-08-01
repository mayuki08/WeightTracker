import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface WeightDao {
    @Insert
    void insert(WeightEntry entry);

    @Query("SELECT * FROM WeightEntry ORDER BY id DESC")
    List<WeightEntry> getAll();
    
    //最新の一件だけのデータを取得する
    /*@Query("SELECT * FROM WeightEntry ORDER BY id DESC LIMIT 1")
WeightEntry getLatest();*/

}
