package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import java.util.HashMap;

@RestController
public class MovieController {

    @Autowired
    MovieService ms;

    @PostMapping("/addMovie")
    public ResponseEntity<String> addMovie(@RequestBody Movie m){

        ms.addMovie(m);
        return new ResponseEntity("Movie Added Successfully", HttpStatus.CREATED);

    }
    @PostMapping("/addDirector")
    public ResponseEntity<String> addDirector(@RequestBody Director d){

        ms.addDirector(d);
        return new ResponseEntity("Director Added Successfully", HttpStatus.CREATED);

    }
    @PutMapping("/add-movie-director-pair")
    public ResponseEntity<String> addMovieDirectorPair(@RequestParam("moviename") String moviename, @RequestParam("directorname") String directorname) {
        ms.director_movie(moviename, directorname);
        return new ResponseEntity<>("Director-Movie pair added Successfully",HttpStatus.CREATED);
    }

    @GetMapping("/get-movie-by-name/{name}")
    public ResponseEntity<Movie> getMovieByName(@PathVariable String name){
        Movie m = ms.getmovie(name);
        return new ResponseEntity<>(m,HttpStatus.CREATED);
    }
    @GetMapping("/get-director-by-name/{name}")
    public ResponseEntity<Director> getDirectorByName(@PathVariable String name){
        Director d = ms.getdirector(name);
        return new ResponseEntity<>(d,HttpStatus.CREATED);
    }
    @GetMapping("/get-movies-by-director-name/{name}")
    public ResponseEntity<List<String>> getMoviesByDirectorName(@PathVariable String name){
        List<String> movies = ms.getdirectormovies(name);
        return new ResponseEntity<>(movies,HttpStatus.CREATED);
    }
    @GetMapping("/get-all-movies")
    public ResponseEntity<List<String>> findAllMovies(){

        List<String> movies = ms.movielist();
        return new ResponseEntity<>(movies,HttpStatus.CREATED);
    }
    @DeleteMapping("/delete-director-by-name")
    public ResponseEntity<String> deleteDirectorByname(@RequestParam("directorname") String directorname){
        ms.removedirector(directorname);
        return new ResponseEntity<>(directorname + "Movie-Director pair Deleted Successfully",HttpStatus.CREATED);
    }
    @DeleteMapping("/delete-all-directors")
    public ResponseEntity<String> deleteAllDirectors(){
        ms.removeDirectorsandmovie();
        return new ResponseEntity<>("Removed all the data Successfully",HttpStatus.CREATED);
    }
}

