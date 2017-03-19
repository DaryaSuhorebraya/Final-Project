package by.epam.movierating.dao.connectionpool;

import java.util.ResourceBundle;

/**
 * Created by Даша on 27.01.2017.
 */
public class DBResourceManager {
    private static final DBResourceManager instance=new DBResourceManager();

    private ResourceBundle resourceBundle= ResourceBundle.getBundle("db");

    private DBResourceManager(){};

    public static DBResourceManager getInstance() {
        return instance;
    }
    public String getValue(String key){
        return resourceBundle.getString(key);
    }
}
