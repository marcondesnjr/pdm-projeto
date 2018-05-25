package marcondesnjr.github.io.wfalert;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.transition.TransitionManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import marcondesnjr.github.io.wfalert.entity.Alert;
import marcondesnjr.github.io.wfalert.entity.Mission;
import marcondesnjr.github.io.wfalert.entity.Reward;

public class AlertLayout extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.alerts_layout, container,false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        AlertAdapter alertAdapter = new AlertAdapter(this.getContext(),makeAlerts());
        recyclerView.setAdapter(alertAdapter);
        TransitionManager.beginDelayedTransition(container);
        return  recyclerView;
    }

    private List<Alert> makeAlerts(){
        ArrayList<Alert> alerts = new ArrayList<>();
        Random r = new Random();
        for (int i = 0; i < 9; i++) {
            Mission m = Mission.random();
            Calendar end = Calendar.getInstance();
            end.add(Calendar.MINUTE, r.nextInt(70));
            List<Reward> rewards = new ArrayList<>();
            rewards.add(Reward.values()[r.nextInt(3)]);
            rewards.add(Reward.values()[r.nextInt(3)]);
            Alert alert = new Alert(i, m, rewards, r.nextInt(100), end);
            alerts.add(alert);
        }

        return alerts;
    }

}
