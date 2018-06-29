package ru.LeonidIvankin.albumviewer.app;

import android.app.Application;

import ru.LeonidIvankin.albumviewer.di.AppComponent;
import ru.LeonidIvankin.albumviewer.di.DaggerAppComponent;
import ru.LeonidIvankin.albumviewer.di.modules.AppModule;

import io.realm.Realm;
import timber.log.Timber;

public class App extends Application {

	private static App instance;
	private AppComponent appComponent;

	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
		Timber.plant(new Timber.DebugTree());
		Realm.init(this);

		appComponent = DaggerAppComponent.builder()
				.appModule(new AppModule(this))
				.build();
	}

	public static App getInstance() {
		return instance;
	}

	public AppComponent getAppComponent() {
		return appComponent;
	}
}
