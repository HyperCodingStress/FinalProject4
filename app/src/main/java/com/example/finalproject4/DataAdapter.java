package com.example.finalproject4;

import static java.security.AccessController.getContext;

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
        holder.btnBo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.nama.getContext());
                builder.setTitle("Apakah Anda Yakin Untuk Membeli");
                builder.setMessage("Jika Sudah Di Membeli tidak dapat di cancel");
                builder.setPositiveButton("Book Now",new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.out.println("pindah ke testing");
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(holder.nama.getContext(), "Cancelled", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singleitem,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{
        TextView nama,type,jam,rating,harga,rate;
        Button btnBo;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            nama = (TextView) itemView.findViewById(R.id.NamaBus);
            type = (TextView) itemView.findViewById(R.id.BusType);
            jam = (TextView) itemView.findViewById(R.id.BusHours);
            rating = (TextView) itemView.findViewById(R.id.Rating);
            rate = (TextView) itemView.findViewById(R.id.Rate);
            harga = (TextView) itemView.findViewById(R.id.Harga);
            btnBo = itemView.findViewById(R.id.booking);

        }
    }
}