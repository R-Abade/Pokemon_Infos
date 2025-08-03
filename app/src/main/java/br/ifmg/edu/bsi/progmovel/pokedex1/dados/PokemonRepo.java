package br.ifmg.edu.bsi.progmovel.pokedex1.dados;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import br.ifmg.edu.bsi.progmovel.pokedex1.apimodel.EvoChain;
import br.ifmg.edu.bsi.progmovel.pokedex1.apimodel.Pokeapi;
import br.ifmg.edu.bsi.progmovel.pokedex1.apimodel.Pokemon;
import br.ifmg.edu.bsi.progmovel.pokedex1.apimodel.Species;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokemonRepo {

    private Pokeapi api;

    public PokemonRepo(Pokeapi api) {
        this.api = api;
    }

    public ArrayList<String> searchEvo(String nome) throws IOException{
        Species species = api.getPokemonSpecies(nome).execute().body(); // vai puxar o id da url e chamar o executador
        if(species == null || species.evolutionChain == null || species.evolutionChain.url == null){
            return new ArrayList<>();
        }
        String url = species.evolutionChain.url;
        String[] parts = url.split("/");
        int id = Integer.parseInt(parts[parts.length - 3]);

        EvoChain evolutions = api.getEvolutionChain(id).execute().body();
        if(evolutions == null || evolutions.chain == null){
            return new ArrayList<>();
        }
        ArrayList<String> pokemons = new ArrayList<>(); // retorna um array dos pokemons da cadeia de evolução
        addEvo(evolutions.chain, pokemons);
        return pokemons;
    }

    public Pokemon buscar(String nome) throws IOException {
        Pokemon p = api.fetch(nome).execute().body();
        return p;
    }

    public void addEvo(EvoChain.ChainLink link, ArrayList<String> pokemon){
        pokemon.add(link.species.name);
        if (link.evolvesTo != null) {
            for (EvoChain.ChainLink i: link.evolvesTo) {
                addEvo(i, pokemon);
            }
        }
    }
}
