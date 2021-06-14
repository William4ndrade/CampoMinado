package com.campominado.Model;

import com.campominado.Exceptions.BombException;

import java.util.ArrayList;
import java.util.List;

public class Campo {

    private boolean ismarked = false;
    private boolean isOpen = false;
    private boolean isBomb = false;
    private final  int x, y;
    private List<Campo> neighbor = new ArrayList<>();


    public Campo(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void ChangeBomb(){
        this.isBomb = !isBomb;
    }


    public boolean Ismarked(){
        return this.ismarked;
    }


    public void ChangeMarket(){
        if(!isOpen){
            this.ismarked = !ismarked;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isBomb(){
        return this.isBomb;
    }



    public boolean FinishedGoal(){
        boolean find = this.isOpen && !this.isBomb;
        boolean protect = this.ismarked && this.isBomb;
        return find || protect;


    }


    void SetOpen(boolean Open){
        this.isOpen = Open;
    }


    public boolean Open() {
        if(!ismarked && !isOpen){
            this.isOpen = true;
            if(isBomb){
                throw new BombException();
            }
            if(this.safeNeighborhood()) {
                neighbor.forEach(Campo::Open);
            }
            return true;

        }
        return  false;
    }

    private boolean safeNeighborhood(){
        return neighbor.stream().noneMatch(v -> v.isBomb);
    }


    public boolean addneighbor(Campo Suitor ){
        boolean diffX = this.x != Suitor.x;
        boolean diffY = this.y != Suitor.y;
        boolean Diagonal = diffX && diffY;
        double delta = Math.abs(this.x - Suitor.x) + Math.abs(this.y - Suitor.y);
        if(delta == 1 && !Diagonal){
            this.neighbor.add(Suitor);
            return true;
        }else if(delta == 2 && Diagonal){
            this.neighbor.add(Suitor);
            return true;

        }else{
            return  false;
        }
    }

    public long neighborBombs(){
        return neighbor.stream().filter(v -> v.isBomb).count();
    }

    public void Restart(){
        this.isBomb = false;
        this.isOpen = false;
        this.ismarked = false;
    }


    @Override
    public String toString() {
        if(ismarked){
            return "x";
        }else if(isOpen && isBomb){
            return "*";
        }else if(isOpen && neighborBombs() > 0){
            return Long.toString(neighborBombs());
        }else if(isOpen){
            return  " ";
        }else{
            return "?";
        }



    }
}
