package ru.LeonidIvankin.albumviewer.view.albumactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import ru.LeonidIvankin.albumviewer.R;
import ru.LeonidIvankin.albumviewer.app.App;
import ru.LeonidIvankin.albumviewer.app.Constant;
import ru.LeonidIvankin.albumviewer.model.image.IImageLoader;
import ru.LeonidIvankin.albumviewer.presenter.AlbumPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class AlbumActivity extends MvpAppCompatActivity implements AlbumView {

	@BindView(R.id.text_view_album_collection_name) TextView textViewAlbumCollectionName;
	@BindView(R.id.text_view_album_artist_name) TextView textViewAlbumArtistName;
	@BindView(R.id.text_view_album_collection_price) TextView textViewAlbumCollectionPrice;
	@BindView(R.id.text_view_album_primary_genre_name) TextView textViewAlbumPrimaryGenreName;
	@BindView(R.id.image_view_album_artwork_url_100) ImageView imageViewAlbumArtworkUrl100;
	@BindView(R.id.recycler_view_album) RecyclerView recyclerView;

	private RecyclerViewAdapterAlbum adapter;

	@Inject
	IImageLoader<ImageView> imageLoader;

	@InjectPresenter AlbumPresenter albumPresenter;

	@ProvidePresenter
	public AlbumPresenter createPresenter() {
		AlbumPresenter presenter = new AlbumPresenter(AndroidSchedulers.mainThread());
		App.getInstance().getAppComponent().inject(presenter);

		return presenter;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_album);

		ButterKnife.bind(this);
		App.getInstance().getAppComponent().inject(this);


		Intent intent = getIntent();
		int position = intent.getIntExtra(Constant.SEND_INTENT_FROM_MAINACTIVITY_TO_ALBUMACTIVITY, 0);
		albumPresenter.loadAlbum(position);
	}


	@Override
	public void initRecyclerView() {
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		adapter = new RecyclerViewAdapterAlbum(albumPresenter.getListPresenterTracks());
		recyclerView.setAdapter(adapter);
	}

	@Override
	public void updateRecyclerView() {
		adapter.notifyDataSetChanged();
	}

	@Override
	public void showAlbumArtworkUrl100(String artworkUrl100) {
		imageLoader.loadInto(artworkUrl100, imageViewAlbumArtworkUrl100);
	}

	@Override
	public void showCollectionName(String collectionName) {
		textViewAlbumCollectionName.setText(collectionName);
	}

	@Override
	public void showCollectionPrice(String collectionPrice) {
		textViewAlbumCollectionPrice.setText(collectionPrice);
	}

	@Override
	public void showPrimaryGenreName(String primaryGenreName) {
		textViewAlbumPrimaryGenreName.setText(primaryGenreName);
	}

	@Override
	public void showArtistName(String artistName) {
		textViewAlbumArtistName.setText(artistName);
	}
}
