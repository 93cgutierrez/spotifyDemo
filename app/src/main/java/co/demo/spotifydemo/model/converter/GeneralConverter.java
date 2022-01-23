package co.demo.spotifydemo.model.converter;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import co.demo.spotifydemo.model.data.Album;
import co.demo.spotifydemo.model.intermediary.Image;

public class GeneralConverter {
    @TypeConverter
    public static String saveAlbum(Album object) {
        return new Gson().toJson(object);
    }
    @TypeConverter
    public static Album getAlbum(String value) {
        return new Gson().fromJson(value , Album.class);
    }

    @TypeConverter
    public static String saveAlbums(List<Album> listOfString) {
        return new Gson().toJson(listOfString);
    }
    @TypeConverter
    public static List<Album> getAlbums(String listOfString) {
        Type listType = new TypeToken<ArrayList<Album>>() {}.getType();
        return new Gson().fromJson(listOfString , listType);
    }

    @TypeConverter
    public static String saveAvailableMarkets(List<String> listOfString) {
        return new Gson().toJson(listOfString);
    }
    @TypeConverter
    public static List<String> getAvailableMarkets(String listOfString) {
        Type listType = new TypeToken<ArrayList<String>>() {}.getType();
        return new Gson().fromJson(listOfString , listType);
    }

    @TypeConverter
    public static String saveImages(List<Image> listOfString) {
        return new Gson().toJson(listOfString);
    }
    @TypeConverter
    public static List<Image> getImages(String listOfString) {
        Type listType = new TypeToken<ArrayList<Image>>() {}.getType();
        return new Gson().fromJson(listOfString , listType);
    }
}
