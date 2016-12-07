package com.zoubelu.gymbuddy.db;

import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

/**
 * Created by root on 3.12.16.
 */

public class DatabaseConfigUtil extends OrmLiteConfigUtil {

    public static void main(String[] args) throws Exception {
        writeConfigFile("ormlite_config.txt");
    }
}
