package ru.LeonidIvankin.albumviewer.model.cache;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import ru.LeonidIvankin.albumviewer.app.Constant;
import ru.LeonidIvankin.albumviewer.model.entity.Albums;

import io.reactivex.Observable;
import ru.LeonidIvankin.albumviewer.model.entity.AlbumsResults;
import ru.LeonidIvankin.albumviewer.model.entity.realm.RealmAlbums;
import ru.LeonidIvankin.albumviewer.model.entity.realm.RealmAlbumsResults;
import timber.log.Timber;

public class RealmCache implements ICache {

	//записываем объекты в realm
	@Override
	public void putAlbum(Albums albums) {
		Realm realm = Realm.getDefaultInstance();

		//ищем книгу
		RealmAlbums realmAlbums = realm
				.where(RealmAlbums.class)
				.equalTo("resultCount", albums.getResultCount())
				.findFirst();
		//если книга не существует, создаём
		if(realmAlbums == null){
			realm.executeTransaction(innerRealm ->{
				RealmAlbums newRealmAlbums = realm.createObject(RealmAlbums.class, albums.getResultCount());
			});
		}

		realmAlbums = realm
				.where(RealmAlbums.class)
				.equalTo("resultCount", albums.getResultCount())
				.findFirst();

		RealmAlbums finalRealmAlbums = realmAlbums;

		//удаляем все объекты из realm и записываем новые
		realm.executeTransaction(innerRealm ->{
			finalRealmAlbums.getResults().deleteAllFromRealm();

			for(AlbumsResults albumsResult : albums.getResults()) {
				RealmAlbumsResults realmAlbumsResults = realm.createObject(RealmAlbumsResults.class, albumsResult.getCollectionId());
				realmAlbumsResults.setCollectionName(albumsResult.getCollectionName());
				realmAlbumsResults.setArtworkUrl100(albumsResult.getArtworkUrl100());
				finalRealmAlbums.getResults().add(realmAlbumsResults);

			}
		});


		realm.close();

	}

	//получаем объекты из realm
	@Override
	public Observable<Albums> getAlbum() {
		return Observable.create(e -> {
			Realm realm = Realm.getDefaultInstance();

			//ищем книгу
			RealmAlbums realmAlbums = realm
					.where(RealmAlbums.class)
					//.equalTo("resultCount", "5")
					.findFirst();

			//если книги не существует, выдаём ошибку
			if(realm == null){
				Timber.d(Constant.ERROR_GETTING_ALBUM_FROM_REALM);
			}else{
				//в противном случае записываем из realm в объект photos данные
				Albums albums = new Albums(realmAlbums.getResultCount());

				List<AlbumsResults> albumsResultsList = new ArrayList<>();

				for(RealmAlbumsResults realmAlbumsResults : realmAlbums.getResults()) {
				    AlbumsResults albumsResults = new AlbumsResults();
				    albumsResults.setCollectionId(realmAlbumsResults.getCollectionId());
				    albumsResults.setCollectionName(realmAlbumsResults.getCollectionName());
				    albumsResults.setArtworkUrl100(realmAlbumsResults.getArtworkUrl100());
					albumsResultsList.add(albumsResults);
				}

				albums.setResults(albumsResultsList);
				e.onNext(albums);
			}
			e.onComplete();
			realm.close();
		});
	}
}
