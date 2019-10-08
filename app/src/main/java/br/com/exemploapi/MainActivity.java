
package br.com.exemploapi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;

import br.com.exemploapi.adapter.GamesAdapter;
import br.com.exemploapi.adapter.listener.GameListListener;
import br.com.exemploapi.model.Game;
import br.com.exemploapi.viewmodel.GameViewModel;

public class MainActivity extends AppCompatActivity implements GameListListener {

    public static final int LIMIT = 20;
    // cria variavel para offset
    private int offset;
    private GamesAdapter gamesAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView gameRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //atricuicao
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
        gameRecyclerView = findViewById(R.id.games_recycler_view_id);
        gamesAdapter = new GamesAdapter(this);

        //set propriedades
        gameRecyclerView.setAdapter(gamesAdapter);
        gameRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //cria view model para
        GameViewModel gameViewModel = ViewModelProviders.of(this).get(GameViewModel.class);

        gameViewModel.atualizarGames(LIMIT, offset);

        gameViewModel.getGameLiveData()
                .observe(this, gameList -> {
                    gamesAdapter.adicionarGames(gameList);
                    swipeRefreshLayout.setRefreshing(false);
                });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                gameViewModel.atualizarGames(LIMIT, offset);
            }
        });

        gameRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if(gameRecyclerView.canScrollVertically(1)){

                    // mesma coisa q offset = LIMIT + offset
                    offset += LIMIT;

                    gameViewModel.atualizarGames(LIMIT, offset);
                }
            }


        });

    }
    @Override
    public void onGameClick(Game game){
        Intent intent = new Intent(Intent.ACTION_SEND);

        intent.setType("text/plain");

        intent.putExtra(Intent.EXTRA_SUBJECT, game.getTitulo());

        intent.putExtra(Intent.EXTRA_TEXT, game.getTitulo() + ": " + game.getDescricao());

        startActivity(Intent.createChooser(intent, "Compartilhar"));

    }
}
