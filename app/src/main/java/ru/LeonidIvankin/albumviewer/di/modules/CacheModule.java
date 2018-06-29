package ru.LeonidIvankin.albumviewer.di.modules;

import ru.LeonidIvankin.albumviewer.model.cache.ICache;
import ru.LeonidIvankin.albumviewer.model.cache.RealmCache;

import dagger.Module;
import dagger.Provides;

@Module
public class CacheModule {

	@Provides
	public ICache cache() {
		return new RealmCache();
	}

}
