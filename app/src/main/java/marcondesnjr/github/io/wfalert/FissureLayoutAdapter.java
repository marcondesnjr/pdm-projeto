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

import marcondesnjr.github.io.wfalert.entity.Fissure;

public class FissureLayoutAdapter extends RecyclerView.Adapter<FissureLayoutAdapter.FissureLayoutHolder>{

    private Context ctx;
    private List<Fissure> fissureList;

    public FissureLayoutAdapter(Context ctx, List<Fissure> fissureList) {
        this.ctx = ctx;
        this.fissureList = fissureList;
    }

    @NonNull
    @Override
    public FissureLayoutHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(ctx);
        View view = layoutInflater.inflate(R.layout.fissure_card_layout,parent,false);
        return new FissureLayoutHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FissureLayoutHolder holder, int position) {
        Fissure fissure = fissureList.get(position);
        holder.textViewFissureNode.setText(fissure.getNode());
        holder.textViewFissureTier.setText(fissure.getTier());
        holder.textViewFissureFaction.setText(fissure.getFaction());
        holder.textViewFissureType.setText(fissure.getType());
    }

    @Override
    public int getItemCount() {
        return fissureList.size();
    }

    class FissureLayoutHolder extends RecyclerView.ViewHolder{

        private ImageView relicView;
        private TextView textViewFissureNode;
        private TextView textViewFissureTier;
        private TextView textViewFissureType;
        private TextView textViewFissureFaction;
        private TextView textViewFissureTime;

        public FissureLayoutHolder(View itemView) {
            super(itemView);
            this.textViewFissureNode = itemView.findViewById(R.id.textViewFissureNode);
            this.textViewFissureTier = itemView.findViewById(R.id.textViewFissureTier);
            this.textViewFissureType = itemView.findViewById(R.id.textViewFissureType);
            this.textViewFissureFaction = itemView.findViewById(R.id.textViewFissureFaction);
            this.textViewFissureTime = itemView.findViewById(R.id.textViewFissureTime);

        }
    }

}
