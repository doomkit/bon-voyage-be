package ctu.via.bonvoyage.service;

import com.github.dozermapper.core.Mapper;
import ctu.via.bonvoyage.interfaces.entity.PlaceEntity;
import ctu.via.bonvoyage.interfaces.entity.RouteEntity;
import ctu.via.bonvoyage.interfaces.entity.TripEntity;
import ctu.via.bonvoyage.interfaces.entity.UserEntity;
import ctu.via.bonvoyage.interfaces.enums.TripTypeEnum;
import ctu.via.bonvoyage.interfaces.error.BadRequestException;
import ctu.via.bonvoyage.interfaces.repository.TripRepository;
import ctu.via.bonvoyage.interfaces.repository.UserRepository;
import ctu.via.bonvoyage.interfaces.request.TripRequest;
import ctu.via.bonvoyage.interfaces.response.TripResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TripService {

    private final UserRepository userRepository;
    private final TripRepository tripRepository;
    private final PlaceService placeService;
    private final RouteService routeService;
    private final Mapper mapper;
    private static final Logger LOGGER = LoggerFactory.getLogger(TripService.class);

    TripService(@NotNull @Autowired UserRepository userRepository,
                @NotNull @Autowired TripRepository tripRepository,
                @NotNull @Autowired PlaceService placeService,
                @NotNull @Autowired RouteService routeService,
                @NonNull @Autowired Mapper mapper){
        this.userRepository = userRepository;
        this.tripRepository = tripRepository;
        this.placeService = placeService;
        this.routeService = routeService;
        this.mapper = mapper;
    }

    public TripResponse planTrip(TripRequest tripRequest){
        LOGGER.debug("planTrip {}", tripRequest);

        PlaceEntity destination = placeService.getInfoByPlaceName(tripRequest.getDestination(),
                tripRequest.getDestinationLan(), tripRequest.getDestinationLng());
        List<PlaceEntity> places = placeService.getInfoByCategory(tripRequest.getCategory().getCode(),
                tripRequest.getDestinationLan(), tripRequest.getDestinationLng());
        RouteEntity route = routeService.getRoute(tripRequest, null);
        UserEntity user = getUser();

        if (user != null && destination != null && route != null){
            TripEntity tripEntity = new TripEntity();
            tripEntity.setUser(user);
            tripEntity.setDestination(destination);
            tripEntity.setRoute(route);
            tripEntity.setPlaces(places);
            tripEntity.setTripType(tripRequest.getTripType().getValue());
            tripEntity.setCreatedAt(new Date());

            return mapper.map(tripRepository.save(tripEntity), TripResponse.class);
        }

        throw new BadRequestException("Not enough information!");
    }

    public TripResponse updateTrip(BigInteger id, TripTypeEnum tripType){
        LOGGER.debug("updateTrip {} {}", id, tripType);

        TripEntity tripEntity = checkPermissions(id);
        TripRequest tripRequest = new TripRequest();
        tripRequest.setOriginLan(tripEntity.getRoute().getOriginLan());
        tripRequest.setOriginLng(tripEntity.getRoute().getOriginLng());
        tripRequest.setDestinationLan(tripEntity.getDestination().getLat());
        tripRequest.setDestinationLng(tripEntity.getDestination().getLng());
        tripRequest.setTripType(tripType);

        RouteEntity routeResponse = routeService.getRoute(tripRequest, id);
        tripEntity.setRoute(routeResponse);

        return mapper.map(tripEntity, TripResponse.class);
    }

    public void deleteTrip(@NotNull BigInteger id){
        LOGGER.debug("deleteTrip {}", id);

        TripEntity tripEntity = checkPermissions(id);
        ArrayList<PlaceEntity> list = new ArrayList<>();
        list.add(tripEntity.getDestination());
        list.addAll(tripEntity.getPlaces());
        placeService.deletePlaces(list);
        routeService.deleteRoute(tripEntity.getRoute());
    }

    public TripResponse getTrip(@NotNull BigInteger id){
        LOGGER.debug("getTrip {}", id);

        UserEntity user = getUser();
        TripEntity tripEntity = tripRepository.findByTripIdAndUser(id, user);

        if (tripEntity != null){
            return mapper.map(tripEntity, TripResponse.class);
        }

        throw new BadRequestException("Trip not found!");
    }

    public List<TripResponse> getTrips(){
        LOGGER.debug("getTrips");

        List<TripResponse> result = new ArrayList<>();
        UserEntity user = getUser();
        List<TripEntity> tripEntities = tripRepository.findByUser(user);

        for (TripEntity tripEntity : tripEntities){
            result.add(mapper.map(tripEntity, TripResponse.class));
        }

        return result;
    }

    private UserEntity getUser(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<UserEntity> userEntities = userRepository.findByEmailIgnoreCaseAndValidIsTrue(user.getUsername());

        if (userEntities == null || userEntities.isEmpty()){
            throw new BadRequestException("User not found!");
        }

        return userEntities.get(0);
    }

    private TripEntity checkPermissions(BigInteger id){
        LOGGER.debug("checkPermissions {}", id);

        UserEntity user = getUser();
        TripEntity tripEntity = tripRepository.findByTripIdAndUser(id, user);

        if (tripEntity == null){
            throw new BadRequestException("Trip does not exist or user does not have permissions!");
        }

        return tripEntity;
    }

}
