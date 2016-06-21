package fr.esgi.retrofit;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nico on 21/06/16.
 */

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    List<Repo> repoList;

    public MyAdapter() {
        repoList = new ArrayList<>();
    }

    public void addObjects(List<Repo> repos){
        repoList.addAll(repos);
        notifyDataSetChanged();
    }

    final static int TYPE_HEADER = 1;
    final static int TYPE_CELL1 = 2;
    final static int TYPE_CELL2 = 3;

    @Override
    public int getItemViewType(int position) {
        if(position == 0){
            return TYPE_HEADER;
        }
        else if(position % 2 == 0) {
            return TYPE_CELL1;
        }
        else {
            return TYPE_CELL2;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View cell = null;
        switch (viewType){
            case TYPE_HEADER:
                cell = LayoutInflater.from(parent.getContext()).inflate(R.layout.header_repos, parent, false);
                return new HeaderViewHolder(cell);
            case TYPE_CELL1:
                cell = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell1, parent, false);
                return new MyViewHolder(cell);
            case TYPE_CELL2:
                cell = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell2, parent, false);
                return new MyViewHolder(cell);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof MyViewHolder) {
            Repo repo = repoList.get(position);
            ((MyViewHolder)holder).bind(repo);
        } else if (holder instanceof HeaderViewHolder){
            ((HeaderViewHolder)holder).bind("Projets");
        }
    }

    @Override
    public int getItemCount() {
        return repoList.size();
    }
}
