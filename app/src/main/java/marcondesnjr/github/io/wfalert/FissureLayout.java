package marcondesnjr.github.io.wfalert;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import marcondesnjr.github.io.wfalert.entity.Fissure;
import marcondesnjr.github.io.wfalert.entity.Mission;
import marcondesnjr.github.io.wfalert.entity.Tier;
import marcondesnjr.github.io.wfalert.services.LoadFissuresService;

public class FissureLayout extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.fissures_layout, container,false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        FissureLayoutAdapter adapter = new FissureLayoutAdapter(this.getContext(), new ArrayList<Fissure>());
        recyclerView.setAdapter(adapter);
        startLoadFissures(adapter);
        return recyclerView;
    }

    public void startLoadFissures(final FissureLayoutAdapter adapter){
        Intent intent = new Intent(this.getActivity(), LoadFissuresService.class);
        getContext().startService(intent);
        IntentFilter filter = new IntentFilter(LoadFissuresService.LOADED_LISTENER_BC);
        BroadcastReceiver br = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                ArrayList<Parcelable> fissures = intent.getParcelableArrayListExtra("fissures");
                if(fissures != null) {
                    for (Parcelable parc : fissures) {
                        Fissure fis = (Fissure) parc;
                        adapter.addItem(fis);
                    }
                }
            }
        };
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(br,filter);
    }


}
