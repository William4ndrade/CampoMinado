package com.campominado;

import com.campominado.Model.Tabuleiro;
import com.campominado.View.Console;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Tabuleiro tb = new Tabuleiro(5,5,10);
        Console c = new Console(tb);

    }



}
