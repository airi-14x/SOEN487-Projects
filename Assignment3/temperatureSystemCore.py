import requests


class Temperature:

    def __init__(self):
        self.longtitude = -73.59
        self.latitude = 45.51
        self.weather_overview = ""
        self.weather_description = ""
        self.current_temperature = ""
        self.current_feels_like = ""
        self.current_min = ""
        self.current_max = ""
        self.current_time = ""
        self.current_city = "Montreal"
        self.units = "metric"
        #self.base_url = "https://api.openweathermap.org/data/2.5/weather?appid=77dde4d032c4ec1284a674d90b1351e3"
        self.formatted_url = ""
        self.response = ""

    def get_current_weather_default(self):
        #self.formatted_url = self.base_url + "&q=" + self.current_city + "&units="+ self.units
        #print("formatted_url: " + self.formatted_url)
        self.response = requests.get(self.formatted_url)

        if self.response.status_code == 200:
            # print(self.response.json())
            # print("")
            response_json = self.response.json()
            import temperatureServiceAPI as service
            current_service_instance = service.ServiceAPI()
            current_service_instance.format_temperature_object(
                response_json)  # Send response to temperatureServiceAPI

            # ------- To Move to UI -------------- #
            # ServiceAPI --> UI: Use the formatted temperature object
            # (!) Example of a call used by UI
            # (1) Need to call a "current_service_instance"
            # (2) Call appropriate function
            # ----------------------------------- #
            current_service_instance.format_time()

        else:
            print(self.response.raise_for_status())

#t = Temperature()
# t.get_current_weather_default()
