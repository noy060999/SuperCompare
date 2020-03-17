package com.example.supercompare.ViewHolder;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.example.supercompare.Interface.ItemClickListener;
import com.example.supercompare.Model.Comparison;
import com.example.supercompare.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public TextView txtCartName;
    public ImageView imgCartCount;
    private ItemClickListener itemClickListener;

    public void setTxtCartName(TextView txtCartName) {
        this.txtCartName = txtCartName;
    }

    public CartViewHolder(@NonNull View itemView) {
        super(itemView);
        txtCartName = itemView.findViewById(R.id.cart_item_name);
        imgCartCount = itemView.findViewById(R.id.cart_item_count);
    }

    @Override
    public void onClick(View v) {

    }
}
public class CartAdapter extends RecyclerView.Adapter<CartViewHolder>{

    private List<Comparison> listData = new ArrayList<>();
    private Context context;
    private String supermarketName="";


    public CartAdapter(List<Comparison> listData, Context context) {
        this.listData = listData;
        this.context = context;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View itemView = layoutInflater.inflate(R.layout.cart_layout,parent,false);

        return new CartViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        TextDrawable drawable = TextDrawable.builder()
                .buildRound(""+listData.get(position).getQuantity(), Color.RED);
        holder.imgCartCount.setImageDrawable(drawable);

        Locale locale = new Locale("he","IL");
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);

        double priceRamiLevi, priceShufersal, priceYinotBitan, priceMega, priceVictory;
        priceRamiLevi = (Double.parseDouble(listData.get(position).getPriceRamiLevi()))*(Double.parseDouble(listData.get(position).getQuantity()));
        priceShufersal = (Double.parseDouble(listData.get(position).getPriceShufersal()))*(Double.parseDouble(listData.get(position).getQuantity()));
        priceVictory = (Double.parseDouble(listData.get(position).getPriceVictory()))*(Double.parseDouble(listData.get(position).getQuantity()));
        priceMega = (Double.parseDouble(listData.get(position).getPriceMega()))*(Double.parseDouble(listData.get(position).getQuantity()));
        priceYinotBitan = (Double.parseDouble(listData.get(position).getPriceYinotBitan()))*(Double.parseDouble(listData.get(position).getQuantity()));
        //holder.txtPrice.setText(numberFormat.format(//lowest price from the super that has been chosen));
        /*if (supermarketName == "Rami Levi")
            holder.txtPrice.setText(numberFormat.format(priceRamiLevi));
        if (supermarketName == "Shufersal")
            holder.txtPrice.setText(numberFormat.format(priceShufersal));
        if (supermarketName == "Victory")
            holder.txtPrice.setText(numberFormat.format(priceVictory));
        if (supermarketName == "Mega")
            holder.txtPrice.setText(numberFormat.format(priceMega));
        if (supermarketName == "Yinot Bitan")
            holder.txtPrice.setText(numberFormat.format(priceYinotBitan));*/
        holder.txtCartName.setText(listData.get(position).getProductName());
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
}
