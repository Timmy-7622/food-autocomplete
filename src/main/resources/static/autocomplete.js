const { createApp } = Vue;
createApp({
  data() {
    return {
      keyword: "",
      results: [],
      showList: false,
      seats: [],
      seatRows: ["A", "B", "C", "D", "E", "F", "G"],
      showModal: false,
      modalMessage: "",
      modalType: "error",
      orders: [],
      showOrders: false,
      currentStep: 1,
      bookingInfo: {
        movieName: "",
        cinema: "",
        date: "",
        weekday: "",
        language: "",
        time: "",
        seats: [],
        totalPrice: 0,
        ticketCount: 0,
      },

      sessions: [
        {
          id: 1,
          movieName: "名偵探柯南 高速公路的墮天使",
          englishName:
            "Detective Conan the Movie : Fallen Angel of the Highway",
          poster: "images/conan.jpg",
          cinema: "泰順店",
          date: "2026-06-24",
          weekday: "星期三",
          rating: "普遍級",
          duration: "109分鐘",
          director: "蓬井隆弘",

          formats: [
            {
              language: "數位/中文",
              times: ["13:30"],
            },
            {
              language: "數位/日語",
              times: ["10:30", "12:40", "14:50", "17:00", "19:10", "21:20"],
            },
          ],
        },
      ],
    };
  },
  // 計算後的資料 filter->篩選
  computed: {
    selectedSeats() {
      return this.seats.filter((seat) => seat.status === "selected");
    },

    totalPrice() {
      return this.selectedSeats.length * 360;
    },
  },
  methods: {
    selectSession(session, format, time) {
      this.bookingInfo.movieName = session.movieName;
      this.bookingInfo.englishName = session.englishName;
      this.bookingInfo.cinema = session.cinema;
      this.bookingInfo.date = session.date;
      this.bookingInfo.weekday = session.weekday;
      this.bookingInfo.language = format.language;
      this.bookingInfo.time = time;

      this.currentStep = 2;
    },
    closeModal() {
      this.showModal = false;
      if (this.modalType === "success") {
        this.currentStep = 3;
      }
    },
    loadSoldSeatsOnStart() {
      fetch("http://localhost:18080/booking/list")
        .then((response) => response.json())
        .then((data) => {
          this.orders = data;
          this.searchSoldSeatsFormOrders();
        })
        .catch((err) => {
          console.log(err);
        });
    },
    searchSoldSeatsFormOrders() {
      this.orders.forEach((order) => {
        const seatIds = order.seats.split(",");

        seatIds.forEach((seatId) => {
          const seat = this.seats.find((s) => s.id === seatId);
          if (seat) {
            seat.status = "sold";
          }
        });
      });
    },
    //生成row 1~20的座位
    getSeatsByRow(row) {
      return this.seats.filter((seat) => seat.row === row);
    },
    selectSeat(seat) {
      if (seat.status === "sold") {
        this.modalType = "error";
        this.modalMessage = "不能選取這個座位,請重新選取";
        this.showModal = true;
        return;
      } else if (seat.status === "wheelchair") {
        this.modalType = "error";
        this.modalMessage = "需至實體櫃台購買";
        this.showModal = true;
        return;
      }
      //selected true = available false = selected
      seat.status = seat.status === "selected" ? "available" : "selected";
    },
    search() {
      if (this.keyword.trim() === "") {
        this.results = [];
        this.showList = false;
        return;
      }
      //Spring Boot API => AJAX / Fetch API
      fetch(`/foodcos/search?keyword=${this.keyword}`)
        .then((res) => res.json())
        .then((data) => {
          this.results = data;
          this.showList = true;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    select(item) {
      alert("您已選擇:" + item.name);
      this.keyword = item.name;
      //清空搜尋解果
      this.results = [];
      this.showList = false;
    },
    confirmBooking() {
      if (this.selectedSeats.length === 0) {
        this.modalType = "error";
        this.modalMessage = "請先選擇座位";
        this.showModal = true;
        return;
      }

      const seatText = this.selectedSeats.map((seat) => seat.id).join("、");
      const now = new Date();
      const orderNo =
        "T" +
        now.getFullYear() +
        String(now.getMonth() + 1).padStart(2, "0") +
        String(now.getDate()).padStart(2, "0") +
        String(now.getHours()).padStart(2, "0") +
        String(now.getMinutes()).padStart(2, "0") +
        String(now.getSeconds()).padStart(2, "0");

      const bookingData = {
        orderNo: orderNo,
        seats: this.selectedSeats.map((seat) => seat.id),
        ticketCount: this.selectedSeats.length,
        totalPrice: this.totalPrice,
      };
      fetch("http://localhost:18080/booking", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(bookingData),
      })
        .then((response) => response.json())
        .then((data) => {
          console.log("後端回傳:", data);
        });

      this.modalType = "success";
      this.modalMessage =
        "訂票成功\n" +
        "座位:" +
        "訂單編號:" +
        orderNo +
        "\n" +
        seatText +
        "\n" +
        "票數:" +
        this.selectedSeats.length +
        "張\n" +
        "總金額:" +
        this.totalPrice +
        "元";

      this.seats.forEach((seat) => {
        if (seat.status === "selected") {
          seat.status = "sold";
        }
      });

      localStorage.setItem("seats", JSON.stringify(this.seats));
      this.modalMessage = "訂票成功";
      this.showModal = true;

      console.log(this.modalMessage);
    },
    closeWhenClickOutSide(event) {
      if (!this.$refs.searchBox.contains(event.target)) {
        this.showList = false;
      }
    },
    loadOrders() {
      this.showOrders = !this.showOrders;
    },
  },
  mounted() {
    document.addEventListener("click", this.closeWhenClickOutSide);

    const rows = ["A", "B", "C", "D", "E", "F", "G"];
    rows.forEach((row) => {
      for (let i = 1; i <= 20; i++) {
        this.seats.push({
          row: row,
          number: i,
          id: row + i,
          status: "available",
        });
      }
    });
    //模擬已售座位
    ["D14", "D15", "D16", "E3", "E4", "F6", "F7", "G8", "G9"].forEach((id) => {
      const seat = this.seats.find((s) => s.id === id);
      if (seat) seat.status = "sold";
    });

    //模擬輪椅座
    ["F4", "F17", "G4", "G17"].forEach((id) => {
      const seat = this.seats.find((s) => s.id === id);
      if (seat) seat.status = "wheelchair";
    });

    const savedSeats = localStorage.getItem("seats");
    if (savedSeats) {
      this.seats = JSON.parse(savedSeats);
    }
    this.loadSoldSeatsOnStart();
  },
}).mount("#app");
