package data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface DaoBase {
    @Query("SELECT * FROM Notebook where Rar = 0")
    Flowable<List<Notebook>> getNotebook();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Notebook... notebook);

    @Update
    void update(Notebook notebook);

    @Delete
    void delete(Notebook notebook);
}
