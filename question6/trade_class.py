class Trade:
    def __init__(self, trade_id, date, time, value):
        self.trade_id = trade_id
        self.date = date
        self.time = time
        self.value = value

    def __str__(self):
        return (f"Trade:\nID: {self.trade_id}\nDATE: {self.date}\nTIME: {self.time}\n"
                f"VALUE ($MM): {self.value}\n----")
