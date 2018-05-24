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

import marcondesnjr.github.io.wfalert.entity.Invasion;

public class InvasionLayout extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.invasion_layout, container,false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        InvasionLayoutAdapter adapter = new InvasionLayoutAdapter(this.getContext(), tmpMakeInv());
        recyclerView.setAdapter(adapter);
        return recyclerView;
    }

    private List<Invasion> tmpMakeInv() {
        String[] nodes = {"Epimetheus (Saturn)","Perdita (Uranus)","Nix (Pluto)","Elion (Mercury)", "Ultor (Mars)"};
        String[] types = {"Infested vs Grineer", "Corpus vs Grineer", "Infested vs Corpus"};
        List<Invasion> list = new ArrayList<>();
        Random r = new Random();
        for (int i = 0; i < 5; i++) {
            list.add(new Invasion(i,nodes[r.nextInt(5)],types[r.nextInt(3)],r.nextInt(101)));
        }
        return list;
    }
}
