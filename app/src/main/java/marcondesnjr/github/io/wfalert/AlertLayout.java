package marcondesnjr.github.io.wfalert;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.transition.TransitionManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextThemeWrapper;
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
import marcondesnjr.github.io.wfalert.services.LoadAlertsService;
import marcondesnjr.github.io.wfalert.services.LoadAlertsService.LoadAlertsBinder;
import marcondesnjr.github.io.wfalert.services.LoadedListener;

public class AlertLayout extends Fragment {

    private LoadAlertsService loadAlertService;
    private boolean isBound;
    private RecyclerView temp;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Intent i = new Intent(getActivity(), LoadAlertsService.class);
        boolean status = getActivity().bindService(i, serviceConnection, Context.BIND_AUTO_CREATE);
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.alerts_layout, container,false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        this.temp = recyclerView;
        AlertAdapter adapter = new AlertAdapter(getActivity(),new ArrayList<Alert>());
        recyclerView.setAdapter(adapter);
        return  recyclerView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getActivity().unbindService(serviceConnection);
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            LoadAlertsBinder binder = (LoadAlertsBinder) service;
            loadAlertService = binder.getService();
            isBound = true;
            loadAlertService.loadAlerts(new LocalLoadedListener());
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };

    private class LocalLoadedListener implements LoadedListener<List<Alert>>{

        @Override
        public void onFinish(List<Alert> response) {
            AlertAdapter adp = (AlertAdapter) temp.getAdapter();
            for (Alert al: response) {
                adp.addItem(al);
            }

        }
    }


}
