package ru.LeonidIvankin.albumviewer.model.image;

public interface IImageLoader<T> {
	void loadInto(String url, T container);
}
