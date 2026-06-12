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

      this.selectedSeats.forEach((seat) => {
        seat.status = "sold";
      });
      console.log("目前 selectedSeats:", this.selectedSeats);
      console.log("全部 seats:", this.seats);
      this.showModal = true;

      console.log(this.modalMessage);
    },
    closeWhenClickOutSide(event) {
      if (!this.$refs.searchBox.contains(event.target)) {
        this.showList = false;
      }
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
  },
}).mount("#app");
