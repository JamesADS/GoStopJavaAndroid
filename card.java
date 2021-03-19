package com.example.gostop;

import java.util.Vector;

public class card  {
    private int value;
    private String suit;

    card(int i, int j)
    {
        value = j;
        switch(i)
        {
            case 0:
            case 68:
                suit = "Diamonds";
                break;
            case 1:
            case 83:
                suit = "Spades";
                break;
            case 2:
            case 72:
                suit = "Hearts";
                break;
            case 3:
            case 67:
                suit = "Clubs";
                break;

            default:
                suit = "Diamonds";
        }
    }

    /**
     Returns the value of the card as an int.
     @int returns the value of the card as an int.
     */

    public int getValue(){
        int value = this.value;
        return value;
    }

    /**
     Sets the value of the card to provided parameter.
     @int uvalue the value to set the card's value to.

     */

    public void setValue(int uvalue){
        this.value = uvalue;
    }

    /**
     Returns the suit of the card as a String.
     @String returns the suit of the card as a String.
     */

    public String getSuit(){ String suit = this.suit;
        return suit;
    }

    /**
     Sets the Suit of the card to provided parameter.
     @String usuit the value to set the card's suit to.

     */


    public void setValue(String usuit){
        this.suit = usuit;
    }

}
