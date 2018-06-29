package ru.LeonidIvankin.albumviewer.model.cache;

import android.graphics.Bitmap;

import ru.LeonidIvankin.albumviewer.app.App;
import ru.LeonidIvankin.albumviewer.app.Constant;
import ru.LeonidIvankin.albumviewer.model.entity.realm.CachedImage;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import io.realm.Realm;
import timber.log.Timber;

public class ImageCache {

	private File imageDir;

	//сохраняем картинку
	public File save(String url, Bitmap bitmap) {

		//если папка существует или не получилось создать, выбрасываем ошибку
		if (!getImageDir().exists() && !getImageDir().mkdirs()) {
			throw new RuntimeException(Constant.FAILED_TO_CREATE_DIRECTORY + getImageDir().toString());
		}

		final String fileFormat = url.contains(".jpg") ? ".jpg" : ".png";

		final File imageFile = new File(getImageDir(), md5(url) + fileFormat);

		//сохраняем картинку на карту памяти
		try (FileOutputStream fos = new FileOutputStream(imageFile)) {
			bitmap.compress(fileFormat.equals("jpg") ? Bitmap.CompressFormat.JPEG : Bitmap.CompressFormat.PNG, 100, fos);
			fos.close();
		} catch (Exception e) {
			Timber.d(Constant.FAILED_TO_SAVE_IMAGE);
			return null;
		}

		//сохраняем путь и url картинки в Realm.
		Realm.getDefaultInstance().executeTransaction(realm -> {
			CachedImage cachedImage = new CachedImage();
			cachedImage.setUrl(url);
			cachedImage.setPath(imageFile.toString());
			realm.copyToRealm(cachedImage);
		});

		//возращаем путь к файлу
		return imageFile;
	}

	//возращаем вместо url hash
	private String md5(String s) {
		MessageDigest m = null;

		try {
			m = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		m.update(s.getBytes(), 0, s.length());
		String hash = new BigInteger(1, m.digest()).toString(16);
		return hash;
	}

	//получаем путь к файлу по url из Realm
	public File getFile(String url) {
		CachedImage cachedImage = Realm
				.getDefaultInstance()
				.where(CachedImage.class)
				.equalTo("url", url)
				.findFirst();

		if (cachedImage != null) {
			return new File(cachedImage.getPath());
		}

		return null;

	}

	//проверяем, есть ли данный путь в Realm
	public boolean contains(String url) {
		return Realm
				.getDefaultInstance()
				.where(CachedImage.class)
				.equalTo("url", url)
				.count() > 0;

	}

	//получаем путь к папке для сохранения фото
	public File getImageDir() {
		return new File(App.getInstance().getExternalFilesDir(null) + "/" + Constant.IMAGE_FOLDER_NAME_PREVIEW_URL);
	}

	//очистка папки
	public void clearDir() {
		File[] files = getImageDir().listFiles();
		if (files != null) {
			for (File file : files) {
				file.delete();
			}
		}
	}
}
