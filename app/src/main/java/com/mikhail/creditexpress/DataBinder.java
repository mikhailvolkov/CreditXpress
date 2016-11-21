package com.mikhail.creditexpress;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;

/**
 * @author Volkov Mikhail
 */
public class DataBinder extends BaseAdapter {

    static class ViewHolder{
        ImageView creditIcon;
        TextView summOfMortgage;
        TextView timeOfMortgage;
    }
    private int size = 0;
    LayoutInflater inflater;
    List<CreditInfo> creditDataCollection;
    ViewHolder holder;
    private Activity act;

    public DataBinder(Activity act, List<CreditInfo> map) {
        this.act = act;
        this.creditDataCollection = map;
        inflater = (LayoutInflater) act.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }



    @Override
    public void notifyDataSetChanged() {
        size = creditDataCollection.size();

        super.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return size;
    }

    @Override
    public CreditInfo getItem(int position) {
        try {
            return creditDataCollection.get(position);
        } catch (Error e) {
            return null;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public View getView(int position, View convertView, ViewGroup parent) {

        View vi=convertView;
        if(convertView==null){
            vi = inflater.inflate(R.layout.list_row, null);
            holder = new ViewHolder();

            holder.timeOfMortgage=(TextView)vi.findViewById(R.id.timeOfMortgageValue);//срок займа
            holder.summOfMortgage=(TextView)vi.findViewById(R.id.summOfMortgageValue);//сумма займа
            holder.creditIcon=(ImageView)vi.findViewById(R.id.list_image);//пикча кредита

            vi.setTag(holder);
        }
        else{
            holder = (ViewHolder)vi.getTag();
        }

        // Setting all values in listview
        holder.timeOfMortgage.setText(creditDataCollection.get(position).getCreditTime());
        holder.summOfMortgage.setText(creditDataCollection.get(position).getCreditSumm());

        Picasso.with(act)
                .load(creditDataCollection.get(position).getImageLink())
                .into(holder.creditIcon);
        return vi;
    }

}
