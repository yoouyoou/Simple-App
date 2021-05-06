package ddwcom.mobile.finalreport;

import java.io.Serializable;

public class movieData implements Serializable {
    private int image;
    private long _id;
    private String title;
    private String mainActor;
    private String director;
    private String rating;
    private String releaseDate;
    private String plot;
    private String category;

    public movieData(String title, String mainActor, String rating, String director, String category, String plot, String releaseDate){
        this.title = title;
        this.mainActor = mainActor;
        this.rating = rating;
        this.director = director;
        this.releaseDate = releaseDate;
        this.plot = plot;
        this.category = category;
    }

    public movieData(long _id, String title, String mainActor, String rating, String director, String category, String plot, String releaseDate){
        this._id = _id;
        this.title = title;
        this.mainActor = mainActor;
        this.rating = rating;
        this.director = director;
        this.releaseDate = releaseDate;
        this.plot = plot;
        this.category = category;
    }

    public void setImage(int image){ this.image = image; }
    public int getImage(){ return image; }

    public long get_id() { return _id; }
    public void set_id(long _id) { this._id = _id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getMainActor() { return mainActor; }
    public void setMainActor(String mainActor) { this.mainActor = mainActor; }

    public String getDirector() { return director; }
    public void setDirector(String director) { this.director = director; }

    public String getRating() { return rating; }
    public void setRating(String rating) { this.rating = rating; }

    public String getReleaseDate() { return releaseDate; }
    public void setReleaseDate(String releaseDate) { this.releaseDate = releaseDate; }

    public String getPlot() { return plot; }
    public void setPlot(String plot) { this.plot = plot; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
}
