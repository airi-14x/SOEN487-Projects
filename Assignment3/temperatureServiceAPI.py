import temperatureSystemCore as temperature
import json


class ServiceAPI:

    def __init__(self):
        self.base_url = "https://api.openweathermap.org/data/2.5/weather?appid=77dde4d032c4ec1284a674d90b1351e3"

    # UI --> ServiceAPI: default parameters to format URL for System Core
    # Default: Montreal and Metric: Celsius
    def format_url_default(self, city):
        current_temperature_instance = temperature.Temperature()
        current_temperature_instance.formatted_url = self.base_url + "&q=" + \
            city + \
            "&units=" + current_temperature_instance.units
        
        current_temperature_instance.get_current_weather_default()
        
  

    # UI --> ServiceAPI: Pass desired parameters to format URL for System Core
    # For Example: Metric - Imperial(Fahrenheit)
    def format_url_with_parameters(self, city, unit_format):

        current_temperature_instance = temperature.Temperature()
        current_temperature_instance.formatted_url = self.base_url + "&q=" + \
            city + \
            "&units=" + unit_format
        current_temperature_instance.get_current_weather_default()

    # SystemCore --> ServiceAPI: Format temperature object for UI
    # Call Functions within to format appropriate (e.g. Want to convert/additional information...)
    def format_temperature_object(self, temperature_json):
        print(temperature_json)

        current_temperature_instance = temperature.Temperature()
        current_temperature_instance.longtitude = temperature_json['coord']['lon']
        current_temperature_instance.latitude = temperature_json['coord']['lat']

        # Weather #
        current_weather = temperature_json['weather'][0]
        current_temperature_instance.weather_overview = current_weather['main']
        current_temperature_instance.weather_description = current_weather['description']

        # Temperature #
        temperature_main = temperature_json['main']
        current_temperature_instance.current_temperature = temperature_main['temp']
        current_temperature_instance.current_feels_like = temperature_main['feels_like']
        current_temperature_instance.current_min = temperature_main['temp_min']
        current_temperature_instance.current_max = temperature_main['temp_max']

        current_temperature_instance.current_time = temperature_json['dt']
        current_temperature_instance.current_city = temperature_json['name']
        current_temperature_instance.response = temperature_json['cod']

        # -------- Set final Temperature Object for UI ------ #
        # ServiceAPI --> UI: Use this object to display

        json_obj = json.dumps(current_temperature_instance.__dict__)

        # Write to json file
        with open("temperature.json", "w") as outfile:
            outfile.write(json_obj)

    # Montreal time when the results were last updated.
    #def format_time(self):
        #from datetime import datetime
        #print(
            #"Received at Montreal Time - Current Time Zone [ISO 8601 Time Representation]")
        #print(self.formatted_temperature_object.current_time)
        #print(datetime.fromtimestamp(
            #self.formatted_temperature_object.current_time))
        #print("")
    
    def error(self):
        return 'An error occured'
#service = ServiceAPI()
# service.format_url_default("montreal")
#service.format_url_with_parameters("london", "imperial")
