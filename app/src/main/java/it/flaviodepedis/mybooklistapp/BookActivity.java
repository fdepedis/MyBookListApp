package it.flaviodepedis.mybooklistapp;

import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.app.LoaderManager;
import java.util.ArrayList;
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
     * Constant for max result in query
     *
     */
    private static final String MAX_RESULT = "15";

    /**
     * URL for book data from the Google Book API dataset
     */
    private static String OPEN_BOOK_MAP_REQUEST_URL =
            "https://www.googleapis.com/books/v1/volumes?";

    /**
     * TextView that is displayed when the list is empty
     */
    private TextView mEmptyStateTextView;

    /**
     * Adapter for the list of book
     */
    private BookAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        // Find a reference to the {@link ListView} in the layout
        ListView bookListView = (ListView) findViewById(R.id.list);

        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        bookListView.setEmptyView(mEmptyStateTextView);

        // Create a new adapter that takes an empty list of books as input
        mAdapter = new BookAdapter(this, new ArrayList<Book>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        bookListView.setAdapter(mAdapter);

        //Search only if network is available else show proper message
        if (isNetworkWorking()) {

            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getLoaderManager();

            Log.i(LOG_TAG, "Log - Before initLoader() call");

            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            loaderManager.initLoader(BOOK_LOADER_ID, null, this);

            Log.i(LOG_TAG, "Log - After initLoader() call");

        } else {
            // Otherwise, display error
            // First, hide loading indicator so error message will be visible
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);

            // Update empty state with no connection error message
            mEmptyStateTextView.setText(R.string.no_internet_connection);
        }

        bookListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                // Find the current book that was clicked on
                Book currentBook = mAdapter.getItem(position);

                // Create a new intent to view the book details and pass with intent
                // the currentBook object
                Intent intent = new Intent(BookActivity.this, BookDetailActivity.class);
                intent.putExtra("currentBook", currentBook);
                startActivity(intent);
            }
        });

    }

    @Override
    public Loader<List<Book>> onCreateLoader(int id, Bundle args) {

        Log.i(LOG_TAG, "Log - onCreateLoader() method");

        Intent intent = getIntent();
        String query = intent.getStringExtra("nameSearch");

        Uri baseUri = Uri.parse(OPEN_BOOK_MAP_REQUEST_URL);
        Uri.Builder uriBuilder = baseUri.buildUpon();
        uriBuilder.appendQueryParameter("q", query);
        uriBuilder.appendQueryParameter("maxResults", MAX_RESULT);

        // Create a new loader for the given URL
        return new BookLoader(this, uriBuilder.toString());
    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> data) {

        Log.i(LOG_TAG, "Log - onLoadFinished() method");

        // Hide loading indicator because the data has been loaded
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);

        // Set empty state text to display "No book found."
        mEmptyStateTextView.setText(R.string.no_book);

        // Clear the adapter of previous book data
        mAdapter.clear();

        // If there is a valid list of {@link Book}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (data != null && !data.isEmpty()) {
            mAdapter.addAll(data);
        }

    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {

        Log.i(LOG_TAG, "Log - onLoaderReset() method");

        // Loader reset, so we can clear out our existing data.
        mAdapter.clear();
    }

    /**
     * Method to check if network is working
     *
     * @return boolean value
     */
    private boolean isNetworkWorking() {

        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connectivityManager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isConnected();
    }
}
