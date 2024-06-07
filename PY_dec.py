from flask import Flask, request, jsonify
from flask_sqlalchemy import SQLAlchemy
from cryptography.fernet import Fernet

app = Flask(__name__)
app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:///credentials.db'
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False
db = SQLAlchemy(app)

# Load encryption key
def load_key():
    with open('PY_dec.key', 'rb') as file:
        key = file.read()
    return key

key = load_key()
fer = Fernet(key)

# Database model
class Credential(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    website = db.Column(db.String(100), nullable=False)
    username = db.Column(db.String(100), nullable=False)
    password = db.Column(db.String(255), nullable=False)

# Routes
@app.route('/view', methods=['GET'])
def view():
    credentials = Credential.query.all()
    credential_list = []
    for cred in credentials:
        credential_list.append({
            "Website": cred.website,
            "Username": cred.username,
            "Password": fer.decrypt(cred.password.encode()).decode()
        })

    json_data = '\n'.join('\n\t\t\tWebsite: "{}"\n\t\t\tUsername: "{}"\n\t\t\tPassword: "{}"\n'.format(
        cred["Website"], cred["Username"], cred["Password"]) for cred in credential_list)

    return json_data

@app.route('/add', methods=['POST'])
def add_credential():
    website = request.json.get("website")
    username = request.json.get("name")
    password = request.json.get("password")

    # Check for missing fields
    if not website or not username or not password:
        return jsonify({"error": "Missing required fields. Please provide website, username, and password."}), 400

    # Check for duplicate credentials
    conflicting_credential = Credential.query.filter_by(username=username).first()
    if conflicting_credential and fer.decrypt(conflicting_credential.password.encode()).decode() == password:
        return jsonify({"message": "The same username and password combination already exists for a different website. Do you want to add this credential?"}), 200

    # Encrypt the password before storing
    encrypted_pwd = fer.encrypt(password.encode()).decode()
    new_credential = Credential(website=website, username=username, password=encrypted_pwd)
    db.session.add(new_credential)
    db.session.commit()
    return jsonify({"message": "Credential added successfully."}), 200


@app.route('/delete', methods=['POST'])
def delete():
    website = request.json.get("website")
    name = request.json.get("name")

    credential = Credential.query.filter_by(website=website, username=name).first()
    if credential:
        db.session.delete(credential)
        db.session.commit()
        return "Credential deleted successfully"
    else:
        return "Credential not found"

if __name__ == '__main__':
    with app.app_context():
        db.create_all()  # Create database tables
    app.run(debug=True)

