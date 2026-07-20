package food_autocomplete.BookingDTO;

import java.util.List;

public class BookingDTO {
    private String orderNo;
    private String movieName;
    private String cinema;
    private String movieDate;
    private String movieTime;

    private List<String> seats;
    private Integer ticketCount;

    private Integer ticketPrice;
    private Integer ServiceFee;
    private Integer totalPrice;

    private String buyerName;
    private String buyerPhone;
    private String buyerEmail;

    private String invoiceType;
    private String invoiceCarrier;
    private String mobileBarcode;
    private String companyName;

    private String companyTaxId;
    private String paymentMethod;
    private String paymentStatus;

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

    public List<String> getSeats() {
        return seats;
    }

    public Integer getTicketCount() {
        return ticketCount;
    }

    public Integer getTicketPrice() {
        return ticketPrice;
    }

    public Integer getServiceFee() {
        return ServiceFee;
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

    public String getCompanyTaxId() {
        return companyTaxId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getPaymentStatus() {
        return paymentStatus;
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

    public void setSeats(List<String> seats) {
        this.seats = seats;
    }

    public void setTicketCount(Integer ticketCount) {
        this.ticketCount = ticketCount;
    }

    public void setTicketPrice(Integer ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public void setServiceFee(Integer serviceFee) {
        ServiceFee = serviceFee;
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

    public void setCompanyTaxId(String companyTaxId) {
        this.companyTaxId = companyTaxId;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

}