package ctu.via.bonvoyage.service;

import com.github.dozermapper.core.Mapper;
import ctu.via.bonvoyage.interfaces.response.PlaceResponse;
import ctu.via.bonvoyage.interfaces.response.api.PlaceApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.HttpServerErrorException;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Service
public class PlaceService {

    private final ApiCommunicationService apiCommunication;
    private final Mapper mapper;
    private static final Logger LOGGER = LoggerFactory.getLogger(PlaceService.class);

    public PlaceService(@NotNull @Autowired ApiCommunicationService apiCommunication,
                        @NotNull @Autowired Mapper mapper) {
        this.apiCommunication = apiCommunication;
        this.mapper = mapper;
    }

    public List<PlaceResponse> getInfoByPlaceName(@NonNull String placeName, String city, String country){
        LOGGER.debug("getInfoByPlaceName {} {} {}", placeName, city, country);
        Assert.notNull(placeName, "placeName cannot be null!");

        List<PlaceResponse> result = new ArrayList<>();
        PlaceApiResponse placeApiResponse;

        try {
            CompletableFuture<PlaceApiResponse> completableFuture = apiCommunication
                    .callApiForPlaceInfoDiscover(placeName, city, country);
            placeApiResponse = completableFuture.get(30, TimeUnit.SECONDS);
        } catch (ExecutionException | InterruptedException | TimeoutException e){
            LOGGER.debug("getInfoByPlaceNameError {} {} {} {}", placeName, city, country, e);
            throw new HttpServerErrorException(HttpStatus.SERVICE_UNAVAILABLE, "HERE API call failed!");
        }

        if (placeApiResponse != null && placeApiResponse.getItems() != null){
            for (PlaceApiResponse.Item place : placeApiResponse.getItems()){
                result.add(mapper.map(place, PlaceResponse.class));
            }
        }

        return result;
    }

}
