package io.swagger.api;

import io.swagger.model.Error;
import io.swagger.model.UpdateUser;
import io.swagger.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.za.gaserve.business.UserBusinessService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-09-10T20:50:13.667Z")

@Controller
public class UsersApiController implements UsersApi {

    private static final Logger log = LoggerFactory.getLogger(UsersApiController.class);

    @Autowired
    private UserBusinessService userBusinessService;

    public ResponseEntity<Void> addUser(@ApiParam(value = "user object to be added"  )  @Valid @RequestBody User user) {
    	
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<User> getUserById(@ApiParam(value = "Id of the user being retrieved",required=true) @PathVariable("id") String id) {
            try {
                return new ResponseEntity<User>(HttpStatus.NOT_IMPLEMENTED);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }

    public ResponseEntity<List<User>> getUsers() {
            try {
                return new ResponseEntity<List<User>>(HttpStatus.NOT_IMPLEMENTED);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<User>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }

    public ResponseEntity<Void> updateUser(@ApiParam(value = "user id to be updated",required=true) @PathVariable("id") String id,@ApiParam(value = "user object to be added"  )  @Valid @RequestBody UpdateUser user) {
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

}
