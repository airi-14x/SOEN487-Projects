class ServiceAPI:

    def __init__(self):
        self.temperature_object = ""

    def format_temperature_object(self,temperature_json):
        #print(temperature_json)
        print("In temperatureServiceAPI - format_temperature_object")

        import temperatureSystemCore as temperature
        formatted_obj = temperature.Temperature()
        formatted_obj.longtitude = temperature_json['coord']['lon']
        formatted_obj.latitude = temperature_json['coord']['lat']

        # Weather #
        current_weather = temperature_json['weather'][0]
        formatted_obj.weather_overview = current_weather['main']
        formatted_obj.weather_description = current_weather['description']

        # Temperature #
        temperature_main = temperature_json['main']
        formatted_obj.current_temperature = temperature_main['temp']
        formatted_obj.current_feels_like = temperature_main['feels_like']
        formatted_obj.current_min = temperature_main['temp_min']
        formatted_obj.current_max = temperature_main['temp_max']

        formatted_obj.current_time = temperature_json['dt']
        #print("current_time: " + str(formatted_obj.current_time))
        formatted_obj.current_city = temperature_json['name']
        self.temperature_object = formatted_obj

    def format_time(self):
        from datetime import datetime
        print("Current Time Zone [ISO 8601 Time Representation]")
        print(self.temperature_object.current_time)
        print(datetime.fromtimestamp(self.temperature_object.current_time))
        print("")
