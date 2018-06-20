package marcondesnjr.github.io.wfalert;

import android.animation.ValueAnimator;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import marcondesnjr.github.io.wfalert.entity.Alert;
import marcondesnjr.github.io.wfalert.entity.Mission;
import marcondesnjr.github.io.wfalert.entity.Reward;
import marcondesnjr.github.io.wfalert.services.LoadImagesService;

public class AlertAdapter extends RecyclerView.Adapter<AlertAdapter.AlertViewHolder> {

    private Context context;
    private List<Alert> alertList;

    public AlertAdapter(Context context, List<Alert> alertList) {
        this.context = context;
        this.alertList = alertList;
    }

    public void addItem(Alert item) {
        if (alertList == null) {
            alertList = new ArrayList<>();
        }
        alertList.add(item);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AlertViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.alerts_cards_layout, parent, false);
        return new AlertViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlertViewHolder holder, int position) {
        Alert alert = alertList.get(position);
        Mission mission = alert.getMission();
        holder.textViewNode.setText(mission.getLocation());
        holder.textViewMissionType.setText(mission.getType());
        holder.textViewFaction.setText(mission.getFaction());
        holder.textViewMinLvl.setText(alert.getMinLevel() + "");
        holder.textViewMaxLvl.setText(alert.getMaxLevel() + "");

        ViewUtils.startCountdownTime(holder.textViewTime, alert.getExpiry(), "%02d : %02d : %02d");

        if (alert.getRewards().size() >= 1) {
            if (alert.getRewards().size() > 1 && alert.getRewards().get(0).getName().equals("Credits")) {
                startLoadImage(alert.getRewards().get(1).getImgRef(), holder.imageViewReward);
            } else {
                startLoadImage(alert.getRewards().get(0).getImgRef(), holder.imageViewReward);
            }
        } else {
            holder.imageViewReward.setImageDrawable(context.getResources().getDrawable(R.drawable.credit));
        }


        //Tabela recompensas
        TableLayout tbl = holder.rewardsTable;
        tbl.removeAllViews();
        for (Reward r : alert.getRewards()) {
            TableRow tableRow = new TableRow(tbl.getContext());

            TextView name = new TextView(tableRow.getContext());
            name.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT, 1f));
            name.setText(r.getName());
            tableRow.addView(name);

            TextView quant = new TextView(tableRow.getContext());
            quant.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT, 1f));
            quant.setText(r.getQuant() + "");
            quant.setGravity(Gravity.END);

            tableRow.addView(quant);
            tableRow.setWeightSum(1);
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT);
            lp.weight = 1;
            tbl.addView(tableRow, lp);
        }

    }


    private void startLoadImage(String img, ImageView view) {
        Intent intent = new Intent(this.context, LoadImagesService.class);
        intent.putExtra("imgId", img);
        intent.putExtra("bcListener", img);
        context.startService(intent);

        IntentFilter filter = new IntentFilter(img);
        LocalBroadcastManager.getInstance(context).registerReceiver(new ImgLoadListener(view, img), filter);
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
        private TextView textViewMinLvl;
        private TextView textViewMaxLvl;

        private AlertViewHolder(View itemView) {
            super(itemView);
            this.imageViewReward = itemView.findViewById(R.id.alertRewardImage);
            this.textViewNode = itemView.findViewById(R.id.textViewNode);
            this.textViewMissionType = itemView.findViewById(R.id.textViewMissionType);
            this.textViewFaction = itemView.findViewById(R.id.textViewMissionFaction);
            this.textViewTime = itemView.findViewById(R.id.textViewTime);
            this.rewardsTable = itemView.findViewById(R.id.alertRewardsTable);
            this.textViewMinLvl = itemView.findViewById(R.id.textViewMinLvl);
            this.textViewMaxLvl = itemView.findViewById(R.id.textViewMaxLvl);

            itemView.findViewById(R.id.alertCardView).setOnClickListener(new AlertAdapter.OnClickListener());


        }

        public ImageView getImageViewReward() {
            return imageViewReward;
        }
    }

    class OnClickListener implements View.OnClickListener {


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

    class ImgLoadListener extends BroadcastReceiver {

        private ImageView imgView;
        private String srcName;

        public ImgLoadListener(ImageView imgView, String srcName) {
            this.imgView = imgView;
            this.srcName = srcName;
        }

        @Override
        public void onReceive(final Context context, Intent intent) {

            new LoadImagesTask(imgView,context).execute(intent);

        }
    }


    private static class LoadImagesTask extends AsyncTask<Intent, Void, Drawable>{

        private WeakReference<ImageView> imgView;
        private WeakReference<Context> context;

        private LoadImagesTask(ImageView imgView, Context contex) {
            this.imgView = new WeakReference<>(imgView);
            this.context = new WeakReference<>(contex);
        }

        @Override
        protected Drawable doInBackground(Intent... intents) {
            byte[] img = intents[0].getByteArrayExtra("img");
            Bitmap b = BitmapFactory.decodeByteArray(img, 0, img.length);
            Bitmap sBit = Bitmap.createScaledBitmap(b, 120, 120, false);
            b.recycle();
            return  new BitmapDrawable(context.get().getResources(), sBit);
        }

        @Override
        protected void onPostExecute(Drawable drawable) {
            imgView.get().setImageDrawable(drawable);
        }
    }
}
