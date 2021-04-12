package Adapter;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.example.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import engine.Elevator;

public class MyAdapterGuest extends RecyclerView.Adapter<MyAdapterGuest.ViewHolder> {

   private List<HashMap<String, Integer>> ListGuest = new ArrayList<HashMap<String, Integer>>();

   public MyAdapterGuest( List<HashMap<String, Integer>> _ListGuest){ ListGuest = _ListGuest; }

    @NonNull
    @Override
    public MyAdapterGuest.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View list = layoutInflater.inflate(R.layout.list_guest, parent, false);
        MyAdapterGuest.ViewHolder viewHolder = new MyAdapterGuest.ViewHolder(list);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapterGuest.ViewHolder holder, int position) {
        //final List<HashMap<String, Integer>> ListGuest = new ArrayList<HashMap<String, Integer>>();
        holder.position.setText(String.valueOf(ListGuest.get(position).get("Position")));
        holder.target.setText(String.valueOf(ListGuest.get(position).get("Target")));
        holder.ID.setText(String.valueOf(position+1));
    }

    @Override
    public int getItemCount() {
        return ListGuest.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView ID;
        public TextView position;
        public TextView target;
        public RelativeLayout relativeLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            this.ID = (TextView) itemView.findViewById(R.id.IDGuest);
            this.position = (TextView) itemView.findViewById(R.id.PositionGuest);
            this.target = (TextView) itemView.findViewById(R.id.TargetGuest);
            this.relativeLayout = (RelativeLayout) itemView.findViewById(R.id.relativeLayout);
        }
    }
}
