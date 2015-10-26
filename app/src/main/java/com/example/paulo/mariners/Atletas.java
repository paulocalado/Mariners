package com.example.paulo.mariners;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Atletas extends ListActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //ListView listaJogadores;
    ArrayList<Jogadores> testeListaJogadores;
    ListaJogadoresAdapter jogadoresAdapter;
    AlertDialog alerta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atletas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });



       //listaJogadores = (ListView)findViewById(R.id.);

        testeListaJogadores = gerarJogadores(); //aqui eu estancio o ArrayList que vai conter minha lista de jogadores
        jogadoresAdapter = new ListaJogadoresAdapter(Atletas.this, testeListaJogadores); //estanciando o adapter
        setListAdapter(jogadoresAdapter);
        /*na linha anterior eu seto o adapter, como o Android vai fazer uma referência automática a minha ListView
        eu não preciso criar um objeto do tipo ListView*/


        /*o método abaixo vai definir o que acontecerá quando um item da lista for clicado é preciso sempre que for
        usar este método, que a classe ao qual ele esteja, seja extendida de uma ListActivity, desse modo ele vai conseguir
        referenciar por meio do id automático a qual listView eu quero trabalhar*/

        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Jogadores jogador = jogadoresAdapter.getItem(position);

                dialogTrigger(jogador.getNome(), jogador.getPosicao(), jogador.getImagem());
                /*Quando clicar num item da lista, ele chama esse método que vai abrir um Dialog mostrando a imagem, o nome
                 e a posição do jogador*/

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void setSupportActionBar(Toolbar toolbar) {
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.atletas, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    //o método abaixo serve para as ações dos itens do NavigationDrawer
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_atletas) {

            Intent intentAtleta = new Intent(Atletas.this, Atletas.class);
            startActivity(intentAtleta);

        } else if (id == R.id.nav_jogos) {
            Intent intentJogos = new Intent(Atletas.this, Jogos.class);
            startActivity(intentJogos);

        } else if (id == R.id.nav_tabela) {

        } else if (id == R.id.nav_galeria) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    


    //esse vai ser o adapter da minha lista
    public class ListaJogadoresAdapter extends ArrayAdapter<Jogadores> {
        private Context context;
        private ArrayList<Jogadores> listaJogadoresAdapter;

        public ListaJogadoresAdapter(Context context, ArrayList<Jogadores> listaJogadoresAdapter) {
            super(context, 0, listaJogadoresAdapter);
            this.context = context;
            this.listaJogadoresAdapter = listaJogadoresAdapter;
        }

        //aqui é onde eu inflo o layout e seto os dados de cada item da lista
        @Override
        public View getView(int position, View view, ViewGroup parent){

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View rowView = inflater.inflate(R.layout.layout_lista_jogadores, parent, false);


            Jogadores jogadores = listaJogadoresAdapter.get(position);

            ImageView imageJogador = (ImageView) rowView.findViewById(R.id.imageJogador);
            imageJogador.setImageResource(jogadores.getImagem());

            TextView textNomeJogador = (TextView) rowView.findViewById(R.id.textNomeJogador);
            textNomeJogador.setText(jogadores.getNome());

            TextView txtPosicao = (TextView)rowView.findViewById(R.id.txtPosicao);
            txtPosicao.setText(jogadores.getPosicao());

            TextView txtNumero = (TextView)rowView.findViewById(R.id.txtNumero);
            txtNumero.setText(jogadores.getNumero());

            return rowView;
        }


    }

    // esse é o metodo para popular a lista
    private ArrayList<Jogadores> gerarJogadores(){
        ArrayList<Jogadores> listaJogadores = new ArrayList<Jogadores>();

        listaJogadores.add(new Jogadores("Andrew Banks", "QB", "2", R.drawable.avatar_drew));
        listaJogadores.add(new Jogadores("Daniel", "DB", "3", R.drawable.avatar_daniel));
        listaJogadores.add(new Jogadores("Pinho", "WR", "5", R.drawable.avatar_pinho));
        listaJogadores.add(new Jogadores("TL Edwards", "DB", "6", R.drawable.avatar_tl));
        listaJogadores.add(new Jogadores("Vinícius", "WR", "8", R.drawable.avatar_jah));
        listaJogadores.add(new Jogadores("David Renan", "LB", "9", R.drawable.avatar_david_renan));
        listaJogadores.add(new Jogadores("Guto", "TE", "11", R.drawable.avatar_guto));
        listaJogadores.add(new Jogadores("Rafael Tavares", "QB", "12", R.drawable.avatar_tavares));

        return listaJogadores;
    }

    private void dialogTrigger(String nome, String posicao, int imagem){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);//seta o builder do dialog

        LayoutInflater li = getLayoutInflater();
        View view = li.inflate(R.layout.layout_dialog_jogadores, null);//Objeto que vai setar a view do Lyout customizado do Dialoh

        ImageView imageJogadorDialog = (ImageView)view.findViewById(R.id.imageJogadorDialog);
        imageJogadorDialog.setImageResource(imagem);

        TextView textNomeJogadorDialog = (TextView)view.findViewById(R.id.txtNomeDialog);
        textNomeJogadorDialog.setText(nome);

        TextView txtPosicaoJogadorDialog = (TextView)view.findViewById(R.id.txtPosicaoDialog);
        txtPosicaoJogadorDialog.setText(posicao);

        builder.setView(view);

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();//quando clicar no Ok, ele apenas fecha o dialog
            }
        });

        alerta = builder.create();

        alerta.show();
    }

}
