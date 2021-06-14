package com.campominado.View;

import com.campominado.Exceptions.BombException;
import com.campominado.Exceptions.LeaveException;
import com.campominado.Model.Tabuleiro;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class Console {

    private Tabuleiro TB;
    private Scanner scan = new Scanner(System.in);

    public Console(Tabuleiro tb){
        this.TB = tb;
        Rungame();
    }


    private String digvalue(String Text){
        System.out.println(Text);
        String dig = scan.nextLine();
        if(dig.trim().equalsIgnoreCase("sair")){
            throw new LeaveException();
        }

        return dig;

    }


    private void game(){
        try {
            while(!TB.objetivoalcançado()){
                System.out.println(TB);
                String value = this.digvalue("Digite (x,y): ");
                Iterator<Integer> valueArray =   Arrays.asList(value.split(",")).stream().map(e -> {
                    return Integer.parseInt(e.toUpperCase().trim());
                }).iterator();

               value = this.digvalue("1- Abrir - 2- Marcar/Desmarcar");


               if(value.trim().equalsIgnoreCase("1")){
                   TB.abrircampo(valueArray.next(),valueArray.next());
               }else if (value.trim().equalsIgnoreCase("2")){
                   TB.marcar(valueArray.next(), valueArray.next());
               }




            }






            System.out.println("você ganhou");
        }catch (BombException e){
            System.out.println(TB);
            System.out.println(e.getMessage());
        }catch (NumberFormatException e){
            System.out.println("Você digitou um valor invalido");
        }



    }


    private void Rungame() {
           try {
               while (true){
                   game();
                   System.out.println("Outra partida (S/N): ");
                   String Result = scan.nextLine();

                   if(Result.trim().equalsIgnoreCase("n")){
                       break;
                   }else{
                       TB.restart();
                   }






               }


           }catch (LeaveException e){
               System.out.println(e.getMessage());

           }finally {
               scan.close();
           }








    }




}
