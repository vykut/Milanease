package com.example.milanease.data.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.milanease.data.database.converter.Converter;
import com.example.milanease.data.database.dao.BillDao;
import com.example.milanease.data.database.dao.ContractDao;
import com.example.milanease.data.database.dao.ProviderDao;
import com.example.milanease.data.model.Bill;
import com.example.milanease.data.model.Contract;
import com.example.milanease.data.model.Provider;

@Database(entities = {Bill.class, Provider.class, Contract.class},
        exportSchema = false,
        version = 1)
@TypeConverters({Converter.class})
public abstract class DatabaseManager extends RoomDatabase {
    private static final String DB_NAME = "milanease_db";

    private static volatile DatabaseManager instance;

    public synchronized static DatabaseManager getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, DatabaseManager.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    public abstract BillDao getBillDao();
    public abstract ContractDao getContractDao();
    public abstract ProviderDao getProviderDao();
}
