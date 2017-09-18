package it.flaviodepedis.mybooklistapp;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BookDetailActivity extends AppCompatActivity {

    @BindView(R.id.tv_detail_title) TextView tvTitleBook;
    @BindView(R.id.tv_detail_authors) TextView tvAuthorsBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        ButterKnife.bind(this);

        /** Get Intent Extras */
        if (getIntent().getExtras() != null) {

            //Intent intent = getIntent();
            Book currentBook = (Book) getIntent().getSerializableExtra("currentBook");

            tvTitleBook.setText(currentBook.getmTitle());
            tvAuthorsBook.setText(currentBook.getmAuthor());

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
