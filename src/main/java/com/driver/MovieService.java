package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    MovieRepository mr;

    public void addMovie(Movie m){
        mr.addMovieToDb(m);
    }
    public void addDirector(Director d){
        mr.addDirectorToDb(d);
    }
    public void director_movie(String movie,String director){

        mr.director_movie(movie,director);
    }
    public Movie getmovie(String name){
      return  mr.getbymovie(name);
    }
    public Director getdirector(String name){
        return mr.getbydirector(name);
    }
    public List<String> getdirectormovies(String directorname){
        return mr.movies_of_director(directorname);
    }
    public String getDirectorByMovieName(String name){
        return mr.getDirectorByMovieName(name);
    }
    public List<String> movielist(){
        return mr.movielist();
    }

    public void removedirector(String directorname){
        mr.removeDirector(directorname);
    }
    public void removeDirectorsandmovie(){
        mr.removeDirectorsAndMovie();
    }
}
