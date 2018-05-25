package marcondesnjr.github.io.wfalert;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import marcondesnjr.github.io.wfalert.entity.Alert;
import marcondesnjr.github.io.wfalert.entity.Mission;
import marcondesnjr.github.io.wfalert.entity.Reward;

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
        Mission mission = alert.getMission();
        holder.textViewNode.setText(mission.getNome());
        holder.textViewMissionType.setText(mission.getTipo());
        holder.textViewFaction.setText(mission.getFaction());

        Map<Integer,Integer> mapTime = Util.timeLeft(Calendar.getInstance(), alert.getEnd());
        int hr = mapTime.get(Util.HOUR);
        int min = mapTime.get(Util.MINUTE);
        int sec = mapTime.get(Util.SECOND);


        holder.textViewTime.setText(String.format("%d : %d : %d",hr,min,sec));

        int drawableId = 0;
        switch (alert.getReward().get(0)){
            case FORMA: drawableId = R.drawable.forma;
            break;
            case CREDIT: drawableId = R.drawable.credit;
            break;
            case REACTOR: drawableId = R.drawable.reactor;
            break;
            default: drawableId = R.drawable.credit;
        }
        holder.imageViewReward.setImageDrawable(context.getResources().getDrawable(drawableId));

        //Tabela recompensas
        TableLayout tbl = holder.rewardsTable;
        tbl.removeAllViews();
        for (Reward r : alert.getReward()){
            TableRow tbr = new TableRow(tbl.getContext());
            TextView textView = new TextView(tbr.getContext());
            textView.setText(r.name());
            tbr.addView(textView);
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            tbl.addView(tbr,lp);
        }

    }

    @Override
    public int getItemCount() {
        return alertList.size();
    }


    class AlertViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageViewReward;
        private TextView textViewNode;
        private TextView textViewMissionType;
        private TextView textViewFaction;
        private TextView textViewTime;
        private TableLayout rewardsTable;

        public AlertViewHolder(View itemView) {
            super(itemView);
            this.imageViewReward = itemView.findViewById(R.id.alertRewardImage);
            this.textViewNode = itemView.findViewById(R.id.textViewNode);
            this.textViewMissionType = itemView.findViewById(R.id.textViewMissionType);
            this.textViewFaction = itemView.findViewById(R.id.textViewMissionFaction);
            this.textViewTime = itemView.findViewById(R.id.textViewTime);
            this.rewardsTable = itemView.findViewById(R.id.alertRewardsTable);

            itemView.findViewById(R.id.alertCardView).setOnClickListener(new AlertAdapter.OnClickListener());


        }


    }

    class OnClickListener implements View.OnClickListener{


        private int originalHeight = 0;
        private boolean IsViewExpanded = false;
        private TableLayout yourCustomView;


        @Override
        public void onClick(final View view) {
            // If the originalHeight is 0 then find the height of the View being used
            // This would be the height of the cardview
            if (originalHeight == 0) {
                originalHeight = view.getHeight();
            }
            boolean mIsViewExpanded = view.findViewById(R.id.alertRewardsTable).getVisibility() == View.VISIBLE;
            yourCustomView = view.findViewById(R.id.alertRewardsTable);
            // Declare a ValueAnimator object
            ValueAnimator valueAnimator;
            if (!mIsViewExpanded) {
                yourCustomView.setVisibility(View.VISIBLE);
                yourCustomView.setEnabled(true);
                mIsViewExpanded = true;
                valueAnimator = ValueAnimator.ofInt(originalHeight, originalHeight + (int) (originalHeight * 0.5)); // These values in this method can be changed to expand however much you like
            } else {
                mIsViewExpanded = false;
                valueAnimator = ValueAnimator.ofInt(originalHeight + (int) (originalHeight * 0.5), originalHeight);

                Animation a = new AlphaAnimation(1.00f, 0.00f); // Fade out

                a.setDuration(200);
                // Set a listener to the animation and configure onAnimationEnd
                a.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        yourCustomView.setVisibility(View.INVISIBLE);
                        yourCustomView.setEnabled(false);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

                // Set the animation on the custom view
                yourCustomView.startAnimation(a);
            }
            valueAnimator.setDuration(200);
            valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator animation) {
                    Integer value = (Integer) animation.getAnimatedValue();
                    view.getLayoutParams().height = value.intValue();
                    view.requestLayout();
                }
            });
            valueAnimator.start();
        }


    }

}
