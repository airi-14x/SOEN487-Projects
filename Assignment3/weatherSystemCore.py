import requests
import weatherServiceAPI as service
import json


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
        self.formatted_url = ""
        self.response = 0

    def get_current_weather_default(self, user):
        username = ""
        with open('config.json') as json_file:
            credentials = json.load(json_file)
            for cred in credentials['users']:
                username = cred['username']

        current_service_instance = service.ServiceAPI()
        if user != username:
            print("403 - Forbidden")
            print(current_service_instance.user_error())
        else:
            self.response = requests.get(self.formatted_url)

            if self.response.status_code == 200:
                response_json = self.response.json()
                current_service_instance.format_temperature_object(
                    response_json)  # Send response to weatherServiceAPI

            else:
                print(self.response.raise_for_status())
