package it.flaviodepedis.mybooklistapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

public class BookAdapter  extends ArrayAdapter<Book> {

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
        // Check if there is an existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.activity_book_list_item, parent, false);
        }

        // Find the meteo at the given position in the list of meteos
        Book currentMeteo = getItem(position);



        return listItemView;
    }
}
