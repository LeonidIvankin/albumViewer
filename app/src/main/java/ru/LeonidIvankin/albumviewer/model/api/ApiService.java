package ru.LeonidIvankin.albumviewer.model.api;

import java.util.List;

import ru.LeonidIvankin.albumviewer.model.entity.Albums;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.LeonidIvankin.albumviewer.model.entity.TrackList;

public interface ApiService {

	@GET("search")
	Observable<Albums> getAlbum(@Query("entity") String entity,
								@Query("country") String country,
								@Query("term") String term);

	@GET("lookup")
	Observable<TrackList> getTracks(@Query("id") String id,
										  @Query("entity") String entity);
}
