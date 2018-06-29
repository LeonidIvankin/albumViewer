package ru.LeonidIvankin.albumviewer.di.modules;

import ru.LeonidIvankin.albumviewer.app.App;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
	private App app;

	public AppModule(App app) {
		this.app = app;
	}

	@Provides
	public App app() {
		return app;
	}
}
