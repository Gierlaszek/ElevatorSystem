package Adapter;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import engine.Elevator;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.example.R;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<Elevator> status = new ArrayList<Elevator>();

    public MyAdapter(List<Elevator> _status){
        status = _status;
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View list = layoutInflater.inflate(R.layout.list_elevator, parent, false);
        ViewHolder viewHolder = new ViewHolder(list);
        return viewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {
        //final List<Elevator> allElevator = new ArrayList<Elevator>();
        holder.ID.setText(String.valueOf(status.get(position).getStatus().get("ID")));
        holder.position.setText(String.valueOf(status.get(position).getStatus().get("position")));
        if(status.get(position).getStatus().get("target") == null){
            holder.target.setText("No target");
        }
        holder.target.setText(String.valueOf(status.get(position).getStatus().get("target")));
        if(status.get(position).getStatus().get("direction") == 1){
            holder.arrow_up.setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);
            holder.arrow_down.clearColorFilter();
        }
        else if(status.get(position).getStatus().get("direction") == -1){
            holder.arrow_down.setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);
            holder.arrow_up.clearColorFilter();
        }
        else {
            holder.arrow_up.clearColorFilter();
            holder.arrow_down.clearColorFilter();
        }

    }

    @Override
    public int getItemCount() {
        return status.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView arrow_up;
        public ImageView arrow_down;
        public TextView ID;
        public TextView position;
        public TextView target;
        public RelativeLayout relativeLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            this.arrow_down = (ImageView) itemView.findViewById(R.id.arrow_down);
            this.arrow_up = (ImageView) itemView.findViewById(R.id.arrow_up);
            this.ID = (TextView) itemView.findViewById(R.id.ID);
            this.position = (TextView) itemView.findViewById(R.id.Position2);
            this.target = (TextView) itemView.findViewById(R.id.Target2);
            this.relativeLayout = (RelativeLayout) itemView.findViewById(R.id.relativeLayout);
        }
    }
}
