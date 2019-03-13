package com.notrika.regular_trafic.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.os.strictmode.Violation;


import java.util.List;

@Dao
public interface TBL_Violation_Dao {

    @Insert
    void insertOnlySingleViolation (TBL_Violation viol);
    @Insert
    void insertMultipleViolations (List<TBL_Violation> viols);
    @Query("SELECT * FROM TBL_Violation ")
    List<TBL_Violation> getallviols ();
    @Update
    void updateViolation (TBL_Violation viol);
    @Delete
    void deleteViolation (TBL_Violation viol);
}