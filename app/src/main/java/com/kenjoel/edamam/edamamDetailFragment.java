package com.kenjoel.edamam;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.kenjoel.green.Product;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class edamamDetailFragment extends Fragment {
    @BindView(R.id.restaurantImageView) ImageView mImageLabel;
    @BindView(R.id.restaurantNameTextView) TextView mNameLabel;
    @BindView(R.id.cuisineTextView) TextView mCategoriesLabel;
    @BindView(R.id.ratingTextView) TextView mRatingLabel;
    @BindView(R.id.phoneTextView) TextView mPhoneLabel;
    @BindView(R.id.addressTextView) TextView mAddressLabel;

    private Product mFood;

    public edamamDetailFragment() {
        // Required empty public constructor
    }

    public static FoodDetailFragment newInstance(Product mFood) {
        FoodDetailFragment restaurantDetailFragment = new FoodDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("restaurant", Parcels.wrap(mFood));
        restaurantDetailFragment.setArguments(args);
        return restaurantDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFood = Parcels.unwrap(getArguments().getParcelable("food"));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_food_detail, container, false);
        ButterKnife.bind(this, view);

        Picasso.get().load(mFood.getImage()).into(mImageLabel);

        List<String> categories = new ArrayList<>();

//        for (String product: mFood.getTitle()) {
//            categories.add(product);
//        }
//
//        mNameLabel.setText(food.getFood().getLabel());
//        mCategoriesLabel.setText(android.text.TextUtils.join(", ", categories));
//        mRatingLabel.setText(food.getFood().getCategory());
//        mPhoneLabel.setText((int) food.getFood().getNutrients().getFAT());
//        mAddressLabel.setText((int) food.getFood().getNutrients().getFAT());

        return view;
    }
}