package it.flaviodepedis.mybooklistapp;

import android.content.Context;

import com.squareup.picasso.Picasso;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

/**
 * Created by flavio.depedis on 15/09/2017.
 */
public class BookAdapter extends ArrayAdapter<Book> {

    /**
     * Constructs a new {@link BookAdapter}.
     *
     * @param context of the app
     * @param book    is the list of books, which is the data source of the adapter
     */
    public BookAdapter(Context context, List<Book> book) {
        super(context, 0, book);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        String imageIcon = "";
        String author = "";
        double price = 0.0;
        String currencyCode = "";
        float averageRating = 0;
        String publishedDate = "";

        // Check if there is an existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.activity_book_list_item, parent, false);
        }

        // Find the book at the given position in the list of books
        Book currentBook = getItem(position);

        // Set title of the book
        TextView tvTitle = listItemView.findViewById(R.id.tv_title);
        tvTitle.setText(currentBook.getmTitle());

        // Set authors of the book
        TextView tvAuthors = listItemView.findViewById(R.id.tv_author);
        author = currentBook.getmAuthor();
        if(!author.isEmpty()){
            tvAuthors.setText(author);
        } else {
            tvAuthors.setText(R.string.no_author);
        }

        // Set image icon of the book if available.
        // Use Picasso library to load url thumbnail
        ImageView imgIconBook = listItemView.findViewById(R.id.book_icon);
        imageIcon = currentBook.getmThumbnail();
        if (imageIcon != null && imageIcon.length() > 0) {
            Picasso.with(getContext()).load(currentBook.getmThumbnail()).into(imgIconBook);
        } else {
            Picasso.with(getContext()).load(R.drawable.image_not_found).into(imgIconBook);
        }

        // Set the published date
        TextView tvPublishedDate = listItemView.findViewById(R.id.tv_publisher_date);
        publishedDate = currentBook.getmPublishedDate();
        if(!publishedDate.isEmpty()){
            tvPublishedDate.setText(publishedDate);
        } else {
            tvPublishedDate.setText(R.string.no_date);
        }

        // Set the price of the book
        TextView tvPrice = listItemView.findViewById(R.id.tv_price);
        price = currentBook.getmAmount();
        currencyCode = currentBook.getmCurrencyCode();
        if (price != 0.0) {
            tvPrice.setText(String.valueOf(price) + " " + currencyCode);
        } else {
            tvPrice.setText(R.string.no_price);
        }

        // Set the average rating of the book
        RatingBar ratingBar = listItemView.findViewById(R.id.rating_bar);
        averageRating = (float) currentBook.getmAverageRating();
        ratingBar.setRating(averageRating);


        return listItemView;
    }
}
