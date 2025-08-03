package br.ifmg.edu.bsi.progmovel.pokedex1;

import static androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.viewmodel.ViewModelInitializer;

import java.io.IOException;
import java.util.ArrayList;

import br.ifmg.edu.bsi.progmovel.pokedex1.apimodel.Pokeapi;
import br.ifmg.edu.bsi.progmovel.pokedex1.apimodel.Pokemon;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VerPokemonViewModel extends ViewModel {
    private PokedexApplication app;
    private MutableLiveData<Integer> loading;
    private MutableLiveData<String> nome;
    private MutableLiveData<String> urlImagem;
    private MutableLiveData<Integer> height;
    private MutableLiveData<Integer> weight;
    private MutableLiveData<ArrayList<String>> evolution = new MutableLiveData<>(new ArrayList<>()); // Inicialização corrigida

    public static ViewModelInitializer<VerPokemonViewModel> initializer = new ViewModelInitializer<>(
            VerPokemonViewModel.class,
            creationExtras -> new VerPokemonViewModel((PokedexApplication) creationExtras.get(APPLICATION_KEY)));

    public VerPokemonViewModel(PokedexApplication app) {
        this.app = app;
        loading = new MutableLiveData<>(View.GONE);
        nome = new MutableLiveData<>();
        urlImagem = new MutableLiveData<>();
        height = new MutableLiveData<>();
        weight = new MutableLiveData<>();
    }

    public void loadPokemon(String nomePokemon) {
        loading.setValue(View.VISIBLE);
        app.getExecutor().execute(() -> {

            try {
                Pokemon p = app.getPokemonRepo().buscar(nomePokemon);
                nome.postValue(p.name);
                height.postValue(p.height);
                weight.postValue(p.weight);
                urlImagem.postValue(p.sprites.other.officialArtwork.front_default);
                ArrayList<String> cadeia = app.getPokemonRepo().searchEvo(nomePokemon);
                evolution.postValue(cadeia);
            } catch (IOException e) {
                // Aumentando a visibilidade da exceção para facilitar o debug
                e.printStackTrace();
                throw new RuntimeException("Erro ao carregar o Pokemon: " + nomePokemon, e);
            } finally {
                loading.postValue(View.GONE);
            }
        });
    }

    public LiveData<Integer> getLoading() {
        return loading;
    }

    public LiveData<String> getNome() {
        return nome;
    }

    public LiveData<String> getUrlImagem() {
        return urlImagem;
    }

    public LiveData<Integer> getHeight() {
        return height;
    }

    public LiveData<Integer> getWeight() {
        return weight;
    }
    public LiveData<ArrayList<String>> getEvolution(){
        return evolution;
    }
}
