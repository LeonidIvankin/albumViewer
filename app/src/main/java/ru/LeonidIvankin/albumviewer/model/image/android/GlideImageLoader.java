package ru.LeonidIvankin.albumviewer.model.image.android;

import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import ru.LeonidIvankin.albumviewer.R;
import ru.LeonidIvankin.albumviewer.app.Constant;
import ru.LeonidIvankin.albumviewer.app.NetworkStatus;
import ru.LeonidIvankin.albumviewer.model.cache.ImageCache;
import ru.LeonidIvankin.albumviewer.model.image.IImageLoader;

import ru.LeonidIvankin.albumviewer.model.image.android.GlideApp;

import timber.log.Timber;

public class GlideImageLoader implements IImageLoader<ImageView> {

	private ImageCache imageCache;

	public GlideImageLoader(ImageCache imageCache) {
		this.imageCache = imageCache;
	}

	@Override
	public void loadInto(String url, ImageView container) {
		//если онлайн и нету на карте памяти, берем картинки по url
		if (NetworkStatus.isOnline() && !imageCache.contains(url)) {

			GlideApp
					.with(container.getContext())
					.asBitmap().load(url)
					.listener(new RequestListener<Bitmap>() {
						@Override
						public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
							Timber.e(e, Constant.IMAGE_LOAD_FAILED);
							return false;
						}

						@Override
						public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
							//сохраняем картинку
							imageCache.save(url, resource);

							return false;
						}
					})
					.placeholder(R.drawable.ic_cloud_download_black_48dp)
					.into(container);
			//если офлайн, берем картинки из кеша
		} else {
			if (imageCache.contains(url)) {
				GlideApp
						.with(container.getContext())
						.load(imageCache.getFile(url))
						.into(container);

			}
		}

	}

}
