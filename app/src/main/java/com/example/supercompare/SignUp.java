package com.example.supercompare;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.supercompare.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class SignUp extends AppCompatActivity {

    boolean notExist = false;
    MaterialEditText edtPhoneSignUp,edtNameSignUp,edtPasswordSignUp;
    Button btnSignUpAction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edtNameSignUp = findViewById(R.id.edtNameSignUp);
        edtPhoneSignUp = findViewById(R.id.edtPhoneSignUp);
        edtPasswordSignUp = findViewById(R.id.edtPasswordSignUp);
        btnSignUpAction = findViewById(R.id.btnSignUpAction);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        btnSignUpAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog mDialog = new ProgressDialog(SignUp.this);
                mDialog.setMessage("Please wait...");
                mDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child(edtPhoneSignUp.getText().toString()).exists()){
                            mDialog.dismiss();
                            if (notExist == true)
                                Toast.makeText(SignUp.this, "Signed Up Successfully!", Toast.LENGTH_SHORT).show();
                            else
                                Toast.makeText(SignUp.this, "Phone Number already registered", Toast.LENGTH_SHORT).show();
                        }
                        else if (!(dataSnapshot.child(edtPhoneSignUp.getText().toString()).exists())){
                            mDialog.dismiss();
                            User user = new User(edtNameSignUp.getText().toString(),edtPasswordSignUp.getText().toString());
                            notExist = true;
                            table_user.child(edtPhoneSignUp.getText().toString()).setValue(user);
                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

    }
}
