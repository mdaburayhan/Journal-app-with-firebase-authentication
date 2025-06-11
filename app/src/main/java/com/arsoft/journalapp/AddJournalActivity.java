package com.arsoft.journalapp;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class AddJournalActivity extends AppCompatActivity {

    // Widgets
    private Button saveButton;
    private ImageView addPhotoBtn;
    private ProgressBar progressBar;
    private EditText titleEditText;
    private EditText thoughtsEditText;
    private ImageView imageView;

    // Firebase (Firestore)
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collectionReference = db.collection("Journal");

    // Firebase (Storage)
    private StorageReference storageReference;

    // Firebase Auth : UserId and UserName
    private String currentUserId;
    private String currentUserName;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseUser user;

    // Using Activity Result Launcher
    ActivityResultLauncher<String> mTakePhoto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_journal);

        progressBar = findViewById(R.id.post_progressBar);
        titleEditText = findViewById(R.id.post_title_et);
        thoughtsEditText = findViewById(R.id.post_description_et);
        imageView = findViewById(R.id.post_imageView);
        saveButton = findViewById(R.id.post_save_journal_button);
        addPhotoBtn = findViewById(R.id.postCameraButton);


        progressBar.setVisibility(View.INVISIBLE);
        // Firebase
        storageReference = FirebaseStorage.getInstance().getReference();

        // Auth
        firebaseAuth = FirebaseAuth.getInstance();

        // Getting the Current User
        if (user != null){
            currentUserId = user.getUid();
            currentUserName = user.getDisplayName();
        }

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveJournal();
            }
        });

        mTakePhoto = registerForActivityResult(
                new ActivityResultContracts.GetContent(),
                new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri o) {
                        // Showing the image
                        imageView.setImageURI(o);
                    }
                }
        );

        addPhotoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Getting image from Gallery
                mTakePhoto.launch("image/*");
            }
        });






    }

    private void SaveJournal() {
    }
}