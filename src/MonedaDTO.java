import java.util.Map;

public record MonedaDTO(String base_code, Map<String, Double> conversion_rates) {
}
