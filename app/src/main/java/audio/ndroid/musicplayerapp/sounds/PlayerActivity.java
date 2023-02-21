package audio.ndroid.musicplayerapp.sounds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.ndroid.musicplayerapp.R;

public class PlayerActivity extends AppCompatActivity {

    TextView title, duration;
    String currenturl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_player );

        Intent intent = getIntent();
        title = findViewById( R.id.played_title );
        title.setText( intent.getStringExtra( "currentTitle" ) );
    }
}