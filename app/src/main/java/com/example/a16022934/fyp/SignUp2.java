package com.example.a16022934.fyp;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SignUp2 extends AppCompatActivity {
    ImageView ivProfile;
    Integer REQUEST_CAMERA = 1, SELECT_FILE = 0;

    EditText etBio;
    EditText etFirstName;
    EditText etLastName;
    EditText etPhoneNo;
    EditText etDateOfBirth;
    RadioGroup rgGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_2);
        initialize();

        Intent i = getIntent();
        final String uid = i.getStringExtra("uid");
        final String email = i.getStringExtra("email");
        setTitle("SIGN UP");

        Button btn = findViewById(R.id.btnNext);
        ivProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectImage();
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkIfEmpty()){
                    String bio = etBio.getText().toString();
                    String firstName = etFirstName.getText().toString();
                    String lastName = etLastName.getText().toString();
                    String phone = etPhoneNo.getText().toString();
                    String dob = etDateOfBirth.getText().toString();
                    RadioButton rb = findViewById(rgGender.getCheckedRadioButtonId());
                    String gender = rb.getText().toString();
                    Intent i = new Intent(SignUp2.this, SignUp3.class);
                    i.putExtra("uid", uid);
                    i.putExtra("email", email);
                    i.putExtra("firstName", firstName);
                    i.putExtra("lastName", lastName);
                    i.putExtra("phone", phone);
                    i.putExtra("dob", dob);
                    i.putExtra("gender", gender);
                    i.putExtra("bio", bio);
                    startActivity(i);
                }else{
                    Toast.makeText(SignUp2.this, "All Fields Must Be Filled", Toast.LENGTH_SHORT).show();
                }





            }
        });


    }


    //    protected void onActivityResult(int requestCode, int resultCode, Intent
//            imageReturnedIntent) {
//        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
//        ImageView ivProfile = (ImageView) findViewById(R.id.ivProfile);
//        switch (requestCode) {
//            case 0:
//                if (resultCode == RESULT_OK) {
//                    Uri selectedImage = imageReturnedIntent.getData();
//                    ivProfile.setImageURI(selectedImage);
//                }
//
//                break;
//            case 1:
//                if (resultCode == RESULT_OK) {
//                    Uri selectedImage = imageReturnedIntent.getData();
//                    ivProfile.setImageURI(selectedImage);
//                }
//                break;
//        }
//    }
    private void SelectImage() {
        final CharSequence[] items = {"Camera", "Gallery", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(SignUp2.this);
        builder.setTitle("Add Image");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (items[i].equals("camera")) {

                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, 0);

                } else if (items[i].equals("Gallery")) {

                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(intent, SELECT_FILE);

                } else if (items[i].equals("Cancel")) {
                    dialogInterface.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent
            data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CAMERA) {
            Bundle bundle = data.getExtras();
            final Bitmap bmp = (Bitmap) bundle.get("data");
            ivProfile.setImageBitmap(bmp);
        } else if (requestCode == SELECT_FILE) {
            Uri selectedImageUri = data.getData();
            ivProfile.setImageURI(selectedImageUri);
        }
    }

    public boolean checkIfEmpty(){
        initialize();
        boolean check = false;
        if(TextUtils.isEmpty(etFirstName.getText().toString()) && TextUtils.isEmpty(etLastName.getText().toString()) && TextUtils.isEmpty(etDateOfBirth.getText().toString()) && TextUtils.isEmpty(etPhoneNo.getText().toString())){
            check = false;
        }else{
            check = true;
        }
        return check;
    }

    public void initialize(){
        ivProfile = findViewById(R.id.ivProfile);
        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etPhoneNo = findViewById(R.id.etPhoneNo);
        etDateOfBirth = findViewById(R.id.etDoB);
        rgGender = findViewById(R.id.rgGender);
    }
}
