package br.com.exemploapi.model;

import com.google.gson.annotations.SerializedName;

public class Game {

    @SerializedName("name")
    private String titulo;

    @SerializedName("deck")
    private String descricao;

    @SerializedName("image")
    private GameImage imagem;


    public GameImage getImagem() {
        return imagem;
    }

    public void setImagem(GameImage imagem) {
        this.imagem = imagem;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


}
