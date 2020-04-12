import requests


class Temperature:

    # Default: Montreal and Metric: Celsius
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
        self.base_url = "https://api.openweathermap.org/data/2.5/weather?appid=77dde4d032c4ec1284a674d90b1351e3"
        self.formatted_url = ""

    def get_current_weather_default(self):
        self.formatted_url = self.base_url + "&q=" + self.current_city + "&units="+ self.units
        print("formatted_url: " + self.formatted_url)
        response = requests.get(self.formatted_url)
        print("City Version:")
        print("City Version - JSON Response")
        print(response.json())
        print("")
        response_json = response.json()


t = Temperature()
t.get_current_weather_default()
