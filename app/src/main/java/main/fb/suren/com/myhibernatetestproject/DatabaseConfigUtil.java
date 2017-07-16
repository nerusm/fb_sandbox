package main.fb.suren.com.myhibernatetestproject;

import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

/**
 * Created by suren on 14/7/17.
 */

public class DatabaseConfigUtil extends OrmLiteConfigUtil {
    private static final Class<?>[] classes = new Class[] {
            SimpleData.class,
    };
    public static void main(String[] args) throws Exception {
        writeConfigFile("ormlite_config.txt", classes);
    }
}
