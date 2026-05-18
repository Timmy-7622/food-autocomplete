package food_autocomplete.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import food_autocomplete.service.FoodcosService;
import food_autocomplete.entity.Foodcos;

//告訴 Spring 這個類別是一個「控制器」，專門處理 HTTP 請求
@RestController
public class FoodcosController {
    @Autowired
    private FoodcosService foodcosService;

    // @RequestParam用來接收網址（URL）後面的參數。
    @GetMapping("/foodcos/search")
    public List<Foodcos> searchFood(@RequestParam("keyword") String keyword) {
        return foodcosService.searchFood(keyword);
    }
}
