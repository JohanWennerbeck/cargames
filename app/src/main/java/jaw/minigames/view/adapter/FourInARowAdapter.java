package jaw.minigames.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.greenrobot.eventbus.EventBus;

import jaw.minigames.R;
import jaw.minigames.eventbus.TileTappedEvent;
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
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View fourInARowView;
        FourInARowViewHolder fourInARowViewHolder;

        fourInARowView = inflater.inflate(R.layout.tile_res_simple, parent, false);
        fourInARowViewHolder = new FourInARowViewHolder(fourInARowView);
        return fourInARowViewHolder;
    }

    public void onBindViewHolder(FourInARowViewHolder holder, int position) {
        holder.fourInARowTile = fourInARow.getTiles().get(position);
        holder.i = position;
        holder.setFourInARowTiles(position);
    }

    @Override
    public void refreshItems() {

    }

    public static class FourInARowViewHolder extends RecyclerView.ViewHolder {
        public IFourInARowTile fourInARowTile;
        public int i;
        public Button button;

        public FourInARowViewHolder(final View itemView) {
            super(itemView);
            button = (Button) itemView.findViewById(R.id.button);

            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view){
                    EventBus.getDefault().post(new TileTappedEvent(i));
                   // EventBus.getDefault().post(new UpdateActivityEvent());
                    /*for(int i = 0; i < 42; i++) {
                        setFourInARowTiles(i);
                    }*/
                }
            });
        }

        public void setFourInARowTiles(int i){
            if(fourInARowTile.getColor()== FourInARowTile.RED){
                button.setText("RED");
            } else if (fourInARowTile.getColor()== FourInARowTile.BLUE) {
                button.setText("BLUE");
            } else {
                button.setText("BLANK");
            }
        }

        private String[] tileText = {"Blank","Blank","Blank","Blank","Blank","Blank","Blank","Blank",
                "Blank","Blank","Blank","Blank","Blank","Blank","Blank","Blank",
                "Blank","Blank","Blank","Blank","Blank","Blank","Blank","Blank",
                "Blank","Blank","Blank","Blank","Blank","Blank","Blank","Blank",
                "Blank","Blank","Blank","Blank","Blank","Blank","Blank","Blank",
                "Blank", "Blank"};
    }
}
