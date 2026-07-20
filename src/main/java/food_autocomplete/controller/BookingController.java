package food_autocomplete.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import food_autocomplete.BookingDTO.BookingDTO;
import food_autocomplete.entity.BookingOrder;
import food_autocomplete.repository.BookingOrderRepository;

// 當你在一個類別的上方加上 @RestController，這個類別就變成了一個 Web 伺服器的後端接口，可以接收前端發送過來的 HTTP 請求
@RestController
public class BookingController {

    @Autowired
    private BookingOrderRepository bookingOrderRepository;

    @PostMapping("/booking")
    public Map<String, Object> booking(@RequestBody BookingDTO dto) {
        System.out.println("===========接收到訂單=============");
        System.out.println(dto.getOrderNo());
        System.out.println(dto.getMovieName());
        System.out.println(dto.getCinema());
        System.out.println(dto.getMovieDate());
        System.out.println(dto.getMovieTime());

        System.out.println(dto.getSeats());
        System.out.println(dto.getTicketCount());

        System.out.println(dto.getTicketPrice());
        System.out.println(dto.getServiceFee());
        System.out.println(dto.getTotalPrice());

        System.out.println(dto.getBuyerName());
        System.out.println(dto.getBuyerPhone());
        System.out.println(dto.getBuyerEmail());

        System.out.println(dto.getInvoiceType());
        System.out.println(dto.getInvoiceCarrier());
        System.out.println(dto.getMobileBarcode());
        System.out.println(dto.getCompanyName());

        BookingOrder order = new BookingOrder();
        order.setOrderNo(dto.getOrderNo());

        order.setMovieName(dto.getMovieName());
        order.setCinema(dto.getCinema());
        order.setMovieDate(dto.getMovieDate());
        order.setMovieTime(dto.getMovieTime());

        order.setSeats(dto.getSeats());
        order.setTicketCount(dto.getTicketCount());
        order.setTicketPrice(dto.getTicketPrice());
        order.setServiceFee(dto.getServiceFee());
        order.setTotalPrice(dto.getTotalPrice());

        order.setBuyerName(dto.getBuyerName());
        order.setBuyerPhone(dto.getBuyerPhone());
        order.setBuyerEmail(dto.getBuyerEmail());

        order.setInvoiceType(dto.getInvoiceType());
        order.setInvoiceCarrier(dto.getInvoiceCarrier());
        order.setMobileBarcode(dto.getMobileBarcode());
        order.setCompanyName(dto.getCompanyName());

        System.out.println("準備存資料庫");
        bookingOrderRepository.save(order);
        System.out.println("存資料庫完成");

        Map<String, Object> result = new HashMap<>();
        result.put("status", "succes");
        result.put("mesage", "訂票成功");
        result.put("orderNo", dto.getOrderNo());

        return result;
    }

    @GetMapping("/booking/list")
    public List<BookingOrder> getAll() {

        return bookingOrderRepository.findAll();
    }

}
