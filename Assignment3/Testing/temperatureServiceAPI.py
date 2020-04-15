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

        # -------- Set final Temperature Object for UI ------ #
        # ServiceAPI --> UI: Use this object to display

        json_obj = json.dumps(current_temperature_instance.__dict__)

        # Write to json file
        with open("temperature.json", "w") as outfile:
            outfile.write(json_obj)

    # ===== Formatting Functions  ====== #       
    def load_file(self, file_name):
        with open(file_name, 'r') as file:
            json_data = file.read()
        json_obj = json.loads(json_data)
        return json_obj

    def fahrenheit_to_celsius(self, value):
        value = (5.0 / 9.0) * (value - 32)
        return value

    def celsius_to_fahrenheit(self, value):
        value = (9.0 / 5.0) * value + 32
        return value

    def format_temperature_to_celsius(self, file_name):

        json_obj = ServiceAPI().load_file(file_name)
        current_units = json_obj['units']

        # imperial --> Fahrenheit
        # metric --> Celsius
        with open("modified_attributes.json", "w") as outfile:

            if current_units == 'imperial':
                current_temperature_celsius = round(
                    ServiceAPI().fahrenheit_to_celsius(json_obj['current_temperature']), 1)
                current_feels_like_celsius = round(
                    ServiceAPI().fahrenheit_to_celsius(json_obj['current_feels_like']), 1)
                current_min_celsius = round(
                    ServiceAPI().fahrenheit_to_celsius(json_obj['current_min']), 1)
                current_max_celsius = round(
                    ServiceAPI().fahrenheit_to_celsius(json_obj['current_max']), 1)

                json.dump({'current_temperature': current_temperature_celsius,
                           'current_feels_like': current_feels_like_celsius,
                           'current_min': current_min_celsius,
                           'current_max': current_max_celsius,
                           'units': 'metric'}, outfile, indent=4)

            elif current_units == 'metric':
                json.dump({'current_temperature': round(json_obj['current_temperature'], 1),
                           'current_feels_like': round(json_obj['current_feels_like'], 1),
                           'current_min': round(json_obj['current_min'], 1),
                           'current_max': round(json_obj['current_max'], 1),
                           'units': current_units}, outfile, indent=4)

    def format_temperature_to_fahrenheit(self, file_name):
        json_obj = ServiceAPI().load_file(file_name)
        current_units = json_obj['units']

        # imperial --> Fahrenheit
        # metric --> Celsius
        with open("modified_attributes.json", "w") as outfile:

            if current_units == 'imperial':
                json.dump({'current_temperature': round(json_obj['current_temperature'], 1),
                           'current_feels_like': round(json_obj['current_feels_like'], 1),
                           'current_min': round(json_obj['current_min'], 1),
                           'current_max': round(json_obj['current_max'], 1),
                           'units': current_units}, outfile, indent=4)

            elif current_units == 'metric':
                current_temperature_fahrenheit = round(
                    ServiceAPI().celsius_to_fahrenheit(json_obj['current_temperature']), 1)
                current_feels_like_fahrenheit = round(
                    ServiceAPI().celsius_to_fahrenheit(json_obj['current_feels_like']), 1)
                current_min_fahrenheit = round(
                    ServiceAPI().celsius_to_fahrenheit(json_obj['current_min']), 1)
                current_max_fahrenheit = round(
                    ServiceAPI().celsius_to_fahrenheit(json_obj['current_max']), 1)

                json.dump({'current_temperature': current_temperature_fahrenheit,
                           'current_feels_like': current_feels_like_fahrenheit,
                           'current_min': current_min_fahrenheit,
                           'current_max': current_max_fahrenheit,
                           'units': 'imperial'}, outfile, indent=4)

    def format_time_iso(self, file_name):
        json_obj = ServiceAPI().load_file(file_name)

        current_time_unix = json_obj['current_time']

        from datetime import datetime
        with open("modified_attributes.json", "w") as outfile:
            json.dump({'current_time': str(datetime.fromtimestamp(current_time_unix)),
                       'iso': 'true'}, outfile, indent=4)
