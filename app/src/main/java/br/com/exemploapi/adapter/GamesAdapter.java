package br.com.exemploapi.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import br.com.exemploapi.R;
import br.com.exemploapi.adapter.listener.GameListListener;
import br.com.exemploapi.model.Game;

// toda recycler precisa de um adapter pra gerencia-la
public class GamesAdapter extends RecyclerView.Adapter<GamesAdapter.ViewHolder> {

    private List<Game> gameList = new ArrayList<>();
    private GameListListener gameListListener;

    public GamesAdapter(GameListListener gameListListener){
        this.gameListListener = gameListListener;
    }

    public void atualizarGames(List<Game> gameList){
        this.gameList = gameList;
        notifyDataSetChanged();
    }

    public void adicionarGames(List<Game> gameList){
        this.gameList.addAll(gameList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public GamesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_game_cell, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GamesAdapter.ViewHolder holder, int position) {
        Game game = gameList.get(position);
        holder.bind(game);

        holder.itemView.setOnClickListener(view -> gameListListener.onGameClick(game));
    }

    @Override
    public int getItemCount() {
        return gameList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tituloTextView;
        private TextView descricaoTextView;
        private ImageView imagemImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tituloTextView = itemView.findViewById(R.id.titulo_text_view_id);
            descricaoTextView = itemView.findViewById(R.id.descricao_text_view_id);
            imagemImageView = itemView.findViewById(R.id.imagem_image_view_id);

        }

        public void bind(Game game) {
            tituloTextView.setText(game.getTitulo());
            descricaoTextView.setText(game.getDescricao());
            Picasso.get().load(game.getImagem().getMedImagem()).into(imagemImageView);
        }
    }
}
