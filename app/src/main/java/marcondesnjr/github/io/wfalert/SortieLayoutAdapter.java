package marcondesnjr.github.io.wfalert;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class SortieLayoutAdapter extends RecyclerView.Adapter<SortieLayoutAdapter.SortieLayoutHolder>{


    @NonNull
    @Override
    public SortieLayoutHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull SortieLayoutHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class SortieLayoutHolder extends RecyclerView.ViewHolder{

        public SortieLayoutHolder(View itemView) {
            super(itemView);
        }
    }

}
