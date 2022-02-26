import users


def user_manager():
    connection = users.connect()
    users.create_table(connection)
#    users.add_user(connection, 1, "lis", "12@dd", "rudy@12.com")
#    users.add_user(connection, 2, "cat", "42@dd", "farmer@dom.com")
#    users.add_user(connection, 3, "doggo", "12^hjd", "star@fall.com")
#    users.add_user(connection, 4, "XD", "404", "bb@do.com")
#    user = users.get_all_users(connection)
#    for u in user:
#        print(u)

