package jaw.minigames.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.greenrobot.eventbus.EventBus;

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
        holder.setFourInARowTiles(position);
    }

    @Override
    public void onBindViewHolder(FourInARowViewHolder viewHolder, int position, List<Object> payloads) {
        if (payloads.isEmpty()) {
            // Perform a full update
            onBindViewHolder(viewHolder, position);
        } else {
            // Perform a partial update
            for (Object payload : payloads) {
                if (payload instanceof FourInARowTile) {
                    bindFourInARowPayload((FourInARowTile) payload);
                }
            }
        }
    }

    public void bindFourInARowPayload(FourInARowTile payload){

    }


    @Override
    public void refreshItems(IFourInARow fourInARow) {
        EventBus.getDefault().post(new RequestFourInARowEvent(this));
    }

    public void updateFourInARow(IFourInARow newFourInARow){
        notifyItemRangeChanged(0, 41, newFourInARow.getTiles());


        /*for (int i = 0; i < 42; i++) {
            new FourInARowViewHolder(fourInARowView).setFourInARowTiles(i);
        }
        final FourInARowDiffCallback diffCallback = new FourInARowDiffCallback(this.fourInARow, newFourInARow);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

        this.fourInARow.getTiles().clear();
        this.fourInARow.getTiles().addAll(newFourInARow.getTiles());
        diffResult.dispatchUpdatesTo(this);
    }

    private class FourInARowDiffCallback extends DiffUtil.Callback {
        private final IFourInARow oldFourInARow;
        private final IFourInARow newFourInARow;

        public FourInARowDiffCallback(IFourInARow oldFourInARow, IFourInARow newFourInARow) {
            this.oldFourInARow = oldFourInARow;
            this.newFourInARow = newFourInARow;
        }

        @Override
        public int getOldListSize() {
            return oldFourInARow.getTiles().size();
        }

        @Override
        public int getNewListSize() {
            return oldFourInARow.getTiles().size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return false;
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            final IFourInARowTile oldTile = oldFourInARow.getTiles().get(oldItemPosition);
            final IFourInARowTile newTile = newFourInARow.getTiles().get(newItemPosition);

            return oldTile.getColor() == (newTile.getColor());
        }*/
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
                    EventBus.getDefault().post(new UpdateFourInARowActivityEvent());
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
    }
}
