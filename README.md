# Food AutoComplete

使用 Vue + Spring Boot + Oracle 製作的餐點名稱自動補全系統。

---

## 功能介紹

- 餐點名稱自動補全
- 即時搜尋（AJAX）
- Vue 3 前端互動
- Spring Boot REST API
- Oracle Database
- Spring Data JPA
- Caffeine Cache 快取
- 鍵盤上下選擇
- 點外面自動關閉清單
- UI 美化

---

## 使用技術

### Frontend

- Vue 3
- HTML
- CSS
- JavaScript

### Backend

- Spring Boot
- Spring Data JPA
- REST API

### Database

- Oracle Database

### Cache

- Caffeine Cache

### Version Control

- Git
- GitHub

---

## 專案結構

```text
food_autocomplete
├─ controller
├─ service
├─ repository
├─ entity
```

---

## API

### 搜尋餐點

```http
GET /foodcos/search?keyword=牛
```

---

## 啟動方式

### 啟動 Spring Boot

```bash
.\mvnw.cmd spring-boot:run
```

---

## autocomplete 功能流程

```text
Vue Input
↓
AJAX Fetch
↓
Controller
↓
Service(Cache)
↓
Repository
↓
Oracle Database
```

---

## 畫面功能

- autocomplete 下拉選單
- hover 效果
- focus 效果
- keyboard navigation
- debounce
- click outside close

---

## 作者

Timmy Chen
