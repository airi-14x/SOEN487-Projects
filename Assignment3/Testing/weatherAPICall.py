import requests


# 77dde4d032c4ec1284a674d90b1351e3

# 1) City Name Version --> Montreal // Celsius #
# --- System Core --- #
response = requests.get("https://api.openweathermap.org/data/2.5/weather?q=montreal&units=metric&appid=77dde4d032c4ec1284a674d90b1351e3")
print("City Version:")
print("City Version - JSON Response")
print(response.json())
print("")
response_json = response.json()

# --- Get Required Parameters to set as Temperature Object --- #
longtitude = response_json['coord']['lon']
latitude = response_json['coord']['lat']

print("longtitude: " + str(longtitude))
print("latitude: " + str(latitude))

current_weather = response_json['weather'][0]
weather_main = current_weather['main']
weather_description = current_weather['description']

print("weather_overview: " + str(weather_main))
print("weather_description: " + str(weather_description))

temperature_main = response_json['main']
current_temperature = temperature_main['temp']
current_feels_like = temperature_main['feels_like']
current_min = temperature_main['temp_min']
current_max = temperature_main['temp_max']

print("temperature_main: " + str(temperature_main))
print("current_temperature: " + str(current_temperature))
print("current_feels_like: " + str(current_feels_like))
print("current_min: " + str(current_min))
print("current_max: " + str(current_max))

current_time = response_json['dt']

print("current_time: " + str(current_time))

from datetime import datetime
print("Current Time Zone [ISO 8601 Time Representation]")
print(datetime.fromtimestamp(current_time))
print("")


# 2) Geographic Coordinates Version #
response_coordinates_ver = requests.get("http://api.openweathermap.org/data/2.5/weather?lat=" + str(latitude) + "&lon=" + str(longtitude) + "&units=metric&appid=77dde4d032c4ec1284a674d90b1351e3")
print("Coordinate Version:")
print("Coordinate Version - JSON Response")
print(response_coordinates_ver.json())
print("")
response_json = response_coordinates_ver.json()

# --- Get Required Parameters to set as Temperature Object --- #
longtitude = response_json['coord']['lon']
latitude = response_json['coord']['lat']

print("longtitude2: " + str(longtitude))
print("latitude2: " + str(latitude))

current_weather = response_json['weather'][0]
weather_main = current_weather['main']
weather_description = current_weather['description']

print("weather_main2: " + str(weather_main))
print("weather_description2: " + str(weather_description))

temperature_main = response_json['main']
current_temperature = temperature_main['temp']
current_feels_like = temperature_main['feels_like']
current_min = temperature_main['temp_min']
current_max = temperature_main['temp_max']

print("temperature_main2: " + str(temperature_main))
print("current_temperature2: " + str(current_temperature))
print("current_feels_like2: " + str(current_feels_like))
print("current_min2: " + str(current_min))
print("current_max2: " + str(current_max))

current_time = response_json['dt']

print("current_time2: " + str(current_time))

from datetime import datetime
print("Current Time Zone [ISO 8601 Time Representation]2")
print(datetime.fromtimestamp(current_time))


# --- WebService API --- #
