# Credential Manager

## Discription

A Console Based Application for managing passwords was developed using Java and Python. The **Cryptography** module from Python was utilized to ensure data security, and the **Flask** framework was deployed to establish connectivity between Python and Java using API. Additionally, a **virtual environment** was set up to manage dependencies effectively. The required modules are documented in a file named ***Creden_Req.txt***.

To know how the Virtual Environment works, [visit here](https://www.freecodecamp.org/news/how-to-setup-virtual-environments-in-python/).

## Features

- Allows user to Create Truly Random Alpha-Numeric Password.
- The user can also alter the Length of Password.
- With its Password Strength Checker, the user can user can check the check of the Password Provided.
- The Password Organiser Feature uses SHA-256 Algorithm.
- Stores User Password in an Encrypted Format to keep the Password Encrypted and protected from being read by Others.

## Requirements

- Java 17
- Python 3.11
- Flask 3.0
- Cryptography 42.0
- SQLAlchemy 2.0
- Terminal or Command Prompt

## Steps for Execution

1. #### Open the Terminal and Clone the Repository
   ```
   git clone https://github.com/Melvin-Shalom/Credential_Manager.git
   ```
2. #### Navigate to the Project Directory
   ```
   cd Credential_Manager/
   ```
3. #### Create and Activate the Virtual Environment
   ```
   source venv/bin/activate
   ```
4. #### Install the Requirements
   ```
   pip install -r Creden_Req.txt
   ```
5. #### Execute the Python Script
   ```
   python PY_dec.py
   ```
6. #### Open Another Terminal Window and Navigate to the Project Directory
   ```
   cd Credential_Manager/
   ```
7. #### Execute the Java Program
   ```
   javac Main.java && java Main
   ```
