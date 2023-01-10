package DAO;

import model.Movie;

import java.util.List;

public interface MovieDAO {

    public int movieCount();

    public int movieSearchCount(String filter,String condition);

    public List<Movie> listMovies(int start,int size);

    public int addMovie(Movie movie);

    public void delMovie(int id);

    public List<Movie> searchMovies(String filter,String condition,int start,int size);

    public Movie searchMovieById(int id);

    public void updateMovie(Movie movie);

    public void addCoverMovie(Movie movie);

    public void addScoreMovie(int score,Movie movie);

    public void addReviewMovie(Movie movie);



}
