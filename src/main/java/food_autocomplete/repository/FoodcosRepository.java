package food_autocomplete.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import food_autocomplete.entity.Foodcos;

public interface FoodcosRepository extends JpaRepository<Foodcos, Integer> {
    // 查詢名字+status = 上架的商品
    List<Foodcos> findByNameContainingAndStatus(String name, String status);

}
