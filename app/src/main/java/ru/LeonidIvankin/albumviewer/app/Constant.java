package ru.LeonidIvankin.albumviewer.app;

public interface Constant {

	//http request
	//https://itunes.apple.com/search?entity=album&country=ru&term=charts
	String BASE_URL = "http://itunes.apple.com";
	String ENTITY = "album";
	String COUNTRY = "ru";

	//directory
	String IMAGE_FOLDER_NAME_PREVIEW_URL = "imagePreviewUrl";

	//error
	String FAILED_TO_CREATE_DIRECTORY = "Failed to create directory: ";
	String FAILED_TO_SAVE_IMAGE = "Failed to save image";
	String ERROR_GETTING_ALBUM_FROM_REALM = "error getting photos from realm";
	String IMAGE_LOAD_FAILED = "Image load failed";

	//send intent
	String SEND_INTENT_FROM_MAINACTIVITY_TO_ALBUMACTIVITY = "sendIntentFromMainactivityToPhotoactivity";

	//length
	int MAX_LENGTH_TAG = 20;

}
