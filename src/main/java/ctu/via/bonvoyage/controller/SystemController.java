package ctu.via.bonvoyage.controller;

import ctu.via.bonvoyage.interfaces.source.RestSource;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "system")
class SystemController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SystemController.class);

    BuildProperties buildProperties;

    public SystemController(@Autowired BuildProperties buildProperties){
        this.buildProperties = buildProperties;
    }

    @ApiOperation(value = "Return info about application",
            notes = "Unsecured endpoint to indicate that BE in running")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful response", response = String.class),
            @ApiResponse(code = 500, message = "Internal server error", response = Exception.class)
    })
    @RequestMapping(value = RestSource.INFO, method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String getInfo() {
        LOGGER.debug("getInfo");

        return buildProperties.getName() + " is running " +
                "(version " + buildProperties.getVersion() + ", " + buildProperties.getTime() + ")";
    }

}