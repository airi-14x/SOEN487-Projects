import json


def format_temperature_to_celsius(file_name):
    json_obj = load_file(file_name)
    current_units = json_obj['units']

    # imperial --> Fahrenheit
    # metric --> Celsius
    with open("modified_attributes.json", "w") as outfile:

        if current_units == 'imperial':
            current_temperature_celsius = round(
                fahrenheit_to_celsius(json_obj['current_temperature']), 1)
            current_feels_like_celsius = round(
                fahrenheit_to_celsius(json_obj['current_feels_like']), 1)
            current_min_celsius = round(
                fahrenheit_to_celsius(json_obj['current_min']), 1)
            current_max_celsius = round(
                fahrenheit_to_celsius(json_obj['current_max']), 1)

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


def format_temperature_to_fahrenheit(file_name):
    json_obj = load_file(file_name)
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
                celsius_to_fahrenheit(json_obj['current_temperature']), 1)
            current_feels_like_fahrenheit = round(
                celsius_to_fahrenheit(json_obj['current_feels_like']), 1)
            current_min_fahrenheit = round(
                celsius_to_fahrenheit(json_obj['current_min']), 1)
            current_max_fahrenheit = round(
                celsius_to_fahrenheit(json_obj['current_max']), 1)

            json.dump({'current_temperature': current_temperature_fahrenheit,
                       'current_feels_like': current_feels_like_fahrenheit,
                       'current_min': current_min_fahrenheit,
                       'current_max': current_max_fahrenheit,
                       'units': 'imperial'}, outfile, indent=4)


def format_time_iso(file_name):
    json_obj = load_file(file_name)

    current_time_unix = json_obj['current_time']

    from datetime import datetime
    with open("modified_attributes.json", "w") as outfile:
        json.dump({'current_time': str(datetime.fromtimestamp(current_time_unix)),
                   'iso': 'true'}, outfile, indent=4)


def load_file(file_name):
    with open(file_name, 'r') as file:
        json_data = file.read()

    json_obj = json.loads(json_data)
    return json_obj


def fahrenheit_to_celsius(value):
    value = (5.0 / 9.0) * (value - 32)
    return value


def celsius_to_fahrenheit(value):
    value = (9.0 / 5.0) * value + 32
    return value


#format_temperature_to_celsius("temperature.json")
#format_temperature_to_fahrenheit("temperature.json")
format_temperature_to_celsius("modified_attributes.json")
# format_time_iso("temperature.json")
