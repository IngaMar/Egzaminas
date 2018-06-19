package com.example.dariusm.egzaminas.View;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dariusm.egzaminas.Model.Klientas;
import com.example.dariusm.egzaminas.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by DariusM on 19/06/2018.
 */

public class KlientasAdapter extends RecycleView.Adapter<RecycleView.View.ViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    private List<Klientas> klientai = Collections.emptyList();
    //private Pokemonas currentPokemon;

    public static final String ENTRY_ID = "com.example.dariusm.egzaminas";

    //konstruktorius reikalingas susieti
    // esama langa ir perduoti sarasa pokemonui is DB
    public KlientasAdapter(Context context, List<Klientas> klientai){
        this.context = context;
        this.klientai = klientai;
        inflater = LayoutInflater.from(context);
    }
/*
    @Override
    //inflate the layout when viewholder is created
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.container_klientas,parent,false);
        MyHolder holder = new MyHolder(view);
        return holder;
    }

    @Override
    //bind data
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //get current position of item in
        // recyclerview to bind data and assign value from list
        MyHolder myHolder = (MyHolder) holder;
        Papuosalas currentPapuosalas = papuosalai.get(position);
        myHolder.tvPavadinimas.setText(           currentPapuosalas.getName());
        myHolder.tvTipas.setText("Tipas: "      + currentPapuosalas.getTipas());
        myHolder.tvMedziagos.setText("Medžiagos: "      + currentPapuosalas.getMedziagos());
        myHolder.tvId.setText("Numeris: "            + currentPapuosalas.getId());
        myHolder.tvDetales.setText("Detalės: "  + currentPapuosalas.getDetales());
    }

    @Override
    public int getItemCount() {
        return papuosalai.size();
    }

    class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tvPavadinimas,tvTipas, tvMedziagos, tvDetales, tvId;

        public MyHolder(View itemView){
            super(itemView);
            tvPavadinimas   = (TextView)itemView.findViewById(R.id.pavadinimas);
            tvTipas         = (TextView)itemView.findViewById(R.id.tipas);
            tvMedziagos = (TextView)itemView.findViewById(R.id.medziagos);
            tvDetales = (TextView)itemView.findViewById(R.id.detales);
            tvId            = (TextView)itemView.findViewById(R.id.id);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            int itemPosition = getAdapterPosition();
            int papuosalaiID = klientai.get(itemPosition).getId();

            // TODO: siųsti pasirinkto pokemono objektą vietoj id
            Klientas klientas = klientai.get(itemPosition);

            Intent intent = new Intent(context, MainActivity.class);

            intent.putExtra(ENTRY_ID, klientaiID);
            context.startActivity(intent);
        }
    }
    */
}
