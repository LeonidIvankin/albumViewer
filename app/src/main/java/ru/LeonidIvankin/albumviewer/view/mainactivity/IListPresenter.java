package ru.LeonidIvankin.albumviewer.view.mainactivity;

public interface IListPresenter {
	void bindView(ListAlbumView holder);

	int getAlbumCount();

}
