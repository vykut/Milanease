package com.example.milanease.data.firebase;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.milanease.data.model.Message;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.firestore.WriteBatch;

import java.util.List;

public class FirebaseManager {

    private static final String TABLE_NAME = "Messages";

    private static final FirebaseManager ourInstance = new FirebaseManager();

    public static FirebaseManager getInstance() {
        return ourInstance;
    }

    private CollectionReference collection;
    private FirebaseFirestore firestore;

    private FirebaseManager() {
        firestore = FirebaseFirestore.getInstance();
        collection = firestore.collection(TABLE_NAME);
    }

    public LiveData<String> insert(Message message) {
        final MutableLiveData<String> id = new MutableLiveData<>();
        collection.add(message)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("tagtag", "DocumentSnapshot added with ID: " + documentReference.getId());
                        id.postValue(documentReference.getId());
                    }
                });
        return id;
    }

    public LiveData<String> insertAll(List<Message> messages) {
        final MutableLiveData<String> id = new MutableLiveData<>();
        WriteBatch writeBatch = firestore.batch();
        for (Message message :
                messages) {
            writeBatch.set(collection.document(), message);
        }
        writeBatch.commit().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                id.postValue("Messages inserted succesfully!");
            }
        });
        return id;
    }

    public LiveData<List<Message>> getAllMessages() {
        final MutableLiveData<List<Message>> mMessages = new MutableLiveData<>();

        collection.orderBy("date").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                if (e != null) {
                    Log.e("tagtag", e.toString());
                    return;
                }
                    mMessages.postValue(documentSnapshots.toObjects(Message.class));
            }
        });

        return mMessages;
    }
}
