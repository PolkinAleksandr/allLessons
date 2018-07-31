package data;

import android.app.Application;
import android.arch.persistence.room.Room;

public class App extends Application {
    public static App instance;

    private DatabaseNote database;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(getApplicationContext(), DatabaseNote.class, "Notebook")
                .allowMainThreadQueries()
                .build();
    }

    public static App getInstance() {
        return instance;
    }

    public DatabaseNote getDatabase() {
        return database;
    }
}
