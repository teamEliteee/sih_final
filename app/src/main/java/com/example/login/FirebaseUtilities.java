package com.example.login;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FirebaseUtilities {
    Context context;
    ProgressDialog progressDialog;
    FirebaseStorage firebaseStorage;
    FirebaseAuth mAuth;
    FirebaseUser firebaseUser;


    public FirebaseUtilities(Context contex){
        this.context = contex;
        firebaseStorage = FirebaseStorage.getInstance();
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading Please wait...");
        progressDialog.setCanceledOnTouchOutside(false);
    }

    String getTimeStamp(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(new Date());
    }

    public void uploadImage(Uri uri){
        progressDialog.show();
        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();

        StorageReference storageReference = firebaseStorage.getReference().child("Images/" + firebaseUser.getUid() + "/" + uri.getLastPathSegment());
        storageReference.putFile(uri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if(task.isSuccessful()){
                    storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            progressDialog.dismiss();
                            Log.d("storageReference URL", "On Success" + uri.toString());
                        }
                    });
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                e.printStackTrace();
                Toast.makeText(context, "Image Upload falied"+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}