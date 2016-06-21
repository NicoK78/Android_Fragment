package fr.esgi.retrofit;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Nico on 21/06/2016.
 */
public class HeaderViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.headerText) TextView textView;

    public HeaderViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(String text){
        textView.setText(text);

    }
}
