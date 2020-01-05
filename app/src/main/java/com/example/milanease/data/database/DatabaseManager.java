package com.example.milanease.data.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.milanease.data.RepositoryManager;
import com.example.milanease.data.database.converter.Converter;
import com.example.milanease.data.database.dao.BillDao;
import com.example.milanease.data.database.dao.ContractDao;
import com.example.milanease.data.database.dao.ProviderDao;
import com.example.milanease.data.model.Bill;
import com.example.milanease.data.model.Contract;
import com.example.milanease.data.model.Provider;

import java.util.concurrent.Executors;

@Database(entities = {Bill.class, Provider.class, Contract.class},
        exportSchema = false,
        version = 1)
@TypeConverters({Converter.class})
public abstract class DatabaseManager extends RoomDatabase {
    private static final String DB_NAME = "milanease_db";

    private static volatile DatabaseManager instance;

    public synchronized static DatabaseManager getInstance(final Context context) {
        if (instance == null) {
            instance = createDB(context);
            instance.initDB();
        }

        return instance;
    }

    private static DatabaseManager createDB(final Context context) {
        return Room.databaseBuilder(context, DatabaseManager.class, DB_NAME)
                .fallbackToDestructiveMigration()
                .build();
    }

    public abstract BillDao getBillDao();
    public abstract ContractDao getContractDao();
    public abstract ProviderDao getProviderDao();

    private void initDB() {
        Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
            @Override
            public void run() {
                if (getProviderDao().count() == 0) {
                    initProviders();
                }
                if (getBillDao().count() == 0)
                    initBills();
                if (getContractDao().count() == 0) {
                    initContracts();
                }
            }
        });
    }

    private void initBills() {
        runInTransaction(new Runnable() {
            @Override
            public void run() {
                getBillDao().insertAll(RepositoryManager.getInstance().initDBBills());
            }
        });
    }

    private void initProviders() {
        runInTransaction(new Runnable() {
            @Override
            public void run() {
                getProviderDao().insertAll(RepositoryManager.getInstance().initDBProviders());
            }
        });
    }

    private void initContracts() {
        runInTransaction(new Runnable() {
            @Override
            public void run() {
                getContractDao().insertAll(RepositoryManager.getInstance().initDBContracts());
            }
        });
    }

}
