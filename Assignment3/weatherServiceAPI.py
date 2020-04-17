import weatherSystemCore as temperature
import json


class ServiceAPI:

    def __init__(self):
        pass

    # UI --> ServiceAPI: default parameters to format URL for System Core
    # Default: Montreal and Units: Metric (a.k.a Celsius)
    def format_url_default(self, city, user):
        base_url = self.load_config()
        current_temperature_instance = temperature.Temperature()
        current_temperature_instance.formatted_url = base_url + "&q=" + \
            city + \
            "&units=" + current_temperature_instance.units

        if user is None:
            # self.user_error()
            message = self.user_error()
            with open("temperatureError.json", "w") as outfile:
                json.dump({'error': message}, outfile, indent=4)
        else:
            current_temperature_instance.get_current_weather_default(user)

    # UI --> ServiceAPI: Pass desired parameters to format URL for System Core
    # For Example: Units: Imperial (a.k.a. Fahrenheit)
    def format_url_with_parameters(self, city, unit_format, user):
        base_url = self.load_config()

        current_temperature_instance = temperature.Temperature()
        current_temperature_instance.formatted_url = base_url + "&q=" + \
            city + \
            "&units=" + unit_format
        print("current user2: " + user)
        if user is None:
            # self.user_error()
            message = self.user_error()
            with open("temperatureError.json", "w") as outfile:
                json.dump({'error': message}, outfile, indent=4)
        else:
            current_temperature_instance.get_current_weather_default(user)

    # Sample call: service.format_url_with_coordinates(-73.59, 45.51, "metric")
    def format_url_with_coordinates(self, longtitude, latitude, unit_format, user):
        base_url = self.load_config()
        current_temperature_instance = temperature.Temperature()
        current_temperature_instance.formatted_url = base_url + "&lon=" + \
            str(longtitude) + "&lat=" + str(latitude) + "&units=" + unit_format
        print("current user3: " + user)
        
        if user is None:
            # self.user_error()
            with open("temperatureError.json", "w") as outfile:
                message = self.user_error()
                json.dump({'error': message}, outfile, indent=4)
        else:
            current_temperature_instance.get_current_weather_default(user)

    # SystemCore --> ServiceAPI: Format temperature object for UI
    # OUTPUT: temperature.json
    def format_temperature_object(self, temperature_json, status_code):
        print(temperature_json)

        if status_code == 403:
            print(self.user_error())
            with open("temperatureError.json", "w") as outfile:
                json.dump({'error': self.user_error()}, outfile, indent=4)

        elif status_code == 200:
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

            json_obj = json.dumps(current_temperature_instance.__dict__)

            # -------- Store Temperature Object for UI ------ #
            # Write to json file
            with open("temperature.json", "w") as outfile:
                outfile.write(json_obj)

        else:
            with open("temperatureError.json", "w") as outfile:
                json.dump({'error': self.location_error()},
                          outfile, indent=4)

    def load_config(self):
        with open('weatherAPIConfig.json') as config_json_file:
            url_info = json.load(config_json_file)
            base_url = url_info['base_url']
            return base_url

    # Displaying error message in case response code not 200
    def location_error(self):
        return 'An error occured, the location could not be found!'

    def user_error(self):
        return 'An error occured, this is an invalid user.'
