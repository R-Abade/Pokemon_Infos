package br.ifmg.edu.bsi.progmovel.pokedex1.apimodel;

import com.google.gson.annotations.SerializedName;

public class Species {
    @SerializedName("evolution_chain") // gpt disse que é necessário essa tag por conta do nomes esperado lá do pokemón.co
    public ChainUrl evolutionChain; //

    public static class ChainUrl{ // construtor da url ca cadeia de evolução
        public String url;
    }
}