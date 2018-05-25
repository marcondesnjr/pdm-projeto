package marcondesnjr.github.io.wfalert;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

public class FissureLayout extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.fissures_layout, container,false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        FissureLayoutAdapter adapter = new FissureLayoutAdapter(this.getContext(),tmpMakeList());
        recyclerView.setAdapter(adapter);
        return recyclerView;
    }

    private List<Fissure> tmpMakeList() {
        Random r = new Random();

        List<Fissure> list = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.MINUTE, r.nextInt(70));
            Fissure fis = new Fissure(i, Tier.values()[r.nextInt(4)], Mission.random(), cal);
            list.add(fis);
        }

        return list;

    }
}
