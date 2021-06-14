package com.campominadoTeste;

import com.campominado.Exceptions.BombException;
import com.campominado.Model.Campo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CampoTeste {

    private final Campo c1 = new Campo(2,2);

    @Test
    public void addNeighborTeste(){
        Campo neighbor = new Campo(3,2);
        boolean result =  c1.addneighbor(neighbor);
        assertTrue(result);
    }

    @Test
    public void ChangemarketTest(){
        c1.ChangeMarket();
        assertTrue(c1.Ismarked());
    }

    @Test
    public void Changemarket2xTest(){
        c1.ChangeMarket();
        c1.ChangeMarket();
        assertFalse(c1.Ismarked());
    }


   @Test
    public void  OpenPerfectCampo(){
        // Um campo perfeito seria um campo sem marcação e fechado;
        assertTrue(c1.Open());
   }

    @Test
    public void  OpenCampoMarked(){
        // Testando tentativa de abrir campo marcado
        c1.ChangeMarket();
        assertFalse(c1.Open());
    }


    @Test
    public void OpenCampoAberto(){
        c1.Open();
        assertFalse(c1.Open());

    }


    @Test
    public void OpenBombCampo(){
        c1.ChangeBomb();
        assertThrows(BombException.class, c1::Open);
    }


    @Test
    public void OpenBombCampoMarked(){
        c1.ChangeBomb();
        c1.ChangeMarket();
        assertFalse(c1.Open());


    }

    @Test
    public void OpenWithNeighborHood(){
      Campo c0 = new Campo(2,3);
      Campo c1 = new Campo(1,1);
      Campo c2 = new Campo(1,2);
     assertTrue(this.c1.addneighbor(c0));
     assertTrue(this.c1.addneighbor(c1));
     assertTrue(this.c1.addneighbor(c2));
     assertTrue(this.c1.Open());
     assertFalse(c1.Open());
     assertFalse(c2.Open());
     assertFalse(c0.Open());






    }




}
