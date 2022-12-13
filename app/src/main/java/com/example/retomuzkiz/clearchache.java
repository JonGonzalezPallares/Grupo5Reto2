package com.example.retomuzkiz;

import android.content.Context;

import java.io.File;

//funciones de limpieza de cache
//-------------------------------------------------------------------------------
public class clearchache {
    public static void deleteCache(Context context) {
        try {
            File dir = context.getCacheDir();
            deleteDir(dir);
        } catch (Exception e) {}
    }

    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            /**
             * for (int i = 0; i < children.length; i++)
             * este for se puede cambiar por este otro
             */
            for (String child : children) {
                boolean success = deleteDir(new File(dir, child));
                if (!success) {
                    return false;
                }
            }
            return dir.delete();
        } else if(dir!= null && dir.isFile()) {
            return dir.delete();
        } else {
            return false;
        }
    }
}
