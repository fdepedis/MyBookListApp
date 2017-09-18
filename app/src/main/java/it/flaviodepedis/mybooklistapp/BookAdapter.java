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

import butterknife.BindView;
import butterknife.ButterKnife;

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

        String imageIcon;
        float averageRating;

        // Check if there is an existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is not null, then inflate a new list item layout.
        View listItemView = convertView;
        ViewHolder holder;
        if (listItemView != null) {
            holder = (ViewHolder) listItemView.getTag();
        } else {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.activity_book_list_item, parent, false);
            holder = new ViewHolder(listItemView);
            listItemView.setTag(holder);
        }

        // Find the book at the given position in the list of books
        Book currentBook = getItem(position);

        // Set title of the book
        holder.tvTitle.setText(currentBook.getmTitle());

        // Set authors of the book if available
        holder.tvAuthors.setText(currentBook.getmAuthor());

        // Set image icon of the book if available.
        // Use Picasso library to load url thumbnail
        imageIcon = currentBook.getmThumbnail();
        if (imageIcon != null && imageIcon.length() > 0) {
            Picasso.with(getContext()).load(currentBook.getmThumbnail()).into(holder.imgIconBook);
        } else {
            Picasso.with(getContext()).load(R.drawable.image_not_found).into(holder.imgIconBook);
        }

        // Set the published date if available
        holder.tvPublishedDate.setText(currentBook.getmPublishedDate());

        // Set the price of the book if available
        holder.tvPrice.setText(currentBook.getmAmount() + " " + currentBook.getmCurrencyCode());

        // Set the average rating of the book
        averageRating = (float) currentBook.getmAverageRating();
        holder.ratingBar.setRating(averageRating);

        return listItemView;
    }

    static class ViewHolder {
        @BindView(R.id.tv_title) TextView tvTitle;
        @BindView(R.id.tv_author) TextView tvAuthors;
        @BindView(R.id.book_icon) ImageView imgIconBook;
        @BindView(R.id.tv_publisher_date) TextView tvPublishedDate;
        @BindView(R.id.tv_price) TextView tvPrice;
        @BindView(R.id.rating_bar) RatingBar ratingBar;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
