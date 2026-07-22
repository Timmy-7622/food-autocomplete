package food_autocomplete.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessionController {
    @GetMapping("/sessions")
    public List<Map<String, Object>> getSessions() {
        List<Map<String, Object>> sessions = new ArrayList<>();

        Map<String, Object> movie = new HashMap<>();
        movie.put("id", 1);
        movie.put("movieName", "名偵探柯南 高速公路的墮天使");
        movie.put("englishName", "Detective Conan the Movie : Fallen Angel of the Highway");
        movie.put("poster", "images/conan.jpg");
        movie.put("cinema", "泰順店");
        movie.put("date", "2026-06-24");
        movie.put("weekday", "星期三");
        movie.put("rating", "普遍級");
        movie.put("duration", "109分鐘");
        movie.put("director", "蓮井隆弘");

        List<Map<String, Object>> formats = new ArrayList<>();

        Map<String, Object> format1 = new HashMap<>();
        format1.put("language", "數位/中文");
        format1.put("times", List.of("13:30"));

        Map<String, Object> format2 = new HashMap<>();
        format2.put("language", "數位/日語");
        format2.put("times", List.of("10:30", "12:40", "14:50", "17:00", "19:10", "21:20"));

        formats.add(format1);
        formats.add(format2);

        movie.put("formats", formats);
        sessions.add(movie);

        Map<String, Object> movie2 = new HashMap<>();
        movie2.put("id", 2);
        movie2.put("movieName", "玩具總動員5");
        movie2.put("englishName", "Toy Story 5");
        movie2.put("poster", "images/demon.jpg");
        movie2.put("cinema", "泰順店");
        movie2.put("date", "2026-06-17");
        movie2.put("weekday", "星期三");
        movie2.put("rating", "普遍級");
        movie2.put("duration", "105分鐘");
        movie2.put("director", "安德魯史坦頓");

        List<Map<String, Object>> formats2 = new ArrayList<>();

        Map<String, Object> format3 = new HashMap<>();
        format3.put("language", "數位/中文");
        format3.put("times", List.of("11:00", "15:20", "20:30"));

        Map<String, Object> format4 = new HashMap<>();
        format4.put("language", "IMAX/英文");
        format4.put("times", List.of("18:00"));

        formats2.add(format3);
        formats2.add(format4);

        movie2.put("formats", formats2);

        sessions.add(movie2);

        Map<String, Object> movie3 = new HashMap<>();
        movie3.put("id", 3);
        movie3.put("movieName", "鬼上車");
        movie3.put("englishName", "Passenger");
        movie3.put("poster", "images/Passenger.jpg");
        movie3.put("cinema", "泰順店");
        movie3.put("date", "2026-06-18");
        movie3.put("weekday", "星期三");
        movie3.put("rating", "輔15級");
        movie3.put("duration", "94分鐘");
        movie3.put("director", "安德烈艾弗道夫");

        List<Map<String, Object>> formats3 = new ArrayList<>();
        Map<String, Object> format5 = new HashMap<>();
        format5.put("language", "數位/英文");
        format5.put("times", List.of("11:00", "12:10", "15:20", "17:35", "20:30", "22:20", "23:40"));
        Map<String, Object> format6 = new HashMap<>();
        format6.put("language", "IMAX/英文");
        format6.put("times", List.of("12:20", "18:00", "23:50"));
        formats3.add(format5);
        formats3.add(format6);
        movie3.put("formats", formats3);
        sessions.add(movie3);

        Map<String, Object> movie4 = new HashMap<>();
        movie4.put("id", 4);
        movie4.put("movieName", "屍速禁區");
        movie4.put("englishName", "Colony");
        movie4.put("poster", "images/Colony.jpg");
        movie4.put("cinema", "泰順店");
        movie4.put("date", "2026-05-22");
        movie4.put("weekday", "星期三");
        movie4.put("rating", "輔15級");
        movie4.put("duration", "123分鐘");
        movie4.put("director", "延尚昊");

        List<Map<String, Object>> formats4 = new ArrayList<>();

        Map<String, Object> format7 = new HashMap<>();
        format7.put("language", "數位/韓文");
        format7.put("times", List.of("10:10", "12:30", "13:25", "14:50", "17:10", "17:40", "19:30", "21:50"));
        formats4.add(format7);
        movie4.put("formats", formats4);
        sessions.add(movie4);

        Map<String, Object> movie5 = new HashMap<>();
        movie5.put("id", 5);
        movie5.put("movieName", "新劇場版 Keroro軍曹 復活後即面臨地球滅亡大危機是也！");
        movie5.put("englishName", "KeroroSession");
        movie5.put("poster", "images/keroro.jpg");
        movie5.put("cinema", "泰順店");
        movie5.put("date", "2026-07-22");
        movie5.put("weekday", "星期三");
        movie5.put("rating", "普遍級");
        movie5.put("duration", "126分鐘");
        movie5.put("director", "追崎史敏");

        List<Map<String, Object>> formats5 = new ArrayList<>();

        Map<String, Object> format8 = new HashMap<>();
        format8.put("language", "數位/日文");
        format8.put("times", List.of("08:00", "8:45", "10:25", "11:50", "12:10", "14:40", "15:30", "18:50", "20:15",
                "21:30", "22:10"));
        formats5.add(format8);
        movie5.put("formats", formats5);
        sessions.add(movie5);
        return sessions;

    }
}
