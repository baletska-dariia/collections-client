package onpu.pnit.collectionsclient.adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import onpu.pnit.collectionsclient.R;
import onpu.pnit.collectionsclient.entities.Item;
import onpu.pnit.collectionsclient.ui.ItemAddEditActivity;

public class ItemListAdapter extends ListAdapter<Item, ItemListAdapter.ItemViewHolder> {


    private Context context;
    private OnItemClickListener listener;

    private static final DiffUtil.ItemCallback<Item> DIFF_UTIL_FOR_ITEMS = new DiffUtil.ItemCallback<Item>() {
        @Override
        public boolean areItemsTheSame(@NonNull Item oldItem, @NonNull Item newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Item oldItem, @NonNull Item newItem) {
            return oldItem.toString().equals(newItem.toString());
        }
    };

    public ItemListAdapter(Context context) {
        super(DIFF_UTIL_FOR_ITEMS);
        this.context = context;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_card, parent, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Item currentItem = getItem(position);
        holder.title.setText(currentItem.getTitle());


        Glide.with(context)
                .load(Uri.parse(currentItem.getImage()))
                .into(holder.img);

    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        ImageView img;
        TextView title;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.card_item_title);
            img = itemView.findViewById(R.id.card_item_photo);

            itemView.setOnClickListener(v -> {
                if (listener != null && getAdapterPosition() != RecyclerView.NO_POSITION) {
                    listener.onItemClick(getItem(getAdapterPosition()).getId(), getAdapterPosition());
                }
            });
        }
    }


    public interface OnItemClickListener {
        void onItemClick(int ItemId, int position);
    }

    public void SetOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public Item GetItemFrom(int position) {
        return getItem(position);
    }
}
