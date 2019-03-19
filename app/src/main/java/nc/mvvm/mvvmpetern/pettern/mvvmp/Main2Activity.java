package nc.mvvm.mvvmpetern.pettern.mvvmp;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import nc.mvvm.mvvmpetern.R;
import nc.mvvm.mvvmpetern.adapter.HeroesAdapter;
import nc.mvvm.mvvmpetern.model.HeroDTO;
import nc.mvvm.mvvmpetern.pettern.mvcp.MainActivity;
import nc.mvvm.mvvmpetern.viewmodels.HeroesViewModel;

public class Main2Activity extends AppCompatActivity {
    HeroesAdapter adapter;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    List<HeroDTO> heroList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        HeroesViewModel model = ViewModelProviders.of(this).get(HeroesViewModel.class);

        model.getHeroes().observe(this, new Observer<List<HeroDTO>>() {
            @Override
            public void onChanged(@Nullable List<HeroDTO> heroList) {
                adapter = new HeroesAdapter(Main2Activity.this, heroList);
                recyclerView.setAdapter(adapter);
            }
        });

    }
}
