package com.example.ppt_application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ppt_application.adapter.template_adapter;
import com.example.ppt_application.model.TemplateModel;

import java.util.ArrayList;
import java.util.List;

public class template_list extends AppCompatActivity {

    ImageView back_button;
    RecyclerView recyclerView;
    template_adapter adapter;
    List<TemplateModel> modelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        setContentView(R.layout.activity_template_list);

        recyclerView = findViewById(R.id.all_category);
        back_button = findViewById(R.id.back);


        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back1 = new Intent(template_list.this, MainActivity.class);
                startActivity(back1);
                finish();
            }
        });

        modelList = new ArrayList<>();
        // imgurl , name, id
        modelList.add(new TemplateModel(R.drawable.flatss, "Flats", "1"));
        modelList.add(new TemplateModel(R.drawable.hotelss, "Hotels", "2"));
        modelList.add(new TemplateModel(R.drawable.villas, "Villas", "3"));
        modelList.add(new TemplateModel(R.drawable.banks, "Banks & ATMs", "4"));
        modelList.add(new TemplateModel(R.drawable.lands, "Lands", "5"));
        modelList.add(new TemplateModel(R.drawable.warehouse, "Warehouse", "6"));
        modelList.add(new TemplateModel(R.drawable.hostels, "Hostel", "7"));
        modelList.add(new TemplateModel(R.drawable.offices, "Offices", "8"));
        modelList.add(new TemplateModel(R.drawable.outlets, "Outlets", "9"));

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(4, dpToPx(16), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new template_adapter(modelList, this);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                if(position == 0){
                    Intent intent1 = new Intent(template_list.this,Details_Flats.class);
                    startActivity(intent1);
                }

                if(position == 1){
                    Intent intent1 = new Intent(template_list.this,Details_hotels.class);
                    startActivity(intent1);
                }

                if(position == 2){
                    Intent intent1 = new Intent(template_list.this,Details_villas.class);
                    startActivity(intent1);
                }

                if(position == 3){
                    Intent intent1 = new Intent(template_list.this,Details_Banks.class);
                    startActivity(intent1);
                }

                if(position == 4){
                    Intent intent1 = new Intent(template_list.this,Details_land.class);
                    startActivity(intent1);
                }

                if(position == 5){
                    Intent intent1 = new Intent(template_list.this,Details_warehouse.class);
                    startActivity(intent1);
                }
                if(position == 6){
                    Intent intent1 = new Intent(template_list.this,Details_Hostel.class);
                    startActivity(intent1);
                }

                if(position == 7){
                    Intent intent1 = new Intent(template_list.this,Details_Office.class);
                    startActivity(intent1);
                }

                if(position == 8){
                    Intent intent1 = new Intent(template_list.this,Details_Outlet.class);
                    startActivity(intent1);
                }
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

    }

    // now we need some item decoration class for manage spacing

    public static class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}