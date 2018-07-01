package ru.LeonidIvankin.albumviewer.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.ArrayList;
import java.util.List;

import ru.LeonidIvankin.albumviewer.app.Constant;
import ru.LeonidIvankin.albumviewer.model.cache.ICache;
import ru.LeonidIvankin.albumviewer.model.entity.Track;
import ru.LeonidIvankin.albumviewer.model.entity.TrackList;
import ru.LeonidIvankin.albumviewer.model.repo.AlbumRepo;
import ru.LeonidIvankin.albumviewer.view.albumactivity.AlbumView;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import ru.LeonidIvankin.albumviewer.view.albumactivity.IListPresenterAlbum;
import ru.LeonidIvankin.albumviewer.view.albumactivity.ListTrackView;
import timber.log.Timber;

@InjectViewState
public class AlbumPresenter extends MvpPresenter<AlbumView> {
	private Scheduler mainThreadScheduler;
	private ListPresenterTracks listPresenterTracks = new ListPresenterTracks();

	@Inject ICache cache;
	@Inject AlbumRepo albumRepo;

	public AlbumPresenter(Scheduler mainThreadScheduler) {
		this.mainThreadScheduler = mainThreadScheduler;
	}

	private class ListPresenterTracks implements IListPresenterAlbum{

		private List<Track> listTrackName = new ArrayList<>();


		@Override
		public void bindView(ListTrackView holder) {
			holder.setTrackName(listTrackName.get(holder.getPos()).getTrackName());
		}

		@Override
		public int getTrackCount() {
			return listTrackName.size();
		}

	}

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();

		getViewState().initRecyclerView();
	}

	public void loadAlbum(int position) {

		Disposable disposable = cache.getAlbum()
				.observeOn(mainThreadScheduler)
				.subscribe(album -> {
					getViewState().showAlbum(album.getResults().get(position).getArtworkUrl100());
					getViewState().showCollectionName(album.getResults().get(position).getCollectionName());

					albumRepo
							.getTracks(album.getResults().get(position).getCollectionId())
							.observeOn(mainThreadScheduler)
							.subscribe(trackList -> {
								this.listPresenterTracks.listTrackName.clear();
								for(Track track : trackList.getResults()) {
								    Timber.d(track.getTrackName());
								}

								this.listPresenterTracks.listTrackName.addAll(trackList.getResults());
								getViewState().updateRecyclerView();
							}, throwable -> {
								Timber.e(throwable, Constant.FAILED_TO_GET_TRACKS);
							});

				}, throwable -> {
					Timber.e(throwable);
				});


	}

	public ListPresenterTracks getListPresenterTracks() {
		return listPresenterTracks;
	}


}
