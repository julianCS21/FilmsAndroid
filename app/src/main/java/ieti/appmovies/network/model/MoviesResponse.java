package ieti.appmovies.network.model;

import java.util.List;

import ieti.appmovies.network.model.Movie;

public class MoviesResponse {

    private List<Movie> Search;
    private String totalResults;
    private String Response;

    public List<Movie> getSearch() {
        return Search;
    }

    public void setSearch(List<Movie> search) {
        Search = search;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public String getResponse() {
        return Response;
    }

    public void setResponse(String response) {
        Response = response;
    }
}
