package com.notrika.regular_trafic.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;


@Database(entities = {TBL_Violation.class}, version = 1, exportSchema = false)
public abstract class Database_app extends RoomDatabase {
    public abstract TBL_Violation_Dao tbl_violation_dao() ;
}