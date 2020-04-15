import temperatureServiceAPI as service
import temperatureSystemCore as temperature

current_service_instance = service.ServiceAPI()
current_service_instance.format_url_default("montreal")
