from flask import Flask, send_from_directory, request, render_template, redirect, url_for, session
import json
import temperatureServiceAPI as service
import temperatureSystemCore as temperature
import urllib.parse as urlparse
import requests
from urllib.parse import parse_qs

app = Flask(__name__, static_url_path='')

# Index page where weather is displayed, displays weather for Montreal as default
@app.route('/')
def index():
    current_city = ""
    current_temperature = ""
    current_feels_like = ""
    current_max = ""
    current_min = ""
    weather_description = ""

    if 'admin' not in session:
        return redirect(url_for('login'))
    else:
        current_service_instance = service.ServiceAPI()
        try:
            current_service_instance.format_url_default('montreal')
            with open('temperature.json') as json_file:
                data = json.load(json_file)
                current_city = data['current_city']
                current_temperature = data['current_temperature']
                current_feels_like = data['current_feels_like']
                current_max = data['current_max']
                current_min = data['current_min']
                weather_description = data['weather_description']
        except:
            message = current_service_instance.error()
            return render_template('index.html', message=message)
        
        return render_template('index.html', current_city=current_city, current_temperature=current_temperature, 
        current_feels_like=current_feels_like, current_max=current_max, current_min=current_min, weather_description=weather_description)


# Get location, display weather for requested location
@app.route('/location', methods=['GET'])
def search_location():
    current_city = ""
    current_temperature = ""
    current_feels_like = ""
    current_max = ""
    current_min = ""
    weather_description = ""
    
    # Get the location from the request url
    location = request.args.get('location')

    # Calling our service 
    current_service_instance = service.ServiceAPI()
    try:
        current_service_instance.format_url_default(location)
    except:
        message = current_service_instance.error()
        return render_template('index.html', message=message)

    # Parsing the response file
    with open('temperature.json') as json_file:
        data = json.load(json_file)
        current_city = data['current_city']
        current_temperature = data['current_temperature']
        current_feels_like = data['current_feels_like']
        current_max = data['current_max']
        current_min = data['current_min']
        weather_description = data['weather_description']
        
    return render_template('index.html', current_city=current_city, current_temperature=current_temperature, 
        current_feels_like=current_feels_like, current_max=current_max, current_min=current_min, weather_description=weather_description)

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
