package myapp.com.bookstore.enums;

public enum BookGenre {

    FICTION("Fiction"),
    DRAMA("Drama"),
    ROMANCE("Romance"),
    SCIENCE_FICTION("Science Fiction"),
    FANTASY("Fantasy"),
    HORROR("Horror"),
    DOCUMENTARY("Documentary"),
    PSYCHOLOGICAL("Psychological"),
    POETRY("Poetry"),
    THRILLER("Thriller"),
    HISTORICAL_FICTION("Historical Fiction"),
    BIOGRAPHY("Biography"),
    MYSTERY("Mystery"),
    SCIENCE("Science");

    private final String genre;

    BookGenre(String genre) {
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }

    @Override
    public String toString() {
        return genre;
    }
}
