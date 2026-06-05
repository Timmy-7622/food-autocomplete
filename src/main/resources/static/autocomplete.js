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
        this.modalMessage = "不能選取這個座位,請重新選取";
        this.showModal = true;
        return;
      } else if (seat.status === "wheelchair") {
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
