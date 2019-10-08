package br.com.exemploapi.model;

import com.google.gson.annotations.SerializedName;

public class GameImage {

    @SerializedName("original_url")
    private String medImagem;

    public String getMedImagem() {
        return medImagem;
    }

    public void setMedImagem(String medImagem) {
        this.medImagem = medImagem;
    }
}
