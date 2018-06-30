package ru.LeonidIvankin.albumviewer.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import ru.LeonidIvankin.albumviewer.model.entity.Album;
import ru.LeonidIvankin.albumviewer.model.repo.AlbumRepo;
import ru.LeonidIvankin.albumviewer.view.mainactivity.IListPresenter;
import ru.LeonidIvankin.albumviewer.view.mainactivity.ListAlbumView;
import ru.LeonidIvankin.albumviewer.view.mainactivity.MainView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

import ru.LeonidIvankin.albumviewer.app.Constant;


@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {
	private Scheduler mainThreadScheduler;
	@Inject AlbumRepo albumRepo;
	private ListPresenter listPresenter = new ListPresenter();

	public MainPresenter(Scheduler mainThreadScheduler) {
		this.mainThreadScheduler = mainThreadScheduler;
	}

	private class ListPresenter implements IListPresenter {

		private List<Album> results = new ArrayList();

		//сетим url и tag для каждой картинки
		@Override
		public void bindView(ListAlbumView holder) {
			holder.setArtworkUrl100(results.get(holder.getPos()).getArtworkUrl100());

			String tag = maxLength(results.get(holder.getPos()).getCollectionName());

			holder.setCollectionName(tag);
		}

		public String maxLength(String str) {
			if (str.length() > Constant.MAX_LENGTH_COLLECTION_NAME) {
				return str.substring(0, Constant.MAX_LENGTH_COLLECTION_NAME) + "...";
			}
			return str;
		}


		@Override
		public int getAlbumCount() {
			return results.size();
		}

	}

	@Override
	protected void onFirstViewAttach() {
		super.onFirstViewAttach();
		getViewState().initRecyclerView();

		loadAlbum();
	}

	public void enterRequest(String request) {
		loadAlbum(request);
	}

	private void loadAlbum(String request) {
		Disposable disposable = albumRepo.getAlbum(request)
				.observeOn(mainThreadScheduler)
				.subscribe(album -> {
					//возвращаем объект с распарсеным json
					Timber.d("result in " + Thread.currentThread().getName());
					//передаём все url
					this.listPresenter.results.clear();
					this.listPresenter.results.addAll(album.getResults());
					getViewState().updateRecyclerView();
					getViewState().hideLoading();
				}, throwable -> {
					Timber.e(throwable);
					getViewState().hideLoading();

				});
	}

	private void loadAlbum() {
		loadAlbum("charts");
	}


	public ListPresenter getListPresenter() {
		return listPresenter;
	}


}
