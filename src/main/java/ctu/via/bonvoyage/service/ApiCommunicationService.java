package ctu.via.bonvoyage.service;

import ctu.via.bonvoyage.interfaces.response.api.PlaceApiResponse;
import ctu.via.bonvoyage.interfaces.response.api.RouteApiResponse;
import ctu.via.bonvoyage.interfaces.source.PropertySource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.concurrent.CompletableFuture;

@Service
class ApiCommunicationService {

    @Value(PropertySource.PLACES_API_DISCOVER_URL)
    private String placeApiUrlDiscover;
    @Value(PropertySource.PLACES_API_BROWSE_URL)
    private String placeApiUrlBrowse;
    @Value(PropertySource.PLACES_API_KEY)
    private String placeApiKey;
    @Value("${route.api.url}")
    private String routeApiUrl;
    @Value("${route.api.key}")
    private String routeApiKey;

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiCommunicationService.class);
    private final RestTemplate restTemplate;


    ApiCommunicationService(@NonNull @Autowired RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    CompletableFuture<PlaceApiResponse> callApiForPlaceInfoDiscover(@NonNull String placeName,
                                                                    String latCity, String lngCity, String country){
        LOGGER.debug("callApiForPlaceInfoDiscover {} {} {} {}", placeName, latCity, lngCity, country);
        Assert.notNull(placeName, "placeName cannot be null!");

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(placeApiUrlDiscover)
                .queryParams(prepareMap(placeApiKey, latCity, lngCity,country, null, null))
                .queryParam("q", "");

        ResponseEntity<PlaceApiResponse> response = restTemplate.exchange(
                builder.toUriString() + placeName, HttpMethod.GET, prepareHttpEntity(), PlaceApiResponse.class);

        return (!response.getStatusCode().is2xxSuccessful() || response.getBody() == null)
                ? null : CompletableFuture.completedFuture(response.getBody());
    }

    CompletableFuture<PlaceApiResponse> callApiForPlaceInfoBrowse(@NotNull String category,
                                                                  @NotNull String latCity, @NotNull String lngCity){
        LOGGER.debug("callApiForPlaceInfoBrowse {} {} {}", category, latCity, lngCity);
        Assert.notNull(category, "category cannot be null!");
        Assert.notNull(latCity, "latCity cannot be null!");
        Assert.notNull(lngCity, "lngCity cannot be null!");

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(placeApiUrlBrowse)
                .queryParams(prepareMap(placeApiKey, latCity, lngCity, null, 50, category));

        ResponseEntity<PlaceApiResponse> response = restTemplate.exchange(
                builder.toUriString(), HttpMethod.GET, prepareHttpEntity(), PlaceApiResponse.class);

        return (!response.getStatusCode().is2xxSuccessful() || response.getBody() == null)
                ? null : CompletableFuture.completedFuture(response.getBody());
    }

    CompletableFuture<RouteApiResponse> callApiForRouteInfo(@NonNull String origin,
                                                            @NotNull String destination,
                                                            @NotNull String tripType){
        LOGGER.debug("callApiForRouteInfo {} {} {}", origin, destination, tripType);
        Assert.notNull(origin, "origin cannot be null!");
        Assert.notNull(destination, "destination cannot be null!");
        Assert.notNull(tripType, "tripType cannot be null!");

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(routeApiUrl)
                .queryParam("key", routeApiKey)
                .queryParam("mode", tripType)
                .queryParam("language", "cs")
                .queryParam("origin", "");

        ResponseEntity<RouteApiResponse> response = restTemplate.exchange(
                builder.toUriString() + origin + "&destination=" + destination,
                HttpMethod.GET, prepareHttpEntity(), RouteApiResponse.class);

        return (!response.getStatusCode().is2xxSuccessful() || response.getBody() == null)
                ? null : CompletableFuture.completedFuture(response.getBody());
    }

    private MultiValueMap<String, String> prepareMap(String apiKey, String atLat, String atLng,
                                                     String in, Integer limit, String categories){
        MultiValueMap<String, String> result = new LinkedMultiValueMap<>();
        result.put("apiKey", Collections.singletonList(apiKey));
        result.put("at", Collections.singletonList(atLat.concat(",").concat(atLng)));

        if (in != null){
            result.put("in", Collections.singletonList("countryCode:".concat(in)));
        }
        if (limit != null){
            result.put("limit", Collections.singletonList("50"));
        }
        if (categories != null) {
            result.put("categories", Collections.singletonList(categories));
        }

        return result;
    }

    private HttpEntity<?> prepareHttpEntity(){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        return new HttpEntity<>(headers);
    }

}
