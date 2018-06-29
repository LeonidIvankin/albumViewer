package ru.LeonidIvankin.albumviewer.di.modules;

import ru.LeonidIvankin.albumviewer.model.cache.ImageCache;

import dagger.Module;
import dagger.Provides;

@Module
public class ImageCacheModule {

	@Provides
	ImageCache provideImageCache() {
		return new ImageCache();
	}
}
