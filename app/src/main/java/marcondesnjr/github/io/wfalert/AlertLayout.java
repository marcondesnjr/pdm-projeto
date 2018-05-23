package marcondesnjr.github.io.wfalert;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import marcondesnjr.github.io.wfalert.entity.Alert;

public class AlertLayout extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.alerts_layout, container,false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        AlertAdapter alertAdapter = new AlertAdapter(this.getContext(),makeAlerts());
        recyclerView.setAdapter(alertAdapter);
        return  recyclerView;
    }

    private List<Alert> makeAlerts(){
        ArrayList<Alert> alerts = new ArrayList<>();
        alerts.add(new Alert(1,"Gaia(Earth)"));
        alerts.add(new Alert(2,"Aphrodite(Venus)"));
        return alerts;
    }
}
