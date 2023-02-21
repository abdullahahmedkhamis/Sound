package audio.ndroid.musicplayerapp.sounds;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.ndroid.musicplayerapp.R;

import java.util.List;

public class AudioAdapter extends RecyclerView.Adapter<AudioAdapter.ViewHolder> implements View.OnClickListener {
    Context context;
    List<AudioInf> audios;

    public AudioAdapter(Context context, List<AudioInf> audios) {
        this.context = context;
        this.audios = audios;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myview = LayoutInflater.from( context ).inflate( R.layout.rows,parent,false );
        return new ViewHolder( myview );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
final AudioInf a = audios.get( position );
holder.audio_title.setText( a.audioTitle );
        holder.audio_duration.setText( a.audioDuration);
        holder.myCardView.setTag( position );
        holder.myCardView.setOnClickListener( this );

    }

    @Override
    public int getItemCount() {
        return audios.size();
    }

    @Override
    public void onClick(View view) {

        AudioInf audioInf;
        int position = (int) view.getTag();
        audioInf = audios.get( position );
        Intent intent = new Intent(context,PlayerActivity.class );
        intent.putExtra( "currentTitle", audioInf.audioTitle );
        context.startActivity( intent );
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView audio_title,audio_duration;
        CardView myCardView;
        public ViewHolder(@NonNull View itemView) {
            super( itemView );
            audio_title=itemView.findViewById( R.id.audio_title );
            audio_duration=itemView.findViewById( R.id.audio_duration );
            myCardView=itemView.findViewById( R.id.cardView );
        }
    }

}
