package br.ifmg.edu.bsi.progmovel.pokedex1.apimodel;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class EvoChain {
    public ChainLink chain;

    public static class Species{
        public String name;
    }
    public static class ChainLink {
        public Species species;

        // A PokeAPI usa "evolves_to" para a lista de evoluções.
        // A anotação @SerializedName é necessária para que o GSON
        // faça o mapeamento corretamente.
        @SerializedName("evolves_to")
        public ArrayList<ChainLink> evolvesTo;
    }
}
