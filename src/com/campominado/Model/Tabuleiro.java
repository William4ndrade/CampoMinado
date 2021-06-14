package com.campominado.Model;

import com.campominado.Exceptions.BombException;

import java.util.ArrayList;

public class Tabuleiro {

    private int linhas;
    private int colunas;
    private int bombas;
    private final ArrayList<Campo> campos = new ArrayList<>();

    public Tabuleiro(int linhas, int colunas, int bombas) {
        this.linhas = linhas;
        this.colunas = colunas;
        this.bombas = bombas;
        criarcampos();
        conectarvizinhos();
        sortearbombas();
        

    }

    private void sortearbombas() {
        int qntMinas = (int) campos.stream().filter(v -> v.isBomb()).count();
        while(qntMinas < this.bombas){
            int random = (int) (Math.random() * campos.size());
            this.campos.get(random).ChangeBomb();
            qntMinas= (int) campos.stream().filter(v -> v.isBomb()).count();
        }




    }


    public boolean objetivoalcançado(){
        return campos.stream().allMatch(v -> v.FinishedGoal());
    }

    public void restart(){
        campos.stream().forEach(v ->v.Restart());
        this.sortearbombas();
    }

    private void conectarvizinhos() {
        for (Campo c1: campos) {
            for(Campo c2: campos){
                c1.addneighbor(c2);
            }
            
        }
        
    }

    public void abrircampo(int l, int c){
        try {
            campos.parallelStream()
                    .filter(v -> l == v.getX())
                    .filter(v -> c == v.getY())
                    .findFirst()
                    .ifPresent(v -> v.Open());
        }catch (BombException E){
            campos.forEach(v -> v.SetOpen(true));
            throw E;
        }

    }

    public void marcar(int l, int c){
        campos.parallelStream()
                .filter(v -> l == v.getX())
                .filter(v -> c == v.getY())
                .findFirst()
                .ifPresent(v -> v.ChangeMarket());

    }


    private void criarcampos() {
        for (int i = 0; i < linhas ; i++) {
            for (int c = 0; c < colunas ; c++) {
                Campo campo = new Campo(i,c);
                this.campos.add(campo);
            }
        }


    }


    @Override
    public String toString() {
        StringBuilder stringbuilder = new StringBuilder();
        int i = 0;

        stringbuilder.append("  ");
        for (int colunas = 0; colunas < this.colunas; colunas++) {
            stringbuilder.append(" ");
            stringbuilder.append(colunas);
            stringbuilder.append(" ");

        }

        stringbuilder.append("\n");

        for (int linhas = 0; linhas < this.linhas ; linhas++) {
            stringbuilder.append(linhas);
            stringbuilder.append(" ");

            for (int colunas = 0; colunas < this.colunas; colunas++) {
                stringbuilder.append(" ");
                stringbuilder.append(campos.get(i));
                stringbuilder.append(" ");
                i++;
            }

            stringbuilder.append("\n");
            
        }
        
        
        return  stringbuilder.toString();
    }
}
