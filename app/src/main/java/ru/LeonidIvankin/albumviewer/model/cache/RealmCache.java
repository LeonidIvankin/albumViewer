package ru.LeonidIvankin.albumviewer.model.cache;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import ru.LeonidIvankin.albumviewer.app.Constant;
import ru.LeonidIvankin.albumviewer.model.entity.AlbumList;

import io.reactivex.Observable;
import ru.LeonidIvankin.albumviewer.model.entity.Album;
import ru.LeonidIvankin.albumviewer.model.entity.realm.RealmAlbumList;
import ru.LeonidIvankin.albumviewer.model.entity.realm.RealmAlbum;
import timber.log.Timber;

public class RealmCache implements ICache {

	//записываем объекты в realm
	@Override
	public void putAlbum(AlbumList albumList) {
		Realm realm = Realm.getDefaultInstance();

		//ищем книгу
		RealmAlbumList realmAlbumList = realm
				.where(RealmAlbumList.class)
				.equalTo("resultCount", albumList.getResultCount())
				.findFirst();
		//если книга не существует, создаём
		if(realmAlbumList == null){
			realm.executeTransaction(innerRealm ->{
				RealmAlbumList newRealmAlbumList = realm.createObject(RealmAlbumList.class, albumList.getResultCount());
			});
		}

		realmAlbumList = realm
				.where(RealmAlbumList.class)
				.equalTo("resultCount", albumList.getResultCount())
				.findFirst();

		RealmAlbumList finalRealmAlbumList = realmAlbumList;

		//удаляем все объекты из realm и записываем новые
		realm.executeTransaction(innerRealm ->{
			finalRealmAlbumList.getResults().deleteAllFromRealm();

			for(Album albumsResult : albumList.getResults()) {
				RealmAlbum realmAlbum = realm.createObject(RealmAlbum.class, albumsResult.getCollectionId());
				realmAlbum.setCollectionName(albumsResult.getCollectionName());
				realmAlbum.setArtworkUrl100(albumsResult.getArtworkUrl100());
				finalRealmAlbumList.getResults().add(realmAlbum);

			}
		});


		realm.close();

	}

	//получаем объекты из realm
	@Override
	public Observable<AlbumList> getAlbum() {
		return Observable.create(e -> {
			Realm realm = Realm.getDefaultInstance();

			//ищем книгу
			RealmAlbumList realmAlbumList = realm
					.where(RealmAlbumList.class)
					//.equalTo("resultCount", "5")
					.findFirst();

			//если книги не существует, выдаём ошибку
			if(realm == null){
				Timber.d(Constant.ERROR_GETTING_ALBUM_FROM_REALM);
			}else{
				//в противном случае записываем из realm в объект photos данные
				AlbumList albumList = new AlbumList(realmAlbumList.getResultCount());

				List<Album> albumsResultsList = new ArrayList<>();

				for(RealmAlbum realmAlbum : realmAlbumList.getResults()) {
				    Album album = new Album();
				    album.setCollectionId(realmAlbum.getCollectionId());
				    album.setCollectionName(realmAlbum.getCollectionName());
				    album.setArtworkUrl100(realmAlbum.getArtworkUrl100());
					albumsResultsList.add(album);
				}

				albumList.setResults(albumsResultsList);
				e.onNext(albumList);
			}
			e.onComplete();
			realm.close();
		});
	}
}
