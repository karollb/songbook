package pl.edu.wszib.songbook;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import pl.edu.wszib.songbook.permissions.StoragePermission;
import pl.edu.wszib.songbook.service.SongbookService;

public class SongbookActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "message";
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songbook);
        StoragePermission.checkPermission(SongbookActivity.this, SongbookActivity.this);

        Intent intent = getIntent();

        ArrayList<String> fileNames = SongbookService.getFilesList(new File(intent.getStringExtra(EXTRA_MESSAGE)));
        Collections.sort(fileNames, String.CASE_INSENSITIVE_ORDER);

        arrayAdapter = new ArrayAdapter<>(SongbookActivity.this, android.R.layout.simple_list_item_1, fileNames);

        ListView listView = findViewById(R.id.files_list_view);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            if (arrayAdapter.getItem(position).endsWith(".pdf")) {
                Intent i = new Intent(SongbookActivity.this, SongActivity.class);
                i.putExtra(SongActivity.EXTRA_MESSAGE, intent.getStringExtra(SongbookActivity.EXTRA_MESSAGE) + "/" + arrayAdapter.getItem(position));
                startActivity(i);
            } else {
                Intent i2 = new Intent(SongbookActivity.this, SongbookActivity.class);
                i2.putExtra(SongbookActivity.EXTRA_MESSAGE, intent.getStringExtra(SongbookActivity.EXTRA_MESSAGE) + "/" + arrayAdapter.getItem(position));
                startActivity(i2);

            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_songbook, menu);

        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                SongbookActivity.this.arrayAdapter.getFilter().filter(newText);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == StoragePermission.getSTORAGE_PERMISSION_code() && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(SongbookActivity.this, "Storage Permission Granted", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(SongbookActivity.this, "Storage Permission Denied", Toast.LENGTH_SHORT).show();
        }
    }
}