package com.example.milanease.data.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.milanease.data.model.Bill;

import java.util.List;

@Dao
public interface BillDao {
    @Query("select * from bills")
    List<Bill> getBills();
    @Insert
    long insert(Bill bill);
    @Update
    int update(Bill bill);
    @Delete
    int delete(Bill bill);
}
