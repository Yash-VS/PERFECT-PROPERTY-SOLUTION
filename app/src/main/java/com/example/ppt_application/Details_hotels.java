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

public class Details_hotels extends AppCompatActivity {
    Bitmap b;
    Bitmap front_view = null;
    Bitmap long_view = null;
    Bitmap reception_view = null;
    Bitmap parking_view = null;
    Bitmap inner1_view = null;
    Bitmap inner2_view = null;
    Bitmap inner3_view = null;
    Bitmap pantry_view = null;
    Bitmap terrace_view = null;
    Bitmap washroom_view = null;


    // for front image
    int front_camera = 1;
    int front_gallery = 2;
    int front_edit_final = 3;

    // for long view image
    int long_camera = 4;
    int long_gallery = 5;
    int long_edit_final = 6;

    int reception_camera = 7;
    int reception_gallery = 8;
    int reception_edit_final = 9;

    int parking_camera = 10;
    int parking_gallery = 11;
    int parking_edit_final = 12;

    int inner1_camera = 13;
    int inner1_gallery = 14;
    int inner1_edit_final = 15;

    int inner2_camera = 16;
    int inner2_gallery = 17;
    int inner2_edit_final = 18;

    int inner3_camera = 19;
    int inner3_gallery = 20;
    int inner3_edit_final = 21;

    int pantry_camera = 22;
    int pantry_gallery = 23;
    int pantry_edit_final = 24;

    int terrace_camera = 25;
    int terrace_gallery = 26;
    int terrace_edit_final = 27;

    int washroom_camera = 28;
    int washroom_gallery = 29;
    int washroom_edit_final = 30;


    // for email
    int req_code = 100;
    Uri uri_mail;


    Double latitude, longitude;
    private GpsTracker gpsTracker;

    LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        setContentView(R.layout.activity_details_hotels);


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

    public void mail(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        startActivityForResult(intent, req_code);
    }


    public void onClickPPT(View view) {
        EditText location = findViewById(R.id.location);
        EditText fileName = findViewById(R.id.File_name);
        EditText consultant_name = findViewById(R.id.product_name_et);
        EditText carpet_area = findViewById(R.id.carpet_Area);
        EditText rent = findViewById(R.id.asking_rent);
        EditText description = findViewById(R.id.description_tv);
        EditText floor = findViewById(R.id.Floor);
        EditText bed_room = findViewById(R.id.bed_room);
        EditText restaurant = findViewById(R.id.restaurant);
        EditText parking = findViewById(R.id.parking);

        Toast.makeText(Details_hotels.this, "Loading", Toast.LENGTH_SHORT).show();

        try {
            Presentation pres = new Presentation(getResources().openRawResource(R.raw.template_hotel));    // ***** you have to change this ppt accordingly *****

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


            ITextFrame tf1 = ((IAutoShape) slide1.getShapes().get_Item(1)).getTextFrame();
            ITextFrame tf2 = ((IAutoShape) slide1.getShapes().get_Item(2)).getTextFrame();
            ITextFrame tf3 = ((IAutoShape) slide1.getShapes().get_Item(3)).getTextFrame();
            ITextFrame tf4 = ((IAutoShape) slide1.getShapes().get_Item(4)).getTextFrame();
            ITextFrame tf5 = ((IAutoShape) slide1.getShapes().get_Item(5)).getTextFrame();
            ITextFrame tf6 = ((IAutoShape) slide1.getShapes().get_Item(6)).getTextFrame();
            ITextFrame tf7 = ((IAutoShape) slide1.getShapes().get_Item(7)).getTextFrame();
            ITextFrame tf8 = ((IAutoShape) slide1.getShapes().get_Item(8)).getTextFrame();
            ITextFrame tf9 = ((IAutoShape) slide1.getShapes().get_Item(9)).getTextFrame();

            tf1.setText("Location : " + location.getText().toString());
            tf2.setText("Carpet Area : " + carpet_area.getText().toString());
            tf3.setText("Consultant Name : " + consultant_name.getText().toString());
            tf4.setText("Floors:" + floor.getText().toString());
            tf5.setText("Bed Rooms:" + bed_room.getText().toString());
            tf6.setText("Restaurant:" + restaurant.getText().toString());
            tf7.setText("Parking:" + parking.getText().toString());
            tf8.setText("Asking Rent per Sq.ft. : " + rent.getText().toString());
            tf9.setText("Description : " + description.getText().toString());

            if (front_view != null) {
                IPPImage front_image = pres.getImages().addImage(front_view);
                slide2.getShapes().addPictureFrame(ShapeType.Rectangle, 70, 50, 400, 400, front_image);
            }

            if (long_view != null) {
                IPPImage long_image = pres.getImages().addImage(long_view);
                slide3.getShapes().addPictureFrame(ShapeType.Rectangle, 70, 50, 400, 400, long_image);
            }

            if (reception_view != null) {
                IPPImage receptio = pres.getImages().addImage(reception_view);
                slide4.getShapes().addPictureFrame(ShapeType.Rectangle, 70, 50, 400, 400, receptio);
            }

            if (parking_view != null) {
                IPPImage parkin = pres.getImages().addImage(parking_view);
                slide5.getShapes().addPictureFrame(ShapeType.Rectangle, 70, 50, 400, 400, parkin);
            }

            if (inner1_view != null) {
                IPPImage inner01 = pres.getImages().addImage(inner1_view);
                slide6.getShapes().addPictureFrame(ShapeType.Rectangle, 70, 50, 400, 400, inner01);
            }

            if (inner2_view != null) {
                IPPImage inner02 = pres.getImages().addImage(inner2_view);
                slide7.getShapes().addPictureFrame(ShapeType.Rectangle, 70, 50, 400, 400, inner02);
            }

            if (inner3_view != null) {
                IPPImage inner03 = pres.getImages().addImage(inner3_view);
                slide8.getShapes().addPictureFrame(ShapeType.Rectangle, 70, 50, 400, 400, inner03);
            }

            if (pantry_view != null) {
                IPPImage pantr = pres.getImages().addImage(pantry_view);
                slide9.getShapes().addPictureFrame(ShapeType.Rectangle, 70, 50, 400, 400, pantr);
            }

            if (terrace_view != null) {
                IPPImage terr = pres.getImages().addImage(terrace_view);
                slide10.getShapes().addPictureFrame(ShapeType.Rectangle, 70, 50, 400, 400, terr);
            }

            if (washroom_view != null) {
                IPPImage wash = pres.getImages().addImage(washroom_view);
                slide11.getShapes().addPictureFrame(ShapeType.Rectangle, 70, 50, 400, 400, wash);
            }


            String sdcardPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath() + File.separator;
            pres.save(sdcardPath + fileName.getText().toString() + ".pptx", SaveFormat.Pptx);

            Toast.makeText(Details_hotels.this, "successful in sd card", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(Details_hotels.this, "unsuccessful in sd card", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

    }

    public void onClickExcel(View view) {
        EditText location = findViewById(R.id.location);
        EditText fileName = findViewById(R.id.File_name);
        EditText consultant_name = findViewById(R.id.product_name_et);
        EditText carpet_area = findViewById(R.id.carpet_Area);
        EditText rent = findViewById(R.id.asking_rent);
        EditText description = findViewById(R.id.description_tv);
        EditText floor = findViewById(R.id.Floor);
        EditText bed_room = findViewById(R.id.bed_room);
        EditText restaurant = findViewById(R.id.restaurant);
        EditText parking = findViewById(R.id.parking);

        try {

            Workbook workbook = new Workbook(getResources().openRawResource(R.raw.database));

            workbook.getWorksheets().get(0).getCells().get("A2").setValue(location.getText().toString());
            workbook.getWorksheets().get(0).getCells().get("C2").setValue(consultant_name.getText().toString());
            workbook.getWorksheets().get(0).getCells().get("S2").setValue(carpet_area.getText().toString());
            workbook.getWorksheets().get(0).getCells().get("AF2").setValue(rent.getText().toString());
            workbook.getWorksheets().get(0).getCells().get("BG2").setValue(description.getText().toString());
            workbook.getWorksheets().get(0).getCells().get("U2").setValue(floor.getText().toString());
            workbook.getWorksheets().get(0).getCells().get("BC2").setValue(bed_room.getText().toString());
            workbook.getWorksheets().get(0).getCells().get("BH2").setValue(restaurant.getText().toString());
            workbook.getWorksheets().get(0).getCells().get("V2").setValue(parking.getText().toString());


            String sdcardPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath() + File.separator;
            workbook.save(sdcardPath + fileName.getText().toString() + ".xlsx", FileFormatType.XLSX);
            //pres.save(sdCardPath + "Textbox.pptx", com.aspose.slides.SaveFormat.Pptx);
            Toast.makeText(Details_hotels.this, "successful in sd card", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(Details_hotels.this, "unsuccessful in sd card", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

    }

    // for capturing image
    public void capture(View view) {

        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        switch (view.getId()) {
            case R.id.camera_image_btn1: {
                startActivityForResult(i, front_camera);
                break;
            }
            case R.id.camera_image_btn2: {
                startActivityForResult(i, long_camera);
                break;
            }
            case R.id.camera_image_btn3: {
                startActivityForResult(i, reception_camera);
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
                startActivityForResult(i, inner3_camera);
                break;
            }
            case R.id.camera_image_btn8: {
                startActivityForResult(i, pantry_camera);
                break;
            }
            case R.id.camera_image_btn9: {
                startActivityForResult(i, terrace_camera);
                break;
            }
            case R.id.camera_image_btn10: {
                startActivityForResult(i, washroom_camera);
                break;
            }
        }
    }

    // for gallery image
    public void gallery(View view) {
        Intent inte = new Intent(Intent.ACTION_PICK);
        inte.setType("image/*");

        switch (view.getId()) {
            case R.id.gallery_image_btn1: {
                startActivityForResult(inte, front_gallery);
                break;
            }
            case R.id.gallery_image_btn2: {
                startActivityForResult(inte, long_gallery);
                break;
            }
            case R.id.gallery_image_btn3: {
                startActivityForResult(inte, reception_gallery);
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
                startActivityForResult(inte, inner3_gallery);
                break;
            }
            case R.id.gallery_image_btn8: {
                startActivityForResult(inte, pantry_gallery);
                break;
            }
            case R.id.gallery_image_btn9: {
                startActivityForResult(inte, terrace_gallery);
                break;
            }
            case R.id.gallery_image_btn10: {
                startActivityForResult(inte, washroom_gallery);
                break;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestcode,
                                    int resultcode, Intent data) {

        super.onActivityResult(requestcode, resultcode, data);
        if (resultcode == RESULT_OK) {
            Uri uri = data.getData();

            if (requestcode == front_camera) {
                b = (Bitmap) data.getExtras().get("data");
                Uri imgUri = getImageUri(this, b);
                startEditor(imgUri, front_edit_final);
            }
            else if (requestcode == front_gallery) {
                startEditor(uri, front_edit_final);
            }
            else if (requestcode == front_edit_final) {
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

            else if (requestcode == long_camera) {
                b = (Bitmap) data.getExtras().get("data");
                Uri imgUrix = getImageUri(this, b);
                startEditor(imgUrix, long_edit_final);
            }
            else if (requestcode == long_gallery) {
                startEditor(uri, long_edit_final);
            }
            else if (requestcode == long_edit_final) {
                ImageView long_front = findViewById(R.id.long_upload_image);
                Bitmap bt2;
                long_front.setImageURI(uri);
                try {
                    bt2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                    long_view = getResizedBitmap(bt2, 1000);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(), "PHOTO SAVED", Toast.LENGTH_SHORT).show();
            }

            else if (requestcode == reception_camera) {
                b = (Bitmap) data.getExtras().get("data");
                Uri imgUrix = getImageUri(this, b);
                startEditor(imgUrix, long_edit_final);
            }
            else if (requestcode == reception_gallery) {
                startEditor(uri, reception_edit_final);
            }
            else if (requestcode == reception_edit_final) {
                ImageView upload_reception = findViewById(R.id.reception_upload_image);
                Bitmap bt2;
                upload_reception.setImageURI(uri);
                try {
                    bt2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                    long_view = getResizedBitmap(bt2, 1000);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(), "PHOTO SAVED", Toast.LENGTH_SHORT).show();
            }

            else if (requestcode == parking_camera) {
                b = (Bitmap) data.getExtras().get("data");
                Uri imgUrix = getImageUri(this, b);
                startEditor(imgUrix, parking_edit_final);
            }
            else if (requestcode == parking_gallery) {
                startEditor(uri, parking_edit_final);
            }
            else if (requestcode == parking_edit_final) {
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

            else if (requestcode == inner1_camera) {
                b = (Bitmap) data.getExtras().get("data");
                Uri imgUrix = getImageUri(this, b);
                startEditor(imgUrix, inner1_edit_final);
            }
            else if (requestcode == inner1_gallery) {
                startEditor(uri, inner1_edit_final);
            }
            else if (requestcode == inner1_edit_final) {
                ImageView inner1_front = findViewById(R.id.inner1_upload_image);
                Bitmap bt2;
                inner1_front.setImageURI(uri);
                try {
                    bt2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                    inner1_view = getResizedBitmap(bt2, 1000);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(), "PHOTO SAVED", Toast.LENGTH_SHORT).show();
            }

            else if (requestcode == inner2_camera) {
                b = (Bitmap) data.getExtras().get("data");
                Uri imgUrix = getImageUri(this, b);
                startEditor(imgUrix, inner2_edit_final);
            }
            else if (requestcode == inner2_gallery) {
                startEditor(uri, inner2_edit_final);
            }
            else if (requestcode == inner2_edit_final) {
                ImageView inner2_front = findViewById(R.id.inner2_upload_image);
                Bitmap bt2;
                inner2_front.setImageURI(uri);
                try {
                    bt2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                    inner2_view = getResizedBitmap(bt2, 1000);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(), "PHOTO SAVED", Toast.LENGTH_SHORT).show();
            }

            else if (requestcode == inner3_camera) {
                b = (Bitmap) data.getExtras().get("data");
                Uri imgUrix = getImageUri(this, b);
                startEditor(imgUrix, inner3_edit_final);
            }
            else if (requestcode == inner3_gallery) {
                startEditor(uri, inner3_edit_final);
            }
            else if (requestcode == inner3_edit_final) {
                ImageView inner3_front = findViewById(R.id.inner3_upload_image);
                Bitmap bt2;
                inner3_front.setImageURI(uri);
                try {
                    bt2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                    inner3_view = getResizedBitmap(bt2, 1000);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(), "PHOTO SAVED", Toast.LENGTH_SHORT).show();
            }

            else if (requestcode == pantry_camera) {
                b = (Bitmap) data.getExtras().get("data");
                Uri imgUrix = getImageUri(this, b);
                startEditor(imgUrix, pantry_edit_final);
            }
            else if (requestcode == pantry_gallery) {
                startEditor(uri, pantry_edit_final);
            }
            else if (requestcode == pantry_edit_final) {
                ImageView pantry_front = findViewById(R.id.pantry_upload_image);
                Bitmap bt2;
                pantry_front.setImageURI(uri);
                try {
                    bt2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                    pantry_view = getResizedBitmap(bt2, 1000);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(), "PHOTO SAVED", Toast.LENGTH_SHORT).show();
            }

            else if (requestcode == terrace_camera) {
                b = (Bitmap) data.getExtras().get("data");
                Uri imgUrix = getImageUri(this, b);
                startEditor(imgUrix, terrace_edit_final);
            }
            else if (requestcode == terrace_gallery) {
                startEditor(uri, terrace_edit_final);
            }
            else if (requestcode == terrace_edit_final) {
                ImageView terrace_front = findViewById(R.id.terrace_upload_image);
                Bitmap bt2;
                terrace_front.setImageURI(uri);
                try {
                    bt2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                    terrace_view = getResizedBitmap(bt2, 1000);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(), "PHOTO SAVED", Toast.LENGTH_SHORT).show();
            }

            else if (requestcode == washroom_camera) {
                b = (Bitmap) data.getExtras().get("data");
                Uri imgUrix = getImageUri(this, b);
                startEditor(imgUrix, washroom_edit_final);
            }
            else if (requestcode == washroom_gallery) {
                startEditor(uri, washroom_edit_final);
            }
            else if (requestcode == washroom_edit_final) {
                ImageView washroom_front = findViewById(R.id.washroom_upload_image);
                Bitmap bt2;
                washroom_front.setImageURI(uri);
                try {
                    bt2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                    washroom_view = getResizedBitmap(bt2, 1000);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(), "PHOTO SAVED", Toast.LENGTH_SHORT).show();
            }

            else if (requestcode == req_code) {
                if (data.getData() == null) {
                    return;
                }
                uri_mail = data.getData();
                Intent intentx = new Intent(Intent.ACTION_SENDTO);
                intentx.setType("text/plain");
                String message = "File to be shared is ";
                intentx.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                intentx.putExtra(Intent.EXTRA_STREAM, uri);
                intentx.putExtra(Intent.EXTRA_TEXT, message);
                intentx.setData(Uri.parse("mailto:xyz@gmail.com"));
                intentx.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intentx);
            }
        }
    }

    // for photo editor intent

    public void startEditor(Uri uri, int req_c) {
        Intent intent = new Intent(Details_hotels.this, DsPhotoEditorActivity.class);

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

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }

    public Uri getImageUri(Context inContext, Bitmap
            inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }


    public void get_Latitude_longitude() {
        gpsTracker = new GpsTracker(Details_hotels.this);
        if (gpsTracker.canGetLocation()) {
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
        } else {
            gpsTracker.showSettingsAlert();
        }
    }

}