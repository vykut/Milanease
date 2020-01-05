package com.example.milanease.data.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.milanease.data.model.Contract;

import java.util.List;

@Dao
public interface ContractDao {
    @Query("SELECT * FROM CONTRACTS")
    LiveData<List<Contract>> getContracts();
    @Query("SELECT provider_id FROM CONTRACTS")
    LiveData<List<Long>> getProvidersIDs();
    @Query("SELECT * FROM CONTRACTS WHERE PROVIDER_ID = :providerID")
    LiveData<Contract> getContract(long providerID);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Contract contract);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertAll(List<Contract> contracts);
    @Update
    int update(Contract contract);
    @Delete
    int delete(Contract contract);
    @Query("SELECT COUNT(*) FROM contracts")
    int count();

}
