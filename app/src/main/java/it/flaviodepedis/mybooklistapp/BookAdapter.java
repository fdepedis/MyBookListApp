package it.flaviodepedis.mybooklistapp;

import android.content.Context;
import com.squareup.picasso.Picasso;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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
     * @param book is the list of books, which is the data source of the adapter
     */
    public BookAdapter(Context context, List<Book> book) {
        super(context, 0, book);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        String imageIcon = "";

        // Check if there is an existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.activity_book_list_item, parent, false);
        }

        // Find the book at the given position in the list of books
        Book currentBook = getItem(position);

        TextView tvTitle = (TextView) listItemView.findViewById(R.id.tv_title);
        tvTitle.setText(currentBook.getmTitle());

        ImageView imgIconBook = (ImageView) listItemView.findViewById(R.id.book_icon);
        // Drawable image = currentBook.getmThumbnail();
        // imgIconBook.setBackgroundResource(currentBook.getmThumbnail());

        // Set Image if available
        imageIcon = currentBook.getmThumbnail();
        if (imageIcon != null && imageIcon.length() > 0) {
            Picasso.with(getContext()).load(currentBook.getmThumbnail()).into(imgIconBook);
        } else {
            Picasso.with(getContext()).load(R.drawable.image_not_found).into(imgIconBook);
        }


        return listItemView;
    }
}
