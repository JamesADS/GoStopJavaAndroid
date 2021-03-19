/**
 ************************************************************
 * Name:  James Giordano                                    *
 * Project:  4 Java Android GoStop                          *
 * Class:  OPL, CRN:  21321                                 *
 * Date:  5/1/2020                                          *
 ************************************************************
 */

package com.example.gostop;

import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.Vector;
import java.util.Collections;

public class client {

    private static Vector<card> deck = new Vector<card>(104);
    private static Vector<Vector<card>> layout =  new Vector<Vector<card>>(8);
    private static Vector<card> stock = new Vector<card>(80);
    private static Vector<Vector<card>> p1Cap = new Vector<Vector<card>>(10);
    private static Vector<Vector<card>> cpuCap = new Vector<Vector<card>>(10);
    private static Vector<card> p1Hand = new Vector<card>(10);
    private static Vector<card> cpuHand = new Vector<card>(10);
    private static String turn = new String();
    private static int gameState = 0; //0 = CPU turn, 1 = human turn (card 1), 2 = human turn (card 2)
    private static int roundNum = 1;
    private static int p1score = 0;
    private static int p1roundscore = 0;
    private static int cpuscore = 0;
    private static int cpuroundscore = 0;
    private static int selectedCard = 0;
    private static int indexA = 0;
    private static int[] matches = new int[] {-1, -1, -1};
    private static Boolean twomatch = false;
    private static Boolean threematch = false;
    private static Boolean stocktwomatch = false;
    private static int cpuIndex = 0;
    private static int cpuNumMatches = 0;
    private static int cpulayindex = 0;
    private static int[] cpumatches = new int[] {-1, -1, -1};
    private Boolean extrapt = false;

    /**
     Returns the current round number.

     @int returns roundNum, the current round number.
     */

    public int getRoundNum() {
        return roundNum;
    }

    /**
     Sets the current round number.
     @int uroundNum the number to set the current round number to.

     */

    public void setRoundNum(int uroundNum) {
        roundNum = uroundNum;

    }

    /**
     Returns the current P1 score.

     @int returns p1score.
     */

    public int getP1Score()
    {
        int score = p1score;
        return score;
    }

    /**
     Sets the P1 score.
     @int uscore the score to set the P1 score to.

     */

    public void setP1Score(int uscore)
    {
        p1score = uscore;
    }

    /**
     Returns the current CPU Score.

     @int returns cpuscore.
     */


    public int getCPUScore()
    {
        int score = cpuscore;
        return score;
    }

    /**
     Sets the CPU score.
     @int uscore the score to set the CPU score to.

     */

    public void setCPUScore(int uscore)
    {
        cpuscore = uscore;
    }


    /**
     Returns the current turn.

     @String returns the turn, which holds the name of the player whose turn it is.
     */

    public String getTurn() {
        String turn = client.turn;
        return turn;
    }

    /**
     Starts a newRound, handles both starting the first round and starting subsequent rounds. Populates the data structures accordingly.

     @int urndnum the round number that it will be.
     */

    public void newRound(int urndnum)
    {

        /**deck = new Vector<card>(104) ;
        layout = new Vector<Vector<card>>(8) ;
        stock =  new Vector<card>(80) ;
        p1Cap = new Vector<Vector<card>>(2) ;
        cpuCap =  new Vector<Vector<card>>(2) ;
        p1Hand =  new Vector<card>(10) ;
        cpuHand = new Vector<card>(10) ;*/

        roundNum = urndnum;

        if (roundNum == 1)
        {
            createCards();
            dealCards();
            String first = firstMove();


            if (first == "Human")
            {
                setTurn(first);
                setgameState(1);

            }
            else if (first == "CPU")
            {
                setTurn(first);
                setgameState(0);
            }
            else //reshuffle is needed
            {

                newRound( urndnum );
            }

        }
        else
        {


            /**deck = new Vector<card>(104) ;
            layout = new Vector<Vector<card>>(8) ;
            stock =  new Vector<card>(80) ;


            p1Hand =  new Vector<card>(10) ;
            cpuHand = new Vector<card>(10) ;*/
            layout.clear();
            stock.clear();
            p1Cap.clear();
            cpuCap.clear();
            cpuCap =  new Vector<Vector<card>>(8);
            p1Cap = new Vector<Vector<card>>(8);
            for(int i = 0; i < 8; i++)
            {
                Vector<card> v = new Vector<card>(4);
                layout.add(v);

            }

            createCards();
            dealCards();


            String highscore = higherScore();
            if (highscore == "Human")
            {

                setTurn(highscore);
                setgameState(1);

            }
            else if (highscore == "CPU")
            {

                setTurn(highscore);
                setgameState(0);
            }
            else //reshuffle is needed
            {


                String first = firstMove();
                if (first == "Human")
                {
                    setTurn(first);
                    setgameState(1);

                }
                else if (first == "CPU")
                {
                    setTurn(first);
                    setgameState(0);
                }
                else //reshuffle is needed
                {

                    newRound( urndnum );
                }


            }

        }

    }

    /**
     Sets the Turn.
     @String uturn String to set the turn member to.

     */

    public void setTurn(String uturn) {
        turn = uturn;
    }

    /**
     Sets the gameState.
     @int ugamestate the value to set the gameState to. (0 = CPU Turn, 1 = P1 turn (hand card), 2 = P1 Stock Card).

     */

    public void setgameState(int ugamestate) { gameState = ugamestate;}

    /**
     Returns the gamestate data member

     @int returns the gameState data member.
     */

    public int getgameState()
    {
        int ugameState = 3;
        ugameState = gameState;
        return ugameState;
    }

    /**
     Returns the stocktwomatch data member.

     @Boolean returns stocktwomatch, which signifies if a stockpile card matches two cards.
     */

    public Boolean getstocktwomatch()
        {
        return stocktwomatch;
        }

    /**
     Constructor for the client class.
     */

    client()
    {
        for(int i = 0; i < 8; i++)
        {
            layout.add(new Vector<card>());
        }
    }

    /**
     Returns the deck.

     @Vector<card> returns the Deck.
     */

    public Vector<card> getDeck() {
        return deck;
    }

    /**
     Sets the Deck.
     @Vector<card> udeck The vector to set the deck to.

     */

    public void setDeck(Vector<card> udeck) {
        deck = udeck;
    }

    /**
     Returns the layout.

     @Vector<Vector<card> returns the layout.
     */

    public Vector<Vector<card>> getLayout() {
        Vector<Vector<card>> layout = client.layout;
        return layout;
    }

    /**
     Sets the Layout.
     @Vector<Vector<card> ulayout the vector of vector of cards to set the layout to.

     */

    public void setLayout(Vector<Vector<card>> ulayout)
    {
       layout = ulayout;

    }

    /**
     Returns the extrapt data member.

     @Boolean returns extrapt, a boolean used to determine if making a two stack will result in an extra point via matching an existing capture pile two stack.
     */

    public Boolean getExtrapt() { return extrapt;}


    /**
     Returns the Stock Pile.

     @Vector<card> returns the Stock Pile.
     */


    public Vector<card> getStock() {
        return stock;
    }

    /**
     Sets the StockPile.
     @Vector<card> ustock The Vector to set the stockpile to.

     */

    public void setStock(Vector<card> ustock) {
        stock = ustock;
    }

    /**
     Returns the Human Capture Pile.

     @Vector<Vector<card>> returns the Human Capture pile.
     */

    public Vector<Vector<card>> getP1Cap() {
        return p1Cap;
    }

    /**
     Sets the P1 Capture Pile.
     @Vector<Vector<card> up1Cap the vector of vector of cards to set the P1 Capture Pile to.

     */

    public void setP1Cap(Vector<Vector<card>> up1Cap) {
        p1Cap = up1Cap;
    }

    /**
     Returns the CPU Capture Pile.

     @Vector<Vector<card>> returns the CPU Capture pile.
     */

    public Vector<Vector<card>> getCpuCap() {
        return cpuCap;
    }

    /**
     Sets the Computer Capture Pile.
     @Vector<Vector<card> ucpuCap the vector of vector of cards to set the CPU Capture Pile to.

     */

    public void setCpuCap(Vector<Vector<card>> ucpuCap) {
        cpuCap = ucpuCap;
    }

    /**
     Returns the P1Hand.
     @Vector<card> returns p1Hand.
     */

    public Vector<card> getP1Hand() {
        return p1Hand;
    }

    /**
     Sets the P1 Hand.
     @Vector<card> up1Hand the Vector to set the P1Hand to.

     */

    public void setP1Hand(Vector<card> up1Hand) {
        p1Hand = up1Hand;
    }

    /**
     Returns the current round number.

     @int returns roundNum, the current round number.
     */

    public Vector<card> getCpuHand() {
        final Vector<card> cpuHand = client.cpuHand;
        return cpuHand;
    }

    /**
     Sets the CPU Hand.
     @Vector<card> up1Hand the Vector to set the CPUHand to.

     */

    public void setCpuHand(Vector<card> ucpuHand) {
       cpuHand = ucpuHand;
    }

    /**
     Returns twomatch, a Boolean used to determine if a card matches 2 cards.

     @Boolean returns twomatch.
     */

    public Boolean gettwomatch(){
        return twomatch;
    }

    /**
     Sets the twomatch member.
     @Boolean utwomatch sets the twomatch member to this value. It is used to check if the card played matches two cards specifically.

     */

    public void settwomatch(Boolean utwomatch){
        twomatch = utwomatch;
    }

    /**
     Returns threematch, a Boolean used to determine if a card matches 3 cards.

     @Boolean returns threematch.
     */

    public Boolean getthreematch(){
        return threematch;
    }

    /**
     Sets the threematch member.
     @Boolean uthreematch sets the threematch member to this value. It is used to check if the card played matches three cards specifically.

     */

    public void setthreematch(Boolean uthreematch){
        threematch = uthreematch;
    }

    /**
     Creates the cards which will be used to populate the deck and subsequently all the client data members that use cards.


     */

    public void createCards()
    {
        Vector newV = new Vector();
        for (int i = 0; i < 4; i++)
        {
            for (int j = 1; j < 14; j++)
            {
                card c = new card(i,j);
                newV.add(c);
                newV.add(c);
            }
        }
        setDeck(newV);
    }

   /**
    Populates the client data members that use cards.
    */

    public void dealCards() {
        Collections.shuffle(deck);
        Vector<card> v = new Vector<card>();
        Vector<Vector<card>> vv = new Vector<Vector<card>>();

            for (int j = 0; j < 5; j++)
            {
                p1Hand.addElement( deck.elementAt(0));

                deck.removeElementAt(0);
            }
            //Collections.copy(p1Hand, v);
            //v.clear();
            for (int j = 0; j < 5; j++) {
                cpuHand.addElement( deck.elementAt(0));

                deck.removeElementAt(0);
            }
            //Collections.copy(cpuHand, v);
           // v.clear();
            for (int j = 0; j < 4; j++)
            {

                /**v.addElement(deck.elementAt(0));
                vv.addElement(v);

                setLayout(vv);
                deck.removeElementAt(0);
                v.clear();*/

                layout.elementAt(j).addElement(deck.elementAt(0));
                deck.removeElementAt(0);
            }
            for (int j = 0; j < 5; j++)
        {
            p1Hand.addElement( deck.elementAt(0));

            deck.removeElementAt(0);
        }
        v.clear();
        for (int j = 0; j < 5; j++) {
            cpuHand.addElement( deck.elementAt(0));

            deck.removeElementAt(0);
        }
        v.clear();
        for (int j = 4; j < 8; j++)
        {

            layout.elementAt(j).addElement(deck.elementAt(0));
            deck.removeElementAt(0);
        }

        setStock(deck);
    }

    /**
     Determines who should move first, the CPU or the Human, by comparing the hands of both players.

     @String returns "Human" or "CPU" based on who should have the first turn.
     */

    public String firstMove() {

        card temp = new card(0, 0);
        int humancount = 0, cpucount = 0, valueCheck = 13;

        while (valueCheck >= 1) {
            Iterator it = p1Hand.iterator();
            //Checking human values
            while (it.hasNext()) {
                temp = (card) it.next();
                if (temp.getValue() == valueCheck) {
                    humancount += 1;
                }
            }

            it = cpuHand.iterator();
            //checking CPU values
            while (it.hasNext()){
                temp = (card) it.next();
                if (temp.getValue() == valueCheck){
                    cpucount += 1;
                }
            }
            if(humancount > cpucount){
                return "Human";
            }
            else if(cpucount > humancount){
                return "CPU";
            }
            else {
                valueCheck -= 1;
                humancount = 0;
                cpucount = 0;
            }

        }
        //by default a reshuffle is needed.
        return "reshuffle";
    }

    /**
     Determines who has the higher round score.

     @String returns the player with the higher round score.
     */

    public String higherScore()
    {
        if (p1roundscore > cpuroundscore)
        {
            return "Human";
        }
        else if (cpuroundscore > p1roundscore)
        {
            return "CPU";
        }
        else if (p1roundscore == cpuroundscore)
        {
                return firstMove();
        }
        else return "Human";
    }

    /**
     Returns an integer to determine whose turn it is. 0 = Computer, 1 = Human.

     @int returns 0 or 1 based on whose turn it is.
     */

    public int nextTurn()
    {
        if (turn == "CPU")
        {
            return 0;
        }
        else
        {
            return 1;
        }

    }

    /**
     Handles the first logical part of the computer turn, which checks to see if a 3 stack can be made. All functions called in this function modify data members
     which enable the latter parts of the computer turn.


     */

    public void computerTurn()
    {
       threematch = check3stack();
        if (!threematch)
        {
            getmostMatches();

        }

        //cpuindex now contains the index to play, cpumatches contains the number of matches to play.
    }

    /**
     Handles the first part of the player turn, which identifies which card has been selected, and checks how many cards it matches.

     @int returns the number of matches the selected player card has.
     */

    public int playerTurnA(int index)
    {
        indexA = index;
        selectedCard = index;
        int matches = getp1hMatches(index);
        return matches;

    }

    /**
     Handles the second part of the player turn, which is identified as moving the player hand card and taking the appropriate actions via GoStop rules.
     @int mmatches the number of matches that the most matching player card makes with the layout.
     */

    public void playerTurnB(int mmatches)
    {

        switch(mmatches)
        {
            case 0:
                Vector<card> v = new Vector<card>(1);
                v.add(p1Hand.get(indexA));
                layout.add(v);
                p1Hand.removeElementAt(indexA);
                break;
            case 1:
                //add the card from the player hand at indexA to the layout where it matches.
                wherep1hMatches();
                layout.elementAt( matches[0] ).add(p1Hand.get(indexA));
                p1Hand.removeElementAt(indexA);
                break;
            case 2:
                twomatch = true;
                break;
            case 3:
                wherep1hMatches();
                Vector<card> x = new Vector<card>(4);
                x.add(p1Hand.elementAt(indexA));
                //for every matching index.
                for (int i = 0; i < matches.length; i++)
                {
                    if ( !(matches[i] < 0 ) && !layout.elementAt(matches[i]).isEmpty()) {
                        //add all of the cards to x.
                        x.addAll(layout.elementAt(matches[i]));
                        //get rid of the elements in p1.
                        //layout.removeElementAt(matches[i]);

                    }
                }
                for (int i = 0; i < matches.length; i++)
                {
                    layout.removeElementAt(matches[i]);
                    for (int j = i; j < matches.length; j++){
                        matches[j] -= 1;
                    }
                }
                p1Cap.addElement(x);
                break;

        }
        gameState = 2;
        for (int i = 0; i < 3; i++){

            matches[i] = -1;
        }
    }

    /**
     Handles the player hand card matching two cards at separate locations in the layout.
     @int layInd the layout index selected by the player when prompted which of the two they would like to match at.
     */


    public void handle2match(int layInd)
    {
        layout.elementAt(layInd).add(p1Hand.get(indexA));
        p1Hand.removeElementAt(indexA);
        twomatch = false;
        gameState = 2;
    }

    /**
     Returns the number of matches the Player card at the passed index has.
     @int index - the index of the player card to check.
     @int returns a counter of how many layout cards match the value of the player card.
     */

    public int getp1hMatches(int index)
    {
        int counter = 0;
        card toMatch = p1Hand.get(index);

        for(int i = 0; i < layout.size(); i++) {
            for (int j = 0; j < layout.get(i).size(); j++ )
            {


                if (toMatch.getValue() == layout.get(i).elementAt(0).getValue())
                {
                    counter += 1;
                }
            }
        }

        return counter;

    }

    /**
     Determines which indices the player hand card matches at and stores them in matches[].
     */

    public void wherep1hMatches()
    {
        card toMatch = p1Hand.get(indexA);
        //Vector<Integer> mmatches = new Vector<Integer>( getp1hMatches(indexA) );

        for(int i = 0; i < matches.length; i++)
        {
            for(int j = 0; j < layout.size(); j++)
            {
                if (toMatch.getValue() == layout.get(j).elementAt(0).getValue() && j != matches[0] && j != matches[1] )
                {
                    matches[i] = j;

                    break;
                }
            }
        }


    }

    /**
     Handles the third part of the player turn, which is identified as moving the player stock card and taking the appropriate actions via GoStop rules.
     @int returns 0 if matches 0, 1, 3 cards or a two stack of cards, returns 1 if matches two single card stacks at separate locations (player must choose which index to match at.)
     */

    public int playerTurnC() //returns 0 for no player action, returns 1 for more player action (select where layout card goes)
    {
        switch(getstockMatches())
        {
            case 0:
                Vector<card> v = new Vector<card>(1);
                v.add(stock.elementAt(0));
                layout.add(v);
                stock.removeElementAt(0);
                break;
            case 1:
                //add the card from the player hand at indexA to the layout where it matches.
                wherestockMatches();
                layout.elementAt( matches[0] ).add(stock.elementAt(0));
                //p1Hand.removeElementAt(indexA);
                stock.removeElementAt(0);
                break;
            case 2:
                wherestockMatches();
                if (matches[0] != -1 && matches[1] != -1)
                {


                    stocktwomatch = true;
                    return 1;
                }
                else{
                    //add the stockpile card to the location where the stack pair that it matches is
                    layout.elementAt(matches[0]).addElement(stock.elementAt(0)) ;
                    stock.removeElementAt(0);

                }
                break;
            case 3:
                wherestockMatches();
                Vector<card> x = new Vector<card>(4);
                x.add(stock.elementAt(0));
                //for every matching index.
                for (int i = 0; i < matches.length; i++)
                {
                    if ( !(matches[i] < 0) && !layout.elementAt(matches[i]).isEmpty()) {
                        //add all of the cards to x.
                        x.addAll(layout.elementAt(matches[i]));
                        //get rid of the elements in p1.
                        //layout.removeElementAt(matches[i]);
                    }



                }
                for (int i = 0; i < matches.length; i++)
                {
                    if(matches[i] >= 0) {
                        layout.removeElementAt(matches[i]);
                        for (int j = i; j < matches.length; j++) {
                            matches[j] -= 1;
                        }
                    }
                }
                stock.removeElementAt(0);
                p1Cap.add(x);
                break;

        }

        gameState = 3;
        return 0;
    }

    /**
     Returns the number of matches the stock card has.

     @int returns a counter of how many layout cards match the value of the stock card.
     */

    public int getstockMatches()
    {
        int counter = 0;
        card toMatch = stock.get(0);

        for(int i = 0; i < layout.size(); i++) {
            for (int j = 0; j < layout.get(i).size(); j++ )
            {


                if (toMatch.getValue() == layout.get(i).elementAt(0).getValue())
                {
                    counter += 1;
                }
            }
        }

        return counter;

    }

    /**
     Determines which indices the top stockpile card matches at, and stores them in matches[].
     */

    public void wherestockMatches()
    {
        card toMatch = stock.get(0);


        for(int i = 0; i < matches.length; i++)
        {
            for(int j = 0; j < layout.size(); j++)
            {
                if (toMatch.getValue() == layout.get(j).elementAt(0).getValue() && j != matches[0] && j != matches[1] )
                {
                    matches[i] = j;

                    break;
                }
            }
        }


    }

    /**
     Handles case where the stockpile card matches two single cards at separate indices. And takes the appropriate action to place the card at the selected index.
     @int layInd the layout index to move the stockpile card to.
     */

    public void handlestock2(int layInd)
    {
        if(layout.elementAt(layInd).elementAt(0).getValue() == stock.elementAt(0).getValue()) {
            layout.elementAt(layInd).add(stock.get(0));
            stock.removeElementAt(0);
            for (int i = 0; i < 3; i++) {

                matches[i] = -1;
            }
            gameState = 3;
            setTurn("CPU");
            stocktwomatch = false;
        }
    }

    /**
     Handles the fourth part of the player turn, which is identified as moving two stacks and four stacks to the player capture pile,
     as well as setting appropriate data members to prepare for the computer turn.

     */

    public void playerTurnD()
    {
            for (int i = 0; i < layout.size(); i++)
            {
                if(layout.get(i).size() == 2 || layout.get(i).size() == 4)
                {
                    p1Cap.add(layout.elementAt(i));
                    layout.removeElementAt(i);
                    i--;

                }
            }
        gameState = 0; //computer will go next
        for (int i = 0; i < 3; i++){

            matches[i] = -1;
        }
        fixCap();
        extrapt = false;
        setTurn("CPU");
    }


    /**
     Sets the cpuIndex (Where in the hand the CPU should play from) and the cpulayindex (Where the CPU should play the card to.)
     based on the most matching card in the CPUHand.


     */

    public void getmostMatches()
    {
        int counter = 0;
        int max = 0;
        //for every card in the cpu hand
        for(int i = 0; i < cpuHand.size(); i++)
        {
            //check against each space of cards in the layout
            for(int j = 0; j < layout.size(); j++)
            {
                //increment the counter by the size of the matching stack
                if (cpuHand.elementAt(i).getValue() == layout.elementAt(j).elementAt(0).getValue())
                {
                    counter += layout.elementAt(j).size();
                    //if this is our most matching card, update the layout index to reflect so.
                    if (counter > max)
                    {
                        cpulayindex = j;
                        if (counter == 3)
                        {
                            cpuIndex = i;
                            cpuNumMatches = 3;
                            return;
                        }
                    }
                    if (counter < 3)
                    {
                        for (int k = 0; k < cpuCap.size(); k++)
                        {
                            if (cpuHand.elementAt(i).getValue() == cpuCap.elementAt(k).elementAt(0).getValue() && cpuCap.elementAt(k).size() == 2 )
                            {
                                cpuIndex = i;
                                cpulayindex = j;
                                cpuNumMatches = counter;
                                extrapt = true;
                                return;
                            }
                        }
                    }

                }
            }

            //we have finished iterating though the layout for this card, so let's check if this is a most matching index
            if (counter > max)
            {
                max = counter;
                cpuIndex = i;
            }


            counter = 0;

        }
        //set the cpu number of most matches to the maximum amount observed here.
        cpuNumMatches = max;
    }

    /**
     Checks if the computer can match 3 cards in the layout, setting the index of the hand card and the index of the layout card to the appropriate values.
     */

    public Boolean check3stack()
    {
        for(int i = 0; i < cpuHand.size(); i++) {
            //check against each space of cards in the layout
            for (int j = 0; j < layout.size(); j++) {
                //increment the counter by the size of the matching stack
                if (cpuHand.elementAt(i).getValue() == layout.elementAt(j).elementAt(0).getValue() && layout.elementAt(j).size() == 3)
                {
                    cpuIndex = i;
                    cpulayindex = j;
                    cpuNumMatches = 3;
                    return true;

                }
            }
        }
        return false;
    }

    /**
     Returns the Index of the card the CPU should play.

     @int cpuIndex
     */

    public int getcpuindex(){
        return cpuIndex;
    }

    /**
     Returns the number of matches the CPU Hand card has.

     @int returns cpuNumMatches.
     */

    public int getcpumatches(){
        return cpuNumMatches;
    }

    /**
     Handles the second part of the computer turn, which is identified as moving the computer hand card to the layout and taking the appropriate actions via GoStop rules.

     */

    public void computerTurnB()
    {
        if(threematch)
        {

            cpuwherematch();
            Vector<card> x = new Vector<card>(4);
            x.add(cpuHand.elementAt(cpuIndex));
            cpuHand.removeElementAt(cpuIndex);
            x.addAll(layout.elementAt(cpulayindex));
            layout.removeElementAt(cpulayindex);
            cpuCap.addElement(x);




        }
        else {
            switch (cpuNumMatches) {
                case 0:
                    Vector<card> v = new Vector<card>(1);
                    v.add(cpuHand.elementAt(cpuIndex));
                    layout.add(v);
                    cpuHand.removeElementAt(cpuIndex);
                    break;
                case 1:
                case 2:
                    //add the card from the cpu hand to the layout where it matches. In the event of matching two spots, just match with the first spot.

                    layout.elementAt(cpulayindex).add(cpuHand.elementAt(cpuIndex));
                    cpuHand.removeElementAt(cpuIndex);
                    break;

                case 3:
                    cpuwherematch();
                    Vector<card> x = new Vector<card>(4);
                    x.add(cpuHand.elementAt(cpuIndex));
                    cpuHand.removeElementAt(cpuIndex);
                    //for every matching index.
                    for (int i = 0; i < cpumatches.length; i++) {
                        if (!layout.elementAt(cpumatches[i]).isEmpty()) {
                            //add all of the cards to x.
                            x.addAll(layout.elementAt(cpumatches[i]));
                            //get rid of the elements in p1.
                            //layout.removeElementAt(cpumatches[i]);

                        }

                    }
                    for (int i = 0; i < cpumatches.length; i++) {
                        if (cpumatches[i] >= 0) {
                            layout.removeElementAt(cpumatches[i]);
                            for (int j = i; j < cpumatches.length; j++) {
                                cpumatches[j] -= 1;
                            }
                        }
                    }
                    cpuCap.addElement(x);
                    break;

            }
        }
        for (int i = 0; i < 3; i++){

            cpumatches[i] = -1;
        }

    }

    /**
     Handles the third part of the player turn, which is identified as moving the computer stock card to the layout and taking the appropriate actions via GoStop rules.

     */

    public void computerTurnC()
    {
        switch(getstockMatches())
        {
            case 0:
                Vector<card> v = new Vector<card>(1);
                v.add(stock.elementAt(0));
                layout.add(v);
                stock.removeElementAt(0);
                break;
            case 1:
                //add the card from the player hand at stock to where it matches.
                wherestockMatches();
                layout.elementAt( matches[0] ).add(stock.elementAt(0));

                stock.removeElementAt(0);
                break;
            case 2:
                wherestockMatches();

                    //add the stockpile card to the location where the stack pair that it matches is
                    layout.elementAt(matches[0]).addElement(stock.elementAt(0)) ;
                    stock.removeElementAt(0);


                break;
            case 3:
                wherestockMatches();
                Vector<card> x = new Vector<card>(4);
                x.add(stock.elementAt(0));
                //for every matching index.
                for (int i = 0; i < matches.length; i++)
                {
                    if (!(matches[i] < 0) && !layout.elementAt(matches[i]).isEmpty()) {
                        //add all of the cards to x.
                        x.addAll(layout.elementAt(matches[i]));
                        //get rid of the elements in p1.

                    }
                }
                for (int i = 0; i < matches.length; i++)
                {
                    if (matches[i] >= 0) {
                        layout.removeElementAt(matches[i]);
                        for (int j = i; j < matches.length; j++) {
                            matches[j] -= 1;
                        }
                    }
                }
                stock.removeElementAt(0);
                cpuCap.add(x);
                break;

        }


        return;
    }

    /**
     Handles the fourth part of the computer turn, which is identified as moving two stacks and four stacks in the layout to the CPU capture pile, as well as
     setting the data members for the upcoming player turn, and future computer turns.

     */

    public void computerTurnD()
    {
        for (int i = 0; i < layout.size(); i++)
        {
            if(layout.get(i).size() == 2 || layout.get(i).size() == 4)
            {
                cpuCap.add(layout.elementAt(i));
                layout.removeElementAt(i);
                i--;

            }
        }
        gameState = 1; //player will go next
        for (int i = 0; i < 3; i++){

            matches[i] = -1;
        }
        setTurn("Human");
        cpuNumMatches = 0;
        cpulayindex = 0;
        cpuIndex = 0;
        extrapt = false;
        fixCap();

    }

    /**
     Determines the matching indices of the selected CPU Hand card.
     */

    public void cpuwherematch()
    {
        card toMatch = cpuHand.get(cpuIndex);


        for(int i = 0; i < cpumatches.length; i++)
        {
            for(int j = 0; j < layout.size(); j++)
            {
                if (toMatch.getValue() == layout.get(j).elementAt(0).getValue() && j != cpumatches[0] && j != cpumatches[1] )
                {
                    cpumatches[i] = j;

                    break;
                }
            }
        }
    }

    /**
     Consolidates the capture piles of the computer and the human, adding matching two stacks to each other, and clearing newly empty spaces.
     */

    public void fixCap()
    {
        for(int i = 0; i < cpuCap.size(); i++)
        {
            if(cpuCap.elementAt(i).size() == 2 && i != cpuCap.size())
            {
                for (int j = i + 1; j < cpuCap.size(); j++)
                {

                        if (cpuCap.elementAt(j).size() == 2 && cpuCap.elementAt(j).elementAt(0).getValue() == cpuCap.elementAt(i).elementAt(0).getValue())
                        {
                            for(int k = 0; k < cpuCap.elementAt(j).size(); k++)
                            {
                                cpuCap.elementAt(i).add(cpuCap.elementAt(j).elementAt(k));
                            }
                            cpuCap.elementAt(j).removeElementAt(1);
                            i++;
                            j++;
                        }

                }
            }
        }
        for(int i = 0; i < p1Cap.size(); i++)
        {
            if(p1Cap.elementAt(i).size() == 2 && i != p1Cap.size())
            {
                for (int j = i + 1; j < p1Cap.size(); j++) {

                        if (p1Cap.elementAt(j).size() == 2 && p1Cap.elementAt(j).elementAt(0).getValue() == p1Cap.elementAt(i).elementAt(0).getValue()) {
                            for(int k = 0; k < p1Cap.elementAt(j).size(); k++)
                            {
                                p1Cap.elementAt(i).add(p1Cap.elementAt(j).elementAt(k));
                            }
                            p1Cap.elementAt(j).removeElementAt(1);
                            i++;
                            j++;
                        }

                }
            }
        }

        for (int i = 0; i < cpuCap.size(); i++)
        {
            if (cpuCap.elementAt(i).size() < 2)
            {
                cpuCap.removeElementAt(i);
            }
        }
        for (int i = 0; i < p1Cap.size(); i++)
        {
            if (p1Cap.elementAt(i).size() < 2)
            {
                p1Cap.removeElementAt(i);
            }
        }
    }

    /**
     Calculates the round scores of the human and the computer.
     */

    public void calcScores()
    {
        for (int i = 0; i < cpuCap.size(); i++)
        {
            if (cpuCap.elementAt(i).size() == 4)
            {
                cpuroundscore += 1;
            }
        }
        for (int i = 0; i < p1Cap.size(); i++)
        {
            if (p1Cap.elementAt(i).size() == 4)
            {
               p1roundscore += 1;
            }
        }

        p1score += p1roundscore;
        cpuscore += cpuroundscore;
    }

    /**
     Returns the current P1 Round Score.

     @int returns p1roundscore.
     */

    public int getP1RoundScore(){
        return p1roundscore;
    }

    /**
     Returns the current CPU Round Score.

     @int returns capuroundscore.
     */

    public int getCPURoundScore(){
        return cpuroundscore;
    }

    /**
     Sets Layout in the event of loading a game. Ensures no spaces of the Layout contain 0 cards.
     @Vector<Vector<card>> toAdd the Vector to set the layout to.

     */

    public void setLayout2(Vector<Vector<card>> toAdd)
    {
        int i = 0;
        layout.clear();
        for (int j = 0; j < toAdd.size(); j++)
        {
            Vector<card> v = new Vector<card>(4);
            layout.add(v);

            layout.elementAt(j).add(toAdd.elementAt(j).elementAt(i));
            i += 1;


        }
        for (int k = 0; k < layout.size(); k++)
        {
            if (layout.elementAt(k).size() == 0)
            {
                layout.removeElementAt(k);
                k--;
            }
        }
    }

    /**
     Sets the CPU Capture Pile in the event of a loaded game.
     @Vector<Vector<card>> toAdd the Vector to set the CPU Capture Pile to.

     */

    public void setcpucap2(Vector<Vector<card>> toAdd)
    {
        int i = 0;
        for (int j = 0; j < toAdd.size(); j++)
        {


            cpuCap.add(toAdd.elementAt(j));
            i += 1;


        }
    }

    /**
     Sets the P1 Capture Pile in the event of a loaded game.
     @Vector<Vector<card>> toAdd the Vector to set the P1 Capture Pile to.

     */
    public void setp1cap2(Vector<Vector<card>> toAdd)
    {
        int i = 0;
        for (int j = 0; j < toAdd.size(); j++)
        {


            p1Cap.add(toAdd.elementAt(j));
            i += 1;


        }
    }

    /**
     Returns the reccomended index of the player hand card to play. Priority: match 3 -> match 2 for extra point -> match most -> index 0 (if no matches).

     @int returns roundNum, the current round number.
     */

    public int getRecco()
    {
            extrapt = false;
            int counter = 0;
            int max = 0;
            int indextoret = 0;
            //for every card in the player hand
            for(int i = 0; i < p1Hand.size(); i++)
            {
                //check against each space of cards in the layout
                for(int j = 0; j < layout.size(); j++)
                {
                    //increment the counter by the size of the matching stack
                    if (p1Hand.elementAt(i).getValue() == layout.elementAt(j).elementAt(0).getValue())
                    {
                        counter += layout.elementAt(j).size();
                        if (counter > max)
                        {

                            indextoret = i;
                        }
                        if (counter < 3 && max != 3)
                        {
                            for(int k = 0; k < p1Cap.size(); k++)
                            {
                                if (p1Hand.elementAt(i).getValue() == p1Cap.elementAt(k).elementAt(0).getValue() && p1Cap.elementAt(k).size() == 2)
                                {
                                    indextoret = i;
                                    extrapt = true;
                                }
                            }
                        }


                    }

                }
                //we have finished iterating though the layout for this card, so let's check if this is a most matching index
                if (counter > max)
                {
                    max = counter;
                    //cpuIndex = i;
                }


                counter = 0;

            }
            //set the cpu number of most matches to the maximum amount observed here.
            return p1Hand.elementAt(indextoret).getValue();

    }








    }











