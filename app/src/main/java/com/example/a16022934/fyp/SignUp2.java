package com.example.a16022934.fyp;

import android.app.Activity;
import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SignUp2 extends AppCompatActivity {
    public static final int SELECT_FILE = 1;
    public static final int REQUEST_CAMERA = 0;

    ImageView ivProfile;
    TextView selectImage;
    EditText etBio;
    EditText etFullName;
    EditText etPhoneNo;
    TextView etDateOfBirth;
    RadioGroup rgGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_2);
        initialize();

        Intent i = getIntent();
        final String password = i.getStringExtra("password");
        final String email = i.getStringExtra("email");
        setTitle("SIGN UP");

        Button btn = findViewById(R.id.btnNext);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkIfEmpty()) {
                    if (etPhoneNo.getText().toString().length() == 8) {
                        String bio = etBio.getText().toString();
                        String fullName = etFullName.getText().toString();
                        String phone = etPhoneNo.getText().toString();
                        String dob = etDateOfBirth.getText().toString();
                        RadioButton rb = findViewById(rgGender.getCheckedRadioButtonId());
                        String gender = rb.getText().toString();
                        Intent i = new Intent(SignUp2.this, SignUp3.class);
                        i.putExtra("password", password);
                        i.putExtra("email", email);
                        i.putExtra("fullName", fullName);
                        i.putExtra("phone", phone);
                        i.putExtra("dob", dob);
                        i.putExtra("gender", gender);
                        i.putExtra("bio", bio);
                        startActivity(i);
                    }
                } else {
                    Toast.makeText(SignUp2.this, "All Fields Must Be Filled", Toast.LENGTH_SHORT).show();
                }


            }
        });

        etDateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        int day = dayOfMonth;
                        int month = monthOfYear + 1;
                        int yr = year;
                        String strDay = "" + day;
                        String strMonth = "" + month;
                        if(day < 10){
                            strDay = "0" + day;
                        }
                        if(month < 10){
                            strMonth = "0" + month;
                        }
                        String dob = strDay + "/" + strMonth + "/" + yr;
                        etDateOfBirth.setText(dob);
                    }
                };
                int Year = now.get(Calendar.YEAR);
                int Month = now.get(Calendar.MONTH);
                int Day = now.get(Calendar.DAY_OF_MONTH);

                // Create the Date Picker Dialog
                DatePickerDialog myDateDialog = new DatePickerDialog(SignUp2.this,
                        myDateListener, Year, Month, Day);
                myDateDialog.getDatePicker().setSpinnersShown(true);
                myDateDialog.getDatePicker().setCalendarViewShown(false);

                myDateDialog.show();

            }
        });

        selectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectImage();
            }
        });
    }

    private void SelectImage() {
        final CharSequence[] items = {"Camera", "Gallery", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(SignUp2.this);
        builder.setTitle("Add Image");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (items[i].equals("camera")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, REQUEST_CAMERA);

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
        selectImage = findViewById(R.id.tvSelectImage);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        //Detects request codes
        if (requestCode == SELECT_FILE && resultCode == Activity.RESULT_OK) {
            Uri selectedImage = data.getData();
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                ivProfile.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}