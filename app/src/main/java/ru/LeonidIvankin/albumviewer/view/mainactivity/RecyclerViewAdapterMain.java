package ru.LeonidIvankin.albumviewer.view.mainactivity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import ru.LeonidIvankin.albumviewer.R;
import ru.LeonidIvankin.albumviewer.app.App;
import ru.LeonidIvankin.albumviewer.model.image.IImageLoader;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewAdapterMain extends RecyclerView.Adapter<RecyclerViewAdapterMain.RecyclerViewHolder> {

	private IListPresenter presenter;
	@Inject
	IImageLoader<ImageView> imageLoader;
	IClickAlbum iClickAlbum;

	public RecyclerViewAdapterMain(IListPresenter presenter, Context context) {
		this.presenter = presenter;
		iClickAlbum = (IClickAlbum) context;
		//inject для Dagger
		App.getInstance().getAppComponent().inject(this);
	}

	@NonNull
	@Override
	public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		return new RecyclerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view_main, parent, false));
	}

	@Override
	public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
		holder.pos = position;
		presenter.bindView(holder);
		holder.setListener(v -> {
			iClickAlbum.clickAlbum(position);
		});
	}

	@Override
	public int getItemCount() {
		return presenter.getAlbumCount();
	}

	class RecyclerViewHolder extends RecyclerView.ViewHolder implements ListAlbumView {

		@BindView(R.id.text_view_item_collection_name) TextView textViewItemCollectionName;
		@BindView(R.id.image_view_item_recycler_view) ImageView imageViewItemRecyclerView;
		int pos = 0;

		public RecyclerViewHolder(View itemView) {
			super(itemView);
			ButterKnife.bind(this, itemView);

		}

		//загружаем фото
		@Override
		public void setArtworkUrl100(String artworkUrl100) {
			imageLoader.loadInto(artworkUrl100, imageViewItemRecyclerView);
		}

		//показываем имя альбома
		@Override
		public void setCollectionName(String collectionName) {
			textViewItemCollectionName.setText(collectionName);
		}

		@Override
		public int getPos() {
			return pos;
		}

		public void setListener(View.OnClickListener listener) {
			itemView.setOnClickListener(listener);
		}

	}
}
