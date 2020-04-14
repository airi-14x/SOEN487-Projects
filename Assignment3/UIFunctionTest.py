import temperatureServiceAPI as service
import temperatureSystemCore as temperature

current_service_instance = service.ServiceAPI()
current_service_instance.format_url_default("montreal")

print(current_service_instance.formatted_temperature_object)

#formatted_temperature_object = current_service_instance.format_url_default("montreal")

#print(current_service_instance.formatted_temperature_object.current_min)
#current_service_instance.format_url_with_parameters("london", "imperial")
#current_service_instance.format_time()
