package ru.LeonidIvankin.albumviewer.app;

public interface Constant {

	//http request
	//https://itunes.apple.com/search?entity=album&country=ru&term=charts
	String BASE_URL = "http://itunes.apple.com";
	String ENTITY = "album";
	String COUNTRY = "ru";

	//directory
	String IMAGE_FOLDER_NAME_PREVIEW_URL = "artworkUrl100";

	//error
	String FAILED_TO_CREATE_DIRECTORY = "Failed to create directory: ";
	String FAILED_TO_SAVE_IMAGE = "Failed to save image";
	String ERROR_GETTING_ALBUM_FROM_REALM = "error getting photos from realm";
	String IMAGE_LOAD_FAILED = "Image load failed";
	String FAILED_TO_GET_TRACKS = "Failed to get tracks";

	//send intent
	String SEND_INTENT_FROM_MAINACTIVITY_TO_ALBUMACTIVITY = "sendIntentFromMainactivityToAlbumactivity";

	//length
	int MAX_LENGTH_COLLECTION_NAME = 20;

	//keys
	String REALM_ALBUMS_KEY = "realmAlbumsKey";

	//entity iTunes
	String ENTITY_SONG = "song";
	String ENTITY_COLLECTION = "collection";

	//request
	String REQUEST_CHARTS = "charts";

}
