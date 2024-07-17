import datetime

def parse_transaction(line):
    parts = line.strip().split(', ')
    date_time = datetime.datetime.strptime(parts[0], '%Y-%m-%d %H:%M:%S')
    crypto_type = parts[1]
    amount = float(parts[2])
    trans_id = parts[3]
    return (date_time, crypto_type, amount, trans_id)

def get_50th_transaction(filename):
    transactions = []

    # Read and parse each line in the file
    with open(filename, 'r') as file:
        for line in file:
            transactions.append(parse_transaction(line))
    
    # Sort transactions by datetime
    transactions.sort(key=lambda x: x[0])
    
    # Check if there are at least 50 transactions
    if len(transactions) < 50:
        return "There are less than 50 transactions in the file."
    
    # Retrieve the 50th transaction
    transaction = transactions[49]  # Index 49 since list is 0-based
    
    # Format the output as required
    return f"{transaction[0].strftime('%Y-%m-%d %H:%M:%S')}, {transaction[1]}, {transaction[2]}, {transaction[3]}"

# Example usage
filename = 'input5.txt'  # Assuming the file name where transactions are stored
result = get_50th_transaction(filename)
print(result)
