package ru.LeonidIvankin.albumviewer.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import ru.LeonidIvankin.albumviewer.model.cache.ICache;
import ru.LeonidIvankin.albumviewer.view.photoactivity.AlbumView;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

@InjectViewState
public class AlbumPresenter extends MvpPresenter<AlbumView> {
	private Scheduler mainThreadScheduler;

	@Inject ICache cache;

	public AlbumPresenter(Scheduler mainThreadScheduler) {
		this.mainThreadScheduler = mainThreadScheduler;
	}

	public void loadAlbum(int position) {

		Disposable disposable = cache.getAlbum()
				.observeOn(mainThreadScheduler)
				.subscribe(album -> {
					//getViewState().showAlbum(photos.getHits().get(position).getWebformatURL());
					getViewState().showAlbum(album.getResults().get(position).getArtworkUrl100());
					//getViewState().showCollectionName(photos.getHits().get(position).getTags());
					getViewState().showCollectionName(album.getResults().get(position).getCollectionName());
				}, throwable -> {
					Timber.e(throwable);
				});


	}
}
