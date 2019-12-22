package com.example.milanease.data.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.milanease.data.model.Contract;

import java.util.List;

@Dao
public interface ContractDao {
    @Query("select * from contracts")
    List<Contract> getContracts();
    @Insert
    long insert(Contract contract);
    @Update
    int update(Contract contract);
    @Delete
    int delete(Contract contract);

}
