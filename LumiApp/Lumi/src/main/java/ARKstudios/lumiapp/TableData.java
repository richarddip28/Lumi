package arkstudios.lumiapp;

import android.provider.BaseColumns;

/**
 * Created by Richard Dip on 10/9/2017.
 */

public class TableData {

    public TableData(){

    }

    public static abstract class TableInfo implements BaseColumns{

        public static final String SERIAL_NO = "serial_no";
        public static final String SERIAL_PASS = "serial_pass";
        public static final String DATABASE_NAME = "lumi_info";
        public static final String TABLE_NAME = "reg_info";

    }


}
