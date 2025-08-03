package br.ifmg.edu.bsi.progmovel.pokedex1.apimodel;

import com.google.gson.annotations.SerializedName;

public class Species {
    @SerializedName("evolution_chain")
    public ChainUrl evolutionChain;

    public static class ChainUrl{
        public String url;
    }
}