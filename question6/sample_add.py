def sum_trade_values(file_path):
    total_value = 0  # Initialize a variable to hold the sum of the values

    # Open the file and read line by line
    with open(file_path, 'r') as file:
        for line in file:
            if "VALUE ($MM):" in line:
                # Extract the part of the line after "VALUE ($MM): "
                value_str = line.split("VALUE ($MM): ")[1]
                # Convert the extracted part to an integer
                try:
                    value = int(value_str)
                    total_value += value  # Add the value to the total sum
                except ValueError:
                    print(f"Error converting '{value_str}' to an integer.")

    return total_value

# Example usage:
file_path = 'path_to_your_file.txt'  # Replace with the actual path to your file
total_trade_value = sum_trade_values(file_path)
print(f"Total Value ($MM): {total_trade_value}")
