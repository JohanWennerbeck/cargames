package jaw.minigames.view.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import jaw.minigames.R;
import jaw.minigames.eventbus.CarBingoNewGameEvent;
import jaw.minigames.eventbus.FourInARowNewGameEvent;
import jaw.minigames.eventbus.FourInARowVictoryEvent;
import jaw.minigames.eventbus.MemoryNewGameEvent;

public class VictoryFragment extends DialogFragment {
    int game;
    int playerOneScore, playerTwoScore;
    private TextView mActionOk, mActionCancel, mVictoryText;


    public static final int GAME_MEMORY = 1;
    public static final int GAME_FOURINAROW = 2;
    public static final int GAME_CARBINGO = 3;

    public void setGame(int game) {
        this.game = game;
    }
    public void setPlayerOneScore(int score){
        this.playerOneScore = score;
    }
    public void setPlayerTwoScore(int score){
        this.playerTwoScore = score;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.victory_dialog, container, false);

        mActionCancel = (TextView) view.findViewById(R.id.action_main_menu);
        mActionOk = (TextView) view.findViewById(R.id.action_new_game);
        mVictoryText = (TextView) view.findViewById(R.id.victory_text);

        if (game == GAME_MEMORY) {
            if (playerOneScore == playerTwoScore){
                mVictoryText.setText("It's a Tie!");
            } else if (playerOneScore > playerTwoScore) {
                mVictoryText.setText("Player one won");
            } else {
                mVictoryText.setText("Player two won");
            }
        } else if (game == GAME_FOURINAROW){
            if(playerOneScore == 1){
                mVictoryText.setText("Red player won!");
            } else {
                mVictoryText.setText("Blue player won!");
            }
        } else if (game == GAME_CARBINGO){
            mVictoryText.setText("Congratz you got BINGO!");
        }
        mActionCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });
        mActionOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (game == GAME_MEMORY) {
                    EventBus.getDefault().post(new MemoryNewGameEvent());
                } else if (game == GAME_FOURINAROW){
                    EventBus.getDefault().post(new FourInARowNewGameEvent());
                } else if (game == GAME_CARBINGO){
                    EventBus.getDefault().post(new CarBingoNewGameEvent());
                }
                getDialog().dismiss();
            }
        });
        return view;
    }

    /*
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        switch (game){
            case GAME_MEMORY: buildMemoryVictory(builder);
                break;
            case GAME_FOURINAROW: buildFourInARowVictory(builder);
                break;
            case GAME_CARBINGO: buildCarBingoVictory(builder);
                break;
            default: break;
        }
        return builder.create();
    }

    private void buildMemoryVictory(AlertDialog.Builder builder){
        builder.setTitle(R.string.victory_in_game).setPositiveButton("New Game", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                EventBus.getDefault().post(new MemoryNewGameEvent());
            }
        }).setNegativeButton("Main Menu", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
    }
    private void buildFourInARowVictory(AlertDialog.Builder builder){

    }
    private void buildCarBingoVictory(AlertDialog.Builder builder){

    }*/
}
