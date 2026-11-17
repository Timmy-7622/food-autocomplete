package food_autocomplete.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import food_autocomplete.dto.BookingDTO;
import food_autocomplete.dto.SoldSeatResponseDTO;
import food_autocomplete.entity.BookingOrder;
import food_autocomplete.entity.Member;
import food_autocomplete.repository.BookingOrderRepository;
import food_autocomplete.service.MemberService;

// 當你在一個類別的上方加上 @RestController，這個類別就變成了一個 Web 伺服器的後端接口，可以接收前端發送過來的 HTTP 請求
@RestController
public class BookingController {

    @Autowired
    private BookingOrderRepository bookingOrderRepository;
    @Autowired
    private MemberService memberService;

    @PostMapping("/booking")
    // 這裡的 Authentication 是 Spring Security 提供的登入身分資訊。
    public Map<String, Object> booking(@RequestBody BookingDTO dto, Authentication authentication) {
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

        order.setSeats(String.join((","), dto.getSeats()));
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

        // 確認目前有已登入的會員
        if (authentication == null || !authentication.isAuthenticated()
                || "anonymousUser".equals(authentication.getPrincipal())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
                    "請先登入會員");
        }
        String account = authentication.getName();
        Member member = memberService.findByAccount(account);
        order.setMemberId(member.getMemberId());
        System.out.println("準備存資料庫");
        bookingOrderRepository.save(order);
        System.out.println("存資料庫完成");

        Map<String, Object> result = new HashMap<>();
        result.put("status", "success");
        result.put("message", "訂票成功");
        result.put("orderNo", dto.getOrderNo());

        return result;
    }

    @GetMapping("/booking/list")
    public List<SoldSeatResponseDTO> getAll() {
        List<BookingOrder> orders = bookingOrderRepository.findAll();
        // stream() 是 Java 提供的資料處理功能，可以對集合中的元素進行轉換、篩選等操作。
        // 你可以先把它理解成： 準備依序處理 orders 裡面的每一筆資料。
        return orders.stream()
                .map(order -> new SoldSeatResponseDTO(
                        order.getMovieName(),
                        order.getCinema(),
                        order.getMovieDate(),
                        order.getMovieTime(),
                        order.getSeats()))
                .toList();
        // 最後的 toList() 就是把轉換結果收集成一個新的 List。
    }

    @GetMapping("/booking/my")
    public List<BookingOrder> getMyBookings(Authentication authentication) {
        String account = authentication.getName();
        Member member = memberService.findByAccount(account);
        return bookingOrderRepository.findByMemberId(member.getMemberId());
    }

}
