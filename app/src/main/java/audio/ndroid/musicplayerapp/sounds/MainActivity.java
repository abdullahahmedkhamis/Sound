package audio.ndroid.musicplayerapp.sounds;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import com.ndroid.musicplayerapp.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {


    List<AudioInf> audios = new ArrayList<>();
    RecyclerView recyclerView;
    AudioAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );


        recyclerView = findViewById( R.id.list_item );
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager( this );
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration( recyclerView.getContext(),linearLayoutManager.getOrientation() );

        recyclerView.setLayoutManager( linearLayoutManager );
        recyclerView.addItemDecoration( dividerItemDecoration );
        adapter = new AudioAdapter( this,audios );

        loadAudios();
    }


    public static String getDuration (long millis){
        long hours, minutes,secounds;

        hours = TimeUnit.MILLISECONDS.toHours(millis);
        millis -= TimeUnit.HOURS.toMillis(hours);
        minutes = TimeUnit.MILLISECONDS.toHours( millis );
        millis -= TimeUnit.MINUTES.toMillis(minutes);
        secounds = TimeUnit.MILLISECONDS.toSeconds(millis);

        StringBuilder finleduration = new StringBuilder(64);
        if(hours != 0){
            finleduration.append( hours );
            finleduration.append( ":" );

        }
        if(minutes != 0){
            finleduration.append( minutes );
            finleduration.append( ":" );
        }
        if(secounds != 0){
            if(hours == 0 && minutes == 0 ){
                finleduration.append( secounds );
                finleduration.append( "sec" );
            }
            if(hours!=0 || minutes !=0)
            {
                finleduration.append( secounds );

            }
        }
        return finleduration.toString();
    }
    public void loadAudios(){
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
String orderBy = MediaStore.Audio.Media.DISPLAY_NAME;
        String selected = MediaStore.Audio.Media.IS_MUSIC+"!=0";
        Cursor cursor = getContentResolver().query( uri,null,selected,null, orderBy );

        if(cursor != null){
            if(cursor.moveToFirst()){
                do {
                    String title = cursor.getString( cursor.getColumnIndex( MediaStore.Audio.Media.TITLE ) );
                    String duration = cursor.getString( cursor.getColumnIndex( MediaStore.Audio.Media.DURATION ) );
                    String url = cursor.getString( cursor.getColumnIndex( MediaStore.Audio.Media.DATA ) );
                    String rightduration = getDuration( Long.parseLong( duration ) );
                    AudioInf audio = new AudioInf( title,duration,url );
                    audios.add( audio );


                }while (cursor.moveToNext());
            }
            cursor.close();
            adapter = new AudioAdapter( this, audios );
        }

       recyclerView.setAdapter( adapter );

    }


}
