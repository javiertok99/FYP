package com.example.a16022934.fyp;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
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

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SignUp2 extends AppCompatActivity {
    ImageView ivProfile;
    Uri file;
    int REQUEST_CAMERA = 1, SELECT_FILE = 0;

    EditText etBio;
    EditText etFullName;
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
                if (checkIfEmpty()) {
                    String bio = etBio.getText().toString();
                    String fullName = etFullName.getText().toString();
                    String phone = etPhoneNo.getText().toString();
                    String dob = etDateOfBirth.getText().toString();
                    RadioButton rb = findViewById(rgGender.getCheckedRadioButtonId());
                    String gender = rb.getText().toString();
                    Intent i = new Intent(SignUp2.this, SignUp3.class);
                    i.putExtra("uid", uid);
                    i.putExtra("email", email);
                    i.putExtra("fullName", fullName);
                    i.putExtra("phone", phone);
                    i.putExtra("dob", dob);
                    i.putExtra("gender", gender);
                    i.putExtra("bio", bio);
                    startActivity(i);
                } else {
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
                    Uri file = Uri.fromFile(getOutputMediaFile());
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, file);

                    startActivityForResult(intent, 1);

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

        if (requestCode == REQUEST_CAMERA && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ivProfile.setImageBitmap(imageBitmap);

//            ivProfile.setImageBitmap(bmp);
        } else if (requestCode == SELECT_FILE) {
            Uri selectedImageUri = data.getData();
            ivProfile.setImageURI(selectedImageUri);
        }
    }

    public boolean checkIfEmpty() {
        initialize();
        boolean check = false;
        if (TextUtils.isEmpty(etFullName.getText().toString()) && TextUtils.isEmpty(etDateOfBirth.getText().toString()) && TextUtils.isEmpty(etPhoneNo.getText().toString())) {
            check = false;
        } else {
            check = true;
        }
        return check;
    }

    public void initialize() {
        ivProfile = findViewById(R.id.ivProfile);
        etFullName = findViewById(R.id.etFullName);
        etPhoneNo = findViewById(R.id.etPhoneNo);
        etDateOfBirth = findViewById(R.id.etDoB);
        rgGender = findViewById(R.id.rgGender);
        etBio = findViewById(R.id.etBio);
    }

    private static File getOutputMediaFile() {
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "CameraDemo");

        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null;
            }
        }
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        return new File(mediaStorageDir.getPath() + File.separator +
                "IMG_" + timeStamp + ".jpg");
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_CAMERA);
        }
    }

//    static final int REQUEST_IMAGE_CAPTURE = 1;
//
//    private void dispatchTakePictureIntent() {
//        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
//            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
//        }
//    }
}




