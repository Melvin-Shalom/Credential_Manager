# Credential Manager

## Description

A secure, console-based password manager built using Java and Python. This project leverages the **Cryptography** module for AES-based encryption and **Flask** to bridge Python with Java via REST APIs. A dedicated **virtual environment** handles Python dependencies, all listed in `Creden_Req.txt`. Designed with both practicality and protection in mind.

> 💡 Learn more about virtual environments [here](https://www.freecodecamp.org/news/how-to-setup-virtual-environments-in-python/)

## Features

- 🔐 Generates strong, truly random alphanumeric passwords  
- ✨ Lets users customize password length  
- 📊 Built-in Password Strength Checker  
- 🧠 SHA-256 used in the password organizer for added integrity  
- 🔒 Encrypts stored passwords to ensure data confidentiality  

## Requirements

- Java 17  
- Python 3.11  
- Flask 3.0  
- Cryptography 42.0  
- SQLAlchemy 2.0  
- Terminal or Command Prompt  

## Steps to Run

1. **Clone the repository**  
   ```bash
   git clone https://github.com/Melvin-Shalom/Credential_Manager.git
   ```

2. **Navigate to the project directory**  
   ```bash
   cd Credential_Manager/
   ```

3. **Create & activate the virtual environment**  
   ```bash
   python -m venv venv
   source venv/bin/activate   # For Linux/macOS
   .\venv\Scripts\activate    # For Windows
   ```

4. **Install dependencies**  
   ```bash
   pip install -r Creden_Req.txt
   ```

5. **Run the Python server**  
   ```bash
   python PY_dec.py
   ```

6. **In a new terminal, navigate back to the project directory**  
   ```bash
   cd Credential_Manager/
   ```

7. **Compile and run the Java client**  
   ```bash
   javac Main.java && java Main
   ```

## Usage

- Use the Java client to interact with the secure credential system.  
- Backend logic like encryption, password generation, and strength checks run via Flask APIs.  
- All passwords are stored encrypted and processed securely with modern cryptographic standards.  

## Author

Developed with precision and passion by [Melvin Shalom](https://github.com/Melvin-Shalom) 🧠💻

## License

This project is licensed under the [MIT License](https://opensource.org/licenses/MIT).
