import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

public class ConsultaMoneda {

    public MonedaDTO buscaMoneda (String base_code){
        URI url = URI.create
                (
                "https://v6.exchangerate-api.com/v6/920776226d4e546f4c13c0a7/latest/" + base_code + "/"
                );
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest req = HttpRequest.newBuilder()
                .uri(url)
                .build();

        try {
            HttpResponse<String> res = client.send(req, HttpResponse.BodyHandlers.ofString());
            MonedaDTO fullMonedaDTO = new Gson().fromJson(res.body(), MonedaDTO.class);
            return filtraMonedas(fullMonedaDTO);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Valor de moneda no encontrado: " + e);
        }
    }

    private MonedaDTO filtraMonedas(MonedaDTO fullMonedaDTO) {
        Map<String, Double> filteredRates = new HashMap<>();
        Map<String, Double> conversionRates = fullMonedaDTO.conversion_rates();

        filteredRates.put("USD", conversionRates.get("USD"));
        filteredRates.put("ARS", conversionRates.get("ARS"));
        filteredRates.put("BRL", conversionRates.get("BRL"));
        filteredRates.put("COP", conversionRates.get("COP"));

        return new MonedaDTO(fullMonedaDTO.base_code(), filteredRates);

    }

}
