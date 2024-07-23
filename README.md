<h1>Credential Manager</h1>

<h2>Discription</h2>
<p>
A Console Based Application for managing passwords was developed using Java and Python. The <strong>Cryptography</strong> module from Python was utilized to ensure data security, and the <strong>Flask</strong> framework was deployed to establish connectivity between Python and Java using API. Additionally, a <strong>virtual environment</strong> was set up to manage dependencies effectively. The required modules are documented in a file named <strong><em>Creden_Req.txt</em></strong>.
</p>

<p>To know how the Virtual Environment works, <a href="https://www.freecodecamp.org/news/how-to-setup-virtual-environments-in-python/" target="_blank">visit here</a>.</p>

<h2>Features</h2>
<ul>
	<li>Allows user to Create Truly Random Alpha-Numeric Password.</li>
	<li>The user can also alter the Length of Password.</li>
	<li>With its Password Strength Checker, the user can user can check the check of the Password Provided.</li>
	<li>The Password Organiser Feature uses SHA-256 Algorithm.</li>
	<li>Stores User Password in an Encrypted Format to keep the Password Encrypted and protected from being read by Others.</li>
</ul>

<h2>Requirements</h2>
<ul>
	<li>Java 17</li>
	<li>Python 3.11</li>
	<li>Flask Module</li>
	<li>Cryptography Module</li>
	<li>SQLAlchemy Module</li>
  	<li>Terminal or Command Prompt</li>
</ul>

<h2>Steps for Execution</h2>
<ol>
	<li>
		<h4>Open the Terminal and Clone the Repository</h4>
		<code>git clone https://github.com/Melvin-Shalom/Credential_Manager.git</code><br>
	</li>
	<li>
		<h4>Navigate to the Project Directory</h4>
		<code>cd Credential_Manager/</code>
	</li>
	<li>
		<h4>Create and Activate the Virtual Environment</h4>
		<code>source venv/bin/activate</code>
	</li>
	<li>
		<h4>Install the Requirements</h4>
		<code>pip install -r Creden_Req.txt</code>
	</li>
	<li>
		<h4>Execute the Python Script</h4>
		<code>python PY_dec.py</code>
	</li>
	<li>
		<h4>Open Another Terminal Window and Navigate to the Project Directory</h4>
		<code>cd Credential_Manager/</code>
	</li>
	<li>
		<h4>Execute the Java Program</h4>
	    <code>javac Main.java && java Main</code>
	</li>
</ol>
