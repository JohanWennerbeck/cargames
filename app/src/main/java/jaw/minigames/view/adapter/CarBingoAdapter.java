package jaw.minigames.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.greenrobot.eventbus.EventBus;

import jaw.minigames.R;
import jaw.minigames.eventbus.TileCheckedEvent;
import jaw.minigames.model.minigamemodule.carbingogame.ICarBingo;
import jaw.minigames.model.minigamemodule.carbingogame.ICarBingoTile;

/**
 * Created by johan on 7/4/2017.
 */
    public class CarBingoAdapter extends RecyclerView.Adapter<CarBingoAdapter.CarBingoViewHolder> implements ICarBingoAdapter {
        private ICarBingo carBingo;


        public CarBingoAdapter(ICarBingo carBingo) {
            this.carBingo = carBingo;
        }

        public int getItemCount() {
            return this.carBingo.getCarBingoTiles().size();
        }

        // create a new ImageView for each item referenced by the Adapter
        public CarBingoViewHolder onCreateViewHolder(ViewGroup parent, int position) {
            System.out.println("CarAdapter");
            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);
            View carBingoView;
            CarBingoViewHolder carBingoViewHolder;

            carBingoView = inflater.inflate(R.layout.tile_res_simple, parent, false);
            carBingoViewHolder = new CarBingoViewHolder(carBingoView);
            return carBingoViewHolder;
        }

        public void onBindViewHolder(CarBingoViewHolder holder, int position) {
            holder.carBingoTile = carBingo.getCarBingoTiles().get(position);
            holder.i = position;
            holder.setCarBingoTiles();
        }

    @Override
    public void refreshItems() {

    }

    public static class CarBingoViewHolder extends RecyclerView.ViewHolder {
            public ICarBingoTile carBingoTile;
            //public ImageView tileImage;
            public int i;
            public Button button;

            public CarBingoViewHolder(final View itemView) {
                super(itemView);
                button = (Button) itemView.findViewById(R.id.button);

                button.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view){
                        EventBus.getDefault().post(new TileCheckedEvent(i));
                        if(carBingoTile.getChecked()){
                            button.setText("Check");
                        } else {
                            button.setText(tileText[carBingoTile.getType()]);
                        }
                    }
                });
                //tileImage = (ImageView) itemView.findViewById(R.id.imageButton);
            }

            public void setCarBingoTiles(){
                //tileImage.setImageResource(mThumbIds[carBingoTile.getType()]);
                //button.setText("Cow");
                button.setText(tileText[carBingoTile.getType()]);

                if(this.carBingoTile.getChecked()){
                    //tileImage.setImageResource(R.drawable.cross);
                }
            }


            private String[] tileText = {
                    "Ambulance",
                    "Airplane",
                    "Bike",
                    "Boat",
                    "Church",
                    "Cow",
                    "Flag",
                    "Helicopter",
                    "Horse",
                    "McDonalds",
                    "Police Car",
                    "Sheep",
                    "Tractor",
                    "Train",
                    "Windmiller",
                    "Wind turbine",
            };

            private Integer[] mThumbIds = {
                    R.drawable.ambulance,
                    R.drawable.airplane,
                    R.drawable.bike,
                    R.drawable.boat,
                    R.drawable.church,
                    R.drawable.cow,
                    R.drawable.flag,
                    R.drawable.helicopter,
                    R.drawable.horse,
                    R.drawable.mcdonalds,
                    R.drawable.police,
                    R.drawable.sheep,
                    R.drawable.tractor,
                    R.drawable.train,
                    R.drawable.windmiller,
                    R.drawable.windturbine,
            };
        }
    }
