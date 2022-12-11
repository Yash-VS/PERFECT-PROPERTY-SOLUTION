package com.example.ppt_application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aspose.slides.IAutoShape;
import com.aspose.slides.IPPImage;
import com.aspose.slides.ISlide;
import com.aspose.slides.ITextFrame;
import com.aspose.slides.Presentation;
import com.aspose.slides.SaveFormat;
import com.aspose.slides.ShapeType;
import com.dsphotoeditor.sdk.activity.DsPhotoEditorActivity;
import com.dsphotoeditor.sdk.utils.DsPhotoEditorConstants;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class Details_Flats extends AppCompatActivity {

    Bitmap b;
    Bitmap front_image = null, long_image = null, parking_image = null, terrace_image = null, inner1_image = null, inner2_image = null, pantry_image = null;


    // for front image
    int front_camera = 1;
    int front_gallery = 2;
    int front_edit_final = 3;

    // for terrace view image
    int terrace_camera = 4;
    int terrace_gallery = 5;
    int terrace_edit_final = 6;

    // for long view image
    int long_camera = 7;
    int long_gallery = 8;
    int long_edit_final = 9;

    // for parking view image
    int parking_camera = 10;
    int parking_gallery = 11;
    int parking_edit_final = 12;

    // for inner 1
    int inner1_camera = 13;
    int inner1_gallery = 14;
    int inner1_edit_final = 15;

    // for inner 2
    int inner2_camera = 16;
    int inner2_gallery = 17;
    int inner2_edit_final = 18;

    // for pantry
    int pantry_camera = 19;
    int pantry_gallery = 20;
    int pantry_edit_final = 21;

    // for email
    int req_code = 100;
    Uri uri_mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        setContentView(R.layout.activity_details_flats);




        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 101);
        }

        // FOR GIVING PERMISSIONS TO WRITE IN EXTERNAL STORAGE
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);

        try {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 101);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void onClick(View view){
        EditText fileName = findViewById(R.id.File_name);
        EditText conslt_name = findViewById(R.id.product_name_et);
        EditText location = findViewById(R.id.location);
        EditText carpet_area = findViewById(R.id.carpet_Area);
        EditText floor = findViewById(R.id.Floor);
        EditText bed_room = findViewById(R.id.bed_room);
        EditText hall_room = findViewById(R.id.hall_room);
        EditText kitchen = findViewById(R.id.kitchen);
        EditText furnished_Shell = findViewById(R.id.shell);
        EditText wash_room = findViewById(R.id.washroom);
        EditText parking = findViewById(R.id.parking);
        EditText asking_Rent = findViewById(R.id.asking_rent);
        EditText security_deposit = findViewById(R.id.security_deposit);
        Toast.makeText(Details_Flats.this, "Loading", Toast.LENGTH_SHORT).show();



        try {
            Presentation pres = new Presentation(getResources().openRawResource(R.raw.template_flat));
            ISlide slide = pres.getSlides().get_Item(0);
            ISlide slide2 = pres.getSlides().get_Item(1);
            ISlide slide3 = pres.getSlides().get_Item(2);
            ISlide slide4 = pres.getSlides().get_Item(3);
            ISlide slide5 = pres.getSlides().get_Item(4);
            ISlide slide6 = pres.getSlides().get_Item(5);
            ISlide slide7 = pres.getSlides().get_Item(6);
            ISlide slide8 = pres.getSlides().get_Item(7);
            ISlide slide9 = pres.getSlides().get_Item(8);

            ITextFrame tf1 = ((IAutoShape)slide2.getShapes().get_Item(1)).getTextFrame();
            ITextFrame tf2 = ((IAutoShape)slide2.getShapes().get_Item(2)).getTextFrame();
            ITextFrame tf3 = ((IAutoShape)slide2.getShapes().get_Item(3)).getTextFrame();
            ITextFrame tf4 = ((IAutoShape)slide2.getShapes().get_Item(4)).getTextFrame();
            ITextFrame tf5 = ((IAutoShape)slide2.getShapes().get_Item(5)).getTextFrame();
            ITextFrame tf6 = ((IAutoShape)slide2.getShapes().get_Item(6)).getTextFrame();
            ITextFrame tf7 = ((IAutoShape)slide2.getShapes().get_Item(7)).getTextFrame();
            ITextFrame tf8 = ((IAutoShape)slide2.getShapes().get_Item(8)).getTextFrame();
            ITextFrame tf9 = ((IAutoShape)slide2.getShapes().get_Item(9)).getTextFrame();
            ITextFrame tf10 = ((IAutoShape)slide2.getShapes().get_Item(10)).getTextFrame();
            ITextFrame tf11 = ((IAutoShape)slide2.getShapes().get_Item(11)).getTextFrame();
            ITextFrame tf12 = ((IAutoShape)slide2.getShapes().get_Item(12)).getTextFrame();
            //ITextFrame tf3 = ((IAutoShape)slide.getShapes().get_Item(2).

            tf1.setText("Location : " + location.getText());

            tf2.setText("Consultant Name : " + conslt_name.getText());

            tf3.setText("Carpet Area : " + carpet_area.getText() + " sq. ft.");

            tf4.setText("Floor Area : " + floor.getText() + " sq. ft.");

            tf5.setText("Bed room : " + bed_room.getText());

            tf6.setText("Hall room : " + hall_room.getText());

            tf7.setText("Kitchen : " + kitchen.getText());

            tf8.setText("Furnished Shell : " + furnished_Shell.getText());

            tf9.setText("Wash room : " + wash_room.getText());

            tf10.setText("Parking Rent : " + parking.getText());

            tf11.setText("Asking Rent : " + asking_Rent.getText());

            tf12.setText("Security Deposit : " + security_deposit.getText());


//            IAutoShape ashp = sid.getShapes().addAutoShape(ShapeType.Rectangle, 150, 75, 150, 50);
//            ashp.addTextFrame(" ");
//            ITextFrame txtFrame = ashp.getTextFrame();
//            IParagraph para = txtFrame.getParagraphs().get_Item(0);
//            IPortion portion = para.getPortions().get_Item(0);
//            portion.setText("YASH IS HERE");
            if(front_image != null){
            IPPImage frnt_image = pres.getImages().addImage(front_image);
            slide3.getShapes().addPictureFrame(ShapeType.Rectangle, 70, 50, 400, 400, frnt_image);}

            if(terrace_image != null){
            IPPImage trc_img = pres.getImages().addImage(terrace_image);
            slide4.getShapes().addPictureFrame(ShapeType.Rectangle, 70, 50, 400, 400, trc_img);}

            if(long_image != null){
            IPPImage lng_img = pres.getImages().addImage(long_image);
            slide5.getShapes().addPictureFrame(ShapeType.Rectangle, 70, 50, 400, 400, lng_img);}

            if(parking_image != null){
            IPPImage prkng_img = pres.getImages().addImage(parking_image);
            slide6.getShapes().addPictureFrame(ShapeType.Rectangle, 70, 50, 400, 400, prkng_img);}

            if(inner1_image != null){
            IPPImage inr1_img = pres.getImages().addImage(inner1_image);
            slide7.getShapes().addPictureFrame(ShapeType.Rectangle, 70, 50, 400, 400, inr1_img);}

            if(inner2_image != null){
            IPPImage inr2_img = pres.getImages().addImage(inner2_image);
            slide8.getShapes().addPictureFrame(ShapeType.Rectangle, 70, 50, 400, 400, inr2_img);}

            if(pantry_image != null){
            IPPImage pntry_img = pres.getImages().addImage(pantry_image);
            slide9.getShapes().addPictureFrame(ShapeType.Rectangle, 70, 50, 400, 400, pntry_img);}

            //imgx = pres.getImages().addImage();
            //pres.save("demo.pptx", com.aspose.slides.SaveFormat.Pptx);
            String sdcardPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath() + File.separator;
            pres.save(sdcardPath + fileName.getText().toString() +".pptx", SaveFormat.Pptx);
            //pres.save(sdCardPath + "Textbox.pptx", com.aspose.slides.SaveFormat.Pptx);
            Toast.makeText(Details_Flats.this, "successfull in sd card", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(Details_Flats.this, "unsuccessfull in sd card", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

    }

    // for capturing image
    public void capture(View view){

        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        switch (view.getId()){
            case R.id.camera_image_btn1:{
                startActivityForResult(i, front_camera);
                break;
            }
            case R.id.camera_image_btn2: {
                startActivityForResult(i, terrace_camera);
                break;
            }
            case R.id.camera_image_btn3: {
                startActivityForResult(i, long_camera);
                break;
            }
            case R.id.camera_image_btn4: {
                startActivityForResult(i, parking_camera);
                break;
            }
            case R.id.camera_image_btn5: {
                startActivityForResult(i, inner1_camera);
                break;
            }
            case R.id.camera_image_btn6: {
                startActivityForResult(i, inner2_camera);
                break;
            }
            case R.id.camera_image_btn7: {
                startActivityForResult(i, pantry_camera);
                break;
            }
        }
    }

    // for gallery image
    public void gallery(View view){
        Intent inte = new Intent(Intent.ACTION_PICK);
        inte.setType("image/*");

        switch (view.getId()){
            case R.id.gallery_image_btn1:{
                startActivityForResult(inte, front_gallery);
                break;
            }
            case R.id.gallery_image_btn2: {
                startActivityForResult(inte, terrace_gallery);
                break;
            }
            case R.id.gallery_image_btn3: {
                startActivityForResult(inte, long_gallery);
                break;
            }
            case R.id.gallery_image_btn4: {
                startActivityForResult(inte, parking_gallery);
                break;
            }
            case R.id.gallery_image_btn5: {
                startActivityForResult(inte, inner1_gallery);
                break;
            }
            case R.id.gallery_image_btn6: {
                startActivityForResult(inte, inner2_gallery);
                break;
            }
            case R.id.gallery_image_btn7: {
                startActivityForResult(inte, pantry_gallery);
                break;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestcode, int resultcode, Intent data) {

        super.onActivityResult(requestcode, resultcode, data);
        if(resultcode == RESULT_OK){
            Uri uri = data.getData();


            // front
            if(requestcode == front_camera) {
                b = (Bitmap)data.getExtras().get("data");
                Uri imgUri = getImageUri(this, b);
                startEditor(imgUri, front_edit_final);
            }
            else if(requestcode == front_gallery){
                startEditor(uri, front_edit_final);
            }

            else if(requestcode == front_edit_final){
                ImageView upload_front = findViewById(R.id.front_upload_image);
                Bitmap bt1;
                upload_front.setImageURI(uri);
                try {
                    bt1 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                    front_image = getResizedBitmap(bt1, 700);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(), "PHOTO SAVED", Toast.LENGTH_SHORT).show();
            }

            // terrace
            else if(requestcode == terrace_camera) {
                b = (Bitmap)data.getExtras().get("data");
                Uri imgUrix = getImageUri(this, b);
                startEditor(imgUrix, terrace_edit_final);
            }
            else if(requestcode == terrace_gallery){
                startEditor(uri, terrace_edit_final);
            }

            else if(requestcode == terrace_edit_final){
                ImageView long_front = findViewById(R.id.terrace_upload_image);
                Bitmap bt2;
                long_front.setImageURI(uri);
                try {
                    bt2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                    terrace_image = getResizedBitmap(bt2, 700);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(), "PHOTO SAVED", Toast.LENGTH_SHORT).show();
            }

            // long
            else if(requestcode == long_camera) {
                b = (Bitmap)data.getExtras().get("data");
                Uri imgUrix = getImageUri(this, b);
                startEditor(imgUrix, long_edit_final);
            }
            else if(requestcode == long_gallery){
                startEditor(uri, long_edit_final);
            }

            else if(requestcode == long_edit_final){
                ImageView long_front = findViewById(R.id.Long_upload_image);
                Bitmap bt2;
                long_front.setImageURI(uri);
                try {
                    bt2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                    long_image = getResizedBitmap(bt2, 700);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(), "PHOTO SAVED", Toast.LENGTH_SHORT).show();
            }


            // parking
            else if(requestcode == parking_camera) {
                b = (Bitmap)data.getExtras().get("data");
                Uri imgUrix = getImageUri(this, b);
                startEditor(imgUrix, parking_edit_final);
            }
            else if(requestcode == parking_gallery){
                startEditor(uri, parking_edit_final);
            }

            else if(requestcode == parking_edit_final){
                ImageView long_front = findViewById(R.id.parking_upload_image);
                Bitmap bt2;
                long_front.setImageURI(uri);
                try {
                    bt2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                    parking_image = getResizedBitmap(bt2, 700);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(), "PHOTO SAVED", Toast.LENGTH_SHORT).show();
            }


            // inner 1
            else if(requestcode == inner1_camera) {
                b = (Bitmap)data.getExtras().get("data");
                Uri imgUrix = getImageUri(this, b);
                startEditor(imgUrix, inner1_edit_final);
            }
            else if(requestcode == inner1_gallery){
                startEditor(uri, inner1_edit_final);
            }

            else if(requestcode == inner1_edit_final){
                ImageView long_front = findViewById(R.id.inner1_upload_image);
                Bitmap bt2;
                long_front.setImageURI(uri);
                try {
                    bt2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                    inner1_image = getResizedBitmap(bt2, 700);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(), "PHOTO SAVED", Toast.LENGTH_SHORT).show();
            }

            // inner 2
            else if(requestcode == inner2_camera) {
                b = (Bitmap)data.getExtras().get("data");
                Uri imgUrix = getImageUri(this, b);
                startEditor(imgUrix, inner2_edit_final);
            }
            else if(requestcode == inner2_gallery){
                startEditor(uri, inner2_edit_final);
            }

            else if(requestcode == inner2_edit_final){
                ImageView long_front = findViewById(R.id.inner2_upload_image);
                Bitmap bt2;
                long_front.setImageURI(uri);
                try {
                    bt2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                    inner2_image = getResizedBitmap(bt2, 700);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(), "PHOTO SAVED", Toast.LENGTH_SHORT).show();
            }

            // pantry
            else if(requestcode == pantry_camera) {
                b = (Bitmap)data.getExtras().get("data");
                Uri imgUrix = getImageUri(this, b);
                startEditor(imgUrix, pantry_edit_final);
            }
            else if(requestcode == pantry_gallery){
                startEditor(uri, pantry_edit_final);
            }

            else if(requestcode == pantry_edit_final){
                ImageView long_front = findViewById(R.id.pantry_upload_image);
                Bitmap bt2;
                long_front.setImageURI(uri);
                try {
                    bt2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                    pantry_image = getResizedBitmap(bt2, 700);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(), "PHOTO SAVED", Toast.LENGTH_SHORT).show();
            }


            else if(requestcode == req_code){
                if(data.getData() == null){
                    return;
                }
                uri_mail = data.getData();
                try{
                    final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                    emailIntent.setType("plain/text");
                    emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"yash"});
                    emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "subject");
                    if (uri != null) {
                        emailIntent.putExtra(Intent.EXTRA_STREAM, uri);
                    }
                    emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "hey");
                    this.startActivity(Intent.createChooser(emailIntent, "Sending email..."));
                } catch (Throwable t) {
                    Toast.makeText(this, "Request failed try again: "+ t.toString(), Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    // for photo editor intent

    public void startEditor(Uri uri, int req_c){
        Intent intent = new Intent(Details_Flats.this, DsPhotoEditorActivity.class);

        intent.setData(uri);
        // set directory name
        intent.putExtra(DsPhotoEditorConstants.DS_PHOTO_EDITOR_OUTPUT_DIRECTORY, "Images");
        // set toolbar color
        intent.putExtra(DsPhotoEditorConstants.DS_TOOL_BAR_BACKGROUND_COLOR, Color.parseColor("#00ADB5"));
        // set background
        intent.putExtra(DsPhotoEditorConstants.DS_MAIN_BACKGROUND_COLOR, Color.parseColor("#393E46"));
        // tools to hide
        intent.putExtra(DsPhotoEditorConstants.DS_PHOTO_EDITOR_TOOLS_TO_HIDE,
                new int[]{DsPhotoEditorActivity.TOOL_WARMTH, DsPhotoEditorActivity.TOOL_PIXELATE});
        // start activty for result
        startActivityForResult(intent, req_c);
    }


    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float)width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }
}