package pl.edu.wszib.songbook;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import pl.edu.wszib.songbook.permissions.StoragePermission;

public class MainActivity extends AppCompatActivity {
    private final String path = Environment.getExternalStorageDirectory().toString() + "/Spiewnik";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StoragePermission.checkPermission(MainActivity.this, MainActivity.this);

        Button songbookButton = findViewById(R.id.songbookButton);
        songbookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == StoragePermission.getSTORAGE_PERMISSION_code() && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(MainActivity.this, "Storage Permission Granted", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "Storage Permission Denaided", Toast.LENGTH_SHORT).show();
        }
    }
}