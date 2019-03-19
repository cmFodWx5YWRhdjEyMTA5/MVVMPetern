package nc.mvvm.mvvmpetern.viewmodels;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import nc.mvvm.mvvmpetern.model.HeroDTO;
import nc.mvvm.mvvmpetern.retrofitsetup.RetrifitEndPoint;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import android.arch.lifecycle.LiveData;

import static nc.mvvm.mvvmpetern.retrofitsetup.RetrifitEndPoint.BASE_URL;

public class HeroesViewModel extends ViewModel {
    //this is the data that we will fetch asynchronously
    private MutableLiveData<List<HeroDTO>> heroList;

    //we will call this method to get the data
    //public LiveDasta<List<HeroDTO>> getHeroes() {
    public MutableLiveData<List<HeroDTO>> getHeroes() {
        //if the list is null
        if (heroList == null) {
            heroList = new MutableLiveData<List<HeroDTO>>();
            //we will load it asynchronously from server in this method
            loadHeroes();
        }

        //finally we will return the list
        return heroList;
    }


    //This method is using Retrofit to get the JSON data from URL
    private void loadHeroes() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrifitEndPoint.Srvices api = retrofit.create(RetrifitEndPoint.Srvices.class);
        Call<List<HeroDTO>> call = api.getHeroes();


        call.enqueue(new Callback<List<HeroDTO>>() {
            @Override
            public void onResponse(Call<List<HeroDTO>> call, Response<List<HeroDTO>> response) {

                //finally we are setting the list to our MutableLiveData
                heroList.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<HeroDTO>> call, Throwable t) {

            }
        });
    }
}