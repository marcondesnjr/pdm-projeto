package marcondesnjr.github.io.wfalert;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import marcondesnjr.github.io.wfalert.entity.Invasion;

public class InvasionLayoutAdapter extends RecyclerView.Adapter<InvasionLayoutAdapter.InvasionLayoutHolder>{

    private List<Invasion> invasionList;
    private Context ctx;

    public InvasionLayoutAdapter(Context ctx, List<Invasion> invasionList) {
        this.invasionList = invasionList;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public InvasionLayoutHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(ctx);
        View view = layoutInflater.inflate(R.layout.invasion_card_layout, parent,false);
        return new InvasionLayoutHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InvasionLayoutHolder holder, int position) {
        Invasion invasion = invasionList.get(position);
        holder.textViewNode.setText(invasion.getMission().getNome());
        holder.textViewFaction.setText(invasion.getMission().getTipo());
        holder.progressBar.setProgress(invasion.getProgress());
    }

    @Override
    public int getItemCount() {
        return invasionList.size();
    }

    class InvasionLayoutHolder extends RecyclerView.ViewHolder{

        private TextView textViewNode;
        private TextView textViewFaction;
        private ProgressBar progressBar;
        private  TextView textViewTime;

        public InvasionLayoutHolder(View itemView) {
            super(itemView);
            this.textViewNode = itemView.findViewById(R.id.textViewNode);
            this.textViewFaction = itemView.findViewById(R.id.textViewMissionFaction);
            this.progressBar = itemView.findViewById(R.id.progressBar);
        }
    }

}
