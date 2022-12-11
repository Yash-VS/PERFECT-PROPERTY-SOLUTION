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

public class Details_Banks extends AppCompatActivity {

    Bitmap b;
    Bitmap front_view = null, long_view= null ,right_view= null, left_view= null, opposite_view= null, opp_right_view= null, opp_left_view= null, inner_1_view= null, inner_2_view= null, inner_3_view= null, terrace_view= null, vsat_view= null, map_view = null;

    int front_camera = 1;
    int front_gallery = 2;
    int front_edit_final = 3;

    int long_camera = 4;
    int long_gallery = 5;
    int long_edit_final = 6;

    int right_camera = 7;
    int right_gallery = 8;
    int right_edit_final = 9;

    int left_camera = 10;
    int left_gallery = 11;
    int left_edit_final = 12;

    int opposite_camera = 13;
    int opposite_gallery = 14;
    int opposite_edit_final = 15;

    int opp_right_camera = 16;
    int opp_right_gallery = 17;
    int opp_right_edit_final = 18;

    int opp_left_camera = 19;
    int opp_left_gallery = 20;
    int opp_left_edit_final = 21;

    int inner_1_camera = 22;
    int inner_1_gallery = 23;
    int inner_1_edit_final = 24;

    int inner_2_camera = 25;
    int inner_2_gallery = 26;
    int inner_2_edit_final = 27;

    int inner_3_camera = 28;
    int inner_3_gallery = 29;
    int inner_3_edit_final = 30;

    int terrace_camera = 31;
    int terrace_gallery = 32;
    int terrace_edit_final = 33;

    int vsat_camera = 34;
    int vsat_gallery = 35;
    int vsat_edit_final = 36;

    int map_camera = 37;
    int map_gallery = 38;
    int map_edit_final = 39;

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
        setContentView(R.layout.activity_details_banks);



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
        EditText city = findViewById(R.id.city);
        EditText state = findViewById(R.id.state);
        EditText pincode = findViewById(R.id.pincode);
        EditText land_lord = findViewById(R.id.land_lord);
        EditText lord_contact = findViewById(R.id.lord_contact);
        EditText rent = findViewById(R.id.rent);
        EditText adv_rent = findViewById(R.id.adv_rent);
        EditText free_period = findViewById(R.id.free_period);
        EditText consultant_name = findViewById(R.id.consultant_name);
        EditText consultant_contact = findViewById(R.id.consultant_contact);
        EditText shop_area= findViewById(R.id.shop_area);
        EditText floor = findViewById(R.id.floor);
        EditText shop_type = findViewById(R.id.shop_type);
        EditText area_carpet = findViewById(R.id.area_carpet);
        EditText availability_power = findViewById(R.id.availability_power);
        EditText available_hours = findViewById(R.id.available_hours);
        EditText depth = findViewById(R.id.depth);
        EditText width = findViewById(R.id.width);
        EditText height = findViewById(R.id.height);
        EditText frontage_total = findViewById(R.id.frontage_total);
        EditText frontage_offered = findViewById(R.id.frontage_offered);
        EditText signage = findViewById(R.id.signage);
        EditText vsat = findViewById(R.id.vsat);
        EditText odu_space = findViewById(R.id.odu_space);
        EditText signage_l_shape = findViewById(R.id.signage_l_shape);
        EditText coverage_cra = findViewById(R.id.coverage_cra);
        EditText agency_cra = findViewById(R.id.agency_cra);
        EditText description = findViewById(R.id.description);

        Toast.makeText(Details_Banks.this, "Loading", Toast.LENGTH_SHORT).show();

        try{
            Presentation pres = new Presentation(getResources().openRawResource(R.raw.template_atm));    // ***** you have to change this ppt accordingly *****

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
            ISlide slide13 = pres.getSlides().get_Item(13);
            ISlide slide14 = pres.getSlides().get_Item(14);


            ITextFrame tf1 = ((IAutoShape)slide1.getShapes().get_Item(0)).getTextFrame();
            ITextFrame tf2 = ((IAutoShape)slide1.getShapes().get_Item(1)).getTextFrame();
            ITextFrame tf3 = ((IAutoShape)slide1.getShapes().get_Item(2)).getTextFrame();
            ITextFrame tf4 = ((IAutoShape)slide1.getShapes().get_Item(3)).getTextFrame();
            ITextFrame tf5 = ((IAutoShape)slide1.getShapes().get_Item(4)).getTextFrame();
            ITextFrame tf6 = ((IAutoShape)slide1.getShapes().get_Item(5)).getTextFrame();
            ITextFrame tf7 = ((IAutoShape)slide1.getShapes().get_Item(6)).getTextFrame();
            ITextFrame tf8 = ((IAutoShape)slide1.getShapes().get_Item(7)).getTextFrame();
            ITextFrame tf9 = ((IAutoShape)slide1.getShapes().get_Item(8)).getTextFrame();
            ITextFrame tf10 = ((IAutoShape)slide1.getShapes().get_Item(9)).getTextFrame();
            ITextFrame tf11 = ((IAutoShape)slide1.getShapes().get_Item(10)).getTextFrame();
            ITextFrame tf12 = ((IAutoShape)slide1.getShapes().get_Item(11)).getTextFrame();
            ITextFrame tf13 = ((IAutoShape)slide1.getShapes().get_Item(12)).getTextFrame();
            ITextFrame tf14 = ((IAutoShape)slide1.getShapes().get_Item(13)).getTextFrame();
            ITextFrame tf15 = ((IAutoShape)slide1.getShapes().get_Item(14)).getTextFrame();
            ITextFrame tf16 = ((IAutoShape)slide1.getShapes().get_Item(15)).getTextFrame();
            ITextFrame tf17 = ((IAutoShape)slide1.getShapes().get_Item(16)).getTextFrame();
            ITextFrame tf18 = ((IAutoShape)slide1.getShapes().get_Item(17)).getTextFrame();
            ITextFrame tf19 = ((IAutoShape)slide1.getShapes().get_Item(18)).getTextFrame();
            ITextFrame tf20 = ((IAutoShape)slide1.getShapes().get_Item(19)).getTextFrame();
            ITextFrame tf21 = ((IAutoShape)slide1.getShapes().get_Item(20)).getTextFrame();
            ITextFrame tf22 = ((IAutoShape)slide1.getShapes().get_Item(21)).getTextFrame();
            ITextFrame tf23 = ((IAutoShape)slide1.getShapes().get_Item(22)).getTextFrame();
            ITextFrame tf24 = ((IAutoShape)slide1.getShapes().get_Item(23)).getTextFrame();
            ITextFrame tf25 = ((IAutoShape)slide1.getShapes().get_Item(24)).getTextFrame();
            ITextFrame tf26 = ((IAutoShape)slide1.getShapes().get_Item(25)).getTextFrame();
            ITextFrame tf27 = ((IAutoShape)slide1.getShapes().get_Item(26)).getTextFrame();
            ITextFrame tf28 = ((IAutoShape)slide1.getShapes().get_Item(27)).getTextFrame();
            ITextFrame tf29 = ((IAutoShape)slide1.getShapes().get_Item(28)).getTextFrame();


            tf1.setText("Location : " + location.getText());
            location.setText(null);
            tf2.setText("City : " + city.getText());
            city.setText(null);
            tf3.setText("State : " + state.getText());
            state.setText(null);
            tf4.setText("PinCode : " + pincode.getText());
            pincode.setText(null);
            tf5.setText("Land Lord : " + land_lord.getText());
            land_lord.setText(null);
            tf6.setText("Land Lord Contact No : " + lord_contact.getText());
            lord_contact.setText(null);
            tf7.setText("Rent : " + rent.getText());
            rent.setText(null);
            tf8.setText("Advance Rent : " + adv_rent.getText());
            adv_rent.setText(null);
            tf9.setText("Free Period : " + free_period.getText());
            free_period.setText(null);
            tf10.setText("Consultant Name : " + consultant_name.getText());
            consultant_name.setText(null);
            tf11.setText("Consultant Contact No : " + consultant_contact.getText());
            consultant_contact.setText(null);
            tf12.setText("Shop Area : " + shop_area.getText() + " Sq.ft.");
            shop_area.setText(null);
            tf13.setText("Floor : " + floor.getText());
            floor.setText(null);
            tf14.setText("Type of Shop : " + shop_type.getText());
            shop_type.setText(null);
            tf15.setText("Carpet Area : " + area_carpet.getText() + " Sq.ft.");
            area_carpet.setText(null);
            tf16.setText("Power Availability : " + availability_power.getText());
            availability_power.setText(null);
            tf17.setText("Hours Available : " + available_hours.getText());
            available_hours.setText(null);
            tf18.setText("Depth : " + depth.getText() + " ft.");
            depth.setText(null);
            tf19.setText("Width : " + width.getText() + " ft.");
            width.setText(null);
            tf20.setText("Height : " + height.getText() + " ft.");
            height.setText(null);
            tf21.setText("Total Frontage : " + frontage_total.getText() + " ft.");
            frontage_total.setText(null);
            tf22.setText("Frontage Offered : " + frontage_offered.getText() + " ft.");
            frontage_offered.setText(null);
            tf23.setText("Signage Dimension : " + signage.getText() + " ft.");
            signage.setText(null);
            tf24.setText("VSAT : " + vsat.getText());
            vsat.setText(null);
            tf25.setText("AC ODU Space : " + odu_space.getText());
            odu_space.setText(null);
            tf26.setText("'L' Shape Signage : " + signage_l_shape.getText());
            signage_l_shape.setText(null);
            tf27.setText("CRA Coverage : " + coverage_cra.getText());
            coverage_cra.setText(null);
            tf28.setText("CRA Agency : " + agency_cra.getText());
            agency_cra.setText(null);
            tf29.setText("Description : " + description.getText());
            description.setText(null);


            if(front_view != null) {
                IPPImage front_image = pres.getImages().addImage(front_view);
                slide2.getShapes().addPictureFrame(ShapeType.Rectangle, 50, 50, 500, 450, front_image);
            }

            if(long_view != null) {
                IPPImage long_image = pres.getImages().addImage(long_view);
                slide3.getShapes().addPictureFrame(ShapeType.Rectangle, 50, 50, 500, 450, long_image);
            }

            if(right_view != null) {
                IPPImage right_image = pres.getImages().addImage(right_view);
                slide4.getShapes().addPictureFrame(ShapeType.Rectangle, 50, 50, 500, 450, right_image);
            }

            if(left_view != null) {
                IPPImage left_image = pres.getImages().addImage(left_view);
                slide5.getShapes().addPictureFrame(ShapeType.Rectangle, 50, 50, 500, 450, left_image);
            }
            if(opposite_view != null) {
                IPPImage opposite_image = pres.getImages().addImage(opposite_view);
                slide6.getShapes().addPictureFrame(ShapeType.Rectangle, 50, 50, 500, 450, opposite_image);
            }
            if(opp_right_view != null) {
                IPPImage opp_right_image = pres.getImages().addImage(opp_right_view);
                slide7.getShapes().addPictureFrame(ShapeType.Rectangle, 50, 50, 500, 450, opp_right_image);
            }
            if(opp_left_view != null) {
                IPPImage opp_left_image = pres.getImages().addImage(opp_left_view);
                slide8.getShapes().addPictureFrame(ShapeType.Rectangle, 50, 50, 500, 450, opp_left_image);
            }
            if(inner_1_view != null) {
                IPPImage inner_1_image = pres.getImages().addImage(inner_1_view);
                slide9.getShapes().addPictureFrame(ShapeType.Rectangle, 50, 50, 500, 450, inner_1_image);
            }
            if(inner_2_view != null) {
                IPPImage inner_2_image = pres.getImages().addImage(inner_2_view);
                slide10.getShapes().addPictureFrame(ShapeType.Rectangle, 50, 50, 500, 450, inner_2_image);
            }
            if(inner_3_view != null) {
                IPPImage inner_3_image = pres.getImages().addImage(inner_3_view);
                slide11.getShapes().addPictureFrame(ShapeType.Rectangle, 50, 50, 500, 450, inner_3_image);
            }
            if(terrace_view != null) {
                IPPImage terrace_image = pres.getImages().addImage(terrace_view);
                slide12.getShapes().addPictureFrame(ShapeType.Rectangle, 50, 50, 500, 450, terrace_image);
            }
            if(vsat_view != null) {
                IPPImage vsat_image = pres.getImages().addImage(vsat_view);
                slide13.getShapes().addPictureFrame(ShapeType.Rectangle, 50, 50, 500, 450, vsat_image);
            }
            if(map_view != null) {
                IPPImage map_image = pres.getImages().addImage(map_view);
                slide14.getShapes().addPictureFrame(ShapeType.Rectangle, 50, 50, 500, 450, map_image);
            }

            String sdcardPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath() + File.separator;
            pres.save(sdcardPath + fileName.getText().toString() +".pptx", SaveFormat.Pptx);

            Toast.makeText(Details_Banks.this, "successful in sd card", Toast.LENGTH_SHORT).show();
        }
        catch(Exception e){
            Toast.makeText(Details_Banks.this, "unsuccessful in sd card", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

    }

    public void onClickExcel(View view){
        EditText fileName = findViewById(R.id.File_name);

        EditText location = findViewById(R.id.location);
        EditText city = findViewById(R.id.city);
        EditText state = findViewById(R.id.state);
        EditText pincode = findViewById(R.id.pincode);
        EditText land_lord = findViewById(R.id.land_lord);
        EditText lord_contact = findViewById(R.id.lord_contact);
        EditText rent = findViewById(R.id.rent);
        EditText adv_rent = findViewById(R.id.adv_rent);
        EditText free_period = findViewById(R.id.free_period);
        EditText consultant_name = findViewById(R.id.consultant_name);
        EditText consultant_contact = findViewById(R.id.consultant_contact);
        EditText shop_area= findViewById(R.id.shop_area);
        EditText floor = findViewById(R.id.floor);
        EditText shop_type = findViewById(R.id.shop_type);
        EditText area_carpet = findViewById(R.id.area_carpet);
        EditText availability_power = findViewById(R.id.availability_power);
        EditText available_hours = findViewById(R.id.available_hours);
        EditText depth = findViewById(R.id.depth);
        EditText width = findViewById(R.id.width);
        EditText height = findViewById(R.id.height);
        EditText frontage_total = findViewById(R.id.frontage_total);
        EditText frontage_offered = findViewById(R.id.frontage_offered);
        EditText signage = findViewById(R.id.signage);
        EditText vsat = findViewById(R.id.vsat);
        EditText odu_space = findViewById(R.id.odu_space);
        EditText signage_l_shape = findViewById(R.id.signage_l_shape);
        EditText coverage_cra = findViewById(R.id.coverage_cra);
        EditText agency_cra = findViewById(R.id.agency_cra);
        EditText description = findViewById(R.id.description);

        try{

            Workbook workbook = new Workbook(getResources().openRawResource(R.raw.database));

            workbook.getWorksheets().get(0).getCells().get("L2").setValue(location.getText());
            workbook.getWorksheets().get(0).getCells().get("F2").setValue(city.getText());
            workbook.getWorksheets().get(0).getCells().get("E2").setValue(state.getText());
            workbook.getWorksheets().get(0).getCells().get("K2").setValue(pincode.getText());
            workbook.getWorksheets().get(0).getCells().get("AI2").setValue(land_lord.getText());
            workbook.getWorksheets().get(0).getCells().get("AJ2").setValue(lord_contact.getText());
            workbook.getWorksheets().get(0).getCells().get("AF2").setValue(rent.getText());
            workbook.getWorksheets().get(0).getCells().get("H2").setValue(adv_rent.getText());
            workbook.getWorksheets().get(0).getCells().get("AL2").setValue(free_period.getText());
            workbook.getWorksheets().get(0).getCells().get("C2").setValue(consultant_name.getText());
            workbook.getWorksheets().get(0).getCells().get("D2").setValue(consultant_contact.getText());
            workbook.getWorksheets().get(0).getCells().get("AW2").setValue(shop_area.getText());
            workbook.getWorksheets().get(0).getCells().get("U2").setValue(floor.getText());
            workbook.getWorksheets().get(0).getCells().get("CL2").setValue(shop_type.getText());       // *********************
            workbook.getWorksheets().get(0).getCells().get("S2").setValue(area_carpet.getText());
            workbook.getWorksheets().get(0).getCells().get("CM2").setValue(availability_power.getText());    //*************
            workbook.getWorksheets().get(0).getCells().get("AO2").setValue(available_hours.getText());
            workbook.getWorksheets().get(0).getCells().get("O2").setValue(depth.getText());
            workbook.getWorksheets().get(0).getCells().get("S2").setValue(width.getText());            // ********************
            workbook.getWorksheets().get(0).getCells().get("Q2").setValue(height.getText());
            workbook.getWorksheets().get(0).getCells().get("M2").setValue(frontage_total.getText());
            workbook.getWorksheets().get(0).getCells().get("N2").setValue(frontage_offered.getText());
            workbook.getWorksheets().get(0).getCells().get("CN2").setValue(signage.getText());          // *********************
            workbook.getWorksheets().get(0).getCells().get("AQ2").setValue(vsat.getText());
            workbook.getWorksheets().get(0).getCells().get("CO2").setValue(odu_space.getText());     // ***************
            workbook.getWorksheets().get(0).getCells().get("CP2").setValue(signage_l_shape.getText());    // *************
            workbook.getWorksheets().get(0).getCells().get("CA2").setValue(coverage_cra.getText());
            workbook.getWorksheets().get(0).getCells().get("CQ2").setValue(agency_cra.getText());       // *********

            workbook.getWorksheets().get(0).getCells().get("BG2").setValue(description.getText());

            String sdcardPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath() + File.separator;
            workbook.save(sdcardPath +fileName.getText().toString()+ ".xlsx", FileFormatType.XLSX);

            Toast.makeText(Details_Banks.this, "successful in sd card", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            Toast.makeText(Details_Banks.this, "unsuccessful in sd card", Toast.LENGTH_SHORT).show();
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
                startActivityForResult(i, right_camera);
                break;
            }
            case R.id.camera_image_btn4: {
                startActivityForResult(i, left_camera);
                break;
            }
            case R.id.camera_image_btn5: {
                startActivityForResult(i, opposite_camera);
                break;
            }
            case R.id.camera_image_btn6: {
                startActivityForResult(i, opp_right_camera);
                break;
            }
            case R.id.camera_image_btn7: {
                startActivityForResult(i, opp_left_camera);
                break;
            }
            case R.id.camera_image_btn8: {
                startActivityForResult(i, inner_1_camera);
                break;
            }
            case R.id.camera_image_btn9: {
                startActivityForResult(i, inner_2_camera);
                break;
            }
            case R.id.camera_image_btn10: {
                startActivityForResult(i, inner_3_camera);
                break;
            }
            case R.id.camera_image_btn11: {
                startActivityForResult(i, terrace_camera);
                break;
            }
            case R.id.camera_image_btn12: {
                startActivityForResult(i, vsat_camera);
                break;
            }
            case R.id.camera_image_btn13: {
                startActivityForResult(i, map_camera);
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
                startActivityForResult(inte, right_gallery);
                break;
            }
            case R.id.gallery_image_btn4: {
                startActivityForResult(inte, left_gallery);
                break;
            }
            case R.id.gallery_image_btn5: {
                startActivityForResult(inte, opposite_gallery);
                break;
            }
            case R.id.gallery_image_btn6: {
                startActivityForResult(inte, opp_right_gallery);
                break;
            }
            case R.id.gallery_image_btn7: {
                startActivityForResult(inte, opp_left_gallery);
                break;
            }
            case R.id.gallery_image_btn8: {
                startActivityForResult(inte, inner_1_gallery);
                break;
            }
            case R.id.gallery_image_btn9: {
                startActivityForResult(inte, inner_2_gallery);
                break;
            }
            case R.id.gallery_image_btn10: {
                startActivityForResult(inte, inner_3_gallery);
                break;
            }
            case R.id.gallery_image_btn11: {
                startActivityForResult(inte, terrace_gallery);
                break;
            }
            case R.id.gallery_image_btn12: {
                startActivityForResult(inte, vsat_gallery);
                break;
            }
            case R.id.gallery_image_btn13: {
                startActivityForResult(inte, map_gallery);
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

            else if(requestcode == right_camera) {
                b = (Bitmap)data.getExtras().get("data");
                Uri imgUri = getImageUri(this, b);
                startEditor(imgUri, right_edit_final);
            }
            else if(requestcode == right_gallery){
                startEditor(uri, right_edit_final);
            }

            else if(requestcode == right_edit_final){
                ImageView upload_right = findViewById(R.id.right_upload_image);
                Bitmap bt2;
                upload_right.setImageURI(uri);
                try {
                    bt2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                    right_view = getResizedBitmap(bt2, 1000);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(), "PHOTO SAVED", Toast.LENGTH_SHORT).show();
            }

            else if(requestcode == left_camera) {
                b = (Bitmap)data.getExtras().get("data");
                Uri imgUri = getImageUri(this, b);
                startEditor(imgUri, left_edit_final);
            }
            else if(requestcode == left_gallery){
                startEditor(uri, left_edit_final);
            }

            else if(requestcode == left_edit_final){
                ImageView upload_left = findViewById(R.id.left_upload_image);
                Bitmap bt2;
                upload_left.setImageURI(uri);
                try {
                    bt2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                    left_view = getResizedBitmap(bt2, 1000);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(), "PHOTO SAVED", Toast.LENGTH_SHORT).show();
            }

            else if(requestcode == opposite_camera) {
                b = (Bitmap)data.getExtras().get("data");
                Uri imgUri = getImageUri(this, b);
                startEditor(imgUri, opposite_edit_final);
            }
            else if(requestcode == opposite_gallery){
                startEditor(uri, opposite_edit_final);
            }

            else if(requestcode == opposite_edit_final){
                ImageView upload_opposite = findViewById(R.id.opposite_upload_image);
                Bitmap bt2;
                upload_opposite.setImageURI(uri);
                try {
                    bt2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                    opposite_view = getResizedBitmap(bt2, 1000);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(), "PHOTO SAVED", Toast.LENGTH_SHORT).show();
            }

            else if(requestcode == opp_right_camera) {
                b = (Bitmap)data.getExtras().get("data");
                Uri imgUri = getImageUri(this, b);
                startEditor(imgUri, opp_right_edit_final);
            }
            else if(requestcode == opp_right_gallery){
                startEditor(uri, opp_right_edit_final);
            }

            else if(requestcode == opp_right_edit_final){
                ImageView upload_opp_right = findViewById(R.id.opp_right_upload_image);
                Bitmap bt2;
                upload_opp_right.setImageURI(uri);
                try {
                    bt2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                    opp_right_view = getResizedBitmap(bt2, 1000);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(), "PHOTO SAVED", Toast.LENGTH_SHORT).show();
            }

            else if(requestcode == opp_left_camera) {
                b = (Bitmap)data.getExtras().get("data");
                Uri imgUri = getImageUri(this, b);
                startEditor(imgUri, opp_left_edit_final);
            }
            else if(requestcode == opp_left_gallery){
                startEditor(uri, opp_left_edit_final);
            }

            else if(requestcode == opp_left_edit_final){
                ImageView upload_opp_left = findViewById(R.id.opp_left_upload_image);
                Bitmap bt2;
                upload_opp_left.setImageURI(uri);
                try {
                    bt2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                    opp_left_view = getResizedBitmap(bt2, 1000);
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

            else if(requestcode == vsat_camera) {
                b = (Bitmap)data.getExtras().get("data");
                Uri imgUri = getImageUri(this, b);
                startEditor(imgUri, vsat_edit_final);
            }
            else if(requestcode == vsat_gallery){
                startEditor(uri, vsat_edit_final);
            }

            else if(requestcode == vsat_edit_final){
                ImageView upload_vsat = findViewById(R.id.vsat_upload_image);
                Bitmap bt2;
                upload_vsat.setImageURI(uri);
                try {
                    bt2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                    vsat_view = getResizedBitmap(bt2, 1000);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(), "PHOTO SAVED", Toast.LENGTH_SHORT).show();
            }

            else if(requestcode == map_camera) {
                b = (Bitmap)data.getExtras().get("data");
                Uri imgUri = getImageUri(this, b);
                startEditor(imgUri, map_edit_final);
            }
            else if(requestcode == map_gallery){
                startEditor(uri, map_edit_final);
            }

            else if(requestcode == map_edit_final){
                ImageView upload_map = findViewById(R.id.map_upload_image);
                Bitmap bt2;
                upload_map.setImageURI(uri);
                try {
                    bt2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                    map_view = getResizedBitmap(bt2, 1000);
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
        Intent intent = new Intent(Details_Banks.this, DsPhotoEditorActivity.class);

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
        gpsTracker = new GpsTracker(Details_Banks.this);
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