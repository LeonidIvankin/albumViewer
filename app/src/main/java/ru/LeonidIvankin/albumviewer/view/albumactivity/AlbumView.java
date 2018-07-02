package ru.LeonidIvankin.albumviewer.view.albumactivity;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(value = AddToEndSingleStrategy.class)
public interface AlbumView extends MvpView {

	void initRecyclerView();

	void updateRecyclerView();

	void showAlbumArtworkUrl100(String artworkUrl100);

	void showCollectionName(String collectionName);

	void showCollectionPrice(String collectionPrice);

	void showPrimaryGenreName(String primaryGenreName);

	void showArtistName(String artistName);


}
