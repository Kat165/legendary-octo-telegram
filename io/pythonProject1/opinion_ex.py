import opinion


def opinion_manager():
    connection = opinion.connect()
    opinion.create_table(connection)
    opinion.add_opinion(connection, 1, 1, 1, "Dobra książka", 7)
    opinion.add_opinion(connection, 2, 2, 3, "Fajnie się czyta", 8)
    opinion.add_opinion(connection, 3, 8, 3, "Bardzo wciągająca", 8)
    opinion.add_opinion(connection, 4, 9, 2, "Nudna", 4)
    op = opinion.get_all_opinions(connection)
    for o in op:
        print(o)
