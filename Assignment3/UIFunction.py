import temperatureServiceAPI as service
import temperatureSystemCore as temperature

current_service_instance = service.ServiceAPI()
<<<<<<< HEAD
current_service_instance.format_url_default("montreal")
=======
current_service_instance.format_url_default("london")

# ---- UI calls ---- #
# OUTPUT: JSON --> modified_attributes.json (With corresponding modified data)
# ------------------ #

#current_service_instance.format_temperature_to_celsius("temperature.json")
#current_service_instance.format_temperature_to_fahrenheit("temperature.json")
#current_service_instance.format_temperature_to_celsius("modified_attributes.json")
#current_service_instance.format_time_iso("temperature.json")
>>>>>>> 68b16f50cbde6498fee8867897d54b2426dde369
