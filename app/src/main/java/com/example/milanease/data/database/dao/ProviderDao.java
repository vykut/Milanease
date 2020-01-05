package com.example.milanease.data.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.milanease.data.model.Provider;

import java.util.List;

@Dao
public interface ProviderDao {
    @Query("SELECT * FROM PROVIDERS")
    LiveData<List<Provider>> getProviders();
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Provider provider);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertAll(List<Provider> providers);
    @Update
    int update(Provider provider);
    @Delete
    int delete(Provider provider);
    @Query("SELECT COUNT(*) FROM providers")
    int count();
}
