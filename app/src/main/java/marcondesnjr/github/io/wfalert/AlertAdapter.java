package marcondesnjr.github.io.wfalert;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import marcondesnjr.github.io.wfalert.entity.Alert;

public class AlertAdapter extends RecyclerView.Adapter<AlertAdapter.AlertViewHolder>{

    private Context context;
    private List<Alert> alertList;

    public AlertAdapter(Context context, List<Alert> alertList) {
        this.context = context;
        this.alertList = alertList;
    }

    @NonNull
    @Override
    public AlertViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.alerts_cards_layout, parent,false);
        return  new AlertViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlertViewHolder holder, int position) {
        Alert alert = alertList.get(position);
        holder.getTextViewNode().setText(alert.getPlaneta());
        holder.getImageViewReward().setImageDrawable(context.getResources().getDrawable(R.drawable.credits_pack));
    }

    @Override
    public int getItemCount() {
        return alertList.size();
    }


    class AlertViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageViewReward;
        private TextView textViewNode;
        private TextView textViewMissionType;
        private TextView textViewFaction;
        private TextView textViewTime;

        public AlertViewHolder(View itemView) {
            super(itemView);
            this.imageViewReward = itemView.findViewById(R.id.alertRewardImage);
            this.textViewNode = itemView.findViewById(R.id.textViewNode);
            this.textViewMissionType = itemView.findViewById(R.id.textViewMissionType);
            this.textViewFaction = itemView.findViewById(R.id.textViewMissionFaction);
            this.textViewTime = itemView.findViewById(R.id.textViewTime);
        }

        private void startComponents(){

        }

        public ImageView getImageViewReward() {
            return imageViewReward;
        }

        public TextView getTextViewNode() {
            return textViewNode;
        }

        public TextView getTextViewMissionType() {
            return textViewMissionType;
        }

        public TextView getTextViewFaction() {
            return textViewFaction;
        }

        public TextView getTextViewTime() {
            return textViewTime;
        }
    }
}
