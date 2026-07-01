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
      currentPage: "session",
      currentStep: 1,
      showSeatPreview: false,
      seatPreviewMode: false,
      bookingInfo: {
        movieName: "",
        englishName: "",
        poster: "",
        cinema: "",
        date: "",
        weekday: "",
        language: "",
        time: "",
        seats: [],
      },
      sessions: [],
      packageTicket: [
        { name: "單人套票", price: 330, count: 0, seatCount: 1 },
        { name: "雙人套票", price: 640, count: 0, seatCount: 2 },
        { name: "學生套票", price: 310, count: 0, seatCount: 1 },
      ],

      tickets: [
        { name: "全票", price: 350, count: 0, seatCount: 1 },
        { name: "優待票", price: 330, count: 0, seatCount: 1 },
        { name: "敬老票", price: 175, count: 0, seatCount: 1 },
        { name: "愛心票", price: 175, count: 0, seatCount: 1 },
      ],
    };
  },
  // 計算後的資料 filter->篩選
  computed: {
    selectedSeats() {
      return this.seats.filter((seat) => seat.status === "selected");
    },
    allTickets() {
      return [...this.packageTicket, ...this.tickets];
    },
    selectTickets() {
      return this.allTickets.filter((ticket) => ticket.count > 0);
    },
    totalTicketCount() {
      let totalTicket = 0;
      for (const ticket of this.allTickets) {
        totalTicket += ticket.count * ticket.seatCount;
      }
      return totalTicket;
    },
    totalPrice() {
      let totalPrice = 0;
      for (const ticket of this.allTickets) {
        totalPrice += ticket.count * ticket.price;
      }
      return totalPrice;
    },
    serviceFee() {
      return this.totalTicketCount * 20;
    },
    finalTotalPrice() {
      return this.totalPrice + this.serviceFee;
    },
  },
  methods: {
    goSeatStep() {
      if (totalTicket === 0) {
        this.modalType = "error";
        this.modalMessage = "請選擇票種再繼續";
        this.showModal = true;
        return;
      }

      this.bookingInfo.ticketCount = this.totalTicketCount;
      this.bookingInfo.totalPrice = this.totalPrice;
      this.currentStep = 2;
    },
    increaseTicket(ticket) {
      ticket.count++;
    },
    decreaseTicket(ticket) {
      if (ticket.count > 0) {
        ticket.count--;
      }
    },
    goTicketStep(session) {
      if (!this.bookingInfo.time) {
        this.modalType = "error";
        this.modalMessage = "請先選擇場次時間";
        this.showModal = true;
        return;
      }
      this.bookingInfo.poster = session.poster;
      this.bookingInfo.movieName = session.movieName;
      this.bookingInfo.englishName = session.englishName;
      this.bookingInfo.cinema = session.cinema;
      this.bookingInfo.date = session.date;
      this.bookingInfo.weekday = session.weekday;

      this.currentPage = "booking";
      this.currentStep = 1;
    },
    openSeatPreview() {
      this.seatPreviewMode = true;
      this.showSeatPreview = true;
    },
    closeSeatPreview() {
      this.showSeatPreview = false;
      this.seatPreviewMode = false;
    },
    clickSeat(seat) {
      if (this.seatPreviewMode) {
        return;
      }
      this.selectSeat(seat);
    },
    getRatingClass(rating) {
      switch (rating) {
        case "普遍級":
          return "rating-g";

        case "保護級":
          return "rating-p";

        case "輔12級":
          return "rating-p12";

        case "輔15級":
          return "rating-p15";

        case "限制級":
          return "rating-r";

        default:
          return "";
      }
    },
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
      if (!this.$refs.searchBox) {
        return;
      }

      if (!this.$refs.searchBox.contains(event.target)) {
        this.showList = false;
      }
    },
    loadOrders() {
      this.showOrders = !this.showOrders;
    },
  },
  mounted() {
    fetch("http://localhost:18080/sessions")
      .then((response) => response.json())
      .then((data) => {
        this.sessions = data;
      })
      .catch((err) => {
        console.log(err);
      });
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
