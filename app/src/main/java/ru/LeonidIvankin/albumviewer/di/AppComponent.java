package ru.LeonidIvankin.albumviewer.di;

import javax.inject.Singleton;

import dagger.Component;
import ru.LeonidIvankin.albumviewer.di.modules.AppModule;
import ru.LeonidIvankin.albumviewer.di.modules.ImageLoaderModule;
import ru.LeonidIvankin.albumviewer.di.modules.RepoModule;
import ru.LeonidIvankin.albumviewer.presenter.AlbumPresenter;
import ru.LeonidIvankin.albumviewer.presenter.MainPresenter;
import ru.LeonidIvankin.albumviewer.view.mainactivity.MainActivity;
import ru.LeonidIvankin.albumviewer.view.mainactivity.RecyclerViewAdapterMain;
import ru.LeonidIvankin.albumviewer.view.albumactivity.AlbumActivity;

@Singleton
@Component(modules = {AppModule.class, RepoModule.class, ImageLoaderModule.class})
public interface AppComponent {
	void inject(MainActivity mainActivity);

	void inject(MainPresenter mainPresenter);

	void inject(RecyclerViewAdapterMain recyclerViewAdapterMain);

	void inject(AlbumActivity albumActivity);

	void inject(AlbumPresenter albumPresenter);
}
