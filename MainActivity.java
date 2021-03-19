package com.example.gostop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    private static final String EXTRA_MESSAGE = "com.example.gostop.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     Alerts the game activity that a fresh game will be started, starts the gameactivity.
     @View view the current view calling this function.

     */
    public void newGame(View view)
    {
        //start a new game
        Intent intent = new Intent(this, gameActivity.class);
        intent.putExtra("gamestate", "new");
        startActivity(intent);
    }

    /**
     Displays the Buttons that load the various saved files.
     @View view the current view calling this function.

     */
    public void loadGame(View view)
    {
        view = findViewById(R.id.case1b);
        view.setVisibility(view.VISIBLE);
        view = findViewById(R.id.case2b);
        view.setVisibility(view.VISIBLE);
        view = findViewById(R.id.case3b);
        view.setVisibility(view.VISIBLE);
        view = findViewById(R.id.localb);
        view.setVisibility(view.VISIBLE);
    }

    /**
     Alerts the game activity that test case 1 will be started, starts the gameactivity.
     @View view the current view calling this function.

     */

    public void loadcase1(View view)
    {
        //start a new game
        Intent intent = new Intent(this, gameActivity.class);
        intent.putExtra("gamestate", "a1");
        startActivity(intent);
    }

    /**
     Alerts the game activity that test case 2 will be started, starts the gameactivity.
     @View view the current view calling this function.

     */

    public void loadcase2(View view)
    {
        //start a new game
        Intent intent = new Intent(this, gameActivity.class);
        intent.putExtra("gamestate", "a2");
        startActivity(intent);
    }

    /**
     Alerts the game activity that test case 3 will be started, starts the gameactivity.
     @View view the current view calling this function.

     */

    public void loadcase3(View view)
    {
        //start a new game
        Intent intent = new Intent(this, gameActivity.class);
        intent.putExtra("gamestate", "a3");
        startActivity(intent);
    }

    /**
     Alerts the game activity that a local game will be loaded, starts the gameactivity.
     @View view the current view calling this function.

     */

    public void localgame (View view)
    {
        //start a new game
        Intent intent = new Intent(this, gameActivity.class);
        intent.putExtra("gamestate", "local");
        startActivity(intent);
    }

}
