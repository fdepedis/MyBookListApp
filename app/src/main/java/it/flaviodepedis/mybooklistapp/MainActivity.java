package it.flaviodepedis.mybooklistapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final String ERROR_MESSAGE_EMPTY = "Insert context book";
    private String nameSearch;

    Button btnSearch;
    EditText etSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSearch = (Button) findViewById(R.id.search);
        etSearch = (EditText) findViewById(R.id.etSearchBook);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nameSearch = etSearch.getText().toString().trim();

                if (nameSearch != null && !nameSearch.isEmpty()) {
                    Intent setIntent = new Intent(MainActivity.this, BookActivity.class);
                    setIntent.putExtra("nameSearch", nameSearch);
                    startActivity(setIntent);
                } else {
                    Toast.makeText(getApplicationContext(), ERROR_MESSAGE_EMPTY, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
