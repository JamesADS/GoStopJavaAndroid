/**
 ************************************************************
 * Name:  James Giordano                                    *
 * Project:  4 Java Android GoStop                          *
 * Class:  OPL, CRN:  21321                                 *
 * Date:  5/1/2020                                          *
 ************************************************************
 */
package com.example.gostop;


import android.annotation.SuppressLint;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Vector;


public class gameActivity extends AppCompatActivity
    implements ComponentCallbacks2 {

    TextView chatLog;
    client cc = new client();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newgame);
        String gameState = getIntent().getStringExtra("gamestate");
        assert gameState != null;
        String first = new String();

        if (gameState.equals("new"))
        {
            cc.newRound(1);
        }
        else
        {
            String string = "";
            StringBuilder stringBuilder = new StringBuilder();
            //String inp = "";

            //@SuppressLint("ResourceType")

            BufferedReader reader = null;
            InputStream is;
            FileInputStream f;
            if(gameState.equals("local"))
            {
                try
                {
                    f = this.getBaseContext().openFileInput(gameState);
                    InputStreamReader iR = new InputStreamReader(f);
                    reader = new BufferedReader(iR);
                }
                catch (FileNotFoundException e)
                {
                    e.printStackTrace();
                }

            }
            else
            {
                is = getResources().openRawResource(getResources().getIdentifier(gameState, "raw", getPackageName()));

                reader = new BufferedReader(new InputStreamReader(is));
            }


            String[] containers;
            Boolean who = false; //false for cpu, true for human
            while (true) {
                try
                {
                    if ((string = reader.readLine()) == null) {
                        break;
                    }
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
                if (string.contains("Computer")) {
                    who = false;
                } else if (string.contains("Human")) {
                    who = true;
                }
                if (string.contains("Round")) {
                    containers = string.split(": ", 2);
                    cc.setRoundNum( Integer.parseInt(String.valueOf(containers[1])));
                }
                if (string.contains("Score"))
                {
                    containers = string.split(": ", 2);
                    if (who)
                    {
                        cc.setP1Score(Integer.parseInt(String.valueOf(containers[1])));
                    }
                    else
                    {
                        cc.setCPUScore(Integer.parseInt(String.valueOf(containers[1])));
                    }
                }
                if (string.contains("Hand")) {
                    containers = string.split(": ", 2);
                    if (!who)
                    {
                        String p1h = containers[1];
                        string = p1h;
                        String[] values = string.split(" ", 0);
                        Vector<card> v = new Vector<card>(10);
                        for(String s : values)
                        {
                            int j = 0;
                            switch(Character.getNumericValue(s.charAt(0)))
                            {
                                case 1:
                                case 2:
                                case 3:
                                case 4:
                                case 5:
                                case 6:
                                case 7:
                                case 8:
                                case 9:
                                    j = Character.getNumericValue(s.charAt(0));
                                    break;
                                case 33:
                                    j = 10;
                                    break;
                                case 19:
                                    j = 11;
                                    break;
                                case 26:
                                    j = 12;
                                    break;
                                case 20:
                                    j = 13;
                                    break;

                            }
                            card c = new card( s.charAt(1), j);
                            v.addElement(c);
                            cc.setCpuHand(v);

                        }

                    }
                    else
                    {
                        String cpuh = containers[1];
                        string = cpuh;
                        String[] values = string.split(" ", 0);
                        Vector<card> v = new Vector<card>(10);
                        for(String s : values)
                        {
                            int j = 0;
                            switch(Character.getNumericValue(s.charAt(0)))
                            {
                                case 1:
                                case 2:
                                case 3:
                                case 4:
                                case 5:
                                case 6:
                                case 7:
                                case 8:
                                case 9:
                                    j = Character.getNumericValue(s.charAt(0));
                                    break;
                                case 33:
                                    j = 10;
                                    break;
                                case 19:
                                    j = 11;
                                    break;
                                case 26:
                                    j = 12;
                                    break;
                                case 20:
                                    j = 13;
                                    break;

                            }
                            card c = new card( s.charAt(1), j );
                            v.addElement(c);
                            cc.setP1Hand(v);

                        }

                    }
                }
                if (string.contains("Capture")) {

                    containers = string.split("Pile: ", 2);
                    if (!who)
                    {
                        if (containers[1].length() != 0)
                        {
                            String cpuc = containers[1];
                            Boolean checker = true;
                            string = cpuc;
                            String[] values = string.split(" ", 0);
                            Vector<Vector<card>> v = new Vector<Vector<card>>(12);
                            Vector<card> vv = new Vector<card>(4);
                            for(String s : values)
                            {
                                int j = 0;
                                switch(Character.getNumericValue(s.charAt(0)))
                                {
                                    case 1:
                                    case 2:
                                    case 3:
                                    case 4:
                                    case 5:
                                    case 6:
                                    case 7:
                                    case 8:
                                    case 9:
                                        j = Character.getNumericValue(s.charAt(0));
                                        break;
                                    case 33:
                                        j = 10;
                                        break;
                                    case 19:
                                        j = 11;
                                        break;
                                    case 26:
                                        j = 12;
                                        break;
                                    case 20:
                                        j = 13;
                                        break;

                                }
                                card c = new card(s.charAt(1), j);

                                for (int k = 0; k < vv.size(); k++)
                                {
                                    if (c.getValue() != vv.elementAt(k).getValue())
                                    {
                                        checker = false;
                                    }
                                }
                                if (checker)
                                {
                                    vv.add(c);
                                    if (vv.size() == 4)
                                    {
                                        checker = false;
                                    }
                                }
                                else
                                {
                                    v.add(vv);

                                    vv = new Vector<card> (4);
                                    vv.add(c);

                                    checker = true;
                                }






                            }
                            v.add(vv);
                            cc.setcpucap2(v);

                        }

                    }
                    else {
                        if (containers[1].length() != 0)
                        {
                            String p1c = containers[1];
                            string = p1c;
                            Boolean checker = true;
                            String[] values = string.split(" ", 0);
                            Vector<Vector<card>> v = new Vector<Vector<card>>(12);
                            Vector<card> vv = new Vector<card>(4);
                            for(String s : values)
                            {
                                int j = 0;
                                switch(Character.getNumericValue(s.charAt(0)))
                                {
                                    case 1:
                                    case 2:
                                    case 3:
                                    case 4:
                                    case 5:
                                    case 6:
                                    case 7:
                                    case 8:
                                    case 9:
                                        j = Character.getNumericValue(s.charAt(0));
                                        break;
                                    case 33:
                                        j = 10;
                                        break;
                                    case 19:
                                        j = 11;
                                        break;
                                    case 26:
                                        j = 12;
                                        break;
                                    case 20:
                                        j = 13;
                                        break;

                                }
                                card c = new card(s.charAt(1), j);

                                for (int k = 0; k < vv.size(); k++)
                                {
                                    if (c.getValue() != vv.elementAt(k).getValue())
                                    {
                                        checker = false;
                                    }
                                }
                                if (checker)
                                {
                                    vv.add(c);
                                    if (vv.size() == 4)
                                    {
                                        checker = false;
                                    }
                                }
                                else
                                {
                                    v.add(vv);

                                    vv = new Vector<card> (4);
                                    vv.add(c);

                                    checker = true;
                                }






                            }
                            v.add(vv);
                            cc.setp1cap2(v);

                        }

                    }
                }
                if (string.contains("Layout"))
                {
                    containers = string.split(": ", 2);
                    String lay = containers[1];
                    string = lay;
                    String[] values = string.split(" ", 0);
                    Vector<Vector<card>> v = new Vector<Vector<card>>(16);
                    Vector<card> vv = new Vector<card>(4);
                    for(String s : values)
                    {
                        int j = 0;
                        switch(Character.getNumericValue(s.charAt(0)))
                        {
                            case 1:
                            case 2:
                            case 3:
                            case 4:
                            case 5:
                            case 6:
                            case 7:
                            case 8:
                            case 9:
                                j = Character.getNumericValue(s.charAt(0));
                                break;
                            case 33:
                                j = 10;
                                break;
                            case 19:
                                j = 11;
                                break;
                            case 26:
                                j = 12;
                                break;
                            case 20:
                                j = 13;
                                break;

                        }
                        card c = new card(s.charAt(1), j);


                        vv.add(c);
                        v.add(vv);






                    }
                    cc.setLayout2(v);


                }
                if (string.contains("Stock Pile") || string.contains("StockPile")) {
                    containers = string.split(": ", 2);
                    String stock = containers[1];
                    string = stock;
                    String[] values = string.split(" ", 0);

                    Vector<card> ss = new Vector<card>(80);
                    for(String s : values){
                        int j = 0;
                        switch(Character.getNumericValue(s.charAt(0)))
                        {
                            case 1:
                            case 2:
                            case 3:
                            case 4:
                            case 5:
                            case 6:
                            case 7:
                            case 8:
                            case 9:
                                j = Character.getNumericValue(s.charAt(0));
                                break;
                            case 33:
                                j = 10;
                                break;
                            case 19:
                                j = 11;
                                break;
                            case 26:
                                j = 12;
                                break;
                            case 20:
                                j = 13;
                                break;

                        }
                        card c = new card(s.charAt(1), j);
                        ss.add(c);
                        cc.setStock(ss);

                    }
                }
                if (string.contains("Next Player"))
                {
                    containers = string.split(": ", 2);
                    String uturn = containers[1];

                    if(uturn.equals("Human"))
                    {
                        cc.setgameState(1);
                        cc.setTurn(uturn);
                    }
                    else
                    {
                        cc.setgameState(0);
                        cc.setTurn("CPU");
                    }
                }

                stringBuilder.append(string).append("\n");

            }


        }


        View view = findViewById(R.id.gameView);
        drawscreen(view);
        chatLog = (TextView) findViewById(R.id.chatLog);
        chatLog.append("The " + cc.getTurn() + " will move first." );







        }


    /**
     Gets the Drawable File ID based on a provided String.
     @Context context the current context of the calling function.
     @String imageName The name of the image to return an ID of.
     @int Returns an int.
     */

    public static int getImageId(Context context, String imageName) {
        return context.getResources().getIdentifier("drawable/" + imageName, null, context.getPackageName());
    }

    /**
     Draws the screen by calling all the draw functions, scrolls the textbox to the bottom.
     @View view The view calling the function.

     */

    public void drawscreen(View view)
    {
        drawcpuh(view);
        drawlayout(view);
        drawp1h(view);
        drawstock(view);
        drawnums(view);
        drawp1cap(view);
        drawcpucap(view);
        ScrollView s = new ScrollView(this);
        s = findViewById(R.id.scrollView2);
        final ScrollView finalS = s;
        s.post(new Runnable() {
            @Override
            public void run() {

                finalS.scrollTo(0, finalS.getBottom());
            }
        });

    }

    /**
     Clears the screen by clearing the views containing cards.
     @View view The view calling the function.

     */

    public void clearscreen(View view)
    {
        LinearLayout toclear = findViewById(R.id.cpuh);
        toclear.removeAllViews();
        toclear = findViewById(R.id.cpucap);
        toclear.removeAllViews();
        toclear = findViewById(R.id.layh);
        toclear.removeAllViews();
        toclear = findViewById(R.id.stockh);
        toclear.removeAllViews();
        toclear = findViewById(R.id.p1h);
        toclear.removeAllViews();
        toclear = findViewById(R.id.p1cap);
        toclear.removeAllViews();
    }

    /**
     Draws the current Computer Hand.
     @View view The view calling the function.

     */

    public void drawcpuh(View view)
    {
        LinearLayout cpuh = findViewById(R.id.cpuh);

        LayoutInflater inflater = LayoutInflater.from(this);
        Vector<card> toDraw;
        toDraw = cc.getCpuHand();

        String appendix = new String();
        view = inflater.inflate(R.layout.cpuh_space, cpuh, false);

        ViewGroup clear = (ViewGroup) inflater.inflate(R.layout.cpuh_space, null);
        for(int i = 0; i < clear.getChildCount(); i++)
        {
            ImageView iV = view.findViewById( clear.getChildAt(i).getId() );
            iV.setImageResource(getImageId(this, "invis"));
        }
        for (int i = 0; i < toDraw.size(); i++)
        {

            ViewGroup vg = (ViewGroup) inflater.inflate(R.layout.cpuh_space, null);
            appendix = toDraw.elementAt(i).getSuit().toLowerCase() + toDraw.elementAt(i).getValue();
            int id = vg.getChildAt(i).getId();
            ImageView iV = view.findViewById( vg.getChildAt(i).getId() );
            iV.setImageResource(getImageId(this, appendix));


        }

        cpuh.addView(view);

    }

    /**
     Draws the current Layout.
     @View view The view calling the function.

     */

    public void drawlayout(View view)
    {
        LinearLayout layh = findViewById(R.id.layh);
        /**layh.removeAllViews();*/

        LayoutInflater inflater = LayoutInflater.from(this);
        Vector<Vector<card>> toDraw;
        toDraw = cc.getLayout();

        String appendix = new String();
        view = inflater.inflate(R.layout.lay, layh, false);

        ViewGroup clear = (ViewGroup) inflater.inflate(R.layout.lay, null);
        for(int i = 0; i < clear.getChildCount(); i++)
        {
            ImageView iV = view.findViewById( clear.getChildAt(i).getId() );
            iV.setImageResource(getImageId(this, "invis"));
        }

        for (int i = 0; i < toDraw.size(); i++)
        {

            Vector<card> sizeVec = toDraw.elementAt(i);

            ViewGroup vg = (ViewGroup) inflater.inflate(R.layout.lay, null);
            if (sizeVec.size() == 1)
            {
                appendix = toDraw.elementAt(i).elementAt(0).getSuit().toLowerCase() + toDraw.elementAt(i).elementAt(0).getValue();
            }
            else if(sizeVec.size() == 0)
            {
                ImageView iV = view.findViewById( vg.getChildAt(i).getId() );
                iV.setImageResource(getImageId(this, "invis"));
            }
            else
            {
                appendix = "a" + String.valueOf( sizeVec.size() ) + sizeVec.elementAt(0).getValue();
            }
            ImageView iV = view.findViewById( vg.getChildAt(i).getId() );
            iV.setImageResource(getImageId(this, appendix));
        }
        layh.addView(view);





    }

    /**
     Draws the current P1 Capture Pile.
     @View view The view calling the function.

     */

    public void drawp1cap(View view)
    {
        LinearLayout p1cap = findViewById(R.id.p1cap);
        /**p1cap.removeAllViews();*/

        LayoutInflater inflater = LayoutInflater.from(this);
        Vector<Vector<card>> toDraw;
        toDraw = cc.getP1Cap();

        String appendix = new String();
        view = inflater.inflate(R.layout.p1cap, p1cap, false);

        ViewGroup clear = (ViewGroup) inflater.inflate(R.layout.p1cap, null);
        for(int i = 0; i < clear.getChildCount(); i++)
        {
            ImageView iV = view.findViewById( clear.getChildAt(i).getId() );
            iV.setImageResource(getImageId(this, "invis"));
        }

        for (int i = 0; i < toDraw.size(); i++)
        {

            Vector<card> sizeVec = toDraw.elementAt(i);

            ViewGroup vg = (ViewGroup) inflater.inflate(R.layout.p1cap, null);
            if (sizeVec.size() == 1)
            {
                appendix = toDraw.elementAt(i).elementAt(0).getSuit().toLowerCase() + toDraw.elementAt(i).elementAt(0).getValue();
            }
            else
            {
                appendix = "a" + String.valueOf(sizeVec.size()) + sizeVec.elementAt(0).getValue();
            }
            ImageView iV = view.findViewById(vg.getChildAt(i).getId());
            iV.setImageResource(getImageId(this, appendix));
        }
        p1cap.addView(view);




    }

    /**
     Draws the current Computer Capture Pile.
     @View view The view calling the function.

     */
    public void drawcpucap(View view)
    {
        LinearLayout cpucap = findViewById(R.id.cpucap);
        /**p1cap.removeAllViews();*/

        LayoutInflater inflater = LayoutInflater.from(this);
        Vector<Vector<card>> toDraw;
        toDraw = cc.getCpuCap();

        String appendix = new String();
        view = inflater.inflate(R.layout.cpucap, cpucap, false);

        ViewGroup clear = (ViewGroup) inflater.inflate(R.layout.cpucap, null);
        for(int i = 0; i < clear.getChildCount(); i++)
        {
            ImageView iV = view.findViewById( clear.getChildAt(i).getId() );
            iV.setImageResource(getImageId(this, "invis"));
        }

        for (int i = 0; i < toDraw.size(); i++)
        {

            Vector<card> sizeVec = toDraw.elementAt(i);

            ViewGroup vg = (ViewGroup) inflater.inflate(R.layout.cpucap, null);
            if (sizeVec.size() == 1)
            {
                appendix = toDraw.elementAt(i).elementAt(0).getSuit().toLowerCase() + toDraw.elementAt(i).elementAt(0).getValue();
            }
            else
            {
                appendix = "a" + String.valueOf(sizeVec.size()) + sizeVec.elementAt(0).getValue();
            }
            ImageView iV = view.findViewById(vg.getChildAt(i).getId());
            iV.setImageResource(getImageId(this, appendix));
        }
        cpucap.addView(view);




    }

    /**
     Draws the current P1 Hand.
     @View view The view calling the function.

     */

    public void drawp1h(View view)
    {
        LinearLayout p1h = findViewById(R.id.p1h);
        /**p1h.removeAllViews();*/
        /**clearp1h(view);*/
        LayoutInflater inflater = LayoutInflater.from(this);
        Vector<card> toDraw;
        toDraw = cc.getP1Hand();

        String appendix = new String();
        view = inflater.inflate(R.layout.p1h_space, p1h, false);

        ViewGroup clear = (ViewGroup) inflater.inflate(R.layout.p1h_space, null);
        for(int i = 0; i < clear.getChildCount(); i++)
        {
            ImageView iV = view.findViewById( clear.getChildAt(i).getId() );
            iV.setImageResource(getImageId(this, "invis"));
        }
        for (int i = 0; i < toDraw.size(); i++)
        {

            ViewGroup vg = (ViewGroup) inflater.inflate(R.layout.p1h_space, null);
            appendix = toDraw.elementAt(i).getSuit().toLowerCase() + toDraw.elementAt(i).getValue();
            int id = vg.getChildAt(i).getId();
            ImageView iV = view.findViewById( vg.getChildAt(i).getId() );
            iV.setImageResource(getImageId(this, appendix));


        }

        p1h.addView(view);
    }

    /**
     Clears the P1 Hand.
     @View view The view calling the function.

     */

    public void clearp1h(View view)
    {
        LinearLayout p1h = findViewById(R.id.p1h);
        LayoutInflater inflater = LayoutInflater.from(this);
        ViewGroup vg = (ViewGroup) inflater.inflate(R.layout.p1h_space, null);
        view = inflater.inflate(R.layout.p1h_space, p1h, false);

        for (int i = 0; i < vg.getChildCount(); i++)
        {
            ImageView iV = view.findViewById( vg.getChildAt(i).getId() );
            iV.setImageResource(R.drawable.outline);
        }

    }

    /**
     Draws the current stockpile.
     @View view The view calling the function.

     */

    public void drawstock(View view)
    {
        LinearLayout stockl = findViewById(R.id.stockh);
        /**stockl.removeView(view);*/

        LayoutInflater inflater = LayoutInflater.from(this);
        Vector<card> toDraw;
        toDraw = cc.getStock();

        String appendix = new String();
        ViewGroup clear = (ViewGroup) inflater.inflate(R.layout.stock_space, null);

        view = inflater.inflate(R.layout.stock_space, stockl, false);
        ImageView iV = view.findViewById( clear.getChildAt(0).getId() );
        iV.setImageResource(getImageId(this, "invis"));

        appendix = toDraw.elementAt(0).getSuit().toLowerCase() + toDraw.elementAt(0).getValue();
        iV = view.findViewById(R.id.stockcrd);
        iV.setImageResource(getImageId(this, appendix));

        stockl.addView(view);

    }

    /**
     Sets the GUI values for the scores, the round number, and the turn.
     @View view The view calling the function.
     */

    public void drawnums(View view)
    {
        TextView toSet = findViewById(R.id.p1scoretext);
        toSet.setText( String.valueOf( cc.getP1Score() ) );
        toSet = findViewById(R.id.cpuscoretext);
        toSet.setText( String.valueOf(cc.getCPUScore() ) );
        toSet = findViewById(R.id.roundnumtext);
        toSet.setText( String.valueOf( cc.getRoundNum() ) );
        toSet = findViewById(R.id.turntext);
        toSet.setText( String.valueOf( cc.getTurn() ) );
    }

    /**
     Handles the next operation in the game upon clicking the "Next Move" button, based on the gamestate.
     @View view The current view calling the function.

     */
    public void nextTurn(View view)
    {
        clearscreen(view);
        drawscreen(view);
        cc.fixCap();

        if(cc.getP1Hand().size() == 0 && cc.getCpuHand().size() == 0 && cc.getgameState() != 2){
            cc.fixCap();
            cc.calcScores();
            chatLog.append("\n ROUND OVER. \nHuman round score: " + cc.getP1RoundScore() + "\n Computer round score: " + cc.getCPURoundScore() + "\n Play Again?");
            view = findViewById(R.id.ngbutton);
            view.setVisibility(view.VISIBLE);
            view = findViewById(R.id.quitbutton);
            view.setVisibility(view.VISIBLE);
            clearscreen(view);
            drawscreen(view);

            return;
        }

        if (cc.getgameState() == 2)
        {
            chatLog.append( "\nTop Stock card is a " + cc.getStock().get(0).getValue() );
            if(cc.playerTurnC() == 1)
            {
                chatLog.append("\nStock card matches in two locations, where to stack?");

            }

            /**view = findViewById(R.id.layh);
            final View finalView = view;
            view.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View v)
                {
                    selectedLayout(finalView);
                }
            });*/


            return;
        }

        int next = cc.nextTurn();
        if (next == 0)
        {

            cc.computerTurn();
            chatLog.append("\nThe computer will play the " + cc.getCpuHand().elementAt(cc.getcpuindex()).getValue() + " because it matches " + cc.getcpumatches() + " cards.");
            if (cc.getExtrapt()){
                chatLog.append(" It can also earn a point by doing so. ");
            }
            cc.computerTurnB();
            chatLog.append("\nThe top Stock card is a " + cc.getStock().elementAt(0).getValue() );
            cc.computerTurnC();
            cc.computerTurnD();
            view = findViewById(R.id.gameView);
            clearscreen(view);
            drawscreen(view);



        }
        else if (next == 1)
        {

            chatLog.append("\nSelect your card. ");


        }

        }

    /**
     Handles the player selecting a card from his hand in appropriate circumstances. Does nothing otherwise.
     @View view the current view calling this function.
     */

    public void selectedCard(View view)
    {
        if (cc.nextTurn() == 1 && cc.getgameState() == 1)  {
            Vector<card> v = cc.getP1Hand();
            int matches = 0;
            switch (view.getId()) {
                case R.id.p1h_crd:
                    chatLog.append("\n you selected the " + v.get(0).getValue() + " of " + v.get(0).getSuit());
                    //chatLog.append("\n Where to place the card?");
                    matches = cc.playerTurnA(0);
                    break;
                case R.id.p1h_crd2:
                    chatLog.append("\n you selected the " + v.get(1).getValue() + " of " + v.get(1).getSuit());
                    //chatLog.append("\n Where to place the card?");
                    matches = cc.playerTurnA(1);
                    break;
                case R.id.p1h_crd3:
                    chatLog.append("\n you selected the " + v.get(2).getValue() + " of " + v.get(2).getSuit());
                    //chatLog.append("\n Where to place the card?");
                    matches = cc.playerTurnA(2);
                    break;
                case R.id.p1h_crd4:
                    chatLog.append("\n you selected the " + v.get(3).getValue() + " of " + v.get(3).getSuit());
                    //chatLog.append("\n Where to place the card?");
                    matches = cc.playerTurnA(3);
                    break;
                case R.id.p1h_crd5:
                    chatLog.append("\n you selected the " + v.get(4).getValue() + " of " + v.get(4).getSuit());
                   // chatLog.append("\n Where to place the card?");
                    matches = cc.playerTurnA(4);
                    break;
                case R.id.p1h_crd6:
                    chatLog.append("\n you selected the " + v.get(5).getValue() + " of " + v.get(5).getSuit());
                    //chatLog.append("\n Where to place the card?");
                    matches = cc.playerTurnA(5);
                    break;
                case R.id.p1h_crd7:
                    chatLog.append("\n you selected the " + v.get(6).getValue() + " of " + v.get(6).getSuit());
                    //chatLog.append("\n Where to place the card?");
                    matches = cc.playerTurnA(6);
                    break;
                case R.id.p1h_crd8:
                    chatLog.append("\n you selected the " + v.get(7).getValue() + " of " + v.get(7).getSuit());
                    //chatLog.append("\n Where to place the card?");
                    matches = cc.playerTurnA(7);
                    break;
                case R.id.p1h_crd9:
                    chatLog.append("\n you selected the " + v.get(8).getValue() + " of " + v.get(8).getSuit());
                    //chatLog.append("\n Where to place the card?");
                    matches = cc.playerTurnA(8);
                    break;
                case R.id.p1h_crd10:
                    chatLog.append("\n you selected the " + v.get(9).getValue() + " of " + v.get(9).getSuit());
                    //chatLog.append("\n Where to place the card?");
                    matches = cc.playerTurnA(9);
                    break;
            }
            chatLog.append("\n you matched " + matches + " cards.");
            cc.playerTurnB(matches);
            if (cc.gettwomatch())
            {
                chatLog.append("\n which location to stack at?");

            }
        }

        /** setContentView(R.layout.activity_newgame); */
        view = findViewById(R.id.gameView);
        clearscreen(view);
        drawscreen(view);



    }

    /**
     Handles the selection (clicking) of a layout card, based on the current gamestate. Does nothing in appropriate circumstances.
     @View view the current view calling this function.

     */

    public void selectedLayout(View view) {
        //we need to have the player select between the matching indices.
        if (cc.gettwomatch())
        {
            Vector<Vector<card>> v = cc.getLayout();
            switch (view.getId()) {
                case R.id.laycrd:
                    cc.handle2match(0);
                    break;
                case R.id.laycrd2:
                    cc.handle2match(1);
                    break;
                case R.id.laycrd3:
                    cc.handle2match(2);
                    break;
                case R.id.laycrd4:
                    cc.handle2match(3);
                    break;
                case R.id.laycrd5:
                    cc.handle2match(4);
                    break;
                case R.id.laycrd6:
                    cc.handle2match(5);
                    break;
                case R.id.laycrd7:
                    cc.handle2match(6);
                    break;
                case R.id.laycrd8:
                    cc.handle2match(7);
                    break;

            }


        }
        else if (cc.getgameState() == 2 && cc.getstocktwomatch())
        {
            switch (view.getId())
            {
                case R.id.laycrd:
                    cc.handlestock2(0);
                    break;
                case R.id.laycrd2:
                    cc.handlestock2(1);
                    break;
                case R.id.laycrd3:
                    cc.handlestock2(2);
                    break;
                case R.id.laycrd4:
                    cc.handlestock2(3);
                    break;
                case R.id.laycrd5:
                    cc.handlestock2(4);
                    break;
                case R.id.laycrd6:
                    cc.handlestock2(5);
                    break;
                case R.id.laycrd7:
                    cc.handlestock2(6);
                    break;
                case R.id.laycrd8:
                    cc.handlestock2(7);
                    break;
            }


        }

        if (cc.getgameState() == 3)
        {
            cc.playerTurnD();
            view = findViewById(R.id.gameView);
            clearscreen(view);
            drawscreen(view);

        }

        /**setContentView(R.layout.activity_newgame);*/
        view = findViewById(R.id.gameView);
        clearscreen(view);
        drawscreen(view);
    }

    /**
     Quits the game
     @View view The current view calling this function.

     */

    public void quit(View view)
    {
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }

    /**
     Calls the client newRound function, outputs appropriate messages to the chatLog, refreshes the screen.
     @View view The Current view calling this function.

     */

    public void newRound(View view)
    {

        cc.setRoundNum (cc.getRoundNum() + 1);


        chatLog = (TextView) findViewById(R.id.chatLog);

        view = findViewById(R.id.ngbutton);
        view.setVisibility(view.GONE);
        view = findViewById(R.id.quitbutton);
        view.setVisibility(view.GONE);
        cc.newRound( cc.getRoundNum());
        chatLog.append("The " + cc.getTurn() + " will move first." );
        view = findViewById(R.id.gameView);
        clearscreen(view);
        drawscreen(view);
    }

    /**
     Calls the Client help function, and outputs help data to the chatLog. Ensures that it is the correct time to do so.
     @View view The current view that calls this function.

     */

    public void help(View view)
    {
        if(cc.getgameState() == 1 && cc.getTurn().equals("Human"))
        {
            chatLog.append("\n The computer recommends that you play the " + cc.getRecco() + " from your hand. It matches the most cards in the layout. ");
            if (cc.getExtrapt())
            {
                chatLog.append("You will also complete a 2 stack, earning you a point.");
            }
        }
    }

    /**
     Saves the game if it is the start of a turn (either Human or CPU).
     @View view the current view calling this function.
     */

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void saveGame(View view) throws FileNotFoundException {
        if(cc.getgameState() >= 2){
            chatLog.append("\nCannot save now, please finish your turn first.");
            return;
        }
        String filename = "local";
        String fileContents = "Round: ";
        fileContents += cc.getRoundNum();
        fileContents += "\n\nComputer:";
        fileContents += "\nScore: " + cc.getCPUScore();
        fileContents += "\nHand: ";
        Vector<card> temp = cc.getCpuHand();
        for(int i = 0; i < cc.getCpuHand().size(); i++)
        {

            fileContents += saveConvert(temp.get(i).getValue());
            fileContents += (Character) temp.get(i).getSuit().charAt(0) + " ";
        }
        fileContents += "\nCapture Pile: ";
        Vector<Vector<card>> tmp = cc.getCpuCap();
        for(int i = 0; i < cc.getCpuCap().size(); i++)
        {
            for(int j = 0; j < tmp.get(i).size(); j++)
            {
                fileContents += saveConvert(tmp.get(i).get(j).getValue());
                fileContents += (Character) tmp.get(i).get(j).getSuit().charAt(0) + " ";
            }

        }
        fileContents += "\n\nHuman:";
        fileContents += "\nScore: " + cc.getP1Score();
        fileContents += "\nHand: ";

        temp = cc.getP1Hand();
        for(int i = 0; i < cc.getP1Hand().size(); i++)
        {

            fileContents += saveConvert(temp.get(i).getValue());
            fileContents += (Character) temp.get(i).getSuit().charAt(0) + " ";
        }
        fileContents += "\nCapture Pile: ";
        tmp = cc.getP1Cap();
        for(int i = 0; i < cc.getP1Cap().size(); i++)
        {
            for(int j = 0; j < tmp.get(i).size(); j++)
            {
                fileContents += saveConvert(tmp.get(i).get(j).getValue());
                fileContents += (Character) tmp.get(i).get(j).getSuit().charAt(0) + " ";
            }

        }
        fileContents += "\n\nLayout: ";
        tmp = cc.getLayout();
        for(int i = 0; i < cc.getLayout().size(); i++)
        {
            for(int j = 0; j < tmp.get(i).size(); j++)
            {

                fileContents += saveConvert(tmp.get(i).get(j).getValue());
                fileContents += (Character) tmp.get(i).get(j).getSuit().charAt(0) + " ";
            }

        }
        fileContents += "\n\n";
        fileContents += "Stock Pile: ";
        temp = cc.getStock();
        for(int i = 0; i < cc.getStock().size(); i++)
        {

            fileContents += saveConvert(temp.get(i).getValue());
            fileContents += (Character) temp.get(i).getSuit().charAt(0) + " ";
        }
        fileContents += "\n\n";
        fileContents += "Next Player: ";
        if (cc.getTurn().equals("Human")){
            fileContents += "Human";
        }
        else{
            fileContents += "Computer";
        }


        try (FileOutputStream fos = this.getBaseContext().openFileOutput(filename, Context.MODE_PRIVATE)) {
            fos.write(fileContents.getBytes());
            chatLog.append("Game Saved Successfully.");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }


    /**
     Converts card values to be used in the saveGame function. (10 -> "X", 11 -> "J", etc.)
     @int toConv The integer value to convert to a string.
     @String returns a string which will be appended to the save file.
     */
    public String saveConvert (int toConv)
    {
        switch (toConv)
        {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                return Integer.toString(toConv);

            case 10:
                return "X";

            case 11:
                return "J";

            case 12:
                return "Q";

            case 13:
                return "K";

        }
        return "2";
    }

    public void quit2(View view)
    {
        if (cc.getCPUScore() > cc.getP1Score())
        {
            chatLog.append("\nCPU wins!");

        }
        else if (cc.getCPUScore() == cc.getP1Score())
        {
            chatLog.append("\nDraw!");
        }
        else{
            chatLog.append("P1 Wins!");
        }

        view = findViewById(R.id.quitbutton);

        final View finalView1 = view;
        view.setOnClickListener(new View.OnClickListener()
         {
         public void onClick(View v)
         {
         quit(finalView1);
         }
         });

    }

};
