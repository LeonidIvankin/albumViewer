package ru.LeonidIvankin.albumviewer.model.api;

import ru.LeonidIvankin.albumviewer.model.entity.AlbumList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.LeonidIvankin.albumviewer.model.entity.TrackList;

public interface ApiService {

	@GET("search")
	Observable<AlbumList> getAlbum(@Query("entity") String entity,
								   @Query("country") String country,
								   @Query("term") String term);

	@GET("lookup")
	Observable<TrackList> getTracks(@Query("id") int id,
										  @Query("entity") String entity);
}
