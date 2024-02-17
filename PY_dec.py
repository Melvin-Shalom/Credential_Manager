from cryptography.fernet import Fernet


def load_key():
    file = open('dec.key', 'rb')
    key = file.read()
    file.close()
    return key


key = load_key()
fer = Fernet(key)


def view():
    with open("dec.txt", "r") as f:
        for line in f.readlines():
            data = line.rstrip()
            user, passw = data.split("|")
            print("User:", user, "| password:", fer.decrypt(passw.encode()).decode())


def add():
    name = input("Account Name: ")
    pwd = input("Password: ")
    
    with open("dec.txt", "a") as f:
        f.write(name + "|" + fer.encrypt(pwd.encode()).decode())


while True:
    mode = input("\nAccess Control Panel: Review existing credentials, Generate new passkeys, or Terminate session? (view, add, x): ").lower()

    if mode == "view":
        view()
    elif mode == "add":
        add()
    elif mode == "x":
        break
    else:
        print("Invalid Mode.")
        continue
