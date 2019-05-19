package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.json.JsonUtils;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    private Sandwich sandwich;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
            return;
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI();
        loadImage();

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {
        populateTextView(sandwich.getAlsoKnownAs(), R.id.also_known_label, R.id.also_known_text);
        populateTextView(sandwich.getPlaceOfOrigin(), R.id.origin_label, R.id.origin_text);
        populateTextView(sandwich.getDescription(), R.id.description_label, R.id.description_text);
        populateTextView(sandwich.getIngredients(), R.id.ingredients_label, R.id.ingredients_text);
    }

    private void populateTextView(String sandwichDetail, int labelTvRes, int dataTvRes) {
        TextView dataTv = findViewById(dataTvRes);
        if (sandwichDetail.isEmpty()) {
            findViewById(labelTvRes).setVisibility(View.GONE);
            dataTv.setVisibility(View.GONE);
        } else {
            dataTv.setText(sandwichDetail);
        }
    }

    private void populateTextView(List<String> sandwichDetail, int labelTvRes, int dataTvRes) {
        TextView dataTv = findViewById(dataTvRes);
        if (sandwichDetail.isEmpty()) {
            findViewById(labelTvRes).setVisibility(View.GONE);
            dataTv.setVisibility(View.GONE);
        } else {
            dataTv.setText(TextUtils.join(", ", sandwichDetail));
        }
    }

    private void loadImage() {
        final ImageView sandwichIv = findViewById(R.id.image_iv);
        Picasso.with(this)
                .load(sandwich.getImage())
                .error(R.drawable.ic_broken_image_black_192)
                .into(sandwichIv, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {
                        sandwichIv.setScaleType(ImageView.ScaleType.CENTER);
                    }
                });
    }
}
