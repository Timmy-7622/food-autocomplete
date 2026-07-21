package food_autocomplete.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "BOOKING_ORDER")
public class BookingOrder {

    @Id
    @Column(name = "ORDER_NO")
    private String orderNo;

    @Column(name = "MOVIE_NAME")
    private String movieName;

    @Column(name = "CINEMA")
    private String cinema;

    @Column(name = "MOVIEDATE")
    private String movieDate;

    @Column(name = "MOVIETIME")
    private String movieTime;

    @Column(name = "SEATS")
    private String seats;

    @Column(name = "TICKET_COUNT")
    private Integer ticketCount;

    @Column(name = "TICKETPRICE")
    private Integer ticketPrice;

    @Column(name = "SERVICEFEE")
    private Integer serviceFee;

    @Column(name = "TOTAL_PRICE")
    private Integer totalPrice;

    @Column(name = "BUYERNAME")
    private String buyerName;

    @Column(name = "BUYERPHONE")
    private String buyerPhone;

    @Column(name = "BUYEREMAIL")
    private String buyerEmail;

    @Column(name = "INVOICETYPE")
    private String invoiceType;

    @Column(name = "INVOICECARRIER")
    private String invoiceCarrier;

    @Column(name = "MOBILEBARCODE")
    private String mobileBarcode;

    @Column(name = "COMPANYNAME")
    private String companyName;

    public String getOrderNo() {
        return orderNo;
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

    public Integer getTicketCount() {
        return ticketCount;
    }

    public Integer getTicketPrice() {
        return ticketPrice;
    }

    public Integer getServiceFee() {
        return serviceFee;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public String getBuyerPhone() {
        return buyerPhone;
    }

    public String getBuyerEmail() {
        return buyerEmail;
    }

    public String getInvoiceType() {
        return invoiceType;
    }

    public String getInvoiceCarrier() {
        return invoiceCarrier;
    }

    public String getMobileBarcode() {
        return mobileBarcode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public void setCinema(String cinema) {
        this.cinema = cinema;
    }

    public void setMovieDate(String movieDate) {
        this.movieDate = movieDate;
    }

    public void setMovieTime(String movieTime) {
        this.movieTime = movieTime;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }

    public void setTicketCount(Integer ticketCount) {
        this.ticketCount = ticketCount;
    }

    public void setTicketPrice(Integer ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public void setServiceFee(Integer serviceFee) {
        this.serviceFee = serviceFee;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public void setBuyerPhone(String buyerPhone) {
        this.buyerPhone = buyerPhone;
    }

    public void setBuyerEmail(String buyerEmail) {
        this.buyerEmail = buyerEmail;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }

    public void setInvoiceCarrier(String invoiceCarrier) {
        this.invoiceCarrier = invoiceCarrier;
    }

    public void setMobileBarcode(String mobileBarcode) {
        this.mobileBarcode = mobileBarcode;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

}
