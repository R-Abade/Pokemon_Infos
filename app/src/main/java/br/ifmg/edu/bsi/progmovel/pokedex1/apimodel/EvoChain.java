package br.ifmg.edu.bsi.progmovel.pokedex1.apimodel;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class EvoChain {
    public ChainLink chain;

    public static class Species{
        public String name;
    }
    public static class ChainLink { // chainlink é o  que procuro no pokemón.co
        public Species species;
        @SerializedName("evolves_to") // gpt disse que é necessário essa tag por conta do nomes esperado lá do pokemón.co
        public ArrayList<ChainLink> evolvesTo;
    }
}
