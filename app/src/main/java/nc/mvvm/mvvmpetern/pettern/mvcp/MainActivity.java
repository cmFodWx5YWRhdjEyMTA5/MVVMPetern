package nc.mvvm.mvvmpetern.pettern.mvcp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import nc.mvvm.mvvmpetern.R;
import nc.mvvm.mvvmpetern.model.HeroDTO;
import nc.mvvm.mvvmpetern.pettern.mvvmp.Main2Activity;
import nc.mvvm.mvvmpetern.retrofitsetup.RetrifitEndPoint;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.mvvm_btn)
    Button mvvm_btn;
    @BindView(R.id.listViewHeroes)
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        getHeroes();

        mvvm_btn.setOnClickListener(click ->{
            Intent intent = new Intent(this , Main2Activity.class);
            startActivity(intent);
        });


    }

    private void getHeroes() {
        Call<List<HeroDTO>> call = RetrifitEndPoint.getSrvices().getHerolist1();
        call.enqueue(new Callback<List<HeroDTO>>() {
            @Override
            public void onResponse(Call<List<HeroDTO>> call, Response<List<HeroDTO>> response) {
                List<HeroDTO> heroList = response.body();
                //Creating an String array for the ListView
                String[] heroes = new String[heroList.size()];
                //looping through all the heroes and inserting the names inside the string array
                for (int i = 0; i < heroList.size(); i++) {
                    heroes[i] = heroList.get(i).getName();
                }
                //displaying the string array into listview
                listView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), R.layout.listrow ,  R.id.text1, heroes));

            }

            @Override
            public void onFailure(Call<List<HeroDTO>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
