package com.example.milanease.data.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.milanease.data.model.Provider;

import java.util.List;

@Dao
public interface ProviderDao {
    @Query("select * from providers")
    List<Provider> getProviders();
    @Insert
    long insert(Provider provider);
    @Update
    int update(Provider provider);
    @Delete
    int delete(Provider provider);
}
