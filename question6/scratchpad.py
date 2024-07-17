num_trades = len(predicted_digits) // 24  # adjust 24 based on how many digits per trade

trade_details = []
for i in range(num_trades):
    offset = i * 24  # adjust the slice size based on your digit distribution per trade
    trade_id = ''.join(map(str, predicted_digits[offset:offset+6]))
    date = ''.join(map(str, predicted_digits[offset+6:offset+12]))
    time = ''.join(map(str, predicted_digits[offset+12:offset+18]))
    value = int(''.join(map(str, predicted_digits[offset+18:offset+24])))

    trade_details.append((trade_id, date, time, value))

# Now create trade objects
trades = [Trade(*details) for details in trade_details]

# Example to print or write these trades to a file
with open('output.txt', 'w') as f:
    for trade in trades:
        f.write(f"{trade}\n")
