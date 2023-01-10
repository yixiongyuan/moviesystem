package DAO;

import model.Movie;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieDaoLocalImpl implements MovieDAO{

    private DataSource dataSource;

    public MovieDaoLocalImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public int movieCount(){
        String SQL = "select count(*) from local_movie_list";
        Connection conn = null;

        int count = 0;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){count = Integer.parseInt(rs.getString("count(*)"));}
            rs.close();
            ps.close();
            return count;
        } catch (SQLException e) {
            e.printStackTrace();
            return count;
        }
        finally {
            if(conn!=null){
                try {conn.close();}
                catch (SQLException e) {e.printStackTrace();}
            }
        }
    }

    @Override
    public int movieSearchCount(String filter, String condition) {
        String SQL = "select count(*) from local_movie_list where "+filter+" like ?";
        Connection conn = null;

        int count = 0;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setString(1,"%"+condition+"%");
            //System.out.println(ps);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){count = Integer.parseInt(rs.getString("count(*)"));}
            rs.close();
            ps.close();
            return count;
        } catch (SQLException e) {
            e.printStackTrace();
            return count;
        }
        finally {
            if(conn!=null){
                try {conn.close();}
                catch (SQLException e) {e.printStackTrace();}
            }
        }
    }

    @Override
    public List<Movie> listMovies(int start, int size){
        String SQL = "select * from local_movie_list order by movie_id DESC LIMIT  ? , ?";
        List<Movie> list = new ArrayList<>();
        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setInt(1,start);
            ps.setInt(2,size);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Movie movie = new Movie(
                        rs.getInt("movie_id"),
                        rs.getString("name"),
                        rs.getString("director"),
                        rs.getString("actors"),
                        rs.getInt("year"),
                        rs.getString("label"),
                        rs.getString("review"),
                        rs.getString("cover"),
                        rs.getDouble("avg_score"),
                        rs.getInt("total_score"),
                        rs.getInt("rating_times")
                );
                list.add(movie);
            }
            rs.close();
            ps.close();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        finally {
            if(conn!=null){
                try {conn.close();}
                catch (SQLException e) {e.printStackTrace();}
            }
        }
    }

    @Override
    public List<Movie> searchMovies(String filter, String condition, int start, int size) {
        String SQL = "select * from local_movie_list where "+filter+" like ? order by movie_id DESC LIMIT  ? , ?";
        List<Movie> list = new ArrayList<>();
        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setString(1,"%"+condition+"%");
            ps.setInt(2,start);
            ps.setInt(3,size);
            //System.out.println(ps);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Movie movie = new Movie(
                        rs.getInt("movie_id"),
                        rs.getString("name"),
                        rs.getString("director"),
                        rs.getString("actors"),
                        rs.getInt("year"),
                        rs.getString("label"),
                        rs.getString("review"),
                        rs.getString("cover"),
                        rs.getDouble("avg_score"),
                        rs.getInt("total_score"),
                        rs.getInt("rating_times")
                );
                list.add(movie);
            }
            rs.close();
            ps.close();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        finally {
            if(conn!=null){
                try {conn.close();}
                catch (SQLException e) {e.printStackTrace();}
            }
        }
    }

    @Override
    public int addMovie(Movie movie) {
        String SQL = "insert into local_movie_list (name,director,year,actors,label) values (?,?,?,?,?)";

        String SQLID = "select max(movie_id) from local_movie_list";

        Connection conn = null;

        int id = 0;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setString(1,movie.getName());
            ps.setString(2,movie.getDirector());
            ps.setInt(3,movie.getYear());
            ps.setString(4,movie.getActors());
            ps.setString(5,movie.getLabel());

            PreparedStatement psID = conn.prepareStatement(SQLID);

            synchronized (this){
                ps.executeUpdate();
                ResultSet rs = psID.executeQuery();
                while (rs.next()){id = Integer.parseInt(rs.getString("max(movie_id)"));}
                rs.close();
            }

            ps.close();
            psID.close();
            return id;
        } catch (SQLException e) {
            e.printStackTrace();
            return id;
        }
        finally {
            if(conn!=null){
                try {conn.close();}
                catch (SQLException e) {e.printStackTrace();}
            }
        }
    }


    @Override
    public void delMovie(int id) {
        String SQL = "delete from local_movie_list where movie_id =  ? ";

        Movie target = new Movie();
        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setInt(1,id);
            ps.executeUpdate();
            ps.close();
            return;
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
        finally {
            if(conn!=null){
                try {conn.close();}
                catch (SQLException e) {e.printStackTrace();}
            }
        }
    }


    @Override
    public Movie searchMovieById(int id) {
        String SQL = "select * from local_movie_list where movie_id =  ? ";

        Movie target = new Movie();
        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                target = new Movie(
                        rs.getInt("movie_id"),
                        rs.getString("name"),
                        rs.getString("director"),
                        rs.getString("actors"),
                        rs.getInt("year"),
                        rs.getString("label"),
                        rs.getString("review"),
                        rs.getString("cover"),
                        rs.getDouble("avg_score"),
                        rs.getInt("total_score"),
                        rs.getInt("rating_times")
                );
            }
            rs.close();
            ps.close();
            return target;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        finally {
            if(conn!=null){
                try {conn.close();}
                catch (SQLException e) {e.printStackTrace();}
            }
        }
    }

    @Override
    public void updateMovie(Movie movie) {
        String SQL = "UPDATE local_movie_list SET name=?,director=?,year=?,actors=?,label=?,cover=? where movie_id =  ? ";

        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setString(1,movie.getName());
            ps.setString(2,movie.getDirector());
            ps.setInt(3,movie.getYear());
            ps.setString(4,movie.getActors());
            ps.setString(5,movie.getLabel());
            ps.setString(6,movie.getCover());
            ps.setInt(7,movie.getId());
            //System.out.println(ps);
            ps.executeUpdate();

            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if(conn!=null){
                try {conn.close();}
                catch (SQLException e) {e.printStackTrace();}
            }
        }
    }

    @Override
    public void addCoverMovie(Movie movie) {

        String SQL = "UPDATE local_movie_list SET cover=? where movie_id = ? ";

        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setString(1,movie.getCover());
            ps.setInt(2,movie.getId());
            ps.executeUpdate();

            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if(conn!=null){
                try {conn.close();}
                catch (SQLException e) {e.printStackTrace();}
            }
        }
    }

    @Override
    public void addScoreMovie(int score, Movie movie) {



        String SQL = "UPDATE local_movie_list SET rating_times=?,total_score=?,avg_score=? where movie_id =  ? ";

        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setInt(1,movie.getRating_times());
            ps.setInt(2,movie.getTotal_score());
            ps.setDouble(3,movie.getAvg_score());
            ps.setInt(4,movie.getId());
            //System.out.println(ps);
            ps.executeUpdate();

            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if(conn!=null){
                try {conn.close();}
                catch (SQLException e) {e.printStackTrace();}
            }
        }

    }

    @Override
    public void addReviewMovie(Movie movie) {

        String SQL = "UPDATE local_movie_list SET review=? where movie_id = ? ";

        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setString(1,movie.getReview());
            ps.setInt(2,movie.getId());
            ps.executeUpdate();

            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if(conn!=null){
                try {conn.close();}
                catch (SQLException e) {e.printStackTrace();}
            }
        }
    }
}
