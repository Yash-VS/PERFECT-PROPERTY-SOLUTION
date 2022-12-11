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

public class Details_warehouse extends AppCompatActivity {

    Bitmap b;
    Bitmap front_view = null;
    Bitmap long_view = null;
    Bitmap dg = null;
    Bitmap fir = null;
    Bitmap parkings = null;
    Bitmap inner1 = null;
    Bitmap inner2 = null;
    Bitmap inner3 = null;
    Bitmap pantry = null;
    Bitmap terrace = null;
    Bitmap washroom = null;
    Bitmap floor_ma = null;


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

    int dock_camera = 22;
    int dock_gallery = 23;
    int dock_edit_final = 24;

    int wash_room_camera = 25;
    int wash_room_gallery = 26;
    int wash_room_edit_final = 27;

    int floor_map_camera = 28;
    int floor_map_gallery = 29;
    int floor_map_edit_final = 30;

    int dg_camera = 31;
    int dg_gallery = 32;
    int dg_edit_final = 33;

    int fire_camera = 31;
    int fire_gallery = 32;
    int fire_edit_final = 33;

    // for email
    int req_code = 100;
    Uri uri_mail;


    Double latitude, longitude;
    private GpsTracker gpsTracker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        setContentView(R.layout.activity_details_warehouse);

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
        EditText location = findViewById(R.id.location);
        EditText fileName = findViewById(R.id.File_name);
        EditText consultant_name = findViewById(R.id.consultant_name);
        EditText carpet_area = findViewById(R.id.total_Area);
        EditText offer_area = findViewById(R.id.offer_Area);
        EditText clear_height = findViewById(R.id.clear_height);
        EditText center_height = findViewById(R.id.centrehieght);
        EditText dock = findViewById(R.id.dock_detail);
        EditText possession = findViewById(R.id.possess);
        EditText floort = findViewById(R.id.floor_type);
        EditText struc = findViewById(R.id.stru);
        EditText facilities = findViewById(R.id.facilities);
        EditText venti = findViewById(R.id.ventilation);
        EditText insu = findViewById(R.id.insulation);
        EditText fire = findViewById(R.id.fire_details);
        EditText legal = findViewById(R.id.legal);
        EditText electric = findViewById(R.id.electrical);
        EditText rent = findViewById(R.id.asking_rent);
//        EditText description = findViewById(R.id.description_tv);
//        lottieAnimationView = findViewById(R.id.animationView);
//        lottieAnimationView.setVisibility(View.VISIBLE);
        Toast.makeText(Details_warehouse.this, "Loading", Toast.LENGTH_SHORT).show();

        try{
            Presentation pres = new Presentation(getResources().openRawResource(R.raw.template_warehouse));    // ***** you have to change this ppt accordingly *****

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
            ISlide slide12 = pres.getSlides().get_Item(12);


            ITextFrame tf1 = ((IAutoShape)slide1.getShapes().get_Item(1)).getTextFrame();
            ITextFrame tf2 = ((IAutoShape)slide1.getShapes().get_Item(2)).getTextFrame();
            ITextFrame tf3 = ((IAutoShape)slide1.getShapes().get_Item(3)).getTextFrame();
            ITextFrame tf4 = ((IAutoShape)slide1.getShapes().get_Item(4)).getTextFrame();
            ITextFrame tf5 = ((IAutoShape)slide1.getShapes().get_Item(5)).getTextFrame();
            ITextFrame tf6 = ((IAutoShape)slide1.getShapes().get_Item(6)).getTextFrame();
            ITextFrame tf7 = ((IAutoShape)slide1.getShapes().get_Item(7)).getTextFrame();
            ITextFrame tf8 = ((IAutoShape)slide1.getShapes().get_Item(8)).getTextFrame();
            ITextFrame tf9 = ((IAutoShape)slide1.getShapes().get_Item(9)).getTextFrame();
            ITextFrame tf10 = ((IAutoShape)slide1.getShapes().get_Item(10)).getTextFrame();
            ITextFrame tf11 = ((IAutoShape)slide1.getShapes().get_Item(11)).getTextFrame();
            ITextFrame tf12 = ((IAutoShape)slide1.getShapes().get_Item(12)).getTextFrame();
            ITextFrame tf13 = ((IAutoShape)slide1.getShapes().get_Item(13)).getTextFrame();
            ITextFrame tf14 = ((IAutoShape)slide1.getShapes().get_Item(14)).getTextFrame();
            ITextFrame tf15 = ((IAutoShape)slide1.getShapes().get_Item(15)).getTextFrame();
            ITextFrame tf16 = ((IAutoShape)slide1.getShapes().get_Item(16)).getTextFrame();


            tf1.setText("Location : " + location.getText().toString());

            tf3.setText("Total Area : " + carpet_area.getText().toString());

            tf2.setText("Consultant Name : " + consultant_name.getText().toString());

            tf4.setText("Offered Area:" + offer_area.getText().toString());

            tf5.setText("Height Details: Clear Height"+ clear_height.getText().toString()+"; Center Height:"+center_height.getText().toString());


            tf6.setText("Dock Details:"+dock.getText().toString());

            tf7.setText("Structure:"+ struc.getText().toString());

            tf8.setText("Possession of Warehouse: " + possession.getText().toString());

            tf9.setText("Floor Type : " + floort.getText().toString());

            tf10.setText("Facilities area:"+ facilities.getText().toString());

            tf11.setText("Air changer, Ventilator, Skylights:"+ venti.getText().toString());

            tf12.setText("Insulation:"+insu.getText().toString());

            tf13.setText("Fire Hydrant and Fire NOC:"+ fire.getText().toString());

            tf14.setText("Legal Compliances"+legal.getText().toString());

            tf15.setText("Electrical Load:"+electric.getText().toString());

            tf16.setText("Asking Rental:"+rent.getText().toString());

            if(front_view != null){
            IPPImage front_image = pres.getImages().addImage(front_view);
            slide2.getShapes().addPictureFrame(ShapeType.Rectangle, 50, 50, 500, 450, front_image);}

            if(long_view != null){
            IPPImage long_image = pres.getImages().addImage(long_view);
            slide3.getShapes().addPictureFrame(ShapeType.Rectangle, 50, 50, 500, 450, long_image);}

            if(parkings != null){
            IPPImage parkin = pres.getImages().addImage(parkings);
            slide4.getShapes().addPictureFrame(ShapeType.Rectangle, 50, 50, 500, 450, parkin);}

            if(dg != null){
            IPPImage dgp = pres.getImages().addImage(dg);
            slide5.getShapes().addPictureFrame(ShapeType.Rectangle, 50, 50, 500, 450, dgp);}

            if(fir != null){
            IPPImage firee = pres.getImages().addImage(fir);
            slide6.getShapes().addPictureFrame(ShapeType.Rectangle, 50, 50, 500, 450, firee);}

            if(inner1 != null){
            IPPImage inner01 = pres.getImages().addImage(inner1);
            slide7.getShapes().addPictureFrame(ShapeType.Rectangle, 50, 50, 500, 450, inner01);}

            if(inner2 != null){
            IPPImage inner02 = pres.getImages().addImage(inner2);
            slide8.getShapes().addPictureFrame(ShapeType.Rectangle, 50, 50, 500, 450, inner02);}

            if(inner3 != null){
            IPPImage inner03 = pres.getImages().addImage(inner3);
            slide9.getShapes().addPictureFrame(ShapeType.Rectangle, 50, 50, 500, 450, inner03);}

            if(pantry != null){
            IPPImage pantr = pres.getImages().addImage(pantry);
            slide10.getShapes().addPictureFrame(ShapeType.Rectangle, 50, 50, 500, 450, pantr);}

            if(washroom != null){
            IPPImage washr = pres.getImages().addImage(washroom);
            slide11.getShapes().addPictureFrame(ShapeType.Rectangle, 50, 50, 500, 450, washr);}

            if(floor_ma != null){
            IPPImage floor_map = pres.getImages().addImage(floor_ma);
            slide12.getShapes().addPictureFrame(ShapeType.Rectangle, 50, 50, 500, 450, floor_map);}
            //            IPPImage image = pres.
            String sdcardPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath() + File.separator;
            pres.save(sdcardPath + fileName.getText().toString() +".pptx", SaveFormat.Pptx);
            Toast.makeText(Details_warehouse.this, "successful in sd card", Toast.LENGTH_SHORT).show();
        }
        catch(Exception e){
            Toast.makeText(Details_warehouse.this, "unsuccessful in sd card", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

    }

    public void onClickExcel(View view){
        EditText location = findViewById(R.id.location);
        EditText fileName = findViewById(R.id.File_name);
        EditText consultant_name = findViewById(R.id.consultant_name);
        EditText carpet_area = findViewById(R.id.carpet_Area);
        EditText rent = findViewById(R.id.asking_rent);
        EditText description = findViewById(R.id.description_tv);
        EditText floor = findViewById(R.id.Floor);
        EditText hall_room = findViewById(R.id.hall_room);
        EditText bed_room = findViewById(R.id.bed_room);
        EditText restaurant = findViewById(R.id.kitchen);
        EditText parking = findViewById(R.id.parking);
        EditText buildtype = findViewById(R.id.buildtype);
        EditText wash = findViewById(R.id.washroom);
        EditText secu = findViewById(R.id.security);

        try{

            Workbook workbook = new Workbook(getResources().openRawResource(R.raw.database));

            workbook.getWorksheets().get(0).getCells().get("A2").setValue(location.getText().toString());
            workbook.getWorksheets().get(0).getCells().get("C2").setValue(consultant_name.getText().toString());
            workbook.getWorksheets().get(0).getCells().get("S2").setValue(carpet_area.getText().toString());
            workbook.getWorksheets().get(0).getCells().get("AF2").setValue(rent.getText().toString());
            workbook.getWorksheets().get(0).getCells().get("BG2").setValue(description.getText().toString());
            workbook.getWorksheets().get(0).getCells().get("U2").setValue(floor.getText().toString());
            workbook.getWorksheets().get(0).getCells().get("BC2").setValue(bed_room.getText().toString());
            workbook.getWorksheets().get(0).getCells().get("BA2").setValue(restaurant.getText().toString());
            workbook.getWorksheets().get(0).getCells().get("V2").setValue(parking.getText().toString());
            workbook.getWorksheets().get(0).getCells().get("AN2").setValue(secu.getText().toString());
            workbook.getWorksheets().get(0).getCells().get("BD2").setValue(wash.getText().toString());
            workbook.getWorksheets().get(0).getCells().get("AZ2").setValue(buildtype.getText().toString());
            workbook.getWorksheets().get(0).getCells().get("BB2").setValue(hall_room.getText().toString());



            String sdcardPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath() + File.separator;
            workbook.save(sdcardPath +fileName.getText().toString()+ ".xlsx", FileFormatType.XLSX);
            //pres.save(sdCardPath + "Textbox.pptx", com.aspose.slides.SaveFormat.Pptx);
            Toast.makeText(Details_warehouse.this, "successful in sd card", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            Toast.makeText(Details_warehouse.this, "unsuccessful in sd card", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

    }

    // for capturing image
    public void capture(View view){

        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        switch (view.getId()){
            case R.id.upload_front_img_btn:{
                startActivityForResult(i, front_camera);
                break;
            }
            case R.id.long_img_upload_btn: {
                startActivityForResult(i, long_camera);
                break;
            }
            case R.id.parking_img_upload_btn: {
                startActivityForResult(i, parking_camera);
                break;
            }
            case R.id.inner_img_upload_btn: {
                startActivityForResult(i, inner_1_camera);
                break;
            }
            case R.id.inner_img_upload_btn2: {
                startActivityForResult(i, inner_2_camera);
                break;
            }
            case R.id.inner_img_upload_btn3: {
                startActivityForResult(i, inner_3_camera);
                break;
            }
            case R.id.pantry_img_upload_btn: {
                startActivityForResult(i, pantry_camera);
                break;
            }
            case R.id.dock_img_upload_btn: {
                startActivityForResult(i, dock_camera);
                break;
            }
            case R.id.washroom_img_upload_btn: {
                startActivityForResult(i, wash_room_camera);
                break;
            }
            case R.id.floor_map_img_upload_btn: {
                startActivityForResult(i, floor_map_camera);
                break;
            }
            case R.id.DG_img_upload_btn:{
                startActivityForResult(i, dg_camera);
            }
            case R.id.fire_img_upload_btn:{
                startActivityForResult(i, fire_camera);
            }
        }
    }

    // for gallery image
    public void gallery(View view){
        Intent inte = new Intent(Intent.ACTION_PICK);
        inte.setType("image/*");

        switch (view.getId()){
            case R.id.gallery_front_btn:{
                startActivityForResult(inte, front_gallery);
                break;
            }
            case R.id.gallery_long_btn: {
                startActivityForResult(inte, long_gallery);
                break;
            }
            case R.id.gallery_parking_btn: {
                startActivityForResult(inte, parking_gallery);
                break;
            }
            case R.id.gallery_inner_btn: {
                startActivityForResult(inte, inner_1_gallery);
                break;
            }
            case R.id.gallery_inner2_btn: {
                startActivityForResult(inte, inner_2_gallery);
                break;
            }
            case R.id.gallery_inner3_btn: {
                startActivityForResult(inte, inner_3_gallery);
                break;
            }
            case R.id.gallery_pantry_btn: {
                startActivityForResult(inte, pantry_gallery);
                break;
            }
            case R.id.dock_img_upload_btn:{
                startActivityForResult(inte, dock_gallery);
            }
            case R.id.gallery_washroom_btn: {
                startActivityForResult(inte, wash_room_gallery);
                break;
            }
            case R.id.gallery_floor_map_btn: {
                startActivityForResult(inte, floor_map_gallery);
                break;
            }
            case R.id.gallery_DG_btn:{
                startActivityForResult(inte, dg_gallery);
            }
            case R.id.gallery_fire_btn:{
                startActivityForResult(inte, fire_gallery);
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
                ImageView upload_front = findViewById(R.id.front_image);
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
                Uri imgUrix = getImageUri(this, b);
                startEditor(imgUrix, long_edit_final);
            }
            else if(requestcode == long_gallery){
                startEditor(uri, long_edit_final);
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
                ImageView upload_parking = findViewById(R.id.parking_image);
                Bitmap bt2;
                upload_parking.setImageURI(uri);
                try {
                    bt2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                    parkings = getResizedBitmap(bt2, 1000);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(), "PHOTO SAVED", Toast.LENGTH_SHORT).show();
            }
            else if(requestcode == dg_camera) {
                b = (Bitmap)data.getExtras().get("data");
                Uri imgUri = getImageUri(this, b);
                startEditor(imgUri, dg_edit_final);
            }
            else if(requestcode == dg_gallery){
                startEditor(uri, dg_edit_final);
            }

            else if(requestcode == dg_edit_final){
                ImageView upload_pantry = findViewById(R.id.DG_image);
                Bitmap bt2;
                upload_pantry.setImageURI(uri);
                try {
                    bt2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                    dg = getResizedBitmap(bt2, 1000);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(), "PHOTO SAVED", Toast.LENGTH_SHORT).show();
            }
            else if(requestcode == fire_camera) {
                b = (Bitmap)data.getExtras().get("data");
                Uri imgUri = getImageUri(this, b);
                startEditor(imgUri, fire_edit_final);
            }
            else if(requestcode == fire_gallery){
                startEditor(uri, fire_edit_final);
            }

            else if(requestcode == fire_edit_final){
                ImageView upload_pantry = findViewById(R.id.fire_image);
                Bitmap bt2;
                upload_pantry.setImageURI(uri);
                try {
                    bt2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                    fir = getResizedBitmap(bt2, 1000);
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
                ImageView upload_inner_1 = findViewById(R.id.inner_image);
                Bitmap bt2;
                upload_inner_1.setImageURI(uri);
                try {
                    bt2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                    inner1 = getResizedBitmap(bt2, 1000);
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
                ImageView upload_inner_2 = findViewById(R.id.inner_image2);
                Bitmap bt2;
                upload_inner_2.setImageURI(uri);
                try {
                    bt2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                    inner2 = getResizedBitmap(bt2, 1000);
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
                ImageView upload_inner_3 = findViewById(R.id.inner_image3);
                Bitmap bt2;
                upload_inner_3.setImageURI(uri);
                try {
                    bt2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                    inner3 = getResizedBitmap(bt2, 1000);
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
                ImageView upload_pantry = findViewById(R.id.pantry_image);
                Bitmap bt2;
                upload_pantry.setImageURI(uri);
                try {
                    bt2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                    pantry = getResizedBitmap(bt2, 1000);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(), "PHOTO SAVED", Toast.LENGTH_SHORT).show();
            }


            else if(requestcode == dock_camera) {
                b = (Bitmap)data.getExtras().get("data");
                Uri imgUri = getImageUri(this, b);
                startEditor(imgUri, dock_edit_final);
            }
            else if(requestcode == dock_gallery){
                startEditor(uri, dock_edit_final);
            }

            else if(requestcode == dock_edit_final){
                ImageView upload_terrace = findViewById(R.id.dock_image);
                Bitmap bt2;
                upload_terrace.setImageURI(uri);
                try {
                    bt2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                    pantry = getResizedBitmap(bt2, 1000);
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
                ImageView upload_wash_room = findViewById(R.id.washroom_image);
                Bitmap bt2;
                upload_wash_room.setImageURI(uri);
                try {
                    bt2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                    washroom = getResizedBitmap(bt2, 1000);
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
                ImageView upload_floor_map = findViewById(R.id.floor_map_image);
                Bitmap bt2;
                upload_floor_map.setImageURI(uri);
                try {
                    bt2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                    floor_ma = getResizedBitmap(bt2, 1000);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(), "PHOTO SAVED", Toast.LENGTH_SHORT).show();
            }

            else if(requestcode == long_edit_final){
                ImageView long_front = findViewById(R.id.long_image);
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

            else if(requestcode == req_code){
                if(data.getData() == null){
                    return;
                }
                uri_mail = data.getData();
                Intent intentx = new Intent(Intent.ACTION_SENDTO);
                intentx.setType("text/plain");
                String message="File to be shared is ";
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

    public void startEditor(Uri uri, int req_c){
        Intent intent = new Intent(Details_warehouse.this, DsPhotoEditorActivity.class);

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
        gpsTracker = new GpsTracker(Details_warehouse.this);
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