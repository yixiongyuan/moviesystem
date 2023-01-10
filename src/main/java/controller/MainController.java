package controller;

import DAO.MovieDaoLocalImpl;
import DAO.MovieDaoImpl;
import config.MovieConfig;
import model.Movie;
import model.Page;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    @RequestMapping(value = "/")
    public ModelAndView mainPage(){

        ModelAndView model = new ModelAndView("main");

        return model;
    }


    @RequestMapping(value = "/list")
    public ModelAndView listAllMovie(HttpServletRequest request){

        //default return page
        ModelAndView model = new ModelAndView("listAllMovie");

        //data access
        AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext(MovieConfig.class);

        //MovieDaoImpl DAO = context.getBean("DAOBean",MovieDaoImpl.class);
        MovieDaoLocalImpl DAO = context.getBean("DAOLocalBean",MovieDaoLocalImpl.class);

        //related parameter
        String pageNow = request.getParameter("pageNow");
        Page page = null;
        List<Movie> movies = new ArrayList<>();
        String filter = request.getParameter("filter");
        String condition =  request.getParameter("condition");

        //retrieve data from db
        int totalCount=0;
        if(filter.equals("all")){
            totalCount = DAO.movieCount();

            //page for creating content and limit number of movie showing in one page
            if (pageNow != null && pageNow.length()>0 &&Integer.parseInt(pageNow)>0) {page = new Page(totalCount, Integer.parseInt(pageNow));}
            else {page = new Page(totalCount, 1);}

            movies = DAO.listMovies(page.getStartPos(), page.getPageSize());
        }else{

            totalCount = DAO.movieSearchCount(filter,condition);

            if(totalCount==0){
                model = new ModelAndView("searchMovie");
                request.setAttribute("alert","true");
                return model;
            }

            //page for creating content and limit number of movie showing in one page
            if (pageNow != null && pageNow.length()>0 &&Integer.parseInt(pageNow)>0) {page = new Page(totalCount, Integer.parseInt(pageNow));}
            else {page = new Page(totalCount, 1);}

            movies = DAO.searchMovies(filter,condition,page.getStartPos(), page.getPageSize());

        }

        //return object to model
        model.addObject("movies",movies);
        model.addObject("page",page);
        model.addObject("filter",filter);
        model.addObject("condition",condition);
        addIndexInfo(model,request);
        context.close();
        return model;
    }


    @RequestMapping(value = "/add")
    public String addNewMovie(Model model,
                              @ModelAttribute("movie")Movie movie,
                              @RequestParam(value = "file" ,required = false)CommonsMultipartFile file,
                              HttpServletRequest request){

        if(movie.getName()==null){
            model.addAttribute("movie",movie);
            return "addNewMovie";
        }
        else {

            //data access
            AnnotationConfigApplicationContext context
                    = new AnnotationConfigApplicationContext(MovieConfig.class);

            //MovieDaoImpl DAO = context.getBean("DAOBean",MovieDaoImpl.class);
            MovieDaoLocalImpl DAO = context.getBean("DAOLocalBean", MovieDaoLocalImpl.class);

            // add new movie item and return related id
            int id = DAO.addMovie(movie);
            movie.setId(id);

            // new item initialization

            //create initial txt for reviewing
            writeReview(movie,request,"");
            DAO.addReviewMovie(movie);


            //upload the cover image
            fileUpload(movie, request, file);
            DAO.addCoverMovie(movie);

            context.close();
            return "forward:/list?filter=all";

        }

    }

    @RequestMapping(value = "/detail")
    public ModelAndView detailPage(ModelAndView model, HttpServletRequest request){

        String id = request.getParameter("movieId");
        if(id==null||id.length()==0){return model;}

        //return page
        model = new ModelAndView("movieDetail");

        //data access
        AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext(MovieConfig.class);

        //MovieDaoImpl DAO = context.getBean("DAOBean",MovieDaoImpl.class);
        MovieDaoLocalImpl DAO = context.getBean("DAOLocalBean",MovieDaoLocalImpl.class);

        //retrieve data from db
        int movieId = Integer.parseInt(id);
        Movie movie = DAO.searchMovieById(movieId);

        writeReview(movie,request,"");

        //add info to model
        model.addObject("movie",movie);
        request.setAttribute("reviewFile",movie.getReview());
        addIndexInfo(model,request);

        context.close();
        return model;
    }


    //function for update movie data
    @RequestMapping(value = "/update",method = RequestMethod.GET)
    public ModelAndView updatePage(ModelAndView model, HttpServletRequest request){

        String id = request.getParameter("movieId");
        if(id==null||id.length()==0){return new ModelAndView("main");}


        //jump page
        model = new ModelAndView("updateMovie");

        //data access
        AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext(MovieConfig.class);

        //MovieDaoImpl DAO = context.getBean("DAOBean",MovieDaoImpl.class);
        MovieDaoLocalImpl DAO = context.getBean("DAOLocalBean",MovieDaoLocalImpl.class);

        // retrieve data from db
        int movieId = Integer.parseInt(id);
        Movie movie = DAO.searchMovieById(movieId);


        //add info to model
        model.addObject("movie",movie);
        request.setAttribute("movieId",movieId);
        addIndexInfo(model,request);

        context.close();
        return model;
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public ModelAndView updateOperation(ModelAndView model,
                                        @ModelAttribute("movie")Movie movie,
                                        HttpServletRequest request,
                                        @RequestParam(value = "file" ,required = false)CommonsMultipartFile file){

        String id = request.getParameter("movieId");
        if(id==null||id.length()==0){return new ModelAndView("main");}


        //jump page
        model = new ModelAndView("movieDetail");

        AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext(MovieConfig.class);

        //data access
        //MovieDaoImpl DAO = context.getBean("DAOBean",MovieDaoImpl.class);
        MovieDaoLocalImpl DAO = context.getBean("DAOLocalBean",MovieDaoLocalImpl.class);

        // get the movie id
        int movieId = Integer.parseInt(id);
        movie.setId(movieId);

        //update the cover
        fileUpload(movie, request, file);
        DAO.updateMovie(movie);


        //retrieve the updated movie item
        Movie movieUpdated = DAO.searchMovieById(movieId);


        // add info to model
        model.addObject("movie",movieUpdated);
        request.setAttribute("reviewFile",movieUpdated.getReview());
        addIndexInfo(model,request);


        context.close();
        return model;
    }

    public void fileUpload(Movie movie,
                           HttpServletRequest request,
                           CommonsMultipartFile file){

        String uploadFileName = file.getOriginalFilename();

        if(uploadFileName!=null&&uploadFileName.length()>0){

            int id =movie.getId();
            String substring = uploadFileName.substring(uploadFileName.lastIndexOf("."));
            String fileName= id+substring;

            movie.setCover(fileName);

            String coverPath = request.getServletContext().getRealPath("/upload/CoverData");

            File newFile = new File(coverPath,fileName);
            try{
                file.transferTo(newFile);
            }catch (IOException e){
                e.printStackTrace();
            }

        }
    }

    @RequestMapping(value = "/rating")
    public ModelAndView ratingPage(ModelAndView model,
                                   @RequestParam(value = "review" ,required = false)String review,
                                   @RequestParam(value = "score" ,required = false)Integer score,
                                   HttpServletRequest request){

        if(review==null&&score==null){
            model = new ModelAndView("ratingMovie");
            model.addObject("movieId",request.getParameter("movieId"));
            addIndexInfo(model,request);
            return model;
        }

        //return page
        model = new ModelAndView("movieDetail");


        // data access
        AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext(MovieConfig.class);

        //MovieDaoImpl DAO = context.getBean("DAOBean",MovieDaoImpl.class);
        MovieDaoLocalImpl DAO = context.getBean("DAOLocalBean",MovieDaoLocalImpl.class);

        // retrieve movie item from db by id
        String id = request.getParameter("movieId");
        int movieId = Integer.parseInt(id);
        Movie movie = DAO.searchMovieById(movieId);

        //add new review
        if(review!=null&& review.length()>0){
            writeReview(movie,request,review);
        }

        //add new score
        if(score!=null){
            updateScore(score,movie);
            DAO.addScoreMovie(score,movie);
        }


        //add info to model
        model.addObject("movie",movie);
        request.setAttribute("reviewFile",movie.getReview());
        addIndexInfo(model,request);

        context.close();
        return model;
    }



    public void writeReview(Movie movie,HttpServletRequest request,String txt){

        int id = movie.getId();
        String fileName = id+".txt";
        String txtPath = request.getServletContext().getRealPath("/upload/ReviewData/")+id+".txt";

        File file = new File(txtPath);

        if(!file.exists()){

            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        movie.setReview(fileName);

        if(txt!=null&&txt.length()>0){
            FileWriter fw = null;
            try
            {
                fw = new FileWriter(txtPath,true);
                fw.write(txt +"\r\n");
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            finally
            {
                try
                {
                    fw.close();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }

            }
        }


    }

    public void updateScore(Integer score,Movie movie){

        movie.setRating_times(movie.getRating_times()+1);
        movie.setTotal_score(movie.getTotal_score()+score);

        BigDecimal bd = BigDecimal.valueOf((double) movie.getTotal_score() / (double) movie.getRating_times());
        movie.setAvg_score(bd.setScale(2, RoundingMode.UP).doubleValue());
    }

    public void addIndexInfo(ModelAndView model,HttpServletRequest request){

        model.addObject("pageNow",request.getParameter("pageNow"));
        model.addObject("filter",request.getParameter("filter"));
        model.addObject("condition",request.getParameter("condition"));

    }

    @RequestMapping(value = "/delete")
    public String deletePage(ModelAndView model, HttpServletRequest request){

        String id = request.getParameter("movieId");
        if(id==null||Integer.valueOf(id)<=0){return "forward:/list?filter=all";}


        //data access
        AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext(MovieConfig.class);

        //MovieDaoImpl DAO = context.getBean("DAOBean",MovieDaoImpl.class);
        MovieDaoLocalImpl DAO = context.getBean("DAOLocalBean",MovieDaoLocalImpl.class);

        //delete movie item from db and local data
        int movieId = Integer.parseInt(id);
        Movie movie = DAO.searchMovieById(movieId);
        deleteData(movie,request);
        DAO.delMovie(movieId);


        //return page with parameters
        context.close();
        return "forward:/list?pageNow="+request.getParameter("pageNow")+
                "&filter="+ request.getParameter("filter")+
                "&condition="+ request.getParameter("condition");
    }

    public void deleteData(Movie movie,HttpServletRequest request){

        //1.cover image
        if(movie.getCover()!="default.jpg"){
            String coverPath = request.getServletContext().getRealPath("/upload/CoverData");

            File coverFile = new File(coverPath,movie.getCover());

            try {
                coverFile.delete();
            }
            catch (Exception e){
                e.printStackTrace();
            }

        }

        //2. review file

        String reviewPath = request.getServletContext().getRealPath("/upload/ReviewData");

        File reviewFile = new File(reviewPath,movie.getReview());

        try {
            reviewFile.delete();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }


    @RequestMapping(value = "/search",method = RequestMethod.GET)
    public ModelAndView searchResult(){

        ModelAndView model = new ModelAndView("searchMovie");

        return model;
    }


    @RequestMapping(value = "/back")
    public String returnPage(HttpServletRequest request){

        return "forward:/list?pageNow="+request.getParameter("pageNow")+
                "&filter="+ request.getParameter("filter")+
                "&condition="+ request.getParameter("condition");

    }


}
