package ru.LeonidIvankin.albumviewer.model.repo;

import java.util.Collections;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import ru.LeonidIvankin.albumviewer.app.Constant;
import ru.LeonidIvankin.albumviewer.app.NetworkStatus;
import ru.LeonidIvankin.albumviewer.model.api.ApiService;
import ru.LeonidIvankin.albumviewer.model.cache.ICache;
import ru.LeonidIvankin.albumviewer.model.entity.AlbumList;
import ru.LeonidIvankin.albumviewer.model.entity.TrackList;

public class AlbumRepo {


	private ICache cache;
	private ApiService api;

	public AlbumRepo(ICache cache, ApiService api) {
		this.cache = cache;
		this.api = api;
	}

	public Observable<AlbumList> getAlbum(String request) {
		if (NetworkStatus.isOnline()) {
			//если онлайн, получаем из сети
			return api
					.getAlbum(Constant.ENTITY, Constant.COUNTRY, request)
					.subscribeOn(Schedulers.io())
					.map(albumList -> {
						//сортировка альбомов
						Collections.sort(albumList.getResults(), (a, b) -> a.getCollectionName().compareTo(b.getCollectionName()));
						//записываем в кеш
						cache.putAlbum(albumList);
						return albumList;
					});
		} else {
			//если офлайн, из кеша
			return cache.getAlbum();
		}

	}

	public Observable<TrackList> getTracks(String id) {
		return api.getTracks(id, Constant.ENTITY_SONG).subscribeOn(Schedulers.io());
	}
}
