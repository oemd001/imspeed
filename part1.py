import datetime

def parse_transaction(line):
    parts = line.strip().split(', ')
    date_time = datetime.datetime.strptime(parts[0], '%Y-%m-%d %H:%M:%S')
    return date_time, parts[1], float(parts[2]), parts[3]

def print_50th_transaction(filename):
    transactions = []

    # Read and parse each line in the file
    with open(filename, 'r') as file:
        for line in file:
            transactions.append(parse_transaction(line))
    
    # Sort transactions by datetime
    transactions.sort(key=lambda x: x[0])
    
    # Check if there are at least 50 transactions
    if len(transactions) < 50:
        print("There are less than 50 transactions in the file.")
        return
    
    # Retrieve and print the 50th transaction
    transaction = transactions[49]  # Index 49 since list is 0-based
    print(f"{transaction[0].strftime('%Y-%m-%d %H:%M:%S')}, {transaction[1]}, {transaction[2]}, {transaction[3]}")

# Example usage
filename = 'input5.txt'  # Replace with your actual file name
print_50th_transaction(filename)
