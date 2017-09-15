package it.flaviodepedis.mybooklistapp;

import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

/**
 * Created by flavio.depedis on 15/09/2017.
 */
public class BookActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<List<Book>> {

    private static final String LOG_TAG = BookActivity.class.getName();

    /**
     * Constant value for the book loader ID. We can choose any integer.
     * This really only comes into play if you're using multiple loaders.
     */
    private static final int BOOK_LOADER_ID = 1;

    /**
     * URL for book data from the Google Book API dataset
     */
    private static String OPEN_BOOK_MAP_REQUEST_URL =
            "https://www.googleapis.com/books/v1/volumes?q=";

    /**
     * TextView that is displayed when the list is empty
     */
    private TextView mEmptyStateTextView;

    /**
     * Adapter for the list of meteo
     */
    private BookAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);



    }

    @Override
    public Loader<List<Book>> onCreateLoader(int id, Bundle args) {

        Log.i(LOG_TAG, "Log - onCreateLoader() method");

        return null;
    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> data) {

        Log.i(LOG_TAG, "Log - onLoadFinished() method");

    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {

        Log.i(LOG_TAG, "Log - onLoaderReset() method");

    }
}
