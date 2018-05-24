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
import java.util.List;
import java.util.Random;

import marcondesnjr.github.io.wfalert.entity.Fissure;

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
        String[] nodes = {"Epimetheus (Saturn)","Perdita (Uranus)","Nix (Pluto)","Elion (Mercury)", "Ultor (Mars)"};
        String[] types = {"Mobile Defense", "Extermination", "Survival"};
        String[] tier = {"Lith","Meso", "Neo", "Axi"};
        String[] faction = {"Corpus", "Grineer", "Infested"};
        Random r = new Random();

        List<Fissure> list = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            Fissure fis = new Fissure(i, nodes[r.nextInt(5)],tier[r.nextInt(4)],types[r.nextInt(3)],
                    faction[r.nextInt(3)]);
            list.add(fis);
        }

        return list;

    }
}
