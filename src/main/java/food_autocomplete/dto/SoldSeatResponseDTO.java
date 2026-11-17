package food_autocomplete.dto;

public class SoldSeatResponseDTO {

    private String movieName;
    private String cinema;
    private String movieDate;
    private String movieTime;
    private String seats;

    public SoldSeatResponseDTO(
            String movieName,
            String cinema,
            String movieDate,
            String movieTime,
            String seats) {
        this.movieName = movieName;
        this.cinema = cinema;
        this.movieDate = movieDate;
        this.movieTime = movieTime;
        this.seats = seats;
    }

    public String getMovieName() {
        return movieName;
    }

    public String getCinema() {
        return cinema;
    }

    public String getMovieDate() {
        return movieDate;
    }

    public String getMovieTime() {
        return movieTime;
    }

    public String getSeats() {
        return seats;
    }

}
