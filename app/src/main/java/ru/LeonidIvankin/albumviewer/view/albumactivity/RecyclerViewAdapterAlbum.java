package ru.LeonidIvankin.albumviewer.view.albumactivity;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.LeonidIvankin.albumviewer.R;

public class RecyclerViewAdapterAlbum extends RecyclerView.Adapter<RecyclerViewAdapterAlbum.RecyclerViewHolderAlbum> {

	private IListPresenterAlbum presenterAlbum;

	public RecyclerViewAdapterAlbum(IListPresenterAlbum presenterAlbum) {
		this.presenterAlbum = presenterAlbum;
	}

	@NonNull
	@Override
	public RecyclerViewHolderAlbum onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		return new RecyclerViewHolderAlbum(LayoutInflater.from(parent.getContext())
				.inflate(R.layout.item_recycler_view_album, parent, false));
	}

	@Override
	public void onBindViewHolder(@NonNull RecyclerViewHolderAlbum holder, int position) {
		holder.position = position;
		presenterAlbum.bindView(holder);
	}

	@Override
	public int getItemCount() {
		return presenterAlbum.getTrackCount();
	}


	class RecyclerViewHolderAlbum extends RecyclerView.ViewHolder implements ListTrackView {

		@BindView(R.id.text_view_item_track_name) TextView textViewItemTrackName;

		int position = 0;


		public RecyclerViewHolderAlbum(View itemView) {
			super(itemView);
			ButterKnife.bind(this, itemView);
		}

		@Override
		public void setTrackName(String trackName) {
			textViewItemTrackName.setText(trackName);
		}

		@Override
		public int getPos() {
			return position;
		}
	}
}
