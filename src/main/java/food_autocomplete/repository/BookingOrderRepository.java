package food_autocomplete.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import food_autocomplete.entity.BookingOrder;

public interface BookingOrderRepository extends JpaRepository<BookingOrder, String> {

    List<BookingOrder> findByMemberId(Long memberId);
}
