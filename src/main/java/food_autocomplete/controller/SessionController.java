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
        movie.put("actors", "高山南、山崎和佳奈、小山力也、林原惠美");
        movie.put("genre", "動畫");
        movie.put("description",
                "本片為柯南劇場版系列第29彈作品，由蓮井隆弘執導，大倉崇裕編劇。" +
                        "故事描述柯南、小蘭、園子、小五郎一行人前往橫濱港未來，參加車界盛會「神奈川機車大展」，" +
                        "並且在那裡與熱愛重機的世良真純會合。就在這時候，突然出現一台暴走的神秘黑色機車，" +
                        "從柯南他們搭乘的車輛上空飛越而過，而緊追在後的，是小蘭之前曾見過的「風之女神」神奈川縣警交通機動隊成員萩原千速。" +
                        "在經歷一場激烈的追逐戰之後，千速騎乘的機車嚴重受損，僅只一步之差，最後還是被犯人逃脫了。");
        movie.put("trailer", "https://www.youtube.com/embed/CsxkouRl7Yo");

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
        movie2.put("actors", "提姆艾倫 Blake Clark");
        movie2.put("genre", "動畫");
        movie2.put("description",
                "胡迪、巴斯光年、翠絲與其他玩具夥伴再度回歸。這一次，他們面臨的對手不再只是其他玩具，" +
                        "而是一台名為 Lilypad 的全新智慧平板。隨著邦妮越來越沉迷於電子裝置，玩具們原本陪伴孩子、" +
                        "帶來歡樂的角色也受到前所未有的挑戰。面對科技改變孩子玩樂方式的新時代，" +
                        "胡迪、巴斯與翠絲必須重新思考玩具存在的意義，並努力守護屬於孩子與玩具之間的珍貴情感。");
        movie2.put("trailer", "https://www.youtube.com/embed/yKoD02D_8mI");

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
        movie3.put("actors", "雅各布西皮奧、盧洛貝爾、梅莉莎李歐");
        movie3.put("genre", "驚悚");
        movie3.put("description",
                "一對年輕情侶在目睹一場血腥駭人的高速公路事故之後，很快就發現他們無法獨自離開車禍現場，因為一個名" +
                        "為“鬼乘客”的惡魔般跟蹤者無論如何都要奪取他們的性命，於是他們原本展開的廂型車旅行生活就變成了一" +
                        "場噩夢。");
        movie3.put("trailer", "https://www.youtube.com/embed/M-5sKSxzbAA");

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
        movie4.put("actors", "全智賢、具教煥、池昌旭、申鉉彬、金新綠、高洙");
        movie4.put("genre", "驚悚");
        movie4.put("description",
                "首爾市中心的一棟摩天大樓發生原因不明的集體感染事件。起初如野獸般爬行的感染者逐漸進化，開始以雙腳" +
                        "步行，並能分辨人類，進而成群結隊地攻擊倖存者。生物工程師權世貞（全智賢 飾）和倖存者們，尋找聲稱已" +
                        "在自己體內注射疫苗的徐英哲（具教煥 飾），並前往救難隊所在的頂樓。然而愈往上走，情況愈令人難以預" +
                        "測，徐英哲竟利用感染者阻擋倖存者們的去路……");
        movie4.put("trailer", "https://www.youtube.com/embed/EU0BkX3Sepw");

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
        movie5.put("actors", "渡邊久美子、小櫻悅子、中田讓治、子安武人、草尾毅、桑島法子、齋藤千和、平松晶子、Jesse");
        movie5.put("genre", "動畫");
        movie5.put("description",
                "原本應該忙著侵略地球，卻依舊過著懶散日子的 Keroro 小隊，" +
                        "某天突然遇上在澀谷大量出現的神祕妖怪，隨後日本全國各地也接連發生各種不可思議的異常現象。" +
                        "事件現場不斷出現神祕文字，背後更浮現出一名神祕天才發明家的身影。" +
                        "察覺新的侵略者可能正威脅地球後，Keroro 小隊決定賭上身為侵略者的尊嚴挺身而出。" +
                        "然而此時，Keroro 一行人面前又出現了名為阿爾爾與德爾爾的 Keron 星人兄弟。" +
                        "面對系列史上最強大的敵人，Keroro 小隊將展開一場攸關地球命運、史上最大規模的戰鬥。");
        movie5.put("trailer", "https://www.youtube.com/embed/VPCMgv0Rz24");

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
        movie6.put("actors", "(配音)神木隆之介、上白石萌音、長澤雅美、市原悅子、成田凌悠木碧、島崎信長、石川界人、谷花音");
        movie6.put("genre", "動畫");
        movie6.put("description",
                "住在深山小鎮的女高中生宮水三葉（上白石萌音 飾），夢見自己變成東京男高中生。憧憬著都市生活的三葉，總在夢中盡情享受都市生活。而住在東京的男高中生" +
                        "立花瀧（神木隆之介 飾），則夢見了自己成為深山小鎮的女高中生。原來他們兩人的身心竟然都對調了，知道彼此存在後的瀧和三葉，終於知道了意外的真相……");
        movie6.put("trailer", "https://www.youtube.com/embed/MR13sXmeXJs");

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
        movie7.put("actors", "小林由美子 楢橋美紀 森川智之 興梠里美 伊藤沙莉 梶裕貴");
        movie7.put("genre", "動畫");
        movie7.put("description", "某個夏天，日本各地接連出現怪異現象而引發騷動。在這之中，野原一家決定要回廣志的故鄉秋田縣老家。在準備行李時，有奇怪的影子正從庭院的暗處偷偷" +
                "看著無憂無慮地玩著鬼抓人遊戲的小新。抵達秋田的野原一家，在爺爺．銀之介的家中度過安穩的時光。隔天早上，上面寫著「妖怪假期村」的傳單竟散落在" +
                "小新一家就寢的房間。在小新的請求下，野原一家決定前往妖怪假期村，沒想到卻在杳無人煙的深山中闖入一個奇妙的空間，而那裡就是禁止人類進入的「妖" +
                "怪之國」的入口……");
        movie7.put("trailer", "https://www.youtube.com/embed/pnjYxnn9Xm4");

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
        movie8.put("actors", "湯姆霍蘭德 辛蒂亞");
        movie8.put("genre", "劇情");
        movie8.put("description",
                "在這集中，在一個再也沒有人知道他是誰的紐約，他全心投入打擊犯罪，成為一名「全職蜘蛛人」，將守護城市視為唯一使命。然而，隨著責任與壓力不斷升" +
                        "高，他的身體竟出現出乎意料的變化，甚至威脅到他的存在本身。同時，一連串詭異的新型犯罪逐漸浮現，並引出一股前所未見的強大威脅，讓他面臨至今最" +
                        "艱鉅的挑戰。");
        movie8.put("trailer", "https://www.youtube.com/embed/Upn2ejyAvnM");

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
        movie9.put("actors", "水田山葵、大原惠、嘉數由美、木村昴、關智一、千葉翔也、廣橋涼");
        movie9.put("genre", "動畫");
        movie9.put("description",
                "大雄與朋友們對於暑假的露營地點意見不一，最後他們決定採納哆啦A夢的建議，前往大海中央露營！借助秘密道具「海底車」和「適應燈」的力量，五人展開海" +
                        "底露營之旅，與各式各樣的海底生物相遇。後來，他們發現了一艘沉船，並遇到一位神祕青年艾魯。原來，他是來自領土廣闊的海底王國「姆聯邦」的海底人！因" +
                        "為討厭陸上人，所以海底人對大雄一行人充滿戒心。這時，傳來了「鬼岩城…開始活動了！」的消息。這個讓海底人畏懼的鬼岩城究竟是什麼？懷抱著對夥伴的信" +
                        "任，大雄一行人展開足以決定地球命運的大冒險！");
        movie9.put("trailer", "https://www.youtube.com/embed/t1xLVsUPm-c");

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
