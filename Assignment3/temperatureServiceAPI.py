class ServiceAPI:

    def __init__(self):
        self.temperature_object = ""

    def format_temperature_object(self,temperature_json):
        print(temperature_json)
        print("In temperatureServiceAPI")
        import temperatureSystemCore as temperature
        formatted_obj = temperature.Temperature()
        formatted_obj.longtitude = temperature_json['coord']['lon']
        formatted_obj.latitude = temperature_json['coord']['lat']

        current_weather = temperature_json['weather'][0]
        formatted_obj.weather_overview = current_weather['main']
        print("Current Temperature values:")
        print(formatted_obj.weather_overview)

    def set_object(self,temperature_object):
        print("Currently at setting object")
        print(temperature_object)
