def parse_input(filename):
    bond_payments = {}
    with open(filename, 'r') as file:
        for line in file:
            parts = line.strip().split(', ')
            bond_identifier = parts[0]
            payment_dates = set(parts[1:])
            bond_payments[bond_identifier] = payment_dates
    return bond_payments

def find_clashes(bond_payments):
    bonds = sorted(bond_payments.keys())
    clashes = set()
    for i in range(len(bonds)):
        for j in range(i + 1, len(bonds)):
            bond1, bond2 = bonds[i], bonds[j]
            if bond_payments[bond1] & bond_payments[bond2]:  # Check for intersection in payment dates
                clashes.add(f"{bond1}-{bond2}")
    return clashes

def main():
    filename = 'input1.txt'
    bond_payments = parse_input(filename)
    clashes = find_clashes(bond_payments)
    
    # Printing each clash in a new line
    for clash in sorted(clashes):
        print(clash)

    # If required to just print the number of clashes, uncomment the next line:
    # print(len(clashes))

if __name__ == "__main__":
    main()
