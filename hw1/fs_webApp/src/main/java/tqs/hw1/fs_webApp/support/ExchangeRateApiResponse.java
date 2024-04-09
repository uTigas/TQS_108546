package tqs.hw1.fs_webApp.support;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExchangeRateApiResponse {
    @JsonProperty("result")
    private String result;

    @JsonProperty("documentation")
    private String documentation;

    @JsonProperty("terms_of_use")
    private String termsOfUse;

    @JsonProperty("time_last_update_unix")
    private long lastUpdateUnix;

    @JsonProperty("time_last_update_utc")
    private String lastUpdateUtc;

    @JsonProperty("time_next_update_unix")
    private long nextUpdateUnix;

    @JsonProperty("time_next_update_utc")
    private String nextUpdateUtc;

    @JsonProperty("base_code")
    private String baseCode;

    @JsonProperty("conversion_rates")
    private Map<String, Double> conversionRates;

    // Getters and setters
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getDocumentation() {
        return documentation;
    }

    public void setDocumentation(String documentation) {
        this.documentation = documentation;
    }

    public String getTermsOfUse() {
        return termsOfUse;
    }

    public void setTermsOfUse(String termsOfUse) {
        this.termsOfUse = termsOfUse;
    }

    public long getLastUpdateUnix() {
        return lastUpdateUnix;
    }

    public void setLastUpdateUnix(long lastUpdateUnix) {
        this.lastUpdateUnix = lastUpdateUnix;
    }

    public String getLastUpdateUtc() {
        return lastUpdateUtc;
    }

    public void setLastUpdateUtc(String lastUpdateUtc) {
        this.lastUpdateUtc = lastUpdateUtc;
    }

    public long getNextUpdateUnix() {
        return nextUpdateUnix;
    }

    public void setNextUpdateUnix(long nextUpdateUnix) {
        this.nextUpdateUnix = nextUpdateUnix;
    }

    public String getNextUpdateUtc() {
        return nextUpdateUtc;
    }

    public void setNextUpdateUtc(String nextUpdateUtc) {
        this.nextUpdateUtc = nextUpdateUtc;
    }

    public String getBaseCode() {
        return baseCode;
    }

    public void setBaseCode(String baseCode) {
        this.baseCode = baseCode;
    }

    public Map<String, Double> getConversionRates() {
        return conversionRates;
    }

    public void setConversionRates(Map<String, Double> conversionRates) {
        this.conversionRates = conversionRates;
    }
}
