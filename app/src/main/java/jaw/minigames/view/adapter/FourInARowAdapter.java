package jaw.minigames.view.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import jaw.minigames.R;
import jaw.minigames.eventbus.RequestFourInARowEvent;
import jaw.minigames.eventbus.TileTappedEvent;
import jaw.minigames.eventbus.UpdateFourInARowActivityEvent;
import jaw.minigames.model.minigamemodule.fourinarow.FourInARowTile;
import jaw.minigames.model.minigamemodule.fourinarow.IFourInARow;
import jaw.minigames.model.minigamemodule.fourinarow.IFourInARowTile;

/**
 * Created by johan on 7/19/2017.
 */

public class FourInARowAdapter extends RecyclerView.Adapter<FourInARowAdapter.FourInARowViewHolder> implements IFourInARowAdapter {
    private IFourInARow fourInARow;

    public FourInARowAdapter(IFourInARow fourInARow) {
        this.fourInARow = fourInARow;
    }

    public int getItemCount() {
        return this.fourInARow.getTiles().size();
    }

    public long getItemId(int position) {
        return position;
    }

    // create a new ImageView for each item referenced by the Adapter
    public FourInARowViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        System.out.println("Adapter");
        View fourInARowView;
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        FourInARowViewHolder fourInARowViewHolder;
        fourInARowView = inflater.inflate(R.layout.tile_res_simple, parent, false);
        fourInARowViewHolder = new FourInARowViewHolder(fourInARowView);
        return fourInARowViewHolder;
    }

    public void onBindViewHolder(FourInARowViewHolder holder, int position) {
        holder.fourInARowTile = fourInARow.getTiles().get(position);
        holder.i = position;
        holder.setFourInARowTiles();
    }

    @Override
    public void refreshItems(IFourInARow fourInARow) {
        EventBus.getDefault().post(new RequestFourInARowEvent(this));
    }


    public static class FourInARowViewHolder extends RecyclerView.ViewHolder {
        public IFourInARowTile fourInARowTile;
        public int i;
        public Button button;

        public FourInARowViewHolder(final View itemView) {
            super(itemView);
            button = (Button) itemView.findViewById(R.id.button);
            EventBus.getDefault().register(this);
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view){
                    System.out.println("On click");
                    System.out.println(i);
                    EventBus.getDefault().post(new TileTappedEvent(i));

                    setFourInARowTiles();

                }
            });
        }

        @Subscribe (threadMode = ThreadMode.MAIN)
        public void onTileTappedEvent(TileTappedEvent event){
            setFourInARowTiles();
        }


        public void setFourInARowTiles(){
            if(fourInARowTile.getColor()== FourInARowTile.RED){
                button.setBackgroundColor(Color.RED);
                button.setText("");
            } else if (fourInARowTile.getColor()== FourInARowTile.BLUE) {
                button.setBackgroundColor(Color.BLUE);
                button.setText("");
            } else {
                button.setBackgroundColor(Color.WHITE);
                button.setText("O");
            }
        }
    }
}
