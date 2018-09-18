package com.kirilldrob.savelocation.network;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;






public class CuratedCollection {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("published_at")
    @Expose
    public String publishedAt;
    @SerializedName("updated_at")
    @Expose
    public String updatedAt;
    @SerializedName("curated")
    @Expose
    public Boolean curated;
    @SerializedName("featured")
    @Expose
    public Boolean featured;
    @SerializedName("total_photos")
    @Expose
    public Integer totalPhotos;
    @SerializedName("private")
    @Expose
    public Boolean _private;
    @SerializedName("share_key")
    @Expose
    public Object shareKey;
    @SerializedName("tags")
    @Expose
    public List<Object> tags = null;
    @SerializedName("cover_photo")
    @Expose
    public CoverPhoto coverPhoto;
    @SerializedName("preview_photos")
    @Expose
    public List<PreviewPhoto> previewPhotos = null;
    @SerializedName("user")
    @Expose
    public User_ user;
    @SerializedName("links")
    @Expose
    public Links___ links;


public class CoverPhoto {

    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("created_at")
    @Expose
    public String createdAt;
    @SerializedName("updated_at")
    @Expose
    public String updatedAt;
    @SerializedName("width")
    @Expose
    public Integer width;
    @SerializedName("height")
    @Expose
    public Integer height;
    @SerializedName("color")
    @Expose
    public String color;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("urls")
    @Expose
    public Urls urls;
    @SerializedName("links")
    @Expose
    public Links links;
    @SerializedName("categories")
    @Expose
    public List<Object> categories = null;
    @SerializedName("sponsored")
    @Expose
    public Boolean sponsored;
    @SerializedName("likes")
    @Expose
    public Integer likes;
    @SerializedName("liked_by_user")
    @Expose
    public Boolean likedByUser;
    @SerializedName("current_user_collections")
    @Expose
    public List<Object> currentUserCollections = null;
    @SerializedName("slug")
    @Expose
    public Object slug;
    @SerializedName("user")
    @Expose
    public User user;

}


public class Links {

    @SerializedName("self")
    @Expose
    public String self;
    @SerializedName("html")
    @Expose
    public String html;
    @SerializedName("download")
    @Expose
    public String download;
    @SerializedName("download_location")
    @Expose
    public String downloadLocation;

}

public class Links_ {

    @SerializedName("self")
    @Expose
    public String self;
    @SerializedName("html")
    @Expose
    public String html;
    @SerializedName("photos")
    @Expose
    public String photos;
    @SerializedName("likes")
    @Expose
    public String likes;
    @SerializedName("portfolio")
    @Expose
    public String portfolio;
    @SerializedName("following")
    @Expose
    public String following;
    @SerializedName("followers")
    @Expose
    public String followers;

}


public class Links__ {

    @SerializedName("self")
    @Expose
    public String self;
    @SerializedName("html")
    @Expose
    public String html;
    @SerializedName("photos")
    @Expose
    public String photos;
    @SerializedName("likes")
    @Expose
    public String likes;
    @SerializedName("portfolio")
    @Expose
    public String portfolio;
    @SerializedName("following")
    @Expose
    public String following;
    @SerializedName("followers")
    @Expose
    public String followers;

}


public class Links___ {

    @SerializedName("self")
    @Expose
    public String self;
    @SerializedName("html")
    @Expose
    public String html;
    @SerializedName("photos")
    @Expose
    public String photos;
    @SerializedName("download")
    @Expose
    public String download;

}


public class PreviewPhoto {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("urls")
    @Expose
    public Urls_ urls;

}

public class ProfileImage {

    @SerializedName("small")
    @Expose
    public String small;
    @SerializedName("medium")
    @Expose
    public String medium;
    @SerializedName("large")
    @Expose
    public String large;

}


public class ProfileImage_ {

    @SerializedName("small")
    @Expose
    public String small;
    @SerializedName("medium")
    @Expose
    public String medium;
    @SerializedName("large")
    @Expose
    public String large;

}


public class Urls {

    @SerializedName("raw")
    @Expose
    public String raw;
    @SerializedName("full")
    @Expose
    public String full;
    @SerializedName("regular")
    @Expose
    public String regular;
    @SerializedName("small")
    @Expose
    public String small;
    @SerializedName("thumb")
    @Expose
    public String thumb;

}


public class Urls_ {

    @SerializedName("raw")
    @Expose
    public String raw;
    @SerializedName("full")
    @Expose
    public String full;
    @SerializedName("regular")
    @Expose
    public String regular;
    @SerializedName("small")
    @Expose
    public String small;
    @SerializedName("thumb")
    @Expose
    public String thumb;

}


public class User {

    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("updated_at")
    @Expose
    public String updatedAt;
    @SerializedName("username")
    @Expose
    public String username;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("first_name")
    @Expose
    public String firstName;
    @SerializedName("last_name")
    @Expose
    public String lastName;
    @SerializedName("twitter_username")
    @Expose
    public Object twitterUsername;
    @SerializedName("portfolio_url")
    @Expose
    public String portfolioUrl;
    @SerializedName("bio")
    @Expose
    public Object bio;
    @SerializedName("location")
    @Expose
    public Object location;
    @SerializedName("links")
    @Expose
    public Links_ links;
    @SerializedName("profile_image")
    @Expose
    public ProfileImage profileImage;
    @SerializedName("instagram_username")
    @Expose
    public String instagramUsername;
    @SerializedName("total_collections")
    @Expose
    public Integer totalCollections;
    @SerializedName("total_likes")
    @Expose
    public Integer totalLikes;
    @SerializedName("total_photos")
    @Expose
    public Integer totalPhotos;

}


public class User_ {

    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("updated_at")
    @Expose
    public String updatedAt;
    @SerializedName("username")
    @Expose
    public String username;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("first_name")
    @Expose
    public String firstName;
    @SerializedName("last_name")
    @Expose
    public String lastName;
    @SerializedName("twitter_username")
    @Expose
    public String twitterUsername;
    @SerializedName("portfolio_url")
    @Expose
    public String portfolioUrl;
    @SerializedName("bio")
    @Expose
    public String bio;
    @SerializedName("location")
    @Expose
    public Object location;
    @SerializedName("links")
    @Expose
    public Links__ links;
    @SerializedName("profile_image")
    @Expose
    public ProfileImage_ profileImage;
    @SerializedName("instagram_username")
    @Expose
    public String instagramUsername;
    @SerializedName("total_collections")
    @Expose
    public Integer totalCollections;
    @SerializedName("total_likes")
    @Expose
    public Integer totalLikes;
    @SerializedName("total_photos")
    @Expose
    public Integer totalPhotos;

}

}


/* Сделан по парсингу ОТВЕТА в ДОКУМЕНТАЦИИ
public class CuratedCollection {

    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("created_at")
    @Expose
    public String createdAt;
    @SerializedName("updated_at")
    @Expose
    public String updatedAt;
    @SerializedName("width")
    @Expose
    public Integer width;
    @SerializedName("height")
    @Expose
    public Integer height;
    @SerializedName("color")
    @Expose
    public String color;
    @SerializedName("likes")
    @Expose
    public Integer likes;
    @SerializedName("liked_by_user")
    @Expose
    public Boolean likedByUser;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("user")
    @Expose
    public User user;
    @SerializedName("current_user_collections")
    @Expose
    public List<CurrentUserCollection> currentUserCollections = null;
    @SerializedName("urls")
    @Expose
    public Urls urls;
    @SerializedName("links")
    @Expose
    public Links_ links;




public class CurrentUserCollection {


}
}*/