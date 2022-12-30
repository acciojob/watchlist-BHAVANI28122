package com.driver;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class MovieRepository {


    private HashMap<String,Movie> MovieMap;
    private HashMap<String,Director> DirectorMap;
    private HashMap<String,List<String>> DirectorMovieMap;

    public MovieRepository(HashMap<String, Movie> MovieMap, HashMap<String, Director> DirectorMap, HashMap<String, List<String>> DirectorMovieMap) {
        this.MovieMap = MovieMap;
        this.DirectorMap = DirectorMap;
        this.DirectorMovieMap = DirectorMovieMap;
    }
    public void addMovieToDb(Movie m){
        MovieMap.put(m.getName(),m);

    }
    public void addDirectorToDb(Director d){
        DirectorMap.put(d.getName(),d);

    }
    public void director_movie(String moviename, String directorname){

        if(MovieMap.containsKey(moviename) && DirectorMap.containsKey(directorname)) {
            List<String> director_and_movies = new ArrayList<>();
            if(DirectorMap.containsKey(directorname))
                director_and_movies = DirectorMovieMap.get(directorname);
            director_and_movies.add(moviename);
            DirectorMovieMap.put(directorname,director_and_movies);
        }
    }
    public Movie getbymovie (String name){

        return MovieMap.get(name);
    }
    public Director getbydirector(String name){
        return DirectorMap.get(name);
    }
    public List<String> movies_of_director(String directorname){

        List<String> movies = new ArrayList<>();
        if(DirectorMap.containsKey(directorname)){
            movies = DirectorMovieMap.get(directorname);
        }
        return movies;
    }
     public List<String> movielist( ){

        return new ArrayList<>(MovieMap.keySet());
     }

     public void removeDirector(String directorname){

        List<String> movies = new ArrayList<>();
        if(DirectorMovieMap.containsKey(directorname)){
            movies = DirectorMovieMap.get(directorname);

            for(String m : movies){
                if(MovieMap.containsKey(m)){
                    MovieMap.remove(m);
                }
            }
            DirectorMovieMap.remove(directorname);
        }
        if(DirectorMap.containsKey(directorname)){
            DirectorMap.remove(directorname);
        }
     }
     public void removeDirectorsAndMovie(){

        List<String> movies = new ArrayList<>();
        DirectorMap = new HashMap<>();

        for(String d:DirectorMovieMap.keySet()){

            for(String m: DirectorMovieMap.get(d)){
                movies.add(m);
            }
        }
        for(String m:movies){
            if(MovieMap.containsKey(m)){
                MovieMap.remove(m);
            }
        }
     }
}
