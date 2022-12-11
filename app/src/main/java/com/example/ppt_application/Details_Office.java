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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class Details_Office extends AppCompatActivity {

    Bitmap b;
    Bitmap front_view=null, long_view=null, parking_view=null, inner_1_view=null, inner_2_view=null, inner_3_view=null, pantry_view=null, terrace_view=null, wash_room_view=null, floor_map_view = null;

    int front_camera = 1;
    int front_gallery = 2;
    int front_edit_final = 3;

    int long_camera = 4;
    int long_gallery = 5;
    int long_edit_final = 6;

    int parking_camera = 7;
    int parking_gallery = 8;
    int parking_edit_final = 9;

    int inner_1_camera = 10;
    int inner_1_gallery = 11;
    int inner_1_edit_final = 12;

    int inner_2_camera = 13;
    int inner_2_gallery = 14;
    int inner_2_edit_final = 15;

    int inner_3_camera = 16;
    int inner_3_gallery = 17;
    int inner_3_edit_final = 18;

    int pantry_camera = 19;
    int pantry_gallery = 20;
    int pantry_edit_final = 21;

    int terrace_camera = 22;
    int terrace_gallery = 23;
    int terrace_edit_final = 24;

    int wash_room_camera = 25;
    int wash_room_gallery = 26;
    int wash_room_edit_final = 27;

    int floor_map_camera = 28;
    int floor_map_gallery = 29;
    int floor_map_edit_final = 30;

    // for email
    int req_code = 1;
    Uri uri_mail;

    Double latitude, longitude;
    private GpsTracker gpsTracker;

    LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        setContentView(R.layout.activity_details_office);



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


    public void mail(View view){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        startActivityForResult(intent, req_code);
    }


    public void onClickPPT(View view){
        EditText fileName = findViewById(R.id.File_name);

        EditText location = findViewById(R.id.location);
        EditText consultant_name = findViewById(R.id.consultant_name);
        EditText consultant_contact = findViewById(R.id.consultant_contact);
        EditText area_carpet = findViewById(R.id.area_carpet);
        EditText floor = findViewById(R.id.floor);
        EditText asking_rent = findViewById(R.id.asking_rent);

        EditText description = findViewById(R.id.description);

        Toast.makeText(Details_Office.this, "Loading", Toast.LENGTH_SHORT).show();

        try{
            Presentation pres = new Presentation(getResources().openRawResource(R.raw.template_office));       // ***** you have to change this ppt accordingly *****

            ISlide slide1 = pres.getSlides().get_Item(1);
            ISlide slide2 = pres.getSlides().get_Item(2);
            ISlide slide3 = pres.getSlides().get_Item(3);
            ISlide slide4 = pres.getSlides().get_Item(4);
            ISlide slide5 = pres.getSlides().get_Item(5);
            ISlide slide6 = pres.getSlides().get_Item(6);
            ISlide slide7 = pres.getSlides().get_Item(7);
            ISlide slide8 = pres.getSlides().get_Item(8);
            ISlide slide9 = pres.getSlides().get_Item(9);
            ISlide slide10 = pres.getSlides().get_Item(10);
            ISlide slide11 = pres.getSlides().get_Item(11);


            ITextFrame tf1 = ((IAutoShape)slide1.getShapes().get_Item(0)).getTextFrame();
            ITextFrame tf2 = ((IAutoShape)slide1.getShapes().get_Item(1)).getTextFrame();
            ITextFrame tf3 = ((IAutoShape)slide1.getShapes().get_Item(2)).getTextFrame();
            ITextFrame tf4 = ((IAutoShape)slide1.getShapes().get_Item(3)).getTextFrame();
            ITextFrame tf5 = ((IAutoShape)slide1.getShapes().get_Item(4)).getTextFrame();
            ITextFrame tf6 = ((IAutoShape)slide1.getShapes().get_Item(5)).getTextFrame();
            ITextFrame tf7 = ((IAutoShape)slide1.getShapes().get_Item(6)).getTextFrame();


            tf1.setText("Location : " + location.getText());

            tf2.setText("Consultant Name : " + consultant_name.getText());

            tf3.setText("Consultant Contact : " + consultant_contact.getText());

            tf4.setText("Area of Carpet in sq.ft : " + area_carpet.getText());

            tf5.setText("Floor : " + floor.getText());

            tf6.setText("Asking Rent : " + asking_rent.getText());

            tf7.setText("Description : " + description.getText());


            if(front_view != null){
            IPPImage front_image = pres.getImages().addImage(front_view);
            slide2.getShapes().addPictureFrame(ShapeType.Rectangle, 70, 50, 400, 400, front_image);}

            if(long_view != null){
            IPPImage long_image = pres.getImages().addImage(long_view);
            slide3.getShapes().addPictureFrame(ShapeType.Rectangle, 70, 50, 400, 400, long_image);}

            if(parking_view != null){
            IPPImage parking_image = pres.getImages().addImage(parking_view);
            slide4.getShapes().addPictureFrame(ShapeType.Rectangle, 70, 50, 400, 400, parking_image);}

            if(inner_1_view != null){
            IPPImage inner_1_image = pres.getImages().addImage(inner_1_view);
            slide5.getShapes().addPictureFrame(ShapeType.Rectangle, 70, 50, 400, 400, inner_1_image);}

            if(inner_2_view != null){
            IPPImage inner_2_image = pres.getImages().addImage(inner_2_view);
            slide6.getShapes().addPictureFrame(ShapeType.Rectangle, 70, 50, 400, 400, inner_2_image);}

            if(inner_3_view != null){
            IPPImage inner_3_image = pres.getImages().addImage(inner_3_view);
            slide7.getShapes().addPictureFrame(ShapeType.Rectangle, 70, 50, 400, 400, inner_3_image);}

            if(pantry_view != null){
            IPPImage pantry_image = pres.getImages().addImage(pantry_view);
            slide8.getShapes().addPictureFrame(ShapeType.Rectangle, 70, 50, 400, 400, pantry_image);}

            if(terrace_view != null){
            IPPImage terrace_image = pres.getImages().addImage(terrace_view);
            slide9.getShapes().addPictureFrame(ShapeType.Rectangle, 70, 50, 400, 400, terrace_image);}

            if(wash_room_view != null){
            IPPImage wash_room_image = pres.getImages().addImage(wash_room_view);
            slide10.getShapes().addPictureFrame(ShapeType.Rectangle, 70, 50, 400, 400, wash_room_image);}

            if(floor_map_view != null){
            IPPImage floor_map_image = pres.getImages().addImage(floor_map_view);
            slide11.getShapes().addPictureFrame(ShapeType.Rectangle, 70, 50, 400, 400, floor_map_image);}


            String sdcardPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath() + File.separator;
            pres.save(sdcardPath + fileName.getText().toString() +".pptx", SaveFormat.Pptx);

            Toast.makeText(Details_Office.this, "successful in sd card", Toast.LENGTH_SHORT).show();
        }
        catch(Exception e){
            Toast.makeText(Details_Office.this, "unsuccessful in sd card", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

    }

    public void onClickExcel(View view){
        EditText fileName = findViewById(R.id.File_name);

        EditText location = findViewById(R.id.location);
        EditText consultant_name = findViewById(R.id.consultant_name);
        EditText consultant_contact = findViewById(R.id.consultant_contact);
        EditText area_carpet = findViewById(R.id.area_carpet);
        EditText floor = findViewById(R.id.floor);
        EditText asking_rent = findViewById(R.id.asking_rent);

        EditText description = findViewById(R.id.description);

        try{

            Workbook workbook = new Workbook(getResources().openRawResource(R.raw.database));

            workbook.getWorksheets().get(0).getCells().get("A2").setValue(location.getText());
            workbook.getWorksheets().get(9).getCells().get("B2").setValue(consultant_name.getText());
            workbook.getWorksheets().get(10).getCells().get("C2").setValue(consultant_contact.getText());
            workbook.getWorksheets().get(0).getCells().get("D2").setValue(area_carpet.getText());
            workbook.getWorksheets().get(12).getCells().get("E2").setValue(floor.getText());
            workbook.getWorksheets().get(14).getCells().get("F2").setValue(asking_rent.getText());

            workbook.getWorksheets().get(28).getCells().get("G2").setValue(description.getText());

            String sdcardPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath() + File.separator;
            workbook.save(sdcardPath +fileName.getText().toString()+ ".xlsx", FileFormatType.XLSX);

            Toast.makeText(Details_Office.this, "successful in sd card", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            Toast.makeText(Details_Office.this, "unsuccessful in sd card", Toast.LENGTH_SHORT).show();
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
            case R.id.camera_image_btn3: {
                startActivityForResult(i, parking_camera);
                break;
            }
            case R.id.camera_image_btn4: {
                startActivityForResult(i, inner_1_camera);
                break;
            }
            case R.id.camera_image_btn5: {
                startActivityForResult(i, inner_2_camera);
                break;
            }
            case R.id.camera_image_btn6: {
                startActivityForResult(i, inner_3_camera);
                break;
            }
            case R.id.camera_image_btn7: {
                startActivityForResult(i, pantry_camera);
                break;
            }
            case R.id.camera_image_btn8: {
                startActivityForResult(i, terrace_camera);
                break;
            }
            case R.id.camera_image_btn9: {
                startActivityForResult(i, wash_room_camera);
                break;
            }
            case R.id.camera_image_btn10: {
                startActivityForResult(i, floor_map_camera);
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
            case R.id.gallery_image_btn3: {
                startActivityForResult(inte, parking_gallery);
                break;
            }
            case R.id.gallery_image_btn4: {
                startActivityForResult(inte, inner_1_gallery);
                break;
            }
            case R.id.gallery_image_btn5: {
                startActivityForResult(inte, inner_2_gallery);
                break;
            }
            case R.id.gallery_image_btn6: {
                startActivityForResult(inte, inner_3_gallery);
                break;
            }
            case R.id.gallery_image_btn7: {
                startActivityForResult(inte, pantry_gallery);
                break;
            }
            case R.id.gallery_image_btn8: {
                startActivityForResult(inte, terrace_gallery);
                break;
            }
            case R.id.gallery_image_btn9: {
                startActivityForResult(inte, wash_room_gallery);
                break;
            }
            case R.id.gallery_image_btn10: {
                startActivityForResult(inte, floor_map_gallery);
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
                    front_view = getResizedBitmap(bt1, 1000);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(), "PHOTO SAVED", Toast.LENGTH_SHORT).show();
            }

            else if(requestcode == long_camera) {
                b = (Bitmap)data.getExtras().get("data");
                Uri imgUri = getImageUri(this, b);
                startEditor(imgUri, long_edit_final);
            }
            else if(requestcode == long_gallery){
                startEditor(uri, long_edit_final);
            }

            else if(requestcode == long_edit_final){
                ImageView upload_long = findViewById(R.id.long_upload_image);
                Bitmap bt2;
                upload_long.setImageURI(uri);
                try {
                    bt2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                    long_view = getResizedBitmap(bt2, 1000);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(), "PHOTO SAVED", Toast.LENGTH_SHORT).show();
            }

            else if(requestcode == parking_camera) {
                b = (Bitmap)data.getExtras().get("data");
                Uri imgUri = getImageUri(this, b);
                startEditor(imgUri, parking_edit_final);
            }
            else if(requestcode == parking_gallery){
                startEditor(uri, parking_edit_final);
            }

            else if(requestcode == parking_edit_final){
                ImageView upload_parking = findViewById(R.id.parking_upload_image);
                Bitmap bt2;
                upload_parking.setImageURI(uri);
                try {
                    bt2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                    parking_view = getResizedBitmap(bt2, 1000);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(), "PHOTO SAVED", Toast.LENGTH_SHORT).show();
            }

            else if(requestcode == inner_1_camera) {
                b = (Bitmap)data.getExtras().get("data");
                Uri imgUri = getImageUri(this, b);
                startEditor(imgUri, inner_1_edit_final);
            }
            else if(requestcode == inner_1_gallery){
                startEditor(uri, inner_1_edit_final);
            }

            else if(requestcode == inner_1_edit_final){
                ImageView upload_inner_1 = findViewById(R.id.inner_1_upload_image);
                Bitmap bt2;
                upload_inner_1.setImageURI(uri);
                try {
                    bt2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                    inner_1_view = getResizedBitmap(bt2, 1000);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(), "PHOTO SAVED", Toast.LENGTH_SHORT).show();
            }

            else if(requestcode == inner_2_camera) {
                b = (Bitmap)data.getExtras().get("data");
                Uri imgUri = getImageUri(this, b);
                startEditor(imgUri, inner_2_edit_final);
            }
            else if(requestcode == inner_2_gallery){
                startEditor(uri, inner_2_edit_final);
            }

            else if(requestcode == inner_2_edit_final){
                ImageView upload_inner_2 = findViewById(R.id.inner_2_upload_image);
                Bitmap bt2;
                upload_inner_2.setImageURI(uri);
                try {
                    bt2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                    inner_2_view = getResizedBitmap(bt2, 1000);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(), "PHOTO SAVED", Toast.LENGTH_SHORT).show();
            }

            else if(requestcode == inner_3_camera) {
                b = (Bitmap)data.getExtras().get("data");
                Uri imgUri = getImageUri(this, b);
                startEditor(imgUri, inner_3_edit_final);
            }
            else if(requestcode == inner_3_gallery){
                startEditor(uri, inner_3_edit_final);
            }

            else if(requestcode == inner_3_edit_final){
                ImageView upload_inner_3 = findViewById(R.id.inner_3_upload_image);
                Bitmap bt2;
                upload_inner_3.setImageURI(uri);
                try {
                    bt2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                    inner_3_view = getResizedBitmap(bt2, 1000);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(), "PHOTO SAVED", Toast.LENGTH_SHORT).show();
            }

            else if(requestcode == pantry_camera) {
                b = (Bitmap)data.getExtras().get("data");
                Uri imgUri = getImageUri(this, b);
                startEditor(imgUri, pantry_edit_final);
            }
            else if(requestcode == pantry_gallery){
                startEditor(uri, pantry_edit_final);
            }

            else if(requestcode == pantry_edit_final){
                ImageView upload_pantry = findViewById(R.id.pantry_upload_image);
                Bitmap bt2;
                upload_pantry.setImageURI(uri);
                try {
                    bt2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                    pantry_view = getResizedBitmap(bt2, 1000);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(), "PHOTO SAVED", Toast.LENGTH_SHORT).show();
            }


            else if(requestcode == terrace_camera) {
                b = (Bitmap)data.getExtras().get("data");
                Uri imgUri = getImageUri(this, b);
                startEditor(imgUri, terrace_edit_final);
            }
            else if(requestcode == terrace_gallery){
                startEditor(uri, terrace_edit_final);
            }

            else if(requestcode == terrace_edit_final){
                ImageView upload_terrace = findViewById(R.id.terrace_upload_image);
                Bitmap bt2;
                upload_terrace.setImageURI(uri);
                try {
                    bt2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                    terrace_view = getResizedBitmap(bt2, 1000);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(), "PHOTO SAVED", Toast.LENGTH_SHORT).show();
            }

            else if(requestcode == wash_room_camera) {
                b = (Bitmap)data.getExtras().get("data");
                Uri imgUri = getImageUri(this, b);
                startEditor(imgUri, wash_room_edit_final);
            }
            else if(requestcode == wash_room_gallery){
                startEditor(uri, wash_room_edit_final);
            }

            else if(requestcode == wash_room_edit_final){
                ImageView upload_wash_room = findViewById(R.id.wash_room_upload_image);
                Bitmap bt2;
                upload_wash_room.setImageURI(uri);
                try {
                    bt2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                    wash_room_view = getResizedBitmap(bt2, 1000);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(), "PHOTO SAVED", Toast.LENGTH_SHORT).show();
            }

            else if(requestcode == floor_map_camera) {
                b = (Bitmap)data.getExtras().get("data");
                Uri imgUri = getImageUri(this, b);
                startEditor(imgUri, floor_map_edit_final);
            }
            else if(requestcode == floor_map_gallery){
                startEditor(uri, floor_map_edit_final);
            }

            else if(requestcode == floor_map_edit_final){
                ImageView floor_upload_map = findViewById(R.id.floor_map_upload_image);
                Bitmap bt2;
                floor_upload_map.setImageURI(uri);
                try {
                    bt2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                    floor_map_view = getResizedBitmap(bt2, 1000);
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
        Intent intent = new Intent(Details_Office.this, DsPhotoEditorActivity.class);

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
        inImage.compress(Bitmap.CompressFormat.PNG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }



    public void get_Latitude_longitude() {
        gpsTracker = new GpsTracker(Details_Office.this);
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


}