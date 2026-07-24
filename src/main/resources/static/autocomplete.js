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
      //自動產生日期:用來存未來四天
      availableDates: [],
      //使用者目前選擇的日期:用來記錄使用者現在選了哪一天
      selectedDate: "",
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
      agreeTerms: false,
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
      buyer: {
        name: "",
        phone: "",
        email: "",
      },
      invoice: {
        type: "cloud",
        carrier: "member",
        mobileBarcode: "",
        companyName: "",
        companyTaxId: "",
      },
      payment: {
        cardNumber: "",
        rawCardNumber: "",
        cardholderName: "",
        expMonth: "",
        expYear: "",
        cvv: "",
      },
      paymentCompleted: false,
      orderNo: "",
    };
  },
  // 計算後的資料 filter->篩選
  computed: {
    remainingSeatCount() {
      return this.totalTicketCount - this.selectedSeats.length;
    },
    selectSeatText() {
      if (this.selectedSeats.length === 0) {
        return "";
      }
      // this.selectedSeats是一個陣列，陣列沒有id個屬性所以要用map去一個一個取出來
      return this.selectedSeats.map((seat) => seat.id).join("、");
    },
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
    isPastTime(time) {
      const today = new Date();
      const year = today.getFullYear();
      const month = String(today.getMonth() + 1).padStart(2, "0");
      const day = String(today.getDate()).padStart(2, "0");
      const todayValue = `${year}-${month}-${day}`;

      //如果選的不是今天，場次就不算過期
      if (this.selectedDate !== todayValue) {
        return false;
      }

      // 把 "13:30" 拆成 13 和 30
      //time => 去取得畫面點到的時間 然後把這個時間setHours裡面 最後比較
      const [hour, minute] = time.split(":").map(Number);
      const sessionTime = new Date();
      sessionTime.setHours(hour, minute, 0, 0);
      return sessionTime < today;
    },
    selectDate(date) {
      this.selectedtDate = date.value;

      //如果已經選擇場次
      if (this.bookingInfo.time) {
        const selectedDateInfo = this.availableDates.find(
          (item) => item.value === this.selectedDate,
        );
        this.bookingInfo.date = selectedDateInfo.value;
        this.bookingInfo.weekday = selectedDateInfo.weekday;

        //重新查詢這一天已售出的座位
        this.searchSoldSeatsFormOrders();
      }
    },
    generateAvailableDates() {
      const dates = [];
      //取得今天
      const today = new Date();
      //產生今天開始的4天
      for (let i = 0; i < 4; i++) {
        const currentDate = new Date(today);

        //今天 + i天  today.getDate()是取得今天幾"號"
        currentDate.setDate(today.getDate() + i);
        const year = currentDate.getFullYear();

        const month = String(currentDate.getMonth() + 1).padStart(2, "0");
        const day = String(currentDate.getDate()).padStart(2, "0");
        const weekday = this.getWeekday(currentDate);

        dates.push({
          value: `${year}-${month}-${day}`,
          displayDate: `${month}/${day}`,
          weekday: weekday,
        });
      }
      this.availableDates = dates;
      //預設選擇第一天，也就是今天
      this.selectedDate = dates[0].value;
    },
    getWeekday(date) {
      const weekdays = [
        "星期日",
        "星期一",
        "星期二",
        "星期三",
        "星期四",
        "星期五",
        "星期六",
      ];
      //date.getDay是把"幾號"轉換成星期幾
      return weekdays[date.getDay()];
    },
    // 每把座位表拿出只要不是輪椅座就先把狀態恢復成available
    resetSeatStatus() {
      this.seats.forEach((seat) => {
        if (seat.status !== "wheelchair") {
          seat.status = "available";
        }
      });
    },
    goMovieList() {
      this.resetBooking();
      this.currentPage = "session";
    },
    resetBooking() {
      this.tickets.forEach((ticket) => {
        ticket.count = 0;
      });
      this.packageTicket.forEach((ticket) => {
        ticket.count = 0;
      });

      //座位
      this.seats.forEach((seat) => {
        if (seat.status === "selected") {
          seat.status = "available";
        }
      });

      //購票人資料
      this.buyer.name = "";
      this.buyer.phone = "";
      this.buyer.email = "";

      //發票資料
      this.invoice.type = "cloud";
      this.invoice.carrier = "member";
      this.invoice.mobileBarcode = "";
      this.invoice.companyName = "";
      this.invoice.companyTaxId = "";

      //付款資料
      this.payment.cardholderName = "";
      this.payment.cardNumber = "";
      this.payment.rawCardNumber = "";
      this.payment.expMonth = "";
      this.payment.expYear = "";
      this.payment.cvv = "";

      //其他狀態
      this.agreeTerms = false;
      this.paymentCompleted = false;
      this.orderNo = "";
    },
    generateOrderNo() {
      const now = new Date();
      return (
        "MK" +
        now.getFullYear() +
        String(now.getMonth() + 1).padStart(2, "0") +
        String(now.getDate()).padStart(2, "0") +
        String(now.getHours()).padStart(2, "0") +
        String(now.getMinutes()).padStart(2, "0") +
        String(now.getSeconds()).padStart(2, "0")
      );
    },
    showPaymentError(message) {
      this.modalType = "error";
      this.modalMessage = message;
      this.showModal = true;
    },
    confirmPayment() {
      if (this.payment.rawCardNumber.length !== 16) {
        this.showPaymentError("信用卡號必須為 16 碼");
        return;
      }
      if (!this.payment.cardholderName.trim()) {
        this.showPaymentError("請輸入持卡人姓名");
        return;
      }
      const month = Number(this.payment.expMonth);

      if (!/^\d{2}$/.test(this.payment.expMonth) || month < 1 || month > 12) {
        this.showPaymentError("有效月份請輸入 01～12");
        return;
      }
      if (!/^\d{2}$/.test(this.payment.expYear)) {
        this.showPaymentError("有效年份必須為 2 碼數字");
        return;
      }
      if (!/^\d{3}$/.test(this.payment.cvv)) {
        this.showPaymentError("CVV 必須為 3 碼數字");
        return;
      }
      this.orderNo = this.generateOrderNo();
      const bookingData = {
        orderNo: this.orderNo,

        movieName: this.bookingInfo.movieName,
        cinema: this.bookingInfo.cinema,
        movieDate: this.bookingInfo.date,
        movieTime: this.bookingInfo.time,

        seats: this.selectedSeats.map((seat) => seat.id),
        ticketCount: this.totalTicketCount,

        ticketPrice: this.totalPrice,
        serviceFee: this.serviceFee,
        totalPrice: this.finalTotalPrice,

        buyerName: this.buyer.name,
        buyerPhone: this.buyer.phone,
        buyerEmail: this.buyer.email,

        invoiceType: this.invoice.type,
        invoiceCarrier: this.invoice.carrier,
        mobileBarcode: this.invoice.mobileBarcode,
        companyName: this.invoice.companyName,
        companyTaxId: this.invoice.companyTaxId,

        paymentMethod: "credit",
        paymentStatus: "paid",
      };

      fetch("http://localhost:18080/booking", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(bookingData),
      })
        .then((response) => {
          console.log(response);
          if (!response.ok) {
            throw new Error("訂單儲存失敗");
          }
          return response.json();
        })
        .then((result) => {
          console.log("後端回傳結果:", result);
          this.orderNo = result.orderNo;
          this.paymentCompleted = true;
          this.loadSoldSeatsOnStart();
        })
        .catch((error) => {
          this.showPaymentError("付款失敗,請稍後再試");
        });

      console.log("準備送到後端的訂單資料：", bookingData);
    },
    formatCardNumber(event) {
      let value = event.target.value;

      //移除所有非數字 (正規表示式)
      value = value.replace(/\D/g, "");
      //最多保留16碼 slice() 的意思是：擷取一部分的字串
      value = value.slice(0, 16);
      //保存數字
      this.payment.rawCardNumber = value;
      //每4碼加一個空格
      this.payment.cardNumber = value.replace(/(\d{4})(?=\d)/g, "$1 ");
    },
    maskCardNumber() {
      const value = this.payment.rawCardNumber;
      if (value.length !== 16) {
        return;
      }
      this.payment.cardNumber = "•••• •••• •••• " + value.slice(-4);
    },
    showCardNumber() {
      const value = this.payment.rawCardNumber;
      this.payment.cardNumber = value.replace(/(\d{4})(?=\d)/g, "$1 ");
    },
    checkoutOrder() {
      if (!this.agreeTerms) {
        this.showPaymentError("請勾選閱讀並同意訂票須知");
        return;
      }
      if (
        this.invoice.type === "company" &&
        (!this.invoice.companyName.trim() || !this.invoice.companyTaxId.trim())
      ) {
        this.showPaymentError("請填寫公司抬頭與統一編號");
        return;
      }

      if (this.invoice.type === "cloud" && this.invoice.carrier === "mobile") {
        if (!this.invoice.mobileBarcode.trim()) {
          this.showPaymentError("請填寫手機載具條碼");
          return;
        }
      }
      this.currentStep = 4;
    },
    goSeatStep() {
      if (this.totalTicketCount === 0) {
        this.showPaymentError("請選擇票種再繼續");
        return;
      }

      this.bookingInfo.ticketCount = this.totalTicketCount;
      this.bookingInfo.totalPrice = this.totalPrice;
      this.currentStep = 2;
    },
    goFoodStep() {
      if (this.remainingSeatCount > 0) {
        this.showPaymentError("請先完成座位選擇");
        return;
      }
      this.currentStep = 3;
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
        this.showPaymentError("請先選擇場次時間");
        return;
      }
      this.bookingInfo.poster = session.poster;
      this.bookingInfo.movieName = session.movieName;
      this.bookingInfo.englishName = session.englishName;
      this.bookingInfo.cinema = session.cinema;

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
      const selectedDateInfo = this.availableDates.find(
        (date) => date.value === this.selectedDate,
      );
      this.bookingInfo.movieName = session.movieName;
      this.bookingInfo.englishName = session.englishName;
      this.bookingInfo.cinema = session.cinema;
      this.bookingInfo.date = selectedDateInfo.value;
      this.bookingInfo.weekday = selectedDateInfo.weekday;
      this.bookingInfo.language = format.language;
      this.bookingInfo.time = time;

      console.log(selectedDateInfo);

      this.searchSoldSeatsFormOrders();
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
        })
        .catch((err) => {
          console.log(err);
        });
    },
    // 增加篩選場次條件，並更改座位狀態
    searchSoldSeatsFormOrders() {
      this.resetSeatStatus();
      // currentOrders是篩選過後符合條件的訂單
      const currentOrders = this.orders.filter((order) => {
        return (
          order.movieName === this.bookingInfo.movieName &&
          order.cinema === this.bookingInfo.cinema &&
          order.movieDate === this.bookingInfo.date &&
          order.movieTime === this.bookingInfo.time
        );
      });
      currentOrders.forEach((order) => {
        const seatIds = order.seats.split(",");

        seatIds.forEach((seatId) => {
          const seat = this.seats.find((seat) => seat.id === seatId);

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
      if (
        seat.status === "available" &&
        this.selectedSeats.length >= this.totalTicketCount
      ) {
        this.showPaymentError("已無待選座位數");
        return;
      }
      if (seat.status === "sold") {
        this.showPaymentError("不能選取這個座位,請重新選取");
        return;
      } else if (seat.status === "wheelchair") {
        this.showPaymentError("需至實體櫃台購買");
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
    // confirmBooking() {
    //   if (this.selectedSeats.length === 0) {
    //     this.modalType = "error";
    //     this.modalMessage = "請先選擇座位";
    //     this.showModal = true;
    //     return;
    //   }

    //   const seatText = this.selectedSeats.map((seat) => seat.id).join("、");
    //   const now = new Date();
    //   const orderNo =
    //     "T" +
    //     now.getFullYear() +
    //     String(now.getMonth() + 1).padStart(2, "0") +
    //     String(now.getDate()).padStart(2, "0") +
    //     String(now.getHours()).padStart(2, "0") +
    //     String(now.getMinutes()).padStart(2, "0") +
    //     String(now.getSeconds()).padStart(2, "0");

    //   const bookingData = {
    //     orderNo: orderNo,
    //     seats: this.selectedSeats.map((seat) => seat.id),
    //     ticketCount: this.selectedSeats.length,
    //     totalPrice: this.totalPrice,
    //   };
    //   fetch("http://localhost:18080/booking", {
    //     method: "POST",
    //     headers: { "Content-Type": "application/json" },
    //     body: JSON.stringify(bookingData),
    //   })
    //     .then((response) => response.json())
    //     .then((data) => {
    //       console.log("後端回傳:", data);
    //     });

    //   this.modalType = "success";
    //   this.modalMessage =
    //     "訂票成功\n" +
    //     "座位:" +
    //     "訂單編號:" +
    //     orderNo +
    //     "\n" +
    //     seatText +
    //     "\n" +
    //     "票數:" +
    //     this.selectedSeats.length +
    //     "張\n" +
    //     "總金額:" +
    //     this.totalPrice +
    //     "元";

    //   this.seats.forEach((seat) => {
    //     if (seat.status === "selected") {
    //       seat.status = "sold";
    //     }
    //   });

    //   localStorage.setItem("seats", JSON.stringify(this.seats));
    //   this.modalMessage = "訂票成功";
    //   this.showModal = true;

    //   console.log(this.modalMessage);
    // },
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
    this.generateAvailableDates();
  },
}).mount("#app");
