package com.example.paulo.mariners;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Paulo on 19/10/2015.
 */
public class Jogadores {
    private String nome, posicao, numero, altura;
    private int idade, imagem;


    public Jogadores(String nome, String posicao,  String numero, int imagem) {
        this.nome = nome;
        this.posicao = posicao;
        this.numero = numero;
        this.imagem = imagem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPosicao() {
        return posicao;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }

    public class ListaJogadoresAdapter extends ArrayAdapter<Jogadores> {
        private Context context;
        private ArrayList<Jogadores> listaJogadoresAdapter;

        public ListaJogadoresAdapter(Context context, ArrayList<Jogadores> listaJogadoresAdapter) {
            super(context, 0, listaJogadoresAdapter);
            this.context = context;
            this.listaJogadoresAdapter = listaJogadoresAdapter;
        }

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


}