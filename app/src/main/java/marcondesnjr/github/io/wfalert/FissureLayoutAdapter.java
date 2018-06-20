package marcondesnjr.github.io.wfalert;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import marcondesnjr.github.io.wfalert.entity.Fissure;
import marcondesnjr.github.io.wfalert.entity.Mission;

public class FissureLayoutAdapter extends RecyclerView.Adapter<FissureLayoutAdapter.FissureLayoutHolder>{

    private Context ctx;
    private List<Fissure> fissureList;

    public FissureLayoutAdapter(Context ctx, List<Fissure> fissureList) {
        this.ctx = ctx;
        this.fissureList = fissureList;
    }

    public void addItem(Fissure fissure){
        if(this.fissureList == null){
            this.fissureList = new ArrayList<>();
        }
        this.fissureList.add(fissure);
        notifyDataSetChanged();
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
        holder.textViewFissureNode.setText(fissure.getMission().getLocation());
        holder.textViewFissureTier.setText(fissure.getMission().getModifier());
        holder.textViewFissureFaction.setText(fissure.getMission().getFaction());
        holder.textViewFissureType.setText(fissure.getMission().getType());

        //Set time left
        ViewUtils.startCountdownTime(holder.textViewFissureTime, fissure.getExpiry(), "%02d : %02d : %02d");

        //Set Icon
        int drawableId = 0;
        String modifier = fissure.getMission().getModifier();
        switch (modifier){
            case "Lith": drawableId = R.drawable.lith;
            break;
            case "Meso": drawableId = R.drawable.meso;
            break;
            case "Neo": drawableId = R.drawable.neo;
            break;
            case "Axi": drawableId = R.drawable.axi;
        }
        holder.relicView.setImageDrawable(ctx.getResources().getDrawable(drawableId));

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
            this.relicView = itemView.findViewById(R.id.relicImage);
            this.textViewFissureNode = itemView.findViewById(R.id.textViewFissureNode);
            this.textViewFissureTier = itemView.findViewById(R.id.textViewFissureTier);
            this.textViewFissureType = itemView.findViewById(R.id.textViewFissureType);
            this.textViewFissureFaction = itemView.findViewById(R.id.textViewFissureFaction);
            this.textViewFissureTime = itemView.findViewById(R.id.textViewFissureTime);

        }
    }

}
