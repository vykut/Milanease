package com.example.milanease.data.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.milanease.core.ui.dashboard.Utility;
import com.example.milanease.data.model.Bill;

import java.util.List;

@Dao
public interface BillDao {
    @Query("SELECT * FROM bills")
    LiveData<List<Bill>> getBills();
    @Query("SELECT * FROM BILLS WHERE UTILITY = :utility")
    LiveData<List<Bill>> getSegmentedBills(Utility utility);
    @Query("SELECT SUM(PRICE) FROM BILLS WHERE UTILITY = :utility")
    Double getBillTotal(Utility utility);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Bill bill);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertAll(List<Bill> bills);
    @Update
    int update(Bill bill);
    @Delete
    int delete(Bill bill);
    @Query("SELECT COUNT(*) FROM bills")
    int count();
}