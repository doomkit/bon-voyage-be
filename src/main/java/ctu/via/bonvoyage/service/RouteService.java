package ctu.via.bonvoyage.service;

import com.github.dozermapper.core.Mapper;
import ctu.via.bonvoyage.interfaces.entity.RouteEntity;
import ctu.via.bonvoyage.interfaces.repository.RouteRepository;
import ctu.via.bonvoyage.interfaces.request.TripRequest;
import ctu.via.bonvoyage.interfaces.response.api.RouteApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Service
public class RouteService {

    private final RouteRepository routeRepository;
    private final ApiCommunicationService apiCommunication;
    private final Mapper mapper;
    private static final Logger LOGGER = LoggerFactory.getLogger(RouteService.class);

    RouteService(@NotNull @Autowired RouteRepository routeRepository,
                 @NonNull @Autowired ApiCommunicationService apiCommunication,
                 @NonNull @Autowired Mapper mapper){
        this.routeRepository = routeRepository;
        this.apiCommunication = apiCommunication;
        this.mapper = mapper;
    }

    public RouteEntity getRoute(TripRequest tripRequest, BigInteger id){
        LOGGER.debug("getRoute {}", tripRequest);
        Assert.notNull(tripRequest, "tripRequest cannot be null!");
        RouteEntity result = null;
        RouteApiResponse routeApiResponse;

        try {
            CompletableFuture<RouteApiResponse> future = apiCommunication.callApiForRouteInfo(tripRequest.getOriginLat() + ","
                    + tripRequest.getOriginLng(), tripRequest.getDestinationLat() + ","
                    + tripRequest.getDestinationLng(), tripRequest.getTripType().getValue());
            routeApiResponse = future.get(30, TimeUnit.SECONDS);
        } catch (ExecutionException | InterruptedException | TimeoutException e){
            LOGGER.debug("getRoute {} {}", tripRequest, e);
            return result;
        }

        if ("OK".equals(routeApiResponse.getStatus())){
            routeApiResponse.setOriginTitle(tripRequest.getOrigin());
            result = mapper.map(routeApiResponse, RouteEntity.class);
            if (id != null){
                result.setId(id);
            }
            routeRepository.save(result);
        }

        return result;
    }

    public void deleteRoute(RouteEntity routeEntity){
        LOGGER.debug("deleteRoute {}", routeEntity);

        routeRepository.delete(routeEntity);
    }

}
