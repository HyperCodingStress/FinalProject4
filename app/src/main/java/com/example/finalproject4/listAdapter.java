package com.example.finalproject4;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class listAdapter extends FirebaseRecyclerAdapter<DataModel,listAdapter.myViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public listAdapter(@NonNull FirebaseRecyclerOptions<DataModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull listAdapter.myViewHolder holder, int position, @NonNull DataModel model) {
        holder.nama.setText(model.getNama());
        holder.namaTerminal.setText(model.getNamaAwal());
        holder.nomor.setText(model.getNomorBo());
        holder.tanggal.setText(model. getTanggalAwal());
    }

    @NonNull
    @Override
    public listAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singleorder,parent,false);
        return new myViewHolder(view);
    }
    class myViewHolder extends RecyclerView.ViewHolder{
        TextView nama,namaTerminal,tanggal,nomor;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.namabus);
            namaTerminal = itemView.findViewById(R.id.terminal);
            tanggal = itemView.findViewById(R.id.Tanggal);
            nomor = itemView.findViewById(R.id.nomorBO);
        }
    }

}
