package ctu.via.bonvoyage.service;

import com.github.dozermapper.core.Mapper;
import ctu.via.bonvoyage.interfaces.response.RouteResponse;
import ctu.via.bonvoyage.interfaces.response.api.RouteApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.validation.constraints.NotNull;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Service
public class RouteService {

    private final ApiCommunicationService apiCommunication;
    private final Mapper mapper;
    private static final Logger LOGGER = LoggerFactory.getLogger(RouteService.class);

    RouteService(@NonNull @Autowired ApiCommunicationService apiCommunication,
                 @NonNull @Autowired Mapper mapper){
        this.apiCommunication = apiCommunication;
        this.mapper = mapper;
    }

    public RouteResponse getRoute(@NotNull String sourceLan, @NotNull String sourceLng,
                                  @NotNull String destinationLan, @NotNull String destinationLng,
                                  @NotNull String tripType){
        LOGGER.debug("getRoute {} {} {} {} {}", sourceLan, sourceLng, destinationLan, destinationLng, tripType);
        Assert.notNull(sourceLan, "sourceLan cannot be null!");
        Assert.notNull(sourceLng, "sourceLng cannot be null!");
        Assert.notNull(destinationLan, "destinationLan cannot be null!");
        Assert.notNull(destinationLng, "destinationLng cannot be null!");
        Assert.notNull(tripType, "tripType cannot be null!");
        RouteResponse result = new RouteResponse();
        RouteApiResponse routeApiResponse;

        try {
            CompletableFuture<RouteApiResponse> future = apiCommunication
                    .callApiForRouteInfo(sourceLan + "," + sourceLng, destinationLan + "," + destinationLng, tripType);
            routeApiResponse = future.get(30, TimeUnit.SECONDS);
        } catch (ExecutionException | InterruptedException | TimeoutException e){
            LOGGER.debug("getRoute {} {} {} {} {} {}", sourceLan, sourceLng, destinationLan, destinationLng, tripType, e);
            return result;
        }

        if ("OK".equals(routeApiResponse.getStatus())){
            result = mapper.map(routeApiResponse, RouteResponse.class);
        }

        return result;

    }

//    public RouteResponse getRouteByHistory(@NotNull String email, @NotNull String coordinates, @NotNull String destination){
//        LOGGER.debug("getRoteByHistory {} {} {}", email, coordinates, destination);
//        TripHistoryEntity entity = tripHistoryService.getLastTripHistory(email);
//
//        if (email != null){
//            return getRoute(entity.getOriginPlace(), coordinates, entity.getTripMode(), email, destination);
//        }
//
//        return null;
//    }

}
