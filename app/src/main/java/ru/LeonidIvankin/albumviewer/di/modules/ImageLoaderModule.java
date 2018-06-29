package ru.LeonidIvankin.albumviewer.di.modules;

import android.widget.ImageView;

import ru.LeonidIvankin.albumviewer.model.cache.ImageCache;
import ru.LeonidIvankin.albumviewer.model.image.IImageLoader;
import ru.LeonidIvankin.albumviewer.model.image.android.GlideImageLoader;

import dagger.Module;
import dagger.Provides;

@Module(includes = {ImageCacheModule.class})
public class ImageLoaderModule {

	@Provides
	IImageLoader<ImageView> provideImageLoader(ImageCache imageCache) {
		return new GlideImageLoader(imageCache);
	}
}
