class Movie {
    String name;
    String showTime;
    int adultTickets;
    int childTickets;

    public Movie(String name, String showTime) {
        this.name = name;
        this.showTime = showTime;
        this.adultTickets = 0;
        this.childTickets = 0;
    }
}