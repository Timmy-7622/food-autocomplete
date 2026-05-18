package food_autocomplete.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import food_autocomplete.entity.Foodcos;
import food_autocomplete.repository.FoodcosRepository;

@Service
public class FoodcosService {

    @Autowired
    private FoodcosRepository foodcosRepository;

    // 用 keyword 當 key，把查詢結果存進名叫 foodSearch 的快取區。
    @Cacheable(value = "foodSearch", key = "#keyword")
    public List<Foodcos> searchFood(String keyword) {
        System.out.println("查資料庫 keyword = " + keyword);
        return foodcosRepository.findByNameContainingAndStatus(keyword, "上架");
    }

}
