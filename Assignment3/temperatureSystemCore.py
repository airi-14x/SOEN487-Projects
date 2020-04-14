import requests
import temperatureServiceAPI as service

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
        self.response = ""

    def get_current_weather_default(self):
        self.response = requests.get(self.formatted_url)

        if self.response.status_code == 200:
            response_json = self.response.json()

            current_service_instance = service.ServiceAPI()
            current_service_instance.format_temperature_object(
                response_json)  # Send response to temperatureServiceAPI

            # ------- To Move to UI -------------- #
            # ServiceAPI --> UI: Use the formatted temperature object
            # (!) Example of a call used by UI
            # (1) Need to call a "current_service_instance"
            # (2) Call appropriate function
            # ----------------------------------- #
            #current_service_instance.format_time()

        else:
            print(self.response.raise_for_status())
