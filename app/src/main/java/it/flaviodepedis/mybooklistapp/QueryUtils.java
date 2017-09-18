package it.flaviodepedis.mybooklistapp;

/**
 * Created by flavio.depedis on 15/09/2017.
 */

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import android.text.TextUtils;

import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Helper methods related to requesting and receiving book data from OpenWeatherMap.
 */
public final class QueryUtils {

    /**
     * Tag for the log messages
     */
    private static final String LOG_TAG = QueryUtils.class.getSimpleName();

    /**
     * Context of the caller activity
     */
    private static Context mContext;

    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtils() {
    }

    /**
     * Query the Google Book API dataset and return a list of {@link Book} objects.
     */
    public static List<Book> fetchBookData(String requestUrl, Context context) {

        mContext = context;

        Log.i(LOG_TAG, "Log - fetchBookData() method");

        // Create URL object
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        // Extract relevant fields from the JSON response and create a list of {@link Book}s
        List<Book> books = extractFeatureFromJson(jsonResponse);

        // Return the list of {@link Book}s
        return books;
    }

    /**
     * Returns new URL object from the given string URL.
     */
    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem building the URL ", e);
        }
        return url;
    }

    /**
     * Make an HTTP request to the given URL and return a String as the response.
     */
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.w(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the book JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // Closing the input stream could throw an IOException, which is why
                // the makeHttpRequest(URL url) method signature specifies than an IOException
                // could be thrown.
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    /**
     * Return a list of {@link Book} objects that has been built up from
     * parsing the given JSON response.
     */
    private static List<Book> extractFeatureFromJson(String bookJSON) {
        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(bookJSON)) {
            return null;
        }

        // Create an empty ArrayList that we can start adding books to
        List<Book> books = new ArrayList<>();

        // Try to parse the JSON response string. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            JSONObject baseJsonResponse;
            JSONArray baseJsonArray;
            JSONObject itemBook;
            JSONObject currVolumeInfo;
            JSONObject currSaleInfo;
            JSONObject currAccessInfo;
            JSONArray authorsArray;
            JSONArray categoryArray;
            JSONObject imageLinks;
            JSONObject retailPrice;
            JSONObject isEpub;
            JSONObject isPdf;

            String title;
            String authorsList = "";
            String publisher;
            String publishedDate;
            String description;
            int pageCount;
            String printType;
            String categoryList;
            double averageRating;
            String thumbnail;
            String price;
            String currencyCode;
            boolean isEbook;
            boolean isEpubAvailable = false;
            boolean isPdfAvailable = false;
            String buyLink;
            String webReaderLink;

            baseJsonResponse = new JSONObject(bookJSON);

            baseJsonArray = baseJsonResponse.getJSONArray("items");

            for (int i = 0; i < baseJsonArray.length(); i++) {

                itemBook = baseJsonArray.getJSONObject(i);

                // current volume information
                currVolumeInfo = itemBook.getJSONObject("volumeInfo");

                // current sales information
                currSaleInfo = itemBook.getJSONObject("saleInfo");

                // current access information
                currAccessInfo = itemBook.getJSONObject("accessInfo");

                // Get value for Title if the key exists
                if (currVolumeInfo.has("title")) {
                    title = currVolumeInfo.getString("title");
                } else {
                    title = mContext.getResources().getString(R.string.no_title);
                }

                // Get List of Author if there are more than one, if exist
                if (currVolumeInfo.has("authors")) {
                    authorsArray = currVolumeInfo.getJSONArray("authors");

                    // Verify if the author is one or more then one
                    if (authorsArray.length() > 1) {
                        authorsList = authorsArray.join(", ").replaceAll("\"", "");
                    } else if (authorsArray.length() == 1) {
                        authorsList = authorsArray.getString(0);
                    } else if (authorsArray.length() == 0) {
                        authorsList = mContext.getResources().getString(R.string.no_author);
                    }
                } else {
                    authorsList = mContext.getResources().getString(R.string.no_author);
                }

                // Get value for average rating if the key exists
                if (currVolumeInfo.has("averageRating")) {
                    averageRating = currVolumeInfo.getDouble("averageRating");
                } else {
                    averageRating = 0.0;
                }

                // Get the published date of the book if the key exists
                if (currVolumeInfo.has("publishedDate")) {
                    publishedDate = currVolumeInfo.getString("publishedDate");
                    // verify to format correct published date
                    if (publishedDate.contains("T")) {
                        publishedDate = publishedDate.substring(0, 10);
                    }
                } else {
                    publishedDate = mContext.getResources().getString(R.string.no_date);
                }

                // Get publisher of the book if the key exists
                if (currVolumeInfo.has("publisher")) {
                    publisher = currVolumeInfo.getString("publisher");
                } else {
                    publisher = mContext.getResources().getString(R.string.no_publisher);
                }

                // Get description of the book if the key exists
                if (currVolumeInfo.has("description")) {
                    description = currVolumeInfo.getString("description");
                } else {
                    description = mContext.getResources().getString(R.string.no_description);
                }

                // Get the thumbnail of the book if the key exists
                if (currVolumeInfo.has("imageLinks")) {
                    imageLinks = currVolumeInfo.getJSONObject("imageLinks");
                    thumbnail = imageLinks.getString("thumbnail");
                } else {
                    thumbnail = "";
                }

                // Get the page count of the book if the key exists
                if (currVolumeInfo.has("pageCount")) {
                    pageCount = currVolumeInfo.getInt("pageCount");
                } else {
                    pageCount = 0;
                }

                // Get the print type of the book if the key exists
                if (currVolumeInfo.has("printType")) {
                    printType = currVolumeInfo.getString("printType");
                } else {
                    printType = mContext.getResources().getString(R.string.no_print_type);
                }

                // Get first category if there are more than one, if exist
                if (currVolumeInfo.has("categories")) {
                    categoryArray = currVolumeInfo.getJSONArray("categories");
                    categoryList = categoryArray.getString(0);
                } else {
                    categoryList = mContext.getResources().getString(R.string.no_category);
                }

                // Get price and currency code of the book if the key exists
                if (currSaleInfo.has("retailPrice")) {
                    retailPrice = currSaleInfo.getJSONObject("retailPrice");
                    price = String.valueOf(retailPrice.getDouble("amount"));
                    currencyCode = retailPrice.getString("currencyCode");
                } else {
                    price = mContext.getResources().getString(R.string.no_price);
                    currencyCode = "";
                }

                // Get isEbook if the key exists
                if (currSaleInfo.has("isEbook")) {
                    isEbook = currSaleInfo.getBoolean("isEbook");
                } else {
                    isEbook = false;
                }

                // Get buyLink url if the key exists
                if (currSaleInfo.has("buyLink")) {
                    buyLink = currSaleInfo.getString("buyLink");
                } else {
                    buyLink = mContext.getResources().getString(R.string.no_buylink);
                }

                // Get indicators if ePub versions available
                if (currAccessInfo.has("epub")) {
                    isEpub = currAccessInfo.getJSONObject("epub");
                    if (isEpub.has("isAvailable")) {
                        isEpubAvailable = isEpub.getBoolean("isAvailable");
                    } else {
                        isEpubAvailable = false;
                    }
                } else {
                    isEpubAvailable = false;
                }

                // Get indicators if PDF versions available
                if (currAccessInfo.has("pdf")) {
                    isPdf = currAccessInfo.getJSONObject("pdf");
                    if (isPdf.has("isAvailable")) {
                        isPdfAvailable = isPdf.getBoolean("isAvailable");
                    } else {
                        isPdfAvailable = false;
                    }
                } else {
                    isPdfAvailable = false;
                }

                // Get web reader url if the key exists
                if (currSaleInfo.has("webReaderLink")) {
                    webReaderLink = currSaleInfo.getString("webReaderLink");
                } else {
                    webReaderLink = mContext.getResources().getString(R.string.no_webreaderlink);
                }

                // Create a new {@link Book} object from the JSON response.
                Book book = new Book(title, authorsList, publisher, publishedDate, description,
                        pageCount, printType, categoryList, averageRating, thumbnail, price, currencyCode,
                        isEbook, isEpubAvailable, isPdfAvailable, buyLink, webReaderLink);

                // Add the new {@link Book} to the list of books.
                books.add(book);

                Log.i(LOG_TAG, "Log - extractFeatureFromJson() method");
            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Problem parsing the book JSON results", e);
        }

        // Return the list of books
        return books;
    }

}
