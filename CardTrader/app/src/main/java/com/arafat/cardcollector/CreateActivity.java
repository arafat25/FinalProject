package com.arafat.cardcollector;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;


public class CreateActivity extends AppCompatActivity   {

    int REQUEST_CAMERA = 0; //needs to be initialized for onClickButtonCamera method to work
    private static int SELECT_FILE = 1; //needs to be initialized for startActivityForResult method to work
    ImageView image;
    EditText title,  type;
    Uri selectedImage;



    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);



        image = (ImageView) findViewById(R.id.imageView2);
        title = (EditText) findViewById(R.id.editText);
        type = (EditText) findViewById(R.id.editText2);


    }


    public void onClickHome(View view) {
        Intent intent = new Intent(CreateActivity.this, MainBoardActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


    public void onClickbuttonPhotoLibrary(View view) {
        Intent intent = new Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");

        startActivityForResult(
                Intent.createChooser(intent, "Choose File"),
                SELECT_FILE);
    }


    public void onClickbuttonUpload(View view) {

       try{

           String editTitle = title.getText().toString();

           String editType = type.getText().toString();
           if(editTitle.trim().length() > 0 && editType.trim().length() > 0 && editType.trim().length() > 0) {
               Bitmap bmp = ((BitmapDrawable) image.getDrawable()).getBitmap();
               ByteArrayOutputStream bYtE = new ByteArrayOutputStream();
               bmp.compress(Bitmap.CompressFormat.JPEG, 100, bYtE);
               bmp.recycle();
               byte[] byteArray = bYtE.toByteArray();
               String imageFile = Base64.encodeToString(byteArray, Base64.DEFAULT);

               Map<String, String> card = new HashMap<String, String>();
              card.put("image", imageFile);
               
               card.put("title", editTitle);
               card.put("Type", editType);


               //postRef.push().setValue(card);

               Toast.makeText(CreateActivity.this, "Uploading . . .", Toast.LENGTH_SHORT).show();
           }else{
               Toast.makeText(CreateActivity.this, "Make sure all fields are completed!", Toast.LENGTH_SHORT).show();
           }
       }catch(Exception e){
           Toast.makeText(CreateActivity.this, "Make sure image is selected!", Toast.LENGTH_SHORT).show();

        }

        

    }

    //this method will handle image chosen from gallery 
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == SELECT_FILE && resultCode == RESULT_OK) {

                selectedImage = data.getData();
                image.setImageURI(selectedImage);

            } else if(requestCode == REQUEST_CAMERA && resultCode == RESULT_OK){
                //camera crashes after picture is taken!!
                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                image.setImageBitmap(thumbnail);


            }
    }

}
