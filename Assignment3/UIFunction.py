import temperatureServiceAPI as service
import temperatureSystemCore as temperature

current_service_instance = service.ServiceAPI()
current_service_instance.format_url_default("london")

# ---- UI calls ---- #
# OUTPUT: JSON --> modified_attributes.json (With corresponding modified data)
# ------------------ #

#current_service_instance.format_temperature_to_celsius("temperature.json")
#current_service_instance.format_temperature_to_fahrenheit("temperature.json")
#current_service_instance.format_temperature_to_celsius("modified_attributes.json")
#current_service_instance.format_time_iso("temperature.json")
