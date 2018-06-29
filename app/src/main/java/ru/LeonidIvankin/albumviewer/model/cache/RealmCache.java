package ru.LeonidIvankin.albumviewer.model.cache;

import ru.LeonidIvankin.albumviewer.model.entity.Album;

import io.reactivex.Observable;

public class RealmCache implements ICache {

	//записываем объекты в realm
	@Override
	public void putAlbum(Album album) {
/*		Realm realm = Realm.getDefaultInstance();

		//ищем книгу
		RealmPhotos realmPhotos = realm
				.where(RealmPhotos.class)
				.equalTo("totalHits", photos.getResultCount())
				.findFirst();
		//если книга не существует, создаём
		if (realmPhotos == null) {
			realm.executeTransaction(innerRealm -> {
				RealmPhotos newRealmPhotos = realm.createObject(RealmPhotos.class, photos.getResultCount());
			});
		}

		realmPhotos = realm
				.where(RealmPhotos.class)
				.equalTo("totalHits", photos.getResultCount())
				.findFirst();

		RealmPhotos finalRealmPhotos = realmPhotos;

		//удаляем все объекты из realm и записываем новые
		realm.executeTransaction(innerRealm -> {
			finalRealmPhotos.getHits().deleteAllFromRealm();

			for (Hits hit : photos.getHits()) {
				RealmHits realmHits = realm.createObject(RealmHits.class, hit.getPreviewURL());
				realmHits.setCollectionName(hit.getTags());
				realmHits.setWebformatURL(hit.getWebformatURL());
				finalRealmPhotos.getHits().add(realmHits);

			}
		});

		realm.close();*/

	}

	//получаем объекты из realm
	@Override
	public Observable<Album> getAlbum() {
/*
		return Observable.create(e -> {
			Realm realm = Realm.getDefaultInstance();

			//ищем книгу
			RealmPhotos realmPhotos = realm
					.where(RealmPhotos.class)
					.equalTo("totalHits", "500")
					//FIXME
					.findFirst();

			//если книги не существует, выдаём ошибку
			if (realm == null) {
				Timber.d(Constant.ERROR_GETTING_PHOTOS_FROM_REALM);
			} else {

				//в противном случае записываем из realm в объект photos данные
				Album photos = new Album(realmPhotos.getTotalHits());

				List<Hits> hitsList = new ArrayList();

				for (RealmHits realmHits : realmPhotos.getHits()) {
					Hits hits = new Hits();
					hits.setPreviewURL(realmHits.getPreviewURL());
					hits.setCollectionName(realmHits.getTags());
					hits.setWebformatURL(realmHits.getWebformatURL());
					hitsList.add(hits);
				}

				photos.setHits(hitsList);

				e.onNext(photos);
			}
			e.onComplete();
			realm.close();
		});
*/
return null;
	}
}
