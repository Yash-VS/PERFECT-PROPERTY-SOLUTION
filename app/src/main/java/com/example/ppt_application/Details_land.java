package com.example.ppt_application;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
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
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.aspose.cells.FileFormatType;
import com.aspose.cells.Workbook;
import com.aspose.slides.IAutoShape;
import com.aspose.slides.IPPImage;
import com.aspose.slides.ISlide;
import com.aspose.slides.ITextFrame;
import com.aspose.slides.Presentation;
import com.aspose.slides.SaveFormat;
import com.aspose.slides.ShapeType;
import com.dsphotoeditor.sdk.activity.DsPhotoEditorActivity;
import com.dsphotoeditor.sdk.utils.DsPhotoEditorConstants;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class Details_land extends AppCompatActivity {

    Bitmap b;
    Bitmap front_view = null;
    Bitmap long_view = null;

    // for front image
    int front_camera = 13;
    int front_gallery = 11;
    int front_edit_final = 111;

    // for long view image
    int long_camera = 23;
    int long_gallery = 24;
    int long_edit_final = 223;

    // for email
    int req_code = 1;
    Uri uri_mail;


    Double latitude, longitude;
    private GpsTracker gpsTracker;

    LottieAnimationView lottieAnimationView;


    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        setContentView(R.layout.activity_details_land);

        EditText location = findViewById(R.id.location);
        EditText fileName = findViewById(R.id.File_name);
        EditText consultant_name = findViewById(R.id.consultant_name);
        EditText land_area = findViewById(R.id.land_area);
        EditText rent = findViewById(R.id.asking_rent);
        EditText description = findViewById(R.id.description);


        Intent intent = getIntent();
        key = intent.getStringExtra("key");

        if(key!=null){
            FirebaseDatabase.getInstance().getReference("Lands").child(key).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()){
                        location.setText(snapshot.child("location").getValue().toString());
                        fileName.setText(key);
                        consultant_name.setText(snapshot.child("consultant_name").getValue().toString());
                        land_area.setText(snapshot.child("land_area").getValue().toString());
                        rent.setText(snapshot.child("rent").getValue().toString());
                        description.setText(snapshot.child("description").getValue().toString());
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }



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

        //get_Latitude_longitude();

    }

//    public void map(View view){
//        Intent intent = new Intent(Details_land.this, Map.class);
//        startActivity(intent);
//    }

    public void mail(View view){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        startActivityForResult(intent, req_code);
    }




    public void onClickPPT(View view){
        EditText location = findViewById(R.id.location);
        EditText fileName = findViewById(R.id.File_name);
        EditText consultant_name = findViewById(R.id.consultant_name);
        EditText land_area = findViewById(R.id.land_area);
        EditText rent = findViewById(R.id.asking_rent);
        EditText description = findViewById(R.id.description);
//        lottieAnimationView = findViewById(R.id.animationView);
//        lottieAnimationView.setVisibility(View.VISIBLE);
        Toast.makeText(Details_land.this, "Loading", Toast.LENGTH_SHORT).show();


        HashMap<String,Object> m = new HashMap<String, Object>();
        m.put("location",location.getText().toString());
        m.put("consultant_name",consultant_name.getText().toString());
        m.put("land_area",land_area.getText().toString());
        m.put("rent",rent.getText().toString());
        m.put("description",description.getText().toString());


        FirebaseDatabase.getInstance().getReference("Lands").child(fileName.getText().toString()).setValue(m);

        try{
            Presentation pres = new Presentation(getResources().openRawResource(R.raw.template_land));    // ***** you have to change this ppt accordingly *****

            ISlide slide1 = pres.getSlides().get_Item(1);
            ISlide slide2 = pres.getSlides().get_Item(2);
            ISlide slide3 = pres.getSlides().get_Item(3);

            ITextFrame tf1 = ((IAutoShape)slide1.getShapes().get_Item(1)).getTextFrame();
            ITextFrame tf2 = ((IAutoShape)slide1.getShapes().get_Item(2)).getTextFrame();
            ITextFrame tf3 = ((IAutoShape)slide1.getShapes().get_Item(3)).getTextFrame();
            ITextFrame tf4 = ((IAutoShape)slide1.getShapes().get_Item(4)).getTextFrame();
            ITextFrame tf5 = ((IAutoShape)slide1.getShapes().get_Item(5)).getTextFrame();

            tf1.setText("Location : " + location.getText());
            tf2.setText("Consultant Name : " + consultant_name.getText());
            tf3.setText("Offered Land Area : " + land_area.getText());
            tf4.setText("Asking Rent per Sq.ft. : " + rent.getText());
            tf5.setText("Description : " + description.getText());

            if(front_view != null){
            IPPImage front_image = pres.getImages().addImage(front_view);
            slide2.getShapes().addPictureFrame(ShapeType.Rectangle, 50, 50, 500, 450, front_image);}

            if(long_view != null){
            IPPImage long_image = pres.getImages().addImage(long_view);
            slide3.getShapes().addPictureFrame(ShapeType.Rectangle, 50, 50, 500, 450, long_image);}

            String sdcardPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath() + File.separator;
            pres.save(sdcardPath + fileName.getText().toString() +".pptx", SaveFormat.Pptx);
//            lottieAnimationView.setProgress(0);
//            lottieAnimationView.playAnimation();
            //pres.save(sdCardPath + "Textbox.pptx", com.aspose.slides.SaveFormat.Pptx);
            Toast.makeText(Details_land.this, "successful in sd card", Toast.LENGTH_SHORT).show();
        }
        catch(Exception e){
            Toast.makeText(Details_land.this, "unsuccessful in sd card", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

    }

    public void onClickExcel(View view){
        EditText fileName = findViewById(R.id.File_name);
        EditText location = findViewById(R.id.location);
        EditText consultant_name = findViewById(R.id.consultant_name);
        EditText land_area = findViewById(R.id.land_area);
        EditText rent = findViewById(R.id.asking_rent);
        EditText description = findViewById(R.id.description);

        try{
            Workbook workbook = new Workbook(getResources().openRawResource(R.raw.database));

            workbook.getWorksheets().get(0).getCells().get("A2").setValue(location.getText().toString());
            workbook.getWorksheets().get(0).getCells().get("B2").setValue(consultant_name.getText().toString());
            workbook.getWorksheets().get(0).getCells().get("C2").setValue(land_area.getText().toString());
            workbook.getWorksheets().get(0).getCells().get("D2").setValue(rent.getText().toString());
            workbook.getWorksheets().get(0).getCells().get("E2").setValue(description.getText().toString());

            String sdcardPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath() + File.separator;
            workbook.save(sdcardPath +fileName.getText().toString()+ ".xlsx", FileFormatType.XLSX);
            //pres.save(sdCardPath + "Textbox.pptx", com.aspose.slides.SaveFormat.Pptx);
            Toast.makeText(Details_land.this, "successful in sd card", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            Toast.makeText(Details_land.this, "unsuccessful in sd card", Toast.LENGTH_SHORT).show();
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
                startActivityForResult(i, long_camera);
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
                startActivityForResult(inte, long_gallery);
                break;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestcode, int resultcode, Intent data) {

        super.onActivityResult(requestcode, resultcode, data);
        if(resultcode == RESULT_OK){
            Uri uri = data.getData();

            if(requestcode == front_camera) {
                b = (Bitmap)data.getExtras().get("data");
                Uri imgUri = getImageUri(Details_land.this, b);
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
                    front_view = getResizedBitmap(bt1, 100);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(), "PHOTO SAVED", Toast.LENGTH_SHORT).show();
            }

            else if(requestcode == long_camera) {
                b = (Bitmap)data.getExtras().get("data");
                Uri imgUrix = getImageUri(Details_land.this, b);
                startEditor(imgUrix, long_edit_final);
            }
            else if(requestcode == long_gallery){
                startEditor(uri, long_edit_final);
            }

            else if(requestcode == long_edit_final){
                ImageView long_front = findViewById(R.id.long_upload_image);
                Bitmap bt2;
                long_front.setImageURI(uri);
                try {
                    bt2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                    long_view = getResizedBitmap(bt2, 100);
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
        Intent intent = new Intent(Details_land.this, DsPhotoEditorActivity.class);

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



    public void get_Latitude_longitude() {
        gpsTracker = new GpsTracker(Details_land.this);
        if(gpsTracker.canGetLocation()){
            latitude = gpsTracker.getLatitude();
            longitude = gpsTracker.getLongitude();

//            //URL url = new URL("http://dev.virtualearth.net/REST/v1/Imagery/Map/Road/" +latitude + "," + longitude + "/12?mapSize=400,300&pushpin=" + latitude + "," + longitude + "&key=Ag4wn8HUCOUQ7su1kn5q3LtN55CJL88haXspv4Cr4FFI-78xKaslMtkF2F7epwHI");
//            String url = "https://dev.virtualearth.net/REST/V1/Imagery/Map/Streetside/42.6564%2C-73.7638/13?mapSize=600%2C300&format=png&key=Ag4wn8HUCOUQ7su1kn5q3LtN55CJL88haXspv4Cr4FFI-78xKaslMtkF2F7epwHI";
//
//            IPPImage image = pres.getImages().get_Item(4);
//            //IPictureFrame pictureFrame = pres.Slides[0].Shapes.AddPictureFrame(ShapeType.Rectangle, 10, 10, 100, 100, image);
//            IPictureFrame pictureFrame = pres.getSlides().get_Item(4).getShapes().addPictureFrame(ShapeType.Rectangle, 10, 10, 100, 100, image);
//            IHyperlink hyperlink = new Hyperlink(url);
//            pictureFrame.setHyperlinkClick(hyperlink);
            //showLocation.setText("latitude : " + String.valueOf(latitude) + " longitude : " + String.valueOf(longitude));
        }else{
            gpsTracker.showSettingsAlert();
        }
    }

    public void addDetails(){}


}