import weatherServiceAPI as service
import json

current_service_instance = service.ServiceAPI()
current_service_instance.format_url_default("montreal", None)

message = ""
with open('temperatureError.json') as error_file:
    error_data = json.load(error_file)
    message = error_data['error']
    print("Here's the error message:")
    print(message)
