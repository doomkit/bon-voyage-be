package ctu.via.bonvoyage.service;

import ctu.via.bonvoyage.interfaces.response.api.PlaceApiResponse;
import ctu.via.bonvoyage.interfaces.source.PropertySource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.constraints.NotNull;
import java.util.concurrent.CompletableFuture;

@Service
class ApiCommunicationService {

    @Value(PropertySource.PLACES_API_DISCOVER_URL)
    private String placeApiUrlDiscover;
    @Value(PropertySource.PLACES_API_BROWSE_URL)
    private String placeApiUrlBrowse;
    @Value(PropertySource.PLACES_API_KEY)
    private String placeApiKey;

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiCommunicationService.class);
    private final RestTemplate restTemplate;


    ApiCommunicationService(@NonNull @Autowired RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    CompletableFuture<PlaceApiResponse> callApiForPlaceInfoDiscover(@NonNull String placeName,
                                                                    String city, String country){
        LOGGER.debug("callApiForPlaceInfoDiscover {} {} {}", placeName, city, country);
        Assert.notNull(placeName, "placeName cannot be null!");

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(placeApiUrlDiscover)
                .queryParam("apiKey", placeApiKey)
                //.queryParam("at", "50.073658,14.418540") // TODO
                .queryParam("in", "countryCode:" + country)
                .queryParam("q", "");

        ResponseEntity<PlaceApiResponse> response = restTemplate.exchange(
                builder.toUriString() + placeName, HttpMethod.GET, prepareHttpEntity(), PlaceApiResponse.class);

        return (!response.getStatusCode().is2xxSuccessful() || response.getBody() == null)
                ? null : CompletableFuture.completedFuture(response.getBody());
    }

    CompletableFuture<PlaceApiResponse> callApiForPlaceInfoBrowse(@NotNull String category,
                                                                  @NotNull String city){
        LOGGER.debug("callApiForPlaceInfoBrowse {} {}", category, city);
        Assert.notNull(category, "category cannot be null!");
        Assert.notNull(city, "city cannot be null!");

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(placeApiUrlBrowse)
                .queryParam("apiKey", placeApiKey)
                //.queryParam("at", coordinates) <- city // TODO
                .queryParam("limit", 50)
                .queryParam("categories", category);

        ResponseEntity<PlaceApiResponse> response = restTemplate.exchange(
                builder.toUriString(), HttpMethod.GET, prepareHttpEntity(), PlaceApiResponse.class);

        return (!response.getStatusCode().is2xxSuccessful() || response.getBody() == null)
                ? null : CompletableFuture.completedFuture(response.getBody());
    }

    private HttpEntity<?> prepareHttpEntity(){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        return new HttpEntity<>(headers);
    }

}
