package model;

public class Movie {

    private int id;
    private String name;
    private int year;
    private String director;
    private String actors;
    private String label;
    private String cover = "default.jpg";


    private String review = "0.txt";
    private Double avg_score;
    private int total_score;
    private int rating_times;

    public Movie(){}

    public Movie(String name, int year,String director, String actors, String label, String cover) {
        this.name = name;
        this.year = year;
        this.director = director;
        this.actors = actors;
        this.label = label;
        this.cover = cover;
    }

    public Movie(int id, String name, String director, String actors, int year, String label, String review, String cover, Double avg_score, int total_score, int rating_times) {
        this.id = id;
        this.name = name;
        this.director = director;
        this.actors = actors;
        this.year = year;
        this.label = label;
        this.review = review;
        this.cover = cover;
        this.avg_score = avg_score;
        this.total_score = total_score;
        this.rating_times = rating_times;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Double getAvg_score() {
        return avg_score;
    }

    public void setAvg_score(Double avg_score) {
        this.avg_score = avg_score;
    }

    public int getTotal_score() {
        return total_score;
    }

    public void setTotal_score(int total_score) {
        this.total_score = total_score;
    }

    public int getRating_times() {
        return rating_times;
    }

    public void setRating_times(int rating_times) {
        this.rating_times = rating_times;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", year=" + year +
                ", director='" + director + '\'' +
                ", actors='" + actors + '\'' +
                ", label='" + label + '\'' +
                ", cover='" + cover + '\'' +
                ", review='" + review + '\'' +
                ", avg_score=" + avg_score +
                ", total_score=" + total_score +
                ", rating_times=" + rating_times +
                '}';
    }
}
