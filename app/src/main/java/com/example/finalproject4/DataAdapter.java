package com.example.finalproject4;

import static java.security.AccessController.getContext;
import static java.util.Collections.replaceAll;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class DataAdapter  extends FirebaseRecyclerAdapter<DataModel,DataAdapter.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */

    public String DataNama,DataType,DataRating,DataEstimateAwal,DataEstimateAkhir,DataNamaAkhir,DataNamaAwal;
    public Integer DataHarga,DataRate,DataHours;

    private OnRecyclerViewClickListener listener;

    public interface OnRecyclerViewClickListener {
        void onClick(int position);
    }

    public void OnRecyclerViewClickListener (OnRecyclerViewClickListener listener){
        this.listener = listener;
    }

    public DataAdapter(@NonNull FirebaseRecyclerOptions<DataModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull DataModel model) {
        holder.nama.setText(model.getNama());
        holder.type.setText(model.getType());
        holder.jam.setText(model.getHours() + " Hours");
        holder.rating.setText(model.getRating());
        holder.rate.setText(model.getRate() + " Rating");
        holder.harga.setText("Rp"+model.getHarga());
        holder.estimateAwal.setText(model.getJamAwal());
        holder.estimateAkhir.setText(model.getJamAkhir());
        holder.namaAwal.setText(model.getNamaAwal());
        holder.namaAkhir.setText(model.getNamaAkhir());
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singleitem,parent,false);
        return new myViewHolder(view,listener);
    }

    class myViewHolder extends RecyclerView.ViewHolder{
        TextView nama,type,jam,rating,harga,rate,estimateAwal,estimateAkhir,namaAwal,namaAkhir;
        Button btnBo;
        public myViewHolder(@NonNull View itemView, OnRecyclerViewClickListener listener) {
            super(itemView);
            nama = (TextView) itemView.findViewById(R.id.NamaBus);
            type = (TextView) itemView.findViewById(R.id.BusType);
            jam = (TextView) itemView.findViewById(R.id.BusHours);
            rating = (TextView) itemView.findViewById(R.id.Rating);
            rate = (TextView) itemView.findViewById(R.id.Rate);
            harga = (TextView) itemView.findViewById(R.id.Harga);
            estimateAwal = (TextView) itemView.findViewById(R.id.BusEstimateAwal);
            estimateAkhir = (TextView) itemView.findViewById(R.id.BusEstimateAkhir);
            namaAwal = (TextView) itemView.findViewById(R.id.namaAwal);
            namaAkhir = (TextView) itemView.findViewById(R.id.namaAkhir);
            btnBo = (Button) itemView.findViewById(R.id.booking);

            btnBo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null && getAbsoluteAdapterPosition()!= RecyclerView.NO_POSITION){
                        listener.onClick(getAbsoluteAdapterPosition());
                        DataNama = nama.getText().toString().trim();
                        DataType = type.getText().toString().trim();
                        DataHarga = Integer.valueOf(harga.getText().toString().trim().replaceAll("[^0-9]+", ""));
                        DataRating = rating.getText().toString().trim();
                        DataEstimateAwal = estimateAwal.getText().toString().trim();
                        DataEstimateAkhir = estimateAkhir.getText().toString().trim();
                        DataNamaAwal = namaAwal.getText().toString().trim();
                        DataNamaAkhir = namaAkhir.getText().toString().trim();
                        DataRate = Integer.valueOf(rate.getText().toString().trim().replaceAll("[^0-9]+", ""));
                        DataHours = Integer.valueOf(jam.getText().toString().trim().replaceAll("[^0-9]+", ""));
                    }
                }
            });
        }
    }
}