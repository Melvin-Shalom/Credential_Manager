from flask import Flask, request
from cryptography.fernet import Fernet
import json

PY_dec = Flask(__name__)

def load_key():
    file = open('PY_dec.key', 'rb')
    key = file.read()
    file.close()
    return key

key = load_key()
fer = Fernet(key)

@PY_dec.route('/view', methods=['GET'])
def view():
    credentials = []
    with open("PY_dec.txt", "r") as f:
        for line in f.readlines():
            data = line.rstrip()
            user, passw = data.split("|")
            decrypted_passw = fer.decrypt(passw.encode()).decode()
            credentials.append({"Username": user, "Password": decrypted_passw})
    
    json_data = '[\n'
    for cred in credentials:
        json_data += json.dumps(cred) + ',\n'
    json_data = json_data.rstrip(',\n') 
    json_data += '\n]'

    return json_data

@PY_dec.route('/add', methods=['POST'])
def add():
    name = request.json.get("name")
    pwd = request.json.get("password")
    with open("PY_dec.txt", "a") as f:
        encrypted_pwd = fer.encrypt(pwd.encode()).decode()
        f.write(name + "|" + encrypted_pwd + "\n")
    return "Credentials added successfully"

if __name__ == '__main__':
    PY_dec.run(debug=True)
