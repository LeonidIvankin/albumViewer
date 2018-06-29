package ru.LeonidIvankin.albumviewer.model.api;

import ru.LeonidIvankin.albumviewer.model.entity.Albums;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

	@GET("search")
	Observable<Albums> getAlbum(@Query("entity") String entity,
								@Query("country") String country,
								@Query("term") String term);
}
