package ru.LeonidIvankin.albumviewer.di.modules;

import ru.LeonidIvankin.albumviewer.model.api.ApiService;
import ru.LeonidIvankin.albumviewer.model.cache.ICache;
import ru.LeonidIvankin.albumviewer.model.repo.AlbumRepo;

import dagger.Module;
import dagger.Provides;

@Module(includes = {ApiModule.class, CacheModule.class})
public class RepoModule {

	@Provides
	public AlbumRepo albumRepo(ICache cache, ApiService api) {
		return new AlbumRepo(cache, api);
	}
}
