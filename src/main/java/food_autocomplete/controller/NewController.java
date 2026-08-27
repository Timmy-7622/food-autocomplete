package food_autocomplete.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//RestController 告訴SpringBoot 這個class是專門接收網頁Http請求、回傳資料的Controller
@RestController
public class NewController {
    @GetMapping("/news")
    public List<Map<String, Object>> getNews() {
        List<Map<String, Object>> news = new ArrayList<>();

        Map<String, Object> new1 = new HashMap<>();
        new1.put("id", 1);
        new1.put("title", "【特典】電影哆啦A夢：新‧大雄的海底鬼岩城");
        new1.put("images", "./images/Doraemon1.jpg");
        new1.put("date", "2026-08-18");
        new1.put("description",
                "凡購買 指定時間 場次電影票１張\n\n" +
                        "凡購買 指定時間 場次電影票１張\n\n" +
                        "＊８／２１ 開放兌換，每人限兌換四張，數量有限，送完為止。\n\n" +
                        "注意事項：\n" +
                        "①圖片僅供參考，贈品以現場實物為主，請於當下確認品項狀況，離開後恕不接受更換。\n" +
                        "②電影交換券、電影預售票適用本活動。\n" +
                        "③團劃/包廳、影城免費兌換券、影城特殊活動票券恕不適用本活動。\n" +
                        "④贈品領取以現場兌換順序為主，數量有限，送完為止，網路及APP購票之觀眾，請盡早至觀影影城取票。\n" +
                        "⑤如有退/換票，需連同將贈品一併辦理退/換票手續，如票券/贈品損毀恕無法辦理退票。\n" +
                        "⑥主辦單位保有活動最終解釋權，活動詳情請洽影城官網或現場告示。\n");

        news.add(new1);

        Map<String, Object> new2 = new HashMap<>();
        new2.put("id", 2);
        new2.put("title", "【特典】蜘蛛人：重生日");
        new2.put("images", "./images/spiderman1.jpg");
        new2.put("date", "2026-08-19");
        new2.put("description",
                "凡購買 指定時間 場次電影票１張\n\n" +
                        "即可兌換「琴葛雷款A3工藝海報」１張。\n\n" +
                        "＊８／２８ 開放兌換，每人限兌換4張，數量有限，送完為止。\n\n" +
                        "注意事項：\n" +
                        "①圖片僅供參考，贈品以現場實物為主，請於當下確認品項狀況，離開後恕不接受更換。\n" +
                        "②電影交換券、電影預售票適用本活動。\n" +
                        "③團劃/包廳、影城免費兌換券、影城特殊活動票券恕不適用本活動。\n" +
                        "④贈品領取以現場兌換順序為主，數量有限，送完為止，網路及APP購票之觀眾，請盡早至觀影影城取票。\n" +
                        "⑤如有退/換票，需連同將贈品一併辦理退/換票手續，如票券/贈品損毀恕無法辦理退票。\n" +
                        "⑥主辦單位保有活動最終解釋權，活動詳情請洽影城官網或現場告示。");

        news.add(new2);

        Map<String, Object> new3 = new HashMap<>();
        new3.put("id", 3);
        new3.put("title", "【特典】電影蠟筆小新：奇奇怪怪！我的妖怪假期");
        new3.put("images", "./images/Hsinnosuke.jpg");
        new3.put("date", "2026-08-18");
        new3.put("description",
                "凡購買 指定時間 場次電影票１張\n\n" +
                        "即可兌換「電影主視覺海報」１張。\n\n" +
                        "＊８／２１ 開放兌換，每人限兌換4張，數量有限，送完為止。\n\n" +
                        "注意事項：\n" +
                        "①圖片僅供參考，贈品以現場實物為主，請於當下確認品項狀況，離開後恕不接受更換。\n" +
                        "②電影交換券、電影預售票適用本活動。\n" +
                        "③團劃/包廳、影城免費兌換券、影城特殊活動票券恕不適用本活動。\n" +
                        "④贈品領取以現場兌換順序為主，數量有限，送完為止，網路及APP購票之觀眾，請盡早至觀影影城取票。\n" +
                        "⑤如有退/換票，需連同將贈品一併辦理退/換票手續，如票券/贈品損毀恕無法辦理退票。\n" +
                        "⑥主辦單位保有活動最終解釋權，活動詳情請洽影城官網或現場告示。");

        news.add(new3);

        Map<String, Object> new4 = new HashMap<>();
        new4.put("id", 4);
        new4.put("title", "【特典】劇場版 吉伊卡哇 人魚島的秘密");
        new4.put("images", "./images/jiigawa.jpg");
        new4.put("date", "2026-08-20");
        new4.put("description",
                "凡購買 指定時間 場次電影票１張\n\n" +
                        "即可兌換「好心情賽蓮♪迴力車」１個。（三款隨機贈一款）\n\n" +
                        "＊８／２８ 開放兌換，每人限兌換4張，數量有限，送完為止。\n\n" +
                        "注意事項：\n" +
                        "①圖片僅供參考，贈品以現場實物為主，請於當下確認品項狀況，離開後恕不接受更換。\n" +
                        "②電影交換券、電影預售票適用本活動。\n" +
                        "③團劃/包廳、影城免費兌換券、影城特殊活動票券恕不適用本活動。\n" +
                        "④贈品領取以現場兌換順序為主，數量有限，送完為止，網路及APP購票之觀眾，請盡早至觀影影城取票。\n" +
                        "⑤如有退/換票，需連同將贈品一併辦理退/換票手續，如票券/贈品損毀恕無法辦理退票。\n" +
                        "⑥主辦單位保有活動最終解釋權，活動詳情請洽影城官網或現場告示。");

        news.add(new4);

        Map<String, Object> new5 = new HashMap<>();
        new5.put("id", 5);
        new5.put("title", "【特典】一夜限定");
        new5.put("images", "./images/sex.jpg");
        new5.put("date", "2026-08-10");
        new5.put("description",
                "凡購買 指定時間 場次電影票１張\n\n" +
                        "即可兌換「電影主視覺海報」１張。\n\n" +
                        "＊８／２１ 開放兌換，每人限兌換4張，數量有限，送完為止。\n\n" +
                        "注意事項：\n" +
                        "①圖片僅供參考，贈品以現場實物為主，請於當下確認品項狀況，離開後恕不接受更換。\n" +
                        "②電影交換券、電影預售票適用本活動。\n" +
                        "③團劃/包廳、影城免費兌換券、影城特殊活動票券恕不適用本活動。\n" +
                        "④贈品領取以現場兌換順序為主，數量有限，送完為止，網路及APP購票之觀眾，請盡早至觀影影城取票。\n" +
                        "⑤如有退/換票，需連同將贈品一併辦理退/換票手續，如票券/贈品損毀恕無法辦理退票。\n" +
                        "⑥主辦單位保有活動最終解釋權，活動詳情請洽影城官網或現場告示。");

        news.add(new5);

        Map<String, Object> new6 = new HashMap<>();
        new6.put("id", 6);
        new6.put("title", "【特典】玩命航線");
        new6.put("images", "./images/MUTINY.jpg");
        new6.put("date", "2026-08-14");
        new6.put("description",
                "凡購買 指定時間 場次電影票１張\n\n" +
                        "即可兌換「電影主視覺原文海報」１張。\n\n" +
                        "＊８／１９ 開放兌換，每人限兌換4張，數量有限，送完為止。\n\n" +
                        "注意事項：\n" +
                        "①圖片僅供參考，贈品以現場實物為主，請於當下確認品項狀況，離開後恕不接受更換。\n" +
                        "②電影交換券、電影預售票適用本活動。\n" +
                        "③團劃/包廳、影城免費兌換券、影城特殊活動票券恕不適用本活動。\n" +
                        "④贈品領取以現場兌換順序為主，數量有限，送完為止，網路及APP購票之觀眾，請盡早至觀影影城取票。\n" +
                        "⑤如有退/換票，需連同將贈品一併辦理退/換票手續，如票券/贈品損毀恕無法辦理退票。\n" +
                        "⑥主辦單位保有活動最終解釋權，活動詳情請洽影城官網或現場告示。");

        news.add(new6);

        return news;

    }
}