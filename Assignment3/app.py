from flask import Flask, send_from_directory, request, render_template, redirect, url_for, session
import json
import temperatureServiceAPI as service

app = Flask(__name__, static_url_path='')

# Welcome page
@app.route('/')
def welcome():
    return '<h1>Welcome to the weather app</h1>'

# Index page where weather is displayed
@app.route('/index')
def index():
    if 'admin' not in session:
        return redirect(url_for('login'))
    else:
        return render_template('index.html')

# Get from file 
# Get location
@app.route('/location')
def search_location():
    location = request.args.get('q')
    # MAKE THE CALL TO OUR SERVICE
    url = f'http://127.0.0.1:5000/index?location={location}'
    current_service_instance = service.ServiceAPI()
    current_service_instance.format_url_default(location)

    # THE RESPONSE
    response = request.get(url).json()

    # error like unknown city name, inavalid api key
    if response.get('cod') != 200:
        message = response.get('message', '')
        return f'Error getting temperature for {location.title()}. Error message = {message}'


# Login
@app.route('/login', methods=['GET', 'POST'])
def login():
    username = ""
    password = ""
    with open('config.json') as json_file:
        credentials = json.load(json_file)
        for cred in credentials['users']:
            username = cred['username']
            password = cred['password']

    error = None
    if request.method == 'POST':
        if request.form['username'] != username or request.form['password'] != password:
            error = 'Invalid Credentials. Please try again.'
        else:
            session[username] = request.form['username']
            return redirect(url_for('index'))
    return render_template('login.html', error=error)

# Logout
@app.route('/logout', methods=['GET', 'POST'])
def logout():
    session.pop('admin', None)
    return redirect('login')

if __name__ == "__main__":
    app.secret_key = b'-djoi3#039@@89!jd__/'
    app.run()
