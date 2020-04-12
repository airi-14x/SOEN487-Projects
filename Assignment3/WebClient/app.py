from flask import Flask, send_from_directory, request
app = Flask(__name__, static_url_path='', static_folder='WebClient/static',
            template_folder='WebClient/templates')

#Welcome page
@app.route('/')
def welcome():
    return '<h1>Welcome to the weather app</h1>'

#Index page where weather is displayed
@app.route('/templates/<path:path>')
def index(path):
    return send_from_directory('templates', path)

#Static content
@app.route('/static/<path:path>')
def style(path):
    return send_from_directory('static', path)

#Get location
@app.route('/location')
def search_location():
    API_KEY = 'OUR API KEY'
    location = request.args.get('q')
    
    #MAKE THE CALL TO OUR SERVICE
    url = f'http://api.openweathermap.org/data/2.5/weather?q={location}&APPID={API_KEY}'
    
    #THE RESPONSE
    response = request.get(url).json()

    # error like unknown city name, inavalid api key
    if response.get('cod') != 200:
        message = response.get('message', '')
        return f'Error getting temperature for {location.title()}. Error message = {message}'

    #MAYBE NOT USEFUL
    # get current temperature and convert it into Celsius
    #current_temperature = response.get('main', {}).get('temp')
    #if current_temperature:
        #current_temperature_celsius = round(current_temperature - 273.15, 2)
        #return f'Current temperature of {location.title()} is {current_temperature_celsius} &#8451;'
    #else:
        #return f'Error getting temperature for {location.title()}'


if __name__ == "__main__":
    app.run()