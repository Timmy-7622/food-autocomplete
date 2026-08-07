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
        movie.put("poster", "images/konan.jpg");
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

        Map<String, Object> movie6 = new HashMap<>();
        movie6.put("id", 6);
        movie6.put("movieName", "你的名字。");
        movie6.put("englishName", "YOUR NAME.");
        movie6.put("poster", "images/yourname.jpg");
        movie6.put("cinema", "泰順店");
        movie6.put("date", "2026-07-24");
        movie6.put("weekday", "星期五");
        movie6.put("rating", "普遍級");
        movie6.put("duration", "106分鐘");
        movie6.put("director", "新海誠");

        List<Map<String, Object>> formats6 = new ArrayList<>();

        Map<String, Object> format9 = new HashMap<>();
        format9.put("language", "數位/日文");
        format9.put("times", List.of("10:00", "15:45", "18:50", "20:15"));
        Map<String, Object> format10 = new HashMap<>();
        format10.put("language", "數位/中文");
        format10.put("times", List.of("20:00"));
        formats6.add(format9);
        formats6.add(format10);
        movie6.put("formats", formats6);
        sessions.add(movie6);

        Map<String, Object> movie7 = new HashMap<>();
        movie7.put("id", 7);
        movie7.put("movieName", "電影蠟筆小新：奇奇怪怪！我的妖怪假期");
        movie7.put("englishName", "Crayon Shinchan the Movie：Spooky! My Yokai Vacation");
        movie7.put("poster", "images/CrayonShinchan.jpg");
        movie7.put("cinema", "泰順店");
        movie7.put("date", "2026-08-07");
        movie7.put("weekday", "星期五");
        movie7.put("rating", "普遍級");
        movie7.put("duration", "101分鐘");
        movie7.put("director", "渡邊正樹");

        List<Map<String, Object>> formats7 = new ArrayList<>();

        Map<String, Object> format11 = new HashMap<>();
        format11.put("language", "數位/日文");
        format11.put("times", List.of("11:45", "19:25"));
        Map<String, Object> format12 = new HashMap<>();
        format12.put("language", "數位/中文");
        format12.put("times", List.of("09:50", "13:40", "15:45", "17:30"));
        formats7.add(format11);
        formats7.add(format12);
        movie7.put("formats", formats7);
        sessions.add(movie7);

        Map<String, Object> movie8 = new HashMap<>();
        movie8.put("id", 8);
        movie8.put("movieName", "蜘蛛人：重生日");
        movie8.put("englishName", "Spider-Man: Brand New Day");
        movie8.put("poster", "images/spiderman.jpg");
        movie8.put("cinema", "泰順店");
        movie8.put("date", "2026-07-29");
        movie8.put("weekday", "星期三");
        movie8.put("rating", "保護級");
        movie8.put("duration", "145分鐘");
        movie8.put("director", "達斯汀丹尼爾克雷頓");

        List<Map<String, Object>> formats8 = new ArrayList<>();

        Map<String, Object> format13 = new HashMap<>();
        format13.put("language", "數位/英文");
        format13.put("times", List.of("09:50", "10:20", "11:40", "12:10", "12:35", "13:10", "14:25", "15:00", "15:20",
                "16:00", "16:30", "17:10", "17:45", "18:10", "18:45", "19:20", "20:00", "20:30", "21:00", "21:30"));
        formats8.add(format13);
        movie8.put("formats", formats8);
        sessions.add(movie8);

        Map<String, Object> movie9 = new HashMap<>();
        movie9.put("id", 9);
        movie9.put("movieName", "電影哆啦A夢：新‧大雄的海底鬼岩城");
        movie9.put("englishName", "New Nobita and the Castle of the Undersea Devil");
        movie9.put("poster", "images/doraemon.jpg");
        movie9.put("cinema", "泰順店");
        movie9.put("date", "2026-07-08");
        movie9.put("weekday", "星期三");
        movie9.put("rating", "普遍級");
        movie9.put("duration", "102分鐘");
        movie9.put("director", "矢嶋哲生");

        List<Map<String, Object>> formats9 = new ArrayList<>();

        Map<String, Object> format14 = new HashMap<>();
        format14.put("language", "數位/日文");
        format14.put("times", List.of("14:50", "15:30", "19:40", "21:10"));
        formats9.add(format14);
        movie9.put("formats", formats9);
        sessions.add(movie9);

        return sessions;

    }
}
