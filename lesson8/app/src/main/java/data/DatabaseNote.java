package data;

import android.arch.persistence.room.RoomDatabase;

@android.arch.persistence.room.Database(entities = {Notebook.class}, version = 1, exportSchema = false)
public abstract class DatabaseNote extends RoomDatabase {
    public abstract DaoBase daoBase();

}


