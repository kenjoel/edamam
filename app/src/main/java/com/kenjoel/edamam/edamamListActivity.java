package com.kenjoel.edamam;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kenjoel.green.Adapters.FoodListAdapter;
import com.kenjoel.green.Product;
import com.kenjoel.green.SpoonacularResponseData;
import com.kenjoel.green.network.FoodApi;
import com.kenjoel.green.network.FoodClient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class edamamListActivity extends AppCompatActivity {

//    private SharedPreferences mSharedPreferences;
//
//    private String mRecentAddress;

    public static final String TAG = FoodListActivity.class.getSimpleName();


    //    @BindView(R.id.locationTextView) TextView mLocationTextView;
//    @BindView(R.id.listView) ListView mListView;
    private  FoodListAdapter mAdapter;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.errorTextView) TextView mErrorTextView;
    @BindView(R.id.progressBar) ProgressBar mProgressBar;

    public List<Product> food;

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private String mRecentAddress;

    private String foodStuff;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        foodStuff = intent.getStringExtra("foodstuff");



    FoodApi foodApi = FoodClient.getClient();
    Call<SpoonacularResponseData> call = foodApi.getRecipe(foodStuff, "32ab879467874a569b37ad3a513db984");
        Log.i(TAG, "the call"+ call);

        call.enqueue(new Callback<SpoonacularResponseData>() {
                @Override
                public void onResponse(Call<SpoonacularResponseData> call, Response<SpoonacularResponseData> response) {
                    if (response.isSuccessful()) {
                        hideProgressBar();
                        food = response.body().getProducts();
                        Log.i(TAG, "onResponse" + food);
                        mAdapter = new FoodListAdapter(FoodListActivity.this, food);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager =
                                new LinearLayoutManager(FoodListActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);

                        showRestaurants();
                    } else {
                        showUnsuccessfulMessage();
                        Log.i(TAG, "hapa" + food);
                    }
                }

                @Override
                public void onFailure(Call<SpoonacularResponseData> call, Throwable t) {
                    Log.e(TAG, "onFailure: ", t);
                    hideProgressBar();
                    showFailureMessage();

                }

            });
        }




    private void showFailureMessage() {
        mErrorTextView.setText("Something went wrong. Please check your Internet connection and try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showUnsuccessfulMessage() {
        mErrorTextView.setText("Something went wrong. Please try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showRestaurants() {
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

}