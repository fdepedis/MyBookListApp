package it.flaviodepedis.mybooklistapp;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by flavio.depedis on 18/09/2017.
 */
public class BookDetailActivity extends AppCompatActivity {

    @BindView(R.id.tv_detail_title) TextView tvTitleBook;
    @BindView(R.id.tv_detail_authors) TextView tvAuthorsBook;
    @BindView(R.id.img_detail_icon) ImageView imgIconBook;
    @BindView(R.id.tv_detail_price) TextView tvPriceBook;
    @BindView(R.id.rating_bar_detail) RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        ButterKnife.bind(this);

        String imageIcon;

        /** Get Intent Extras */
        if (getIntent().getExtras() != null) {

            //Intent intent = getIntent();
            Book currentBook = (Book) getIntent().getSerializableExtra("currentBook");

            //set title of current book
            tvTitleBook.setText(currentBook.getmTitle());
            //set authors of current book
            tvAuthorsBook.setText(currentBook.getmAuthor());
            //set image of current book
            imageIcon = currentBook.getmThumbnail();
            if (imageIcon != null && imageIcon.length() > 0) {
                Picasso.with(getApplicationContext()).load(currentBook.getmThumbnail()).into(imgIconBook);
            } else {
                Picasso.with(getApplicationContext()).load(R.drawable.image_not_found).into(imgIconBook);
            }
            //set rating of current book
            ratingBar.setRating((float) currentBook.getmAverageRating());
            //set price of current book
            tvPriceBook.setText(currentBook.getmAmount() + " " + currentBook.getmCurrencyCode());
            //set title of current book

        }
    }

    // When you click the back button on the ActionBar, you must be passing an intent
    // to create a new Activity. What you need to do is check if the Activity already exists or not.
    // If it does, then you need to resume it. Otherwise, create a new one.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                Intent upIntent = NavUtils.getParentActivityIntent(this);
                if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
                    TaskStackBuilder.create(this).addNextIntentWithParentStack(upIntent).startActivities();
                } else {
                    upIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(upIntent);
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
