from flask import Flask, send_from_directory, request, render_template, redirect, url_for, session
import json
import weatherServiceAPI as service
import weatherSystemCore as temperature
from datetime import datetime

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
    symbol = ""
    rain = False
    sun = False
    default = False

    if 'username' not in session:
        return redirect(url_for('login'))
    elif 'username' in session:
        current_service_instance = service.ServiceAPI()
        try:
            if session.get('username'):
                current_service_instance.format_url_default(
                    'montreal', session['username'])
            else:
                raise KeyError("User is not login in properly")

        except KeyError as error:
            current_message = "User is not logged in properly"
            return render_template('index.html', message=current_message)

        except ValueError as error:
            current_message = ""
            with open('temperatureError.json') as error_file:
                error_data = json.load(error_file)
                current_message = error_data['error']
            return render_template('index.html', message=current_message)

        with open('temperature.json') as json_file:
            data = json.load(json_file)
            current_city = data['current_city']
            current_temperature = data['current_temperature']
            current_feels_like = data['current_feels_like']
            current_max = data['current_max']
            current_min = data['current_min']
            weather_description = data['weather_description']
            unix_time = data['current_time']

            symbol = '°C'
            date = datetime.fromtimestamp(unix_time)
            formatted_date = f"{date:%Y-%m-%d %H:%M}"

        if 'clear' in weather_description:
            rain = False
            sun = True
            default = False
        if 'rain' in weather_description:
            default = False
            rain = True
            sun = False
        else:
            default = True
            rain = False
            sun = False

        return render_template('index.html', current_city=current_city, current_temperature=current_temperature,
                               current_feels_like=current_feels_like, current_max=current_max, current_min=current_min, weather_description=weather_description.title(), symbol=symbol, sun=sun, rain=rain, default=default, date=formatted_date)


# Get location, display weather for requested location
@app.route('/location', methods=['GET'])
def search_location():
    current_city = ""
    current_temperature = ""
    current_feels_like = ""
    current_max = ""
    current_min = ""
    weather_description = ""
    symbol = ""
    rain = False
    sun = False
    default = False

    # Get the location and unit from the request url
    location = request.args.get('location')
    unit = request.args.get('unit')

    if 'username' not in session:
        return redirect(url_for('login'))

    elif 'username' in session:
        # Calling our service
        current_service_instance = service.ServiceAPI()
        try:
            if unit == '':
                if session.get('username'):
                    current_service_instance.format_url_default(
                        location, session['username'])
                else:
                    raise KeyError("User is not login properly.")
            else:
                if session.get('username'):
                    current_service_instance.format_url_with_parameters(
                        location, unit, session['username'])
                else:
                    raise KeyError("User is not login properly.")

        except KeyError as error:
            current_message = "User is not logged in properly."
            return render_template('index.html', message=current_message)

        except ValueError as error:
            current_message = ""
            with open('temperatureError.json') as error_file:
                error_data = json.load(error_file)
                current_message = error_data['error']
                return render_template('index.html', message=current_message)

    # Parsing the response file
    with open('temperature.json') as json_file:
        data = json.load(json_file)
        current_city = data['current_city']
        current_temperature = data['current_temperature']
        current_feels_like = data['current_feels_like']
        current_max = data['current_max']
        current_min = data['current_min']
        weather_description = data['weather_description']
        unix_time = data['current_time']

        date = datetime.fromtimestamp(unix_time)
        formatted_date = f"{date:%Y-%m-%d %H:%M}"

        if unit == 'metric':
            symbol = '°C'
        elif unit == 'imperial':
            symbol = '°F'

        if 'sun' in weather_description:
            rain = False
            sun = True
            default = False
        if 'rain' in weather_description:
            default = False
            rain = True
            sun = False
        else:
            default = True
            rain = False
            sun = False

    return render_template('index.html', current_city=current_city, current_temperature=current_temperature,
                           current_feels_like=current_feels_like, current_max=current_max, current_min=current_min, weather_description=weather_description.title(), symbol=symbol, sun=sun, rain=rain, default=default, date=formatted_date)

# Get weather by longitude and latitude
@app.route('/coordinates', methods=['GET', 'POST'])
def search_coordinates():
    current_city = ""
    current_temperature = ""
    current_feels_like = ""
    current_max = ""
    current_min = ""
    weather_description = ""
    symbol = ""
    rain = False
    sun = False
    default = False

    unit = request.args.get('unit')
    longitude = request.args.get('longitude')
    latitude = request.args.get('latitude')

    if 'username' not in session:
        return redirect(url_for('login'))

    elif 'username' in session:
        # Calling our service
        current_service_instance = service.ServiceAPI()
        try:
            if session.get('username'):
                current_service_instance.format_url_with_coordinates(longitude, latitude, unit, session['username'])
            else:
                raise KeyError("User it not logged in properly.")

        except KeyError as error:
            current_message = "User is not logged in properly."
            return render_template('index.html', message=current_message)

        except ValueError as error:
            current_message = ""
            with open('temperatureError.json') as error_file:
                error_data = json.load(error_file)
                current_message = error_data['error']
                return render_template('index.html', message=current_message)

    # Parsing the response file
    with open('temperature.json') as json_file:
        data = json.load(json_file)
        current_city = data['current_city']
        current_temperature = data['current_temperature']
        current_feels_like = data['current_feels_like']
        current_max = data['current_max']
        current_min = data['current_min']
        weather_description = data['weather_description']
        unix_time = data['current_time']
        date = datetime.fromtimestamp(unix_time)
        formatted_date = f"{date:%Y-%m-%d %H:%M}"

        if current_city == '':
            current_message = "Location could not be found"
            return render_template('index.html', message=current_message)
            
        if unit == 'metric':
            symbol = '°C'
        elif unit == 'imperial':
            symbol = '°F'

        if 'sun' in weather_description:
            rain = False
            sun = True
            default = False
        if 'rain' in weather_description:
            default = False
            rain = True
            sun = False
        else:
            default = True
            rain = False
            sun = False

    return render_template('index.html', current_city=current_city, current_temperature=current_temperature,
                           current_feels_like=current_feels_like, current_max=current_max, current_min=current_min, weather_description=weather_description.title(), symbol=symbol, sun=sun, rain=rain, default=default, date=formatted_date)


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
            session['username'] = request.form['username']
            return redirect(url_for('index'))
    return render_template('login.html', error=error)

# Logout
@app.route('/logout', methods=['GET', 'POST'])
def logout():
    session.pop('username', None)
    return redirect('/')


if __name__ == "__main__":
    app.secret_key = b'-djoi3#039@@89!jd__/'
    app.run()
