package com.example.eliza.appnewimg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //0=Rosu; 1=Verde

    int Player = 0;

    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2,2 };

    int[][] WinningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
      boolean GameActive= true;

    public void DropIn(View view) {


        ImageView Contor = (ImageView) view;

        int TappedContor = Integer.parseInt(Contor.getTag().toString());

        if (gameState[TappedContor] == 2 && GameActive) {

            gameState[TappedContor] = Player;

            System.out.println("Vector=" + gameState[TappedContor]);

            if (Player == 0) {
                Contor.setImageResource(R.drawable.o);
                Contor.setTranslationY(-1000f);
                Contor.animate().translationYBy(1000f).setDuration(1000);
                Contor.animate().rotationBy(360).setDuration(300);
                Player = 1;
            } else if (Player == 1) {
                Contor.setImageResource(R.drawable.x);
                Contor.setTranslationY(-1000f);
                Contor.animate().translationYBy(1000f).setDuration(1000);
                Contor.animate().rotationBy(360).setDuration(300);
                Player = 0;
            }
        }

        for (int[] WinningPosition : WinningPositions) {
            if ((gameState[WinningPosition[0]] == gameState[WinningPosition[1]]) &&
                    (gameState[WinningPosition[1]] == gameState[WinningPosition[2]])
                    && (gameState[WinningPosition[0]] != 2)) {
                GameActive=false;
                String WinnerName = "red";
                if (gameState[WinningPosition[0]] == 1) {
                    WinnerName = "green";
                }

                TextView Messaj = (TextView) findViewById(R.id.WinnerMessage);
                Messaj.setText("Winner is  " + WinnerName);

                LinearLayout Message = (LinearLayout) (findViewById(R.id.LinearLayoutMessage));

                Message.setVisibility(View.VISIBLE);

            }
              else {
                boolean GameOver = true;

                for (int ContorState : gameState) {
                    if (ContorState == 2)
                        GameOver = false;
                }


                if (GameOver==true)
                {
                    TextView Messaj = (TextView) findViewById(R.id.WinnerMessage);
                    Messaj.setText("Its a Draw!" );

                    LinearLayout Message = (LinearLayout) (findViewById(R.id.LinearLayoutMessage));

                    Message.setVisibility(View.VISIBLE);
                }

            }
        }
    }


      public void PlayAgain(View view)
      {
          GameActive=true;
          LinearLayout Message = (LinearLayout) (findViewById(R.id.LinearLayoutMessage));

          Message.setVisibility(View.INVISIBLE);

              for(int i=0;i<gameState.length;i++)
              {
                       gameState[i]=2;
              }

          GridLayout grd2 =(GridLayout) findViewById(R.id.gridLayout2);

                 for(int i=0;i<grd2.getChildCount();i++)
                 {
                     ((ImageView)  grd2.getChildAt(i)).setImageResource(0);
                 }
      }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
}
