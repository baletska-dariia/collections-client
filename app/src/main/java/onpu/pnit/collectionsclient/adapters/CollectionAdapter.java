package onpu.pnit.collectionsclient.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import onpu.pnit.collectionsclient.R;
import onpu.pnit.collectionsclient.entities.Collection;

public class CollectionAdapter extends ListAdapter<Collection, CollectionAdapter.CollectionViewHolder>{

    private static final DiffUtil.ItemCallback<Collection> DIFF_CALLBACK = new DiffUtil.ItemCallback<Collection>() {
        @Override
        public boolean areItemsTheSame(@NonNull Collection oldItem, @NonNull Collection newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Collection oldItem, @NonNull Collection newItem) {
            return oldItem.toString().contentEquals(newItem.toString());
        }
    };

    public CollectionAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public CollectionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.collection_card, parent, false);
        return new CollectionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CollectionViewHolder holder, int position) {
        Collection currentCollection = getItem(position);
        holder.title.setText(currentCollection.getTitle());
        holder.description.setText(currentCollection.getDescription());
    }

    public class CollectionViewHolder extends RecyclerView.ViewHolder {
        //TODO: Добавить отображение фото, возможно по URL
        ImageView photo;
        TextView title;
        TextView description;

        public CollectionViewHolder(View itemView){
            super(itemView);
            title = itemView.findViewById(R.id.collection_title);
            description = itemView.findViewById(R.id.collection_description);
            photo = itemView.findViewById(R.id.collection_photo);
        }
    }
}