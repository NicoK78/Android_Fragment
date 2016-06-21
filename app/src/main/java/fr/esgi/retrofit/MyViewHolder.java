package fr.esgi.retrofit;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Nico on 21/06/16.
 */

public class MyViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.text) TextView text;

    public MyViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(Repo repo){
        text.setText(repo.getName());
    }
}
