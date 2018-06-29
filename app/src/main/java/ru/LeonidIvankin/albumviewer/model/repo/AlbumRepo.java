package ru.LeonidIvankin.albumviewer.model.repo;

import ru.LeonidIvankin.albumviewer.app.Constant;
import ru.LeonidIvankin.albumviewer.app.NetworkStatus;
import ru.LeonidIvankin.albumviewer.model.api.ApiService;
import ru.LeonidIvankin.albumviewer.model.cache.ICache;
import ru.LeonidIvankin.albumviewer.model.entity.Album;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class AlbumRepo {

	private ICache cache;
	private ApiService api;

	public AlbumRepo(ICache cache, ApiService api) {
		this.cache = cache;
		this.api = api;
	}

	public Observable<Album> getAlbum(String request) {
		if (NetworkStatus.isOnline()) {
			//если онлайн, получаем из сети
			return api
					.getAlbum(Constant.ENTITY, Constant.COUNTRY, request)
					.subscribeOn(Schedulers.io())
					.map(album -> {
						//записываем в кеш
						//cache.putAlbum(photos);
						return album;
					});
		} else {
			//если офлайн, из кеша
			return null;
			//return cache.getAlbum();
		}

	}
}
